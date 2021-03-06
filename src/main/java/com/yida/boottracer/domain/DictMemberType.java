package com.yida.boottracer.domain;
// Generated 2018-12-17 21:12:26 by Hibernate Tools 5.2.10.Final

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;

import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * DictMemberType generated by hbm2java
 */
@NamedEntityGraphs(value = {
	    @NamedEntityGraph(name = "DictMemberType.dictMemberPrices", attributeNodes = {
	        @NamedAttributeNode("dictMemberPrices")
	    })
	})
@Entity
@Table(name = "dict_member_type")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DictMemberType implements java.io.Serializable
{

	private Integer id;
	private int freeCodeQty;
	private int holdTime;
	private boolean isDefault;
	private boolean isDeleted;
	private String comment;
	private String name;
	private Long version;
	
	private Set<DictMemberPrice> dictMemberPrices = new HashSet<>();

	public DictMemberType()
	{
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId()
	{
		return this.id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	@Column(name = "Name", nullable = false, length = 256)
	@NotBlank(message = "名称不能为空")
	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dictMemberType",cascade= {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.REMOVE,CascadeType.DETACH,CascadeType.MERGE}, orphanRemoval=true /*jpa 2 新增，允许删除子对象*/)
	@JsonManagedReference
	public Set<DictMemberPrice> getDictMemberPrices()
	{
		return this.dictMemberPrices;
	}

	public void setDictMemberPrices(Set<DictMemberPrice> dictMemberPrices)
	{
		this.dictMemberPrices = dictMemberPrices;
	}

	@Version
	@Column(name = "version", nullable = false)
	public Long getVersion()
	{
		return this.version;
	}

	public void setVersion(Long version)
	{
		this.version = version;
	}
}
