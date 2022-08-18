package com.varxyz.fStation.controller.file;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.varxyz.fStation.service.FileServiceImpl;

@Controller
public class JungController {
	
	@Autowired
	FileServiceImpl fileService;
	
	@GetMapping("/file/jung")
	public String jung(HttpServletRequest request) {
		
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
		
		
		return "file/jung";
	}
}
