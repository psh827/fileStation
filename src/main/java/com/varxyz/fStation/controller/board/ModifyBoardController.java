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

import com.varxyz.fStation.domain.Post;
import com.varxyz.fStation.service.BoardServiceImpl;

@Controller
public class ModifyBoardController {
	
	@Autowired
	BoardServiceImpl boardService;
	
	@GetMapping("/board/modify_board")
	public String modifyBoardForm(Model model, HttpSession session, HttpServletRequest request) {
		long bId = (long) session.getAttribute("bId");

		Post post = boardService.viewPostByBid(bId);
		model.addAttribute("post", post);
		
		boardService.modifyPost(post);
		return "board/modify_board";
	}
	
}
