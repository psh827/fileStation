package com.varxyz.fStation.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.varxyz.fStation.domain.Post;
import com.varxyz.fStation.service.BoardServiceImpl;
import com.varxyz.fStation.service.FileServiceImpl;

@Controller
public class AdminController {
	
	@Autowired
	FileServiceImpl fileService; 
	
	@Autowired
	BoardServiceImpl boardService;
	
	@GetMapping("/admin/admin")
	public String jeeForm(HttpServletRequest request, HttpSession session) {
		//세션이 있다면 관리자 페이지로 들어갈 수 있음 (로그인 시 세션을 세팅)
		String admin = (String)session.getAttribute("admin");
		//세션에 대한 유효성 검사
		if(admin == null) {
			request.setAttribute("msg", "잘못된 접근입니다");
			request.setAttribute("url", "/fileStation/file/file_main");
			return "alert";
		}
		
		//jee
		//fileType별 요소들을 가져오기 위한 데이터 가져오기
		List<Integer> checkList = fileService.jee();
		List<String> checkStrList = new ArrayList<String>();
		//필요한 항목들에 대한 값 넣기
		checkStrList.add("IMG");
		checkStrList.add("VIDEO");
		checkStrList.add("DOCUMENT");
		checkStrList.add("ETC");
		checkStrList.add("TEXT");
		//값 넘기기
		request.setAttribute("checkList", checkList);
		request.setAttribute("checkStrList", checkStrList);
		
		//jung
		//월별 이용자 데이터를 가져오기 위한 변수 선언.
		List<String> monthList = new ArrayList<String>();
		List<String> sendList = new ArrayList<String>();
		
		//지금 달 부터 5달 전까지의 월 선언 및 Date값을 ex)2022-08-30으로 변환
		for(int i = -4; i <= 0; i++) {
			Calendar mon = Calendar.getInstance();
			mon.add(Calendar.MONTH , i);
			String monthDate = new java.text.SimpleDateFormat("yyyy-MM-dd").format(mon.getTime());
			monthList.add(monthDate);
		}
		
		//5달전 즉 8월이면 4,5,6,7,8 까지의 데이터를 가져오기
		List<Integer> monthCount =  fileService.jung(monthList);
		for(String s : monthList) {
			s = s.split("-")[1];
			sendList.add(s);
		}
		
		//값 넘기기
		request.setAttribute("monthList", sendList);
		request.setAttribute("monthCount", monthCount);
		
		//nam
		//현재 월에 이용된 파일크기의 합 가져오는 변수 선언.
		Date date = new Date();
		Calendar cl = Calendar.getInstance();
		//현재 시간 설정
		cl.setTime(date);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		long amount = fileService.getFileAmountByMonth(format.format(date));
		
		//값 넘기기
		request.setAttribute("defaultMonth", format.format(date));
		request.setAttribute("amount", amount);
		
		//hoon
		//관리자 게시판 기능
		List<Post> adminPost =  boardService.getAllPostToAdmin();
		request.setAttribute("adminPost", adminPost);
		
		
		return "admin/admin_main";
	}
	
	/**
	 * 월별 사용량 데이터 ajax통신으로 가져오기
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/admin/getMonth", method = { RequestMethod.POST })
	@ResponseBody
	public long getMenuItemForModal(HttpServletRequest request) throws UnsupportedEncodingException{
		//원하는 데이터 월에 대한 값 받기
		String month = request.getParameter("month");
		Date date = new Date();
		Calendar cl = Calendar.getInstance();
		//월 설정
		cl.setTime(date);
		
		//service에 넘길 값 주기 지금 년도로 넘기기
		SimpleDateFormat format = new SimpleDateFormat("yyyy-");
	
		//데이터 받기 (yyyy-MM)
		long amount = fileService.getFileAmountByMonth(format.format(date) + month);
		
	    return amount;
	}
	
	@GetMapping("/admin/logout")
	public String adminLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/file/file_main";
	}
	
	@PostMapping("/admin/adminLogin")
	public String adminLogin(HttpServletRequest request, HttpSession session) {
		String adminId = request.getParameter("adminId");
		String adminPasswd = request.getParameter("adminPasswd");
		String referer = (String)request.getHeader("REFERER");
		
		//관리자 아이디에 대한 유효성 검사
		if(!adminId.equals("admin") || !adminPasswd.equals("qwer123!")) {
			request.setAttribute("msg", "잘못된 접근입니다.");
			request.setAttribute("url", referer);
			return "alert";	
		}
		
		//로그인시 세션을 세팅
		session.setAttribute("admin", "admin");
		return "redirect:/admin/admin";
	}
}
