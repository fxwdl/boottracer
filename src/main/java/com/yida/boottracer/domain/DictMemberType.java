package com.yida.boottracer.domain;
// Generated 2018-12-17 21:12:26 by Hibernate Tools 5.2.10.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * DictMemberType generated by hbm2java
 */
@Entity
@Table(name = "dict_member_type")
public class DictMemberType implements java.io.Serializable
{

	private int id;
	private int freeCodeQty;
	private int holdTime;
	private boolean isDefault;
	private boolean isDeleted;
	private String comment;
	private Set<DictMemberPrice> dictMemberPrices = new HashSet<>();

	public DictMemberType()
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

	@Column(name = "FreeCodeQty", nullable = false)
	public int getFreeCodeQty()
	{
		return this.freeCodeQty;
	}

	public void setFreeCodeQty(int freeCodeQty)
	{
		this.freeCodeQty = freeCodeQty;
	}

	@Column(name = "HoldTime", nullable = false)
	public int getHoldTime()
	{
		return this.holdTime;
	}

	public void setHoldTime(int holdTime)
	{
		this.holdTime = holdTime;
	}

	@Column(name = "IsDefault", nullable = false)
	public boolean isIsDefault()
	{
		return this.isDefault;
	}

	public void setIsDefault(boolean isDefault)
	{
		this.isDefault = isDefault;
	}

	@Column(name = "IsDeleted", nullable = false)
	public boolean isIsDeleted()
	{
		return this.isDeleted;
	}

	public void setIsDeleted(boolean isDeleted)
	{
		this.isDeleted = isDeleted;
	}

	@Column(name = "Comment", length = 256)
	public String getComment()
	{
		return this.comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dictMemberType")
	public Set<DictMemberPrice> getDictMemberPrices()
	{
		return this.dictMemberPrices;
	}

	public void setDictMemberPrices(Set<DictMemberPrice> dictMemberPrices)
	{
		this.dictMemberPrices = dictMemberPrices;
	}

}
