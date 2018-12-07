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
public enum DictCommomType
{
	/**
	 * 行业类型
	 */
	Industry,

	/**
	 * 企业类型
	 */
	CompanyType;

	public static List<EnumObject> getList()
	{
		ArrayList<EnumObject> result = new ArrayList<>();
		result.add(new EnumObject(DictCommomType.Industry.ordinal(), "行业类型"));
		result.add(new EnumObject(DictCommomType.CompanyType.ordinal(), "企业类型"));

		return result;
	}
}
