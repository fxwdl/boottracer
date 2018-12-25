package com.yida.boottracer.domain;
// Generated 2018-12-2 22:28:19 by Hibernate Tools 5.2.10.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * DictRegion generated by hbm2java
 */
@Entity
@Table(name = "dict_region")
public class DictRegion implements java.io.Serializable
{

	private int id;
	private DictRegion parent;
	private String code;
	private String name;
	private int level;
	private int order;
	private String nameEn;
	private String shortNameEn;
	private Set<DictRegion> children = new HashSet<>();

	public DictRegion()
	{
	}

	@Id

	@Column(name = "ID", unique = true, nullable = false)
	public int getId()
	{
		return this.id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Parent_ID")
	@JsonBackReference
	public DictRegion getParent()
	{
		return this.parent;
	}

	public void setParent(DictRegion parent)
	{
		this.parent = parent;
	}

	@Column(name = "Code", nullable = false, length = 100)
	public String getCode()
	{
		return this.code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	@Column(name = "Name", nullable = false, length = 100)
	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Column(name = "Level", nullable = false)
	public int getLevel()
	{
		return this.level;
	}

	public void setLevel(int level)
	{
		this.level = level;
	}

	@Column(name = "Order", nullable = false)
	public int getOrder()
	{
		return this.order;
	}

	public void setOrder(int order)
	{
		this.order = order;
	}

	@Column(name = "Name_En", nullable = false, length = 100)
	public String getNameEn()
	{
		return this.nameEn;
	}

	public void setNameEn(String nameEn)
	{
		this.nameEn = nameEn;
	}

	@Column(name = "ShortName_En", nullable = false, length = 10)
	public String getShortNameEn()
	{
		return this.shortNameEn;
	}

	public void setShortNameEn(String shortNameEn)
	{
		this.shortNameEn = shortNameEn;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
	@JsonManagedReference
	@JsonIgnore
	public Set<DictRegion> getChildren()
	{
		return this.children;
	}

	public void setChildren(Set<DictRegion> children)
	{
		this.children = children;
	}
}
