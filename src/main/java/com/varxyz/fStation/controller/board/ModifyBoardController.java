package com.varxyz.fStation.controller.board;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.varxyz.fStation.domain.Post;
import com.varxyz.fStation.service.BoardServiceImpl;

@Controller
public class ModifyBoardController {
	
	@Autowired
	BoardServiceImpl boardService;
	
	@RequestMapping(value = "/board/modify_board", method = { RequestMethod.POST })
	@ResponseBody
	public Post getMenuItemForModal(HttpServletRequest request) throws UnsupportedEncodingException{
		long bId = Long.parseLong(request.getParameter("bId"));
		String content = request.getParameter("content");
		
		Post post = new Post();
		post.setBoardId(bId);
		post.setContent(content.replace("\n", "<br>"));
		int result = boardService.modifyPost(post);
		
	   return post;
	}
	
	@RequestMapping(value = "/board/admin_comment", method = { RequestMethod.POST })
	@ResponseBody
	public Post adminComment(HttpServletRequest request) throws UnsupportedEncodingException{
		long bId = Long.parseLong(request.getParameter("bId"));
		String content = request.getParameter("content");
		
		Post post = new Post();
		post.setBoardId(bId);
		post.setAdminContent(content.replace("\n", "<br>"));
		int result = boardService.adminComment(post);
		
	   return post;
	}
}
