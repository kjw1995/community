package com.kjw.community.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kjw.community.global.GlobalURL;
import com.kjw.community.util.SessionUtil;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {

	private final SessionUtil sessionUtil;

	@GetMapping(GlobalURL.VIEW_MAIN)
	public ModelAndView getMainView() {

		ModelAndView mav = new ModelAndView("/main/main");
		mav.addObject("SESSION_USER", sessionUtil.getUserSession());

		return mav;
	}

}
