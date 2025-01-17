package com.kjw.community.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kjw.community.global.GlobalURL;

@Controller
public class LoginController {

	@GetMapping(GlobalURL.VIEW_LOGIN)
	public ModelAndView getLoginView() {
		return new ModelAndView("/login/login");
	}

}
