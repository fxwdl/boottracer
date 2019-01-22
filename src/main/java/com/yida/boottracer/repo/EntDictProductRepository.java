package com.yida.boottracer.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.yida.boottracer.domain.DictCommon;
import com.yida.boottracer.domain.EntDictDealer;
import com.yida.boottracer.domain.EntDictProduct;
import com.yida.boottracer.domain.SysMember;
import com.yida.boottracer.domain.SysRole;
import com.yida.boottracer.domain.SysUser;

@Repository
public interface EntDictProductRepository extends EntityGraphJpaRepository<EntDictProduct, Integer>
{
	List<EntDictProduct> findBySysMemberAndCodeAndIsDeletedAndIdNot(SysMember ent,String code,boolean isDelete,Integer id);
}
