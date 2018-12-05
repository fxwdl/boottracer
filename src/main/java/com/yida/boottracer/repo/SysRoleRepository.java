package com.yida.boottracer.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yida.boottracer.domain.SysRole;
import com.yida.boottracer.domain.SysUser;

public interface SysRoleRepository extends JpaRepository<SysRole, String>
{
	SysUser findByName(String name);
	
}
