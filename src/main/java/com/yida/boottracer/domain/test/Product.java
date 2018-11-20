package com.yida.boottracer.domain.test;
// Generated 2018-11-20 12:06:58 by Hibernate Tools 5.2.10.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * TestProduct generated by hbm2java
 */
@Entity
@Table(name = "test_product")
public class Product implements java.io.Serializable
{

	private long id;
	private Integer version;
	private String name;

	public Product()
	{
	}

	public Product(long id)
	{
		this.id = id;
	}

	public Product(long id, String name)
	{
		this.id = id;
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	public long getId()
	{
		return this.id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	@Version
	@Column(name = "version")
	public Integer getVersion()
	{
		return this.version;
	}

	public void setVersion(Integer version)
	{
		this.version = version;
	}

	@Column(name = "name")
	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
