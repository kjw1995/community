package com.kjw.community.dto.common;

import java.io.Serializable;

public record PageRequestDto(int startNum, int endNum) implements Serializable {

	private static final long serialVersionUID = 1L;

}
