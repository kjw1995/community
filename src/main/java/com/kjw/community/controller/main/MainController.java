package com.kjw.community.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kjw.community.global.GlobalURL;

@Controller
public class MainController {

	@GetMapping(GlobalURL.VIEW_MAIN)
	public ModelAndView getMainView() {
		return new ModelAndView("/main/main");
	}

}
