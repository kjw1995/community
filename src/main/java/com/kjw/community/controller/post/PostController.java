package com.kjw.community.controller.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kjw.community.dto.common.ResponseDto;
import com.kjw.community.dto.post.PostCreateRequestDto;
import com.kjw.community.dto.post.PostsResponseDto;
import com.kjw.community.global.GlobalURL;
import com.kjw.community.service.post.PostService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	@GetMapping(GlobalURL.VIEW_POST)
	public ModelAndView getPostView() {
		return new ModelAndView("/post/post");
	}

	@GetMapping(GlobalURL.POST_URL + "/{startNum}")
	@ResponseBody
	public ResponseEntity<ResponseDto<Page<PostsResponseDto>> getPosts(@PathVariable("startNum") int startNum) {
		return postService.getPosts(startNum);
	}

	@GetMapping(GlobalURL.VIEW_POST_CREATE)
	public ModelAndView getPostCreateView() {
		return new ModelAndView("/post/post_create");
	}

	@GetMapping(GlobalURL.VIEW_POST_DETAIL + "/{postIdx}")
	public ModelAndView getPostDetailView(@PathVariable("postIdx") Long postIdx) {

		ModelAndView mav = new ModelAndView("/post/post_detail");
		mav.addObject("data", postService.getPost(postIdx));

		return mav;
	}

	@PostMapping(GlobalURL.POST_URI)
	@ResponseBody
	public ResponseEntity<ResponseDto<Void>> createPost(@RequestBody PostCreateRequestDto requestDto) throws Exception {
		return postService.createPost(requestDto);
	}

}
