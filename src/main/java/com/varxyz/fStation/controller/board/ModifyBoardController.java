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
	public int getMenuItemForModal(HttpServletRequest request) throws UnsupportedEncodingException{
		long bId = Long.parseLong(request.getParameter("bId"));
		String content = request.getParameter("content");
		System.out.println(bId);
		System.out.println(content);
		
		Post post = new Post();
		post.setBId(bId);
		post.setContent(content);
		int result = boardService.modifyPost(post);
		
	   return result;
	}
}
