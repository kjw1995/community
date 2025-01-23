package com.kjw.community.dto.sign;

import lombok.Builder;

@Builder
public record SignUpRequestDto(String id, String password, String nickname, String phoneNumber, String email) {
}
