package com.kjw.community.jpa.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kjw.community.jpa.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
