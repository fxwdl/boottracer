package com.yida.boottracer.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yida.boottracer.domain.SysRole;
import com.yida.boottracer.domain.SysUser;

public interface SysRoleRepository extends JpaRepository<SysRole, String>
{
	SysUser findByName(String name);
}
