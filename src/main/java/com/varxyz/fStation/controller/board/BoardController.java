package com.varxyz.fStation.controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
public class BoardController {
	
	@Autowired
	BoardServiceImpl boardService;
	
	/**
	 * 게시판 메인 페이지
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/board/boardmain")
	public String boardForm(HttpServletRequest request, Model model, HttpSession session,
			@RequestParam(required = false, defaultValue = "") String field,
			@RequestParam(required = false, defaultValue = "") String nickname) {
		
		session.invalidate();
		/**
		 * 페이징
		 */
		int page = 0;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));			
		}
		
		Pageable pageable = PageRequest.of(page, 8, Sort.Direction.ASC, "regDate");
		Page<Post> ulist = boardService.findAll(pageable);
		
		
		if(field.equals("nickname")) { //닉네임으로 검색하기
			ulist = boardService.getPostByNickName(nickname, pageable);
		}
		
		int pageNumber = ulist.getPageable().getPageNumber(); //현재페이지
		int totalPages = ulist.getTotalPages(); //총 페이지 수. 검색에따라 10개면 10개..
		System.out.println("totalPages=" + totalPages);
		int pageBlock = 5; //블럭의 수 1, 2, 3, 4, 5	
		int startBlockPage = ((pageNumber)/pageBlock)*pageBlock+1; //현재 페이지가 7이라면 1*5+1=6
		int endBlockPage = startBlockPage+pageBlock-1; //6+5-1=10. 6,7,8,9,10해서 10.
		endBlockPage= totalPages<endBlockPage? totalPages:endBlockPage;
		
		
		model.addAttribute("startBlockPage", startBlockPage);
		model.addAttribute("endBlockPage", endBlockPage);
		model.addAttribute("ulist", ulist);
		
		return "board/boardmain";
	}
	
}
