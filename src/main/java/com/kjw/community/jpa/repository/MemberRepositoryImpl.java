package com.kjw.community.jpa.repository;

import static com.kjw.community.jpa.entity.QMember.*;

import java.util.List;

import com.kjw.community.config.querydsl.BaseQueryDslRepository;
import com.kjw.community.jpa.entity.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

public class MemberRepositoryImpl extends BaseQueryDslRepository implements MemberRepositoryCustom {

	public MemberRepositoryImpl(EntityManager em,
		JPAQueryFactory queryFactory, Class domainClass) {
		super(em, queryFactory, domainClass);
	}

	@Override
	public List<Member> members() {
		return queryFactory.selectFrom(member).fetch();
	}
}
