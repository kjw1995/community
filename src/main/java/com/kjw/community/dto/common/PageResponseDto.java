package com.kjw.community.dto.common;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PageResponseDto<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	public int totalSize;

	public int startNum;

	public int endNum;

	public T content;

}
