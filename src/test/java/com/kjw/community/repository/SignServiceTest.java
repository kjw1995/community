package com.kjw.community.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kjw.community.common.exception.MemberAlreadyExistsException;
import com.kjw.community.dto.sign.SignUpRequestDto;
import com.kjw.community.jpa.entity.Member;
import com.kjw.community.jpa.repository.member.MemberRepository;
import com.kjw.community.service.sign.SignServiceImpl;

@ExtendWith(MockitoExtension.class)
@DisplayName("[단위] 테스트 - 회원가입 서비스")
public class SignServiceTest {

	@Mock
	private MemberRepository memberRepository;

	@InjectMocks
	private SignServiceImpl signService;

	@Test
	@DisplayName("[단위] - 회원가입 실패(아이디 중복)")
	void testSignUpFailsForDuplicateMemberId() {

		// given
		String testId = "test02";
		SignUpRequestDto request = SignUpRequestDto.builder()
			.id(testId)
			.password("1234")
			.nickname("테스트02")
			.phoneNumber("01011112222")
			.email("test02@naver.com")
			.build();
		when(memberRepository.findByMemberId(testId)).thenReturn(Optional.of(
			Member.builder()
				.memberId(testId)
				.build())
		);

		// when & then
		assertThrows(MemberAlreadyExistsException.class, () -> {
			signService.signup(request);
		});
	}

}
