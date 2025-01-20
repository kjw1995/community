package com.kjw.community.service.sign;

import org.springframework.http.ResponseEntity;

import com.kjw.community.dto.common.ResponseDto;
import com.kjw.community.dto.sign.SignUpRequestDto;

public interface SignService {

	ResponseEntity<ResponseDto<Void>> signup(SignUpRequestDto requestDto);

}
