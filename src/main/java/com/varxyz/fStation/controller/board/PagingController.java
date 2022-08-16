package com.varxyz.fStation.controller.board;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.varxyz.fStation.service.BoardServiceImpl;


@Controller
public class PagingController {
	
	@Autowired
	BoardServiceImpl boardService;
}
