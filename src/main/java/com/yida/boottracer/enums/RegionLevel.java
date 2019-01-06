package com.yida.boottracer.enums;

/**
 * 通用字典表
 * 
 * @author fx__w
 *
 */
public enum RegionLevel
{
	Country(0),

	Province(1),

	City(2),

	County(3);

	private int id; // Could be other data type besides int

	private RegionLevel(int id)
	{
		this.id = id;
	}

	public int getValue()
	{
		return this.id;
	}

	public static RegionLevel fromId(int id)
	{
		for (RegionLevel type : values())
		{
			if (type.id == id)
			{
				return type;
			}
		}
		return null;
	}
}