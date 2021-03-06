package com.yida.boottracer.domain;
// Generated 2019-1-8 21:56:57 by Hibernate Tools 5.2.10.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * EntDictCoderDetail generated by hbm2java
 */
@Entity
@Table(name = "ent_dict_coder_detail")
public class EntDictCoderDetail extends AuditModel implements java.io.Serializable
{

	private int id;
	private Long version;
	private EntDictCoder entDictCoder;
	private Integer seq;
	private int type;
	private String fieldValue;
	private int fieldSize;
	private String comment;
	private String name;
	
	public EntDictCoderDetail()
	{
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public int getId()
	{
		return this.id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	@Version
	@Column(name = "Version", nullable = false)
	public Long getVersion()
	{
		return this.version;
	}

	public void setVersion(Long version)
	{
		this.version = version;
	}

	@JsonBackReference(value="EntDictCoder-EntDictCoderDetail")	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CoderID", nullable = false)
	public EntDictCoder getEntDictCoder()
	{
		return this.entDictCoder;
	}

	public void setEntDictCoder(EntDictCoder entDictCoder)
	{
		this.entDictCoder = entDictCoder;
	}

	@Column(name = "Seq", nullable = false)
	public Integer getSeq()
	{
		return this.seq;
	}

	public void setSeq(Integer seq)
	{
		this.seq = seq;
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
	
	@Column(name = "Type", nullable = false)
	public int getType()
	{
		return this.type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	@Column(name = "FieldValue", length = 50)
	public String getFieldValue()
	{
		return this.fieldValue;
	}

	public void setFieldValue(String fieldValue)
	{
		this.fieldValue = fieldValue;
	}

	@Column(name = "FieldSize", nullable = false)
	public int getFieldSize()
	{
		return this.fieldSize;
	}

	public void setFieldSize(int fieldSize)
	{
		this.fieldSize = fieldSize;
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

}
