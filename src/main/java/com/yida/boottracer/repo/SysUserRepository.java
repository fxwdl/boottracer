package com.yida.boottracer.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yida.boottracer.domain.SysUser;

public interface SysUserRepository extends JpaRepository<SysUser, String>
{
	SysUser findByUserName(String userName);
}
