package com.yida.boottracer.dto;

public class SimpleResponse
{
	/** * 返回 内容 （json格式） */
	private Object content;

	private boolean flag=false;
	
	public SimpleResponse(Object content)
	{
		this(false, content);
	}
	
	public SimpleResponse(boolean flag,Object content)
	{
		this.content = content;
		this.flag=flag;
	}
	
	public boolean getFlag() {
		return flag;
	}
	
	public void setFlag(boolean flag)
	{
		this.flag=flag;
	}

	public void setContent(Object content)
	{
		this.content = content;
	}

	public Object getContent()
	{
		return this.content;
	}
}
