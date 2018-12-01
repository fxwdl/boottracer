package com.yida.boottracer.dto;

import net.bytebuddy.asm.Advice.This;

public class SimpleResponse
{
	private boolean flag = false;

	private Object content;

	private Object attInfo;

	public SimpleResponse(Object content)
	{
		this(false, content);
	}

	public SimpleResponse(boolean flag, Object content)
	{
		this(flag, content, null);
	}

	public SimpleResponse(boolean flag, Object content, Object attInfo)
	{
		this.content = content;
		this.flag = flag;
		this.attInfo = attInfo;
	}

	public boolean getFlag()
	{
		return flag;
	}

	public void setFlag(boolean flag)
	{
		this.flag = flag;
	}

	public void setContent(Object content)
	{
		this.content = content;
	}

	public Object getContent()
	{
		return this.content;
	}

	public void setAttInfo(Object attInfo)
	{
		this.attInfo = attInfo;
	}

	public Object getAttInfo()
	{
		return this.attInfo;
	}
}
