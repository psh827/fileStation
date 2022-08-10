package com.varxyz.fStation.controller.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
}
