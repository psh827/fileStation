package com.varxyz.fStation.controller.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.varxyz.fStation.command.PostCommand;
import com.varxyz.fStation.service.BoardServiceImpl;

@Controller
public class PostController {
	
	@Autowired
	BoardServiceImpl boardService;
	
	@GetMapping
	public String postForm(@ModelAttribute("postcommand") PostCommand postcommand) {
		
		return "board/post";
	}
	

}
