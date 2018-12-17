package com.yida.boottracer.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yida.boottracer.domain.DictMemberType;

@Repository
public interface DictMemberTypeRepository extends JpaRepository<DictMemberType, Integer>
{
	List<DictMemberType> findByNameContaining(String name,Pageable pageable);
	
}
