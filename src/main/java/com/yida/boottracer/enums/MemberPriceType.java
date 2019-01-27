package com.yida.boottracer.enums;

import java.util.ArrayList;
import java.util.List;

import com.yida.boottracer.dto.EnumObject;

/**
 * 续费付款方式
 * 
 * @author fx__w
 *
 */
public enum MemberPriceType
{

	Platform(1),

	CreateBarcode(2);

	private int id; // Could be other data type besides int

	private MemberPriceType(int id)
	{
		this.id = id;
	}
	
	public int getId() 
	{
		return id;
	}
	
	public String getName() 
	{
		switch(id)
		{
		case 1:
			return "平台价格";
		case 2:
			return "码量价格";			

		default:
			return "未知";
		}
	}

	public static MemberPriceType fromId(int id)
	{
		for (MemberPriceType type : values())
		{
			if (type.id == id)
			{
				return type;
			}
		}
		return null;
	}

	public static List<EnumObject> getList()
	{
		ArrayList<EnumObject> result = new ArrayList<>();
		result.add(new EnumObject(MemberPriceType.Platform.getId(), MemberPriceType.Platform.getName()));
		result.add(new EnumObject(MemberPriceType.CreateBarcode.getId(), MemberPriceType.CreateBarcode.getName()));
		return result;
	}
}