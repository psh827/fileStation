package com.varxyz.fStation.controller.board;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.varxyz.fStation.domain.Post;
import com.varxyz.fStation.service.BoardServiceImpl;

@Controller
public class PostController {
	
	@Autowired
	BoardServiceImpl boardService;
	
	@GetMapping("/board/post")
	public String post(HttpServletRequest request, HttpSession session) {
		long bId = Long.parseLong(request.getParameter("boardId"));
		String admin = (String)session.getAttribute("admin");
		if(admin == null || admin == "") {
			request.setAttribute("msg", "잘못된 접근입니다.");
			request.setAttribute("url", "boardmain");
			return "alert";
		}
		Post post = boardService.viewPostByBid(bId);
		post.setContent(post.getContent().replace("\r\n", "<br>"));
		request.setAttribute("bId", bId);
		request.setAttribute("post", post);
//		session.invalidate();
		return "board/post";
	}
	
	@PostMapping("/board/post")
	public String postForm(Model model, HttpServletRequest request, HttpSession session) {
		long bId = Long.parseLong(request.getParameter("bId"));
		String passwd = request.getParameter("passwd");
		
		Post post = boardService.viewPostByPasswd(bId, passwd);
		
		if (post == null) {
			model.addAttribute("msg", "비밀번호가 틀렸습니다.");
			model.addAttribute("url", "boardmain");
			return "alert";
		}
		post.setContent(post.getContent().replace("\r\n", "<br>"));
		session.setAttribute("bId", bId);
		
		model.addAttribute("post", post);
		return "board/post";
	}
	

}
