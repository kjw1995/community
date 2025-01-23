package com.kjw.community.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjw.community.common.message.ResponseMessage;
import com.kjw.community.dto.sign.SignUpRequestDto;
import com.kjw.community.global.GlobalURL;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Transactional
@DisplayName("[통합] 테스트 - 회원가입")
public class SignControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@DisplayName("회원가입 성공 테스트")
	void testSign() throws Exception {

		// given
		SignUpRequestDto request = SignUpRequestDto.builder()
			.id("test03")
			.nickname("테스트03")
			.password("1234")
			.phoneNumber("01011112222")
			.email("test03@naver.com")
			.build();

		// when
		ResultActions actions = mockMvc.perform(post(GlobalURL.MEMBER_URL).contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(request))
			.accept(MediaType.APPLICATION_JSON));

		// then
		actions.andExpect(status().isCreated())
			.andExpect(jsonPath("$.status").value("SUCCESS"))
			.andExpect(jsonPath("$.data").doesNotExist())
			.andExpect(jsonPath("$.msg").value(ResponseMessage.SIGN_COMPLETE.getValue()))
			.andDo(print())
			.andDo(document("signUp",
				requestFields(
					fieldWithPath("id").type(JsonFieldType.STRING).description("아이디"),
					fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호"),
					fieldWithPath("nickname").type(JsonFieldType.STRING).description("닉네임"),
					fieldWithPath("phoneNumber").type(JsonFieldType.STRING).description("전화번호"),
					fieldWithPath("email").type(JsonFieldType.STRING).description("이메일")
				),
				responseFields(
					fieldWithPath("status").type(JsonFieldType.STRING).description("응답 상태"),
					fieldWithPath("data").type(JsonFieldType.NULL).description("응답 데이터").optional(),
					fieldWithPath("msg").type(JsonFieldType.STRING).description("응답 메시지")
				)
			));

	}
}
