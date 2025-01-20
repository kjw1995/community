package com.kjw.community.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kjw.community.dto.common.ResponseDto;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalRestExceptionController {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseDto<Void>> handleRestException(Exception e) {
		log.error("restException", e);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseDto.ofSuccessWithMsg("문제가 발생했습니다."));
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ResponseDto<Void>> handleRestUserNameNotFoundException(UsernameNotFoundException e) {
		log.error("restUserNameNotFoundException", e);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseDto.ofSuccessWithMsg("문제가 발생했습니다."));
	}

}
