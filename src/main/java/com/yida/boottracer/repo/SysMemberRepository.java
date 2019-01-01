package com.yida.boottracer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yida.boottracer.domain.SysMember;

@Repository
public interface SysMemberRepository extends JpaRepository<SysMember, Long>
{
	@Query("SELECT COUNT(d) FROM SysMember d WHERE d.dictMemberType.id=?1")
	int myCountBydictCompanyType(int typeId);

}
