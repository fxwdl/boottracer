package com.yida.boottracer.domain;
// Generated 2018-11-24 12:44:01 by Hibernate Tools 5.2.10.Final

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * DictSystemFunction generated by hbm2java
 */
@Entity
@Table(name = "dict_system_function")
public class DictSystemFunction implements java.io.Serializable
{

	private String id;
	private DictSystemFunction parent;
	private String name;
	private String displayName;
	private String fullName;
	private int funType;
	private String seq;
	private String page;
	private String cssClass;
	private List<DictSystemFunction> children = new ArrayList<>();
	private Set<SysRole> roles = new HashSet<>();
	
	public DictSystemFunction()
	{
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ParentId")
	public DictSystemFunction getParent()
	{
		return this.parent;
	}

	public void setParent(DictSystemFunction parent)
	{
		this.parent = parent;
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

	@Column(name = "DisplayName", nullable = false, length = 256)
	public String getDisplayName()
	{
		return this.displayName;
	}

	public void setDisplayName(String displayName)
	{
		this.displayName = displayName;
	}

	@Column(name = "FullName", nullable = false, length = 1000)
	public String getFullName()
	{
		return this.fullName;
	}

	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}

	@Column(name = "FunType", nullable = false)
	public int getFunType()
	{
		return this.funType;
	}

	public void setFunType(int funType)
	{
		this.funType = funType;
	}

	@Column(name = "Seq", length = 50)
	public String getSeq()
	{
		return this.seq;
	}

	public void setSeq(String seq)
	{
		this.seq = seq;
	}

	@Column(name = "Page", length = 256)
	public String getPage()
	{
		return this.page;
	}

	public void setPage(String page)
	{
		this.page = page;
	}

	@Column(name = "CssClass", length = 256)
	public String getCssClass()
	{
		return this.cssClass;
	}

	public void setCssClass(String cssClass)
	{
		this.cssClass = cssClass;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
	public List<DictSystemFunction> getChildren()
	{
		return this.children;
	}

	public void setChildren(List<DictSystemFunction> children)
	{
		this.children = children;
	}

	@ManyToMany(mappedBy = "functions", fetch = FetchType.LAZY)
	public Set<SysRole> getRoles()
	{
		return this.roles;
	}

	public void setRoles(Set<SysRole> roles)
	{
		this.roles = roles;
	}
}
