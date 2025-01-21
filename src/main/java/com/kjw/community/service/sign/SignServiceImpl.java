package com.kjw.community.service.sign;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kjw.community.common.exception.MemberAlreadyExistsException;
import com.kjw.community.common.member.MemberActive;
import com.kjw.community.common.member.MemberRole;
import com.kjw.community.dto.common.ResponseDto;
import com.kjw.community.dto.sign.SignUpRequestDto;
import com.kjw.community.jpa.entity.Member;
import com.kjw.community.jpa.entity.Role;
import com.kjw.community.jpa.repository.member.MemberRepository;
import com.kjw.community.jpa.repository.role.RoleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SignServiceImpl implements SignService {

	private final MemberRepository memberRepository;

	private final RoleRepository roleRepository;

	private final PasswordEncoder bcryptoPasswordEncoder;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<ResponseDto<Void>> signup(SignUpRequestDto requestDto) {

		Optional<Member> memberOpt = memberRepository.findByMemberId(requestDto.id());

		memberOpt.ifPresentOrElse(Member -> {
			log.error("이미 존재하는 회원, 가입 시도한 아이디 = {}", requestDto.id());
			throw new MemberAlreadyExistsException("이미 존재하는 회원입니다.");
		}, () -> {
			try {

				Member member = memberRepository.save(
					Member.builder()
						.memberId(requestDto.id())
						.password(bcryptoPasswordEncoder.encode(requestDto.password()))
						.phonenumber(requestDto.phoneNumber())
						.email(requestDto.email())
						.isActive(MemberActive.ACTIVE.getStatus())
						.createdAt(LocalDateTime.now())
						.build()
				);

				roleRepository.save(
					Role.builder()
						.type(MemberRole.NORMAL.getValue())
						.memberIdx(member.getId())
						.createdAt(LocalDateTime.now())
						.build()
				);

			} catch (Exception e) {
				throw new RuntimeException("회원가입 중 문제가 발생했습니다.");
			}

		});

		return ResponseEntity.status(HttpStatus.CREATED).body(ResponseDto.ofSuccessWithMsg("회원가입이 완료되었습니다."));

	}

}
