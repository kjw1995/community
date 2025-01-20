package com.kjw.community.controller.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionController {

	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception e) {
		log.error("exception error!!", e);
		return new ModelAndView("/error/error");
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public ModelAndView handleException(UsernameNotFoundException e) {
		log.error("UsernameNotFound", e);
		return new ModelAndView("/error/error");
	}

}
