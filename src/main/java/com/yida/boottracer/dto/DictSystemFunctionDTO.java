package com.yida.boottracer.dto;

import java.util.ArrayList;
import java.util.List;

import com.yida.boottracer.domain.DictSystemFunction;

public class DictSystemFunctionDTO
{
	private String id;
	private DictSystemFunction parent;
	private String name;
	private String displayName;
	private String fullName;
	private int funType;
	private String seq;
	private String page;
	private String cssClass;
	private List<DictSystemFunctionDTO> children = new ArrayList<>();
	
	public DictSystemFunctionDTO()
	{
	}

	public String getId()
	{
		return this.id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public DictSystemFunction getParent()
	{
		return this.parent;
	}

	public void setParent(DictSystemFunction parent)
	{
		this.parent = parent;
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDisplayName()
	{
		return this.displayName;
	}

	public void setDisplayName(String displayName)
	{
		this.displayName = displayName;
	}

	public String getFullName()
	{
		return this.fullName;
	}

	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}

	public int getFunType()
	{
		return this.funType;
	}

	public void setFunType(int funType)
	{
		this.funType = funType;
	}

	public String getSeq()
	{
		return this.seq;
	}

	public void setSeq(String seq)
	{
		this.seq = seq;
	}

	public String getPage()
	{
		return this.page;
	}

	public void setPage(String page)
	{
		this.page = page;
	}

	public String getCssClass()
	{
		return this.cssClass;
	}

	public void setCssClass(String cssClass)
	{
		this.cssClass = cssClass;
	}

	public List<DictSystemFunctionDTO> getChildren()
	{
		return this.children;
	}

	public void setChildren(List<DictSystemFunctionDTO> children)
	{
		this.children = children;
	}
}
