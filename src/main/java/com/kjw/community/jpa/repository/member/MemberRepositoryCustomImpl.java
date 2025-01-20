package com.kjw.community.jpa.repository.member;

import static com.kjw.community.jpa.entity.QMember.*;

import java.util.List;

import com.kjw.community.config.querydsl.BaseQueryDslRepository;
import com.kjw.community.jpa.entity.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

public class MemberRepositoryCustomImpl extends BaseQueryDslRepository implements MemberRepositoryCustom {

	public MemberRepositoryCustomImpl(EntityManager em, JPAQueryFactory queryFactory) {
		super(em, queryFactory, Member.class);
	}

	@Override
	public List<Member> members() {
		return queryFactory.selectFrom(member).fetch();
	}
}
