
package com.yida.boottracer.domain;

import java.util.Date;

public class BizCode
{
	private Integer ID;

	private Integer applyID;

	private String barCode;

	private String QRBarcode;

	private Boolean delivered;

	private Boolean activated;

	private Integer queryCount;
	
	private Long version;

	private Date createdAt;

	public Integer getID()
	{
		return ID;
	}

	public void setID(Integer ID)
	{
		this.ID = ID;
	}

	public Integer getApplyID()
	{
		return applyID;
	}

	public void setApplyID(Integer applyID)
	{
		this.applyID = applyID;
	}

	public String getBarCode()
	{
		return barCode;
	}

	public void setBarCode(String barCode)
	{
		this.barCode = barCode == null ? null : barCode.trim();
	}

	public String getQRBarcode()
	{
		return QRBarcode;
	}

	public void setQRBarcode(String QRBarcode)
	{
		this.QRBarcode = QRBarcode == null ? null : QRBarcode.trim();
	}

	public Boolean getDelivered()
	{
		return delivered;
	}

	public void setDelivered(Boolean delivered)
	{
		this.delivered = delivered;
	}

	public Boolean getActivated()
	{
		return activated;
	}

	public void setActivated(Boolean activated)
	{
		this.activated = activated;
	}

	public Integer getQueryCount()
	{
		return queryCount;
	}
	
	public void setQueryCount(Integer queryCount)
	{
		this.queryCount=queryCount;
	}
	
	public Long getVersion()
	{
		return version;
	}

	public void setVersion(Long version)
	{
		this.version = version;
	}

	public Date getCreatedAt()
	{
		return createdAt;
	}

	public void setCreatedAt(Date createdAt)
	{
		this.createdAt = createdAt;
	}
}