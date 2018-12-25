package com.yida.boottracer.repo;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.yida.boottracer.domain.DictRegion;

@Repository
public interface DictRegionRepository extends EntityGraphJpaRepository<DictRegion, Integer>
{
	public List<DictRegion> findByParent(DictRegion parent,Sort order);
}