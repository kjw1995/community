package com.kjw.community.controller.board;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.kjw.community.global.GlobalURL;

@RestController
public class BoardController {

	@GetMapping(GlobalURL.VIEW_BOARD)
	public ModelAndView getBoardView() {
		return new ModelAndView("/board/board");
	}

}
