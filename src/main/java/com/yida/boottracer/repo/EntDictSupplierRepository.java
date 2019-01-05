package com.yida.boottracer.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yida.boottracer.domain.EntDictCategory;
import com.yida.boottracer.domain.EntDictSupplier;
import com.yida.boottracer.domain.SysMember;

@Repository
public interface EntDictSupplierRepository extends JpaRepository<EntDictSupplier, Integer>
{
	Optional<EntDictSupplier> findBySysMemberAndId(SysMember ent,Integer id);
	
	List<EntDictSupplier> findBySysMemberAndCodeAndIsDeleted(SysMember ent,String code,boolean isDelete);
	
	List<EntDictSupplier> findBySysMemberAndCodeAndIsDeletedAndIdNot(SysMember ent,String code,boolean isDelete,Integer id);
}
