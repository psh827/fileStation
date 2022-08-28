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
		
		// 변수 선언
		long bId = Long.parseLong(request.getParameter("boardId"));
		String admin = (String)session.getAttribute("admin");
		
		// admin 세션이 없다면 메인으로 나가게 하기
		if(admin == null || admin == "") {
			request.setAttribute("msg", "잘못된 접근입니다.");
			request.setAttribute("url", "boardmain");
			return "alert";
		}
		
		// sevice호출 할 객체 생성 및 값 입력
		Post post = boardService.viewPostByBid(bId);
		// html에 표시될때 \r\n은 인식이 안돼서 <br>태그로 변경
		post.setContent(post.getContent().replace("\r\n", "<br>"));
		request.setAttribute("bId", bId);
		request.setAttribute("post", post);
		
		return "board/post";
	}
	
	@PostMapping("/board/post")
	public String postForm(Model model, HttpServletRequest request, HttpSession session) {
		
		// 변수 선언
		long bId = Long.parseLong(request.getParameter("bId"));
		String passwd = request.getParameter("passwd");
		
		// 원하는 값들을 service에 호출해서 가져오기
		Post post = boardService.viewPostByPasswd(bId, passwd);
		
		// 원하는 객체가 없다면 메인으로 나가게 하기
		if (post == null) {
			model.addAttribute("msg", "비밀번호가 틀렸습니다.");
			model.addAttribute("url", "boardmain");
			return "alert";
		}
		
		//객체가 있다면 html에 인식할 수 있게 태그로 변경
		post.setContent(post.getContent().replace("\r\n", "<br>"));
		session.setAttribute("bId", bId);
		
		model.addAttribute("post", post);
		
		return "board/post";
	}
	

}
