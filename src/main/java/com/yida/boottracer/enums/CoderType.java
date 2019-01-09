package com.yida.boottracer.enums;

import java.util.ArrayList;
import java.util.List;

import com.yida.boottracer.dto.EnumObject;

/**
 * 通用字典表
 * 
 * @author fx__w
 *
 */
public enum CoderType
{
	/**
	 * 企业代码
	 */
	EntCode(0),

	/**
	 * 产品编号
	 */
	ProductCode(1),

	/**
	 * 流水号
	 */
	SeqCode(2),

	/**
	 * 验证码
	 */
	CheckCode(3),
	
	/**
	 * 日期码
	 */
	DateCode(4),
	
	/**
	 * 自定义码
	 */
	Custom(5);
	

	private int id; // Could be other data type besides int

	private CoderType(int id)
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
			return "企业代码";
		case 1:
			return "产品编码";
		case 2:
			return "流水码";
		case 3:
			return "随机防伪码";
		case 4:
			return "日期码";
		case 5:
			return "自定义固定值码";
		default:
			return "未知";
		}
	}

	public static CoderType fromId(int id)
	{
		for (CoderType type : values())
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
		result.add(new EnumObject(CoderType.EntCode.getId(), CoderType.EntCode.getName()));
		result.add(new EnumObject(CoderType.ProductCode.getId(), CoderType.ProductCode.getName()));
		result.add(new EnumObject(CoderType.SeqCode.getId(), CoderType.SeqCode.getName()));
		result.add(new EnumObject(CoderType.CheckCode.getId(), CoderType.CheckCode.getName()));
		result.add(new EnumObject(CoderType.DateCode.getId(), CoderType.DateCode.getName()));
		result.add(new EnumObject(CoderType.Custom.getId(), CoderType.Custom.getName()));
		return result;
	}

}