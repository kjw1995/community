package com.kjw.community.service.post;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.kjw.community.dto.common.PageResponseDto;
import com.kjw.community.dto.common.ResponseDto;
import com.kjw.community.dto.post.PostCreateRequestDto;
import com.kjw.community.dto.post.PostDetailResponseDto;
import com.kjw.community.dto.post.PostsResponseDto;

public interface PostService {

	ResponseEntity<PageResponseDto<List<PostsResponseDto>>> getPosts(int startNum, int endNum);

	ResponseEntity<ResponseDto<Void>> createPost(PostCreateRequestDto requestDto) throws Exception;

	ResponseEntity<ResponseDto<PostDetailResponseDto>> getPost(Long postIdx);

}
