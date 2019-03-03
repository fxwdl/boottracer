package com.yida.boottracer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.yida.boottracer.domain.BizCodeApply;

@Repository
public interface BizCodeApplyRepository extends EntityGraphJpaRepository<BizCodeApply, Integer>
{
	
}
