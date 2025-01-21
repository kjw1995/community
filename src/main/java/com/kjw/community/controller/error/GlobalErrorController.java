package com.kjw.community.controller.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kjw.community.global.GlobalURL;

@Controller
public class GlobalErrorController {

	@GetMapping(GlobalURL.VIEW_ERROR)
	public ModelAndView getErrorView() {
		return new ModelAndView("/error/error");
	}

}
