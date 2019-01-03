package com.yida.boottracer.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
// 注：还可以有@CreatedBy、@LastModifiedBy表示人员相关的，通过SecurityContext‘s Authentication自动赋值。也可以定制
//https://www.baeldung.com/database-auditing-jpa
public abstract class AuditModel implements Serializable
{

	private Date createdAt;


	private Date updatedAt;


	private String createdBy;


	private String modifiedBy;

	@Column(name = "CreatedBy", nullable = false, updatable = false)
	@CreatedBy
	public String getCreatedBy()
	{
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}

	@Column(name = "ModifiedBy", nullable = false)
	@LastModifiedBy
	public String getModifiedBy()
	{
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy)
	{
		this.modifiedBy = modifiedBy;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd hh:ss:mm",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CreatedAt", nullable = false, updatable = false)
	@CreatedDate
	public Date getCreatedAt()
	{
		return createdAt;
	}

	public void setCreatedAt(Date createdAt)
	{
		this.createdAt = createdAt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UpdatedAt", nullable = false)
	@LastModifiedDate
	public Date getUpdatedAt()
	{
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt)
	{
		this.updatedAt = updatedAt;
	}
}
