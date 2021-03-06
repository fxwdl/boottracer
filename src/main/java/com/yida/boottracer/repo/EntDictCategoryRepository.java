package com.yida.boottracer.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yida.boottracer.domain.EntDictCategory;
import com.yida.boottracer.domain.SysMember;

@Repository
public interface EntDictCategoryRepository extends JpaRepository<EntDictCategory, Integer>
{
	Optional<EntDictCategory> findBySysMemberAndId(SysMember ent,Integer id);
	
	List<EntDictCategory> findBySysMemberAndCodeAndIsDeleted(SysMember ent,String code,boolean isDelete);
	
	List<EntDictCategory> findBySysMemberAndCodeAndIsDeletedAndIdNot(SysMember ent,String code,boolean isDelete,Integer id);
	
	@Query(value = "FROM EntDictCategory o WHERE parent IS null AND o.isDeleted<>true AND o.sysMember.id=?1 ORDER BY o.code")
	List<EntDictCategory> findFirstLevel(long entID);
}
