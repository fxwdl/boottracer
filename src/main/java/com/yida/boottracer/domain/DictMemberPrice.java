package com.yida.boottracer.domain;
// Generated 2018-12-17 21:12:26 by Hibernate Tools 5.2.10.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * DictMemberPrice generated by hbm2java
 */
@Entity
@Table(name = "dict_member_price")
public class DictMemberPrice implements java.io.Serializable
{

	private int id;
	private DictMemberType dictMemberType;
	private int type;
	private int qty;
	private BigDecimal price;
	private String comment;
	private boolean isDeleted;
	
	public DictMemberPrice()
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
	@JoinColumn(name = "MemberTypeId", nullable = false)
	public DictMemberType getDictMemberType()
	{
		return this.dictMemberType;
	}

	public void setDictMemberType(DictMemberType dictMemberType)
	{
		this.dictMemberType = dictMemberType;
	}

	@Column(name = "Type", nullable = false)
	public int getType()
	{
		return this.type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	@Column(name = "Qty", nullable = false)
	public int getQty()
	{
		return this.qty;
	}

	public void setQty(int qty)
	{
		this.qty = qty;
	}

	@Column(name = "Price", nullable = false, precision = 10)
	public BigDecimal getPrice()
	{
		return this.price;
	}

	public void setPrice(BigDecimal price)
	{
		this.price = price;
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
	
	@Column(name = "IsDeleted", nullable = false)
	public boolean isIsDeleted()
	{
		return this.isDeleted;
	}

	public void setIsDeleted(boolean isDeleted)
	{
		this.isDeleted = isDeleted;
	}
}
