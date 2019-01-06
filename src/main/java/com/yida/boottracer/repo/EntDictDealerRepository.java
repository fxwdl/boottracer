package com.yida.boottracer.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yida.boottracer.domain.EntDictCategory;
import com.yida.boottracer.domain.EntDictDealer;
import com.yida.boottracer.domain.EntDictSupplier;
import com.yida.boottracer.domain.SysMember;

@Repository
public interface EntDictDealerRepository extends JpaRepository<EntDictDealer, Integer>
{
	Optional<EntDictDealer> findBySysMemberAndId(SysMember ent,Integer id);
	
	List<EntDictDealer> findBySysMemberAndCodeAndIsDeleted(SysMember ent,String code,boolean isDelete);
	
	List<EntDictDealer> findBySysMemberAndCodeAndIsDeletedAndIdNot(SysMember ent,String code,boolean isDelete,Integer id);
}
