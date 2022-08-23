package com.varxyz.fStation.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

	
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
		
		if(!adminId.equals("admin") && !adminPasswd.equals("qwer123!")) {
			request.setAttribute("msg", "잘못된 접근입니다.");
			request.setAttribute("url", referer);
			return "alert";
		}
		
		session.setAttribute("admin", "admin");
		return "redirect:/admin/admin";
	}
}
