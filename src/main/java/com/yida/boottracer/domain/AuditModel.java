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
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd hh:ss:mm",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CreatedAt", nullable = true, updatable = false)
	@CreatedDate
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UpdatedAt", nullable = true)
	@LastModifiedDate
	private Date updatedAt;

	@Column(name = "CreatedBy", nullable = true, updatable = false)
	@CreatedBy
	private String createdBy;

	@Column(name = "LastModifiedBy", nullable = true)
	@LastModifiedBy
	private String modifiedBy;

	public String getCreatedBy()
	{
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}

	public String getModifiedBy()
	{
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy)
	{
		this.modifiedBy = modifiedBy;
	}

	public Date getCreatedAt()
	{
		return createdAt;
	}

	public void setCreatedAt(Date createdAt)
	{
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt()
	{
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt)
	{
		this.updatedAt = updatedAt;
	}
}
