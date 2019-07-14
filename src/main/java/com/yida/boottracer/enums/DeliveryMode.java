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
public enum DeliveryMode
{

	CodeRange(0),

	ImportFile(1);

	private int id; // Could be other data type besides int

	private DeliveryMode(int id)
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
			return "码段出库";
		case 1:
			return "按导入文件出库";			
		default:
			return "未知";
		}
	}

	public static DeliveryMode fromId(int id)
	{
		for (DeliveryMode type : values())
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
		result.add(new EnumObject(DeliveryMode.CodeRange.getId(), DeliveryMode.CodeRange.getName()));
		result.add(new EnumObject(DeliveryMode.ImportFile.getId(), DeliveryMode.ImportFile.getName()));
		return result;
	}
}