package com.varxyz.fStation.controller.file;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.varxyz.fStation.domain.OurFile;
import com.varxyz.fStation.service.FileServiceImpl;

@Controller
@RequestMapping("/admin/admin")
public class JeeController {
	
	@Autowired
	FileServiceImpl fileService; 
	
	@GetMapping
	public String jeeForm(HttpServletRequest request, Model model) {
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
		
		return "admin/admin_main";
	}
	
}
