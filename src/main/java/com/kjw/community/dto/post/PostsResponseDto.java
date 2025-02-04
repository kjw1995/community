package com.kjw.community.dto.post;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostsResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public Long postIdx;

	public String title;

	public Long memberIdx;

	public String memberId;

	public LocalDateTime createTime;

}
