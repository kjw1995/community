package com.kjw.community.service.post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kjw.community.common.exception.ResourceNotFound;
import com.kjw.community.dto.common.ResponseDto;
import com.kjw.community.dto.post.PostCreateRequestDto;
import com.kjw.community.dto.post.PostDetailResponseDto;
import com.kjw.community.dto.post.PostsResponseDto;
import com.kjw.community.jpa.entity.Post;
import com.kjw.community.jpa.repository.post.PostRepository;
import com.kjw.community.util.SessionUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

	private final SessionUtil sessionUtil;

	private final PostRepository postRepository;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ResponseDto<List<PostsResponseDto>>> getPosts(int startNum) {

		Page<Post> posts = postRepository.findAll(PageRequest.of(startNum, 10));
		List<PostsResponseDto> responseDtoList = new ArrayList<>();

		for (Post post : posts) {
			PostsResponseDto responseDto = PostsResponseDto.builder()
				.title(post.getTitle())
				.memberId(post.getMember().getMemberId())
				.memberIdx(post.getMemberIdx())
				.createTime(post.getCreatedAt())
				.build();
			responseDtoList.add(responseDto);
		}

		return ResponseEntity.ok(ResponseDto.ofSuccess(responseDtoList, "조회 성공"));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<ResponseDto<Void>> createPost(PostCreateRequestDto requestDto) {

		postRepository.save(
			Post.builder()
				.memberIdx(sessionUtil.getUserSession().getMemberIdx())
				.title(requestDto.getTitle())
				.content(requestDto.getContent())
				.createdAt(LocalDateTime.now())
				.build())
		;

		return ResponseEntity.status(HttpStatus.CREATED).body(ResponseDto.ofSuccessWithMsg("포스트 작성 성공"));
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ResponseDto<PostDetailResponseDto>> getPost(Long postIdx) {

		Post post = postRepository.findById(postIdx).orElseThrow(() -> new ResourceNotFound("해당 포스트를 찾을 수 없습니다."));

		PostDetailResponseDto response = PostDetailResponseDto.builder()
			.title(post.getTitle())
			.content(post.getContent())
			.build();

		return ResponseEntity.ok(ResponseDto.ofSuccess(response, "조회 성공"));

	}

}
