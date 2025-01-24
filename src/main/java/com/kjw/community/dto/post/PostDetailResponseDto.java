package com.kjw.community.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDetailResponseDto {

	private String title;

	private String content;

}
