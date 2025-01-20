package com.kjw.community.controller.sign;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kjw.community.dto.common.ResponseDto;
import com.kjw.community.dto.sign.SignUpRequestDto;
import com.kjw.community.global.GlobalURL;
import com.kjw.community.service.sign.SignService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SignController {

	private final SignService signService;

	@GetMapping(GlobalURL.VIEW_SIGN)
	public ModelAndView getSignView() {
		return new ModelAndView("/sign/signup");
	}

	@PostMapping(GlobalURL.MEMBER_URL)
	@ResponseBody
	public ResponseEntity<ResponseDto<Void>> signup(@RequestBody SignUpRequestDto requestDto) {
		return signService.signup(requestDto);
	}

}
