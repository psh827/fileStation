package com.varxyz.fStation.controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.varxyz.fStation.command.PostCommand;
import com.varxyz.fStation.domain.Post;
import com.varxyz.fStation.service.BoardServiceImpl;

@Controller
public class PostController {
	
	@Autowired
	BoardServiceImpl boardService;
	
	@GetMapping("/board/post")
	public String postForm(@ModelAttribute("postcommand") PostCommand postcommand,Model model, HttpServletRequest request) {

		model.addAttribute("postcommand", postcommand);	
		System.out.println(postcommand);
//		Post post = boardService.viewPostByPasswd(postcommand.getPasswd());
		Post post = boardService.viewPostByPasswd("1111");
		System.out.println(post.getNickname());
		request.setAttribute("post", post);
		return "board/post";
	}
	

}
