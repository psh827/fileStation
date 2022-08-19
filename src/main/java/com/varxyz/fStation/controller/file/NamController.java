package com.varxyz.fStation.controller.file;

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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.varxyz.fStation.command.MonthCommand;
import com.varxyz.fStation.domain.Post;
import com.varxyz.fStation.service.FileServiceImpl;

/**
 * 게시판 컨트롤러
 * 필요한 컨트롤러는 맞는 패키지내에 생성해서 쓰기.
 * ex) 게시글을 수정한다면 controller.board패키지 내에 ModifyPostController 생성.
 * 
 * D조 코드 규칙 지키면서 하기!
 * 1. 띄어쓰기 준수 ex) long number=1; (x) long number = 1; (o)
 * 2. 변수, 메소드 앞글자는 무조건 소문자!
 * 3. 단락 사이사이 한줄 띄우기.
 * 4. 메소드 생성시 메소드 위에 간단하 설명주석 달기. /** <-사용
 * 5. 메소드 내에서는 한 줄 주석으로 간단히 처리
 * 
 * @author Administrator
 *
 */
@Controller
public class NamController {
	
	@Autowired
	FileServiceImpl fileService;
	
	/**
	 * 월별 업로드된 파일크기
	 * @param request
	 * @param model
	 * @param month
	 * @return
	 */
	@GetMapping("/file/nam")
	public String FileGraph(HttpServletRequest request, Model model) {
		Date date = new Date();
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		
		List<String> sendList = new ArrayList<String>();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
		long amount = fileService.getFileAmountByMonth(format.format(date));
		
		if (amount >= 1000) {
			amount = amount / 1000 ;
			if (amount >= 1000) {
				amount = amount / 1000;
			}
		}
		System.out.println(amount);
		model.addAttribute("defaultMonth", format.format(date));
		model.addAttribute("amount", amount);
		return "file/file_main";
	}
//	@RequestMapping(value = "/admin/admin", method = { RequestMethod.POST })
//	@ResponseBody
//	public long getMenuItemForModal(HttpServletRequest request) throws UnsupportedEncodingException{
//		String month = request.getParameter("month");
//		Date date = new Date();
//		Calendar cl = Calendar.getInstance();
//		cl.setTime(date);
//		
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-");
//		
//		long amount = fileService.getFileAmountByMonth(format.format(date) + month);
//		if (amount >= 1000) {
//			amount = amount / 1000 ;
//			if (amount >= 1000) {
//				amount = amount / 1000;
//			}
//		}
//	   return amount;
//	}
	
}
