package com.varxyz.fStation.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.varxyz.fStation.command.PostCommand;
import com.varxyz.fStation.domain.Post;
import com.varxyz.fStation.service.BoardServiceImpl;

@Controller
public class PostDeleteController {
	
	@Autowired
	BoardServiceImpl boardService;

	@PostMapping("/board/delete")
	public String deleteForm(Model model,HttpServletRequest request, HttpSession session) throws Exception {
		
		long bId = (long) session.getAttribute("bId");
		System.out.println(bId);
		String radio = request.getParameter("delete");
		System.out.println(radio);
		Post post = new Post();
		int result = 0;
		post.setBId(bId);
		if(radio.equals("1")) {
			result = boardService.deletePost(post);			
		}
		
		if(result == 0 ) {
			model.addAttribute("msg", "삭제오류");
			model.addAttribute("url", "boardmain");
			return "alert";
		}
		

	    return "redirect:boardmain";
	}

}
		 

	