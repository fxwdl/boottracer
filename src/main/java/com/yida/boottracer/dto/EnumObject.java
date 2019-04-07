package com.yida.boottracer.dto;

public class EnumObject
{
	private int value;
	private String name;
	
	public EnumObject(int value, String name)
	{
		super();
		this.value = value;
		this.name = name;
	}
	public int getValue()
	{
		return value;
	}
	public void setValue(int value)
	{
		this.value = value;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}


}
