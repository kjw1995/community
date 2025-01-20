package com.kjw.community.jpa.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "member")
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idx", nullable = false)
	private Long id;

	@NotNull
	@Column(name = "member_id")
	private String memberId;

	@Column(name = "nickname")
	private String nickname;

	@NotNull
	@Column(name = "password")
	private String password;

	@NotNull
	@Column(name = "phonenumber")
	private String phonenumber;

	@Column(name = "email")
	private String email;

	@NotNull
	@Column(name = "is_active")
	private String isActive;

	@NotNull
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "upated_at")
	private LocalDateTime upatedAt;

	@OneToMany(mappedBy = "id")
	private List<Role> roles;

}