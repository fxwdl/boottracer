package com.yida.boottracer.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yida.boottracer.domain.DictCommon;
import com.yida.boottracer.domain.DictMemberType;
import com.yida.boottracer.domain.SysMember;
import com.yida.boottracer.domain.SysRole;
import com.yida.boottracer.domain.SysUser;
import com.yida.boottracer.enums.DictCommomType;

@Repository
public interface SysMemberRepository extends JpaRepository<SysMember, String>
{
	@Query("SELECT COUNT(d) FROM SysMember d WHERE d.dictMemberType.id=?1")
	int myCountBydictCompanyType(int typeId);
}
