package com.yida.boottracer.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yida.boottracer.domain.EntDictCategory;
import com.yida.boottracer.domain.EntDictCoder;
import com.yida.boottracer.domain.EntDictDealer;
import com.yida.boottracer.domain.SysMember;

@Repository
public interface EntDictCoderRepository extends JpaRepository<EntDictCoder, Integer>
{
	Optional<EntDictCoder> findBySysMemberAndCode(SysMember ent,String code);
	
	@Query(value = "FROM EntDictCoder o WHERE o.isDeleted<>true AND o.sysMember.id=?1 ORDER BY o.code")
	List<EntDictCoder> findByEntID(long entID);
}
