package com.kjw.community.config.querydsl;

import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class BaseQueryDslRepository<T, ID> extends SimpleJpaRepository<T, ID> {

	protected JPAQueryFactory queryFactory;

	@PersistenceContext
	protected EntityManager em;

	public BaseQueryDslRepository(final EntityManager em, final JPAQueryFactory queryFactory,
		final Class<T> domainClass) {
		super(new JpaMetamodelEntityInformation<>(domainClass, em.getMetamodel(),
			em.getEntityManagerFactory().getPersistenceUnitUtil()), em);
		this.queryFactory = queryFactory;
		this.em = em;
	}

}
