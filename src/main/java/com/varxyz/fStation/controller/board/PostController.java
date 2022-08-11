package com.varxyz.fStation.controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.varxyz.fStation.command.PostCommand;
import com.varxyz.fStation.domain.Post;
import com.varxyz.fStation.service.BoardServiceImpl;

@Controller
public class PostController {
	
	@Autowired
	BoardServiceImpl boardService;
	
	@PostMapping("/board/post")
	public String postForm(Model model, HttpServletRequest request, HttpSession session) {

		long bId = Long.parseLong(request.getParameter("bId"));
		String passwd = request.getParameter("passwd");
		
		System.out.println(bId);
		System.out.println(passwd);
		
		System.out.println("dao test = " + boardService.viewPostByPasswd(bId, passwd));
		Post post = boardService.viewPostByPasswd(bId, passwd);
		
		if (post == null) {
			model.addAttribute("msg", "비밀번호가 틀렸습니다.");
			model.addAttribute("url", "boardmain");
			return "alert";
		}

		
		model.addAttribute("post", post);
		return "board/post";
	}
	

}
