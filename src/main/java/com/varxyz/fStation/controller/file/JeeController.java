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

import com.varxyz.fStation.domain.Post;
import com.varxyz.fStation.service.BoardServiceImpl;
import com.varxyz.fStation.service.FileServiceImpl;

@Controller
@RequestMapping
public class JeeController {
	
	@Autowired
	FileServiceImpl fileService; 
	
	@Autowired
	BoardServiceImpl boardService;
	
	@GetMapping("/admin/admin")
	public String jeeForm(HttpServletRequest request, HttpSession session) {
		//jee
		List<Integer> checkList = fileService.jee();
		List<String> checkStrList = new ArrayList<String>();
		checkStrList.add("IMG");
		checkStrList.add("VIDEO");
		checkStrList.add("DOCUMENT");
		checkStrList.add("ETC");
		checkStrList.add("TEXT");
		request.setAttribute("checkList", checkList);
		request.setAttribute("checkStrList", checkStrList);
		
		//jung
		List<String> monthList = new ArrayList<String>();
		List<String> sendList = new ArrayList<String>();
		
		for(int i = -4; i <= 0; i++) {
			Calendar mon = Calendar.getInstance();
			mon.add(Calendar.MONTH , i);
			String monthDate = new java.text.SimpleDateFormat("yyyy-MM-dd").format(mon.getTime());
			monthList.add(monthDate);
		}
		
		List<Integer> monthCount =  fileService.jung(monthList);
		for(String s : monthList) {
			s = s.split("-")[1];
			sendList.add(s);
		}
		
		request.setAttribute("monthList", sendList);
		request.setAttribute("monthCount", monthCount);
		
		//nam
		Date date = new Date();
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		long amount = fileService.getFileAmountByMonth(format.format(date));
		
		if (amount >= 1000) {
			amount = amount / 1000 ;
			if (amount >= 1000) {
				amount = amount / 1000;
			}
		}
		request.setAttribute("defaultMonth", format.format(date));
		request.setAttribute("amount", amount);
		
		//hoon
		List<Post> adminPost =  boardService.getAllPostToAdmin();
		session.setAttribute("admin", "admin");
		request.setAttribute("adminPost", adminPost);
		
		
		return "admin/admin_main";
	}
	
	@RequestMapping(value = "/admin/getMonth", method = { RequestMethod.POST })
	@ResponseBody
	public long getMenuItemForModal(HttpServletRequest request) throws UnsupportedEncodingException{
		String month = request.getParameter("month");
		Date date = new Date();
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-");
		
		long amount = fileService.getFileAmountByMonth(format.format(date) + month);
		if (amount >= 1000) {
			amount = amount / 1000 ;
			if (amount >= 1000) {
				amount = amount / 1000;
			}
		}
	   return amount;
	}
	
}
