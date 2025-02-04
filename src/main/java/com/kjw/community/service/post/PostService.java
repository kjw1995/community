package com.kjw.community.service.post;

import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;

import com.kjw.community.dto.common.ResponseDto;
import com.kjw.community.dto.post.PostCreateRequestDto;
import com.kjw.community.dto.post.PostDetailResponseDto;
import com.kjw.community.dto.post.PostsResponseDto;

public interface PostService {

	ResponseEntity<ResponseDto<PageImpl<PostsResponseDto>>> getPosts(int startNum);

	ResponseEntity<ResponseDto<Void>> createPost(PostCreateRequestDto requestDto) throws Exception;

	ResponseEntity<ResponseDto<PostDetailResponseDto>> getPost(Long postIdx);

}
