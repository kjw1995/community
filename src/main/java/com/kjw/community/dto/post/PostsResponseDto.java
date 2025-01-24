package com.kjw.community.dto.post;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostsResponseDto {

	private String title;

	private Long memberIdx;

	private String memberId;

	private LocalDateTime createTime;

}
