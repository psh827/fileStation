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
	
	/**
	 * 게시글 수정 ajax통신
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/board/modify_board", method = { RequestMethod.POST })
	@ResponseBody
	public Post getMenuItemForModal(HttpServletRequest request) throws UnsupportedEncodingException{
		//필요한 변수받기
		long bId = Long.parseLong(request.getParameter("bId"));
		String content = request.getParameter("content");
		
		//service에 넘길 객체 생성 및 값 입력
		Post post = new Post();
		post.setBoardId(bId);
		post.setContent(content.replace("\n", "<br>"));
		
		//service 호출
		int result = boardService.modifyPost(post);
		
	   return post;
	}
	
	/**
	 * 관리자 댓글 달기 ajax통신
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/board/admin_comment", method = { RequestMethod.POST })
	@ResponseBody
	public Post adminComment(HttpServletRequest request) throws UnsupportedEncodingException{
		//필요한 변수받기
		long bId = Long.parseLong(request.getParameter("bId"));
		String content = request.getParameter("content");
		
		//service에 넘길 객체 생성 및 값 입력
		Post post = new Post();
		post.setBoardId(bId);
		post.setAdminContent(content.replace("\n", "<br>"));
		
		//service 호출
		int result = boardService.adminComment(post);
		
		//html에
	    return post;
	}
}
