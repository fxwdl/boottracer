package com.yida.boottracer.domain;
// Generated 2019-7-14 11:31:05 by Hibernate Tools 5.2.10.Final

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

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * BizDelivery generated by hbm2java
 */
@Entity
@Table(name = "biz_delivery")
public class BizDelivery extends AuditModel implements java.io.Serializable
{

	private int id;
	private Long version;
	private BizCodeApply bizCodeApply;
	private DictRegion dictRegion;
	private EntDictDealer entDictDealer;
	private EntDictSupplier entDictSupplier;
	private String fromCode;
	private String endCode;
	private int qty;
	private String comment;
	private Integer approved;
	private String approveUser;
	private Date appproveTime;
	private int type;
	private int mode;
	private String importFile;

	public BizDelivery()
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
	@Column(name = "Version")
	public Long getVersion()
	{
		return this.version;
	}

	public void setVersion(Long version)
	{
		this.version = version;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CodeApplyID")
	public BizCodeApply getBizCodeApply()
	{
		return this.bizCodeApply;
	}

	public void setBizCodeApply(BizCodeApply bizCodeApply)
	{
		this.bizCodeApply = bizCodeApply;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ToRegionID")
	public DictRegion getDictRegion()
	{
		return this.dictRegion;
	}

	public void setDictRegion(DictRegion dictRegion)
	{
		this.dictRegion = dictRegion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DealerID")
	public EntDictDealer getEntDictDealer()
	{
		return this.entDictDealer;
	}

	public void setEntDictDealer(EntDictDealer entDictDealer)
	{
		this.entDictDealer = entDictDealer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SupplierID")
	public EntDictSupplier getEntDictSupplier()
	{
		return this.entDictSupplier;
	}

	public void setEntDictSupplier(EntDictSupplier entDictSupplier)
	{
		this.entDictSupplier = entDictSupplier;
	}

	@Column(name = "FromCode", length = 256)
	public String getFromCode()
	{
		return this.fromCode;
	}

	public void setFromCode(String fromCode)
	{
		this.fromCode = fromCode;
	}

	@Column(name = "EndCode", length = 256)
	public String getEndCode()
	{
		return this.endCode;
	}

	public void setEndCode(String endCode)
	{
		this.endCode = endCode;
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

	@Column(name = "Comment", length = 500)
	public String getComment()
	{
		return this.comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	@Column(name = "Approved")
	public Integer getApproved()
	{
		return this.approved;
	}

	public void setApproved(Integer approved)
	{
		this.approved = approved;
	}

	@Column(name = "ApproveUser", length = 64)
	public String getApproveUser()
	{
		return this.approveUser;
	}

	public void setApproveUser(String approveUser)
	{
		this.approveUser = approveUser;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "AppproveTime", length = 26)
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getAppproveTime()
	{
		return this.appproveTime;
	}

	public void setAppproveTime(Date appproveTime)
	{
		this.appproveTime = appproveTime;
	}
	
	@Column(name = "Type")
	public Integer getType()
	{
		return this.type;
	}

	public void setType(Integer type)
	{
		this.type = type;
	}
	
	@Column(name = "Mode")
	public Integer getMode()
	{
		return this.type;
	}

	public void setMode(Integer mode)
	{
		this.mode = mode;
	}
	
	@Column(name = "ImportFile", length = 256)
	public String getImportFile()
	{
		return this.importFile;
	}

	public void setImportFile(String importFile)
	{
		this.importFile = importFile;
	}
}