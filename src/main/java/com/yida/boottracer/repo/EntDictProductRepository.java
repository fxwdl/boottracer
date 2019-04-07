package com.yida.boottracer.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.yida.boottracer.domain.EntDictCoder;
import com.yida.boottracer.domain.EntDictProduct;
import com.yida.boottracer.domain.SysMember;
import com.yida.boottracer.domain.test.Order;

@Repository
public interface EntDictProductRepository extends EntityGraphJpaRepository<EntDictProduct, Integer>
{
	List<EntDictProduct> findBySysMemberAndCodeAndIsDeletedAndIdNot(SysMember ent,String code,boolean isDelete,Integer id);

}
