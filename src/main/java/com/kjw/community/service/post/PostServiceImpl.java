package com.kjw.community.service.post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.PageImpl;
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
import com.kjw.community.dto.session.SessionDto;
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
	public ResponseEntity<ResponseDto<PageImpl<PostsResponseDto>>> getPosts(int startNum) {

		List<Post> posts = postRepository.findAll();
		List<PostsResponseDto> responseDtoList = new ArrayList<>();

		for (Post post : posts) {
			PostsResponseDto responseDto = PostsResponseDto.builder()
				.postIdx(post.getId())
				.title(post.getTitle())
				.memberId(post.getMember().getMemberId())
				.memberIdx(post.getMemberIdx())
				.createTime(post.getCreatedAt())
				.build();
			responseDtoList.add(responseDto);
		}

		PageImpl<PostsResponseDto> response = new PageImpl<>(responseDtoList, PageRequest.of(startNum, 10),
			responseDtoList.size());

		return ResponseEntity.ok(ResponseDto.ofSuccess(response, "조회 성공"));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<ResponseDto<Void>> createPost(PostCreateRequestDto requestDto) throws Exception {

		SessionDto session = Optional.of(sessionUtil.getUserSession())
			.orElseThrow(() -> new BadRequestException("잘못된 요청입니다."));

		postRepository.save(
			Post.builder()
				.memberIdx(session.getMemberIdx())
				.title(requestDto.getTitle())
				.content(requestDto.getContent())
				.createdAt(LocalDateTime.now())
				.build());

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
