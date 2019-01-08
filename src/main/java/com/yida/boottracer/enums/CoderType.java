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
		result.add(new EnumObject(CoderType.EntCode.id, "企业代码"));
		result.add(new EnumObject(CoderType.ProductCode.id, "产品编码"));
		result.add(new EnumObject(CoderType.SeqCode.id, "流水码"));
		result.add(new EnumObject(CoderType.CheckCode.id, "随机验证码"));
		result.add(new EnumObject(CoderType.DateCode.id, "日期码"));
		result.add(new EnumObject(CoderType.Custom.id, "自定义固定值码"));
		return result;
	}

}