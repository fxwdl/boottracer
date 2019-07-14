package com.yida.boottracer.enums;

import java.util.ArrayList;
import java.util.List;

import com.yida.boottracer.dto.EnumObject;

/**
 * 出库类别
 * 
 * @author fx__w
 *
 */
public enum DeliveryType
{

	SingleProduct(0),

	MultiProduct(1);

	private int id; // Could be other data type besides int

	private DeliveryType(int id)
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
			return "单货品出库";
		case 1:
			return "多货品出库";			
		default:
			return "未知";
		}
	}

	public static DeliveryType fromId(int id)
	{
		for (DeliveryType type : values())
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
		result.add(new EnumObject(DeliveryType.SingleProduct.getId(), DeliveryType.SingleProduct.getName()));
		result.add(new EnumObject(DeliveryType.MultiProduct.getId(), DeliveryType.MultiProduct.getName()));		
		return result;
	}
}