package com.yida.boottracer.enums;

import java.util.ArrayList;
import java.util.List;

import com.yida.boottracer.dto.EnumObject;

public enum DeliveryQueryType
{
	Initial(0),

	Partial(1),
	
	Finished(2);
	
	private int id; // Could be other data type besides int

	private DeliveryQueryType(int id)
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
		case 0:
			return "未核销出库防伪码";
		case 1:
			return "部分核销出库防伪码";			
		case 2:
			return "已全部核销出库防伪码";				
		default:
			return "未知";
		}
	}

	public static DeliveryQueryType fromId(int id)
	{
		for (DeliveryQueryType type : values())
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
		result.add(new EnumObject(DeliveryQueryType.Initial.getId(), DeliveryQueryType.Initial.getName()));
		result.add(new EnumObject(DeliveryQueryType.Partial.getId(), DeliveryQueryType.Partial.getName()));
		result.add(new EnumObject(DeliveryQueryType.Finished.getId(), DeliveryQueryType.Finished.getName()));
		return result;
	}
}
