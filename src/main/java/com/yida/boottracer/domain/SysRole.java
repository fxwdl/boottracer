package com.yida.boottracer.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sys_role")
public class SysRole
{
	private String id;
	private String name;
	private String comment;
	private Set<SysUser> users = new HashSet<>();

	public SysRole()
	{
	}

	public SysRole(String id, String name, String comment)
	{
		this.id = id;
		this.name = name;
		this.comment = comment;
	}

	@Id
	@Column(name = "Id", unique = true, nullable = false, length = 64)
	public String getId()
	{
		return this.id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	@Column(name = "Name", nullable = false, length = 256)
	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Column(name = "Comment", nullable = true, length = 256)
	public String getComment()
	{
		return this.comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	public Set<SysUser> getUsers()
	{
		return this.users;
	}

	public void setUsers(Set<SysUser> users)
	{
		this.users = users;
	}
}
