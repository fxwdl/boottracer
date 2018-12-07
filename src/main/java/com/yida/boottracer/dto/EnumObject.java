package com.yida.boottracer.dto;

public class EnumObject
{
	private int value;
	private String name;

	public EnumObject()
	{
	}

	public EnumObject(int value, String name)
	{
		this.value = value;
		this.name = name;
	}

	public void setValue(int value)
	{
		this.value = value;
	}

	public int getValue()
	{
		return this.value;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return this.name;
	}
}
