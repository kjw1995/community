package com.kjw.community.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.kjw.community.common.exception.MemberAlreadyExistsException;
import com.kjw.community.common.exception.ResourceNotFound;
import com.kjw.community.dto.common.ResponseDto;
import com.kjw.community.util.ServletUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionController {

	@ExceptionHandler(Exception.class)
	public Object handleException(Exception e, HttpServletRequest request) {
		log.error("exception error!!", e);
		if (ServletUtil.isAjaxRequest(request)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseDto.ofErrorWithMsg("문제가 발생했습니다."));
		}

		return new ModelAndView("/error/error");
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public Object handleUsernameNotFoundException(UsernameNotFoundException e, HttpServletRequest request) {
		log.error("UsernameNotFound", e);
		if (ServletUtil.isAjaxRequest(request)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseDto.ofErrorWithMsg(e.getMessage()));
		}
		return new ModelAndView("/error/error");
	}

	@ExceptionHandler(MemberAlreadyExistsException.class)
	public Object handleMemberAlreadyExistsException(MemberAlreadyExistsException e, HttpServletRequest request) {
		log.error("MemberAlreadyExistsException", e);
		if (ServletUtil.isAjaxRequest(request)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseDto.ofErrorWithMsg(e.getMessage()));
		}
		return new ModelAndView("/error/error");
	}

	@ExceptionHandler(ResourceNotFound.class)
	public Object handleResourceNotFoundException(ResourceNotFound e, HttpServletRequest request) {
		log.error("ResourceNotFoundException", e);
		if (ServletUtil.isAjaxRequest(request)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseDto.ofErrorWithMsg(e.getMessage()));
		}
		return new ModelAndView("/error/error");
	}

}
