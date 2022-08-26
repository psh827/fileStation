package com.varxyz.fStation.controller.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.varxyz.fStation.command.PostCommand;
import com.varxyz.fStation.domain.Post;
import com.varxyz.fStation.service.BoardServiceImpl;

/**
 * 게시판 컨트롤러
 * 필요한 컨트롤러는 맞는 패키지내에 생성해서 쓰기.
 * ex) 게시글을 수정한다면 controller.board패키지 내에 ModifyPostController 생성.
 * 
 * D조 코드 규칙 지키면서 하기!
 * 1. 띄어쓰기 준수 ex) long number=1; (x) long number = 1; (o)
 * 2. 변수, 메소드 앞글자는 무조건 소문자!
 * 3. 단락 사이사이 한줄 띄우기.
 * 4. 메소드 생성시 메소드 위에 간단하 설명주석 달기. /** <-사용
 * 5. 메소드 내에서는 한 줄 주석으로 간단히 처리
 * 
 * @author Administrator
 *
 */
@Controller
public class WriteboardController {
	
	@Autowired
	BoardServiceImpl boardService;

	/**
	 * 글작성 폼 화면
	 * @param postCommand
	 * @return
	 */
	@GetMapping("/board/write_board")
	public String writeBoardForm(@ModelAttribute("postCommand") PostCommand postCommand) {
		return "board/write_board";
	}
	
	/**
	 * 글작성 종료 후
	 * @param postCommand
	 * @param model
	 * @return
	 */
	@PostMapping("/board/write_board")
	public String writeBoard(PostCommand postCommand, Model model) {
		// 변수 선언
		String title = postCommand.getTitle();
		String nickName = postCommand.getNickName();
		String passwd = postCommand.getPasswd();
		String content = postCommand.getContent();
		
		// service에 넣을 객체 생성
		Post post = new Post();		
		post.setTitle(title);
		post.setNickname(nickName);
		post.setPasswd(passwd);
		post.setContent(content);
		
		// 서비스 호출
		int reuslt = boardService.writePost(post);
		// result값이 0이라면 등록 오류. 글작성 페이지로 보내기
		if(reuslt == 0) {
			model.addAttribute("msg", "등록 오류");
			model.addAttribute("url", "write_board");
			return "alert";
		}
		
		model.addAttribute("postCommand", postCommand);
		
		return "redirect:/board/boardmain";
	}
	
	
}
