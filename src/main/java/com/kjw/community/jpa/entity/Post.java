package com.kjw.community.jpa.entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "post")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idx", nullable = false)
	private Long id;

	@Column(name = "member_idx")
	private Long memberIdx;

	@Size(max = 255)
	@NotNull
	@Column(name = "title", nullable = false)
	private String title;

	@Lob
	@Column(name = "content")
	private String content;

	@NotNull
	@Column(name = "created_at", nullable = false)
	private Instant createdAt;

	@Column(name = "upated_at")
	private Instant upatedAt;

}