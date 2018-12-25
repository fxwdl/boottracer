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
public enum SysMemberStatus
{
	/**
	 * 申请中
	 */
	Applying,

	/**
	 * 已拒绝
	 */
    Refused,
    
    /**
	 * 已通过
	 */
    Approved,
    
    /**
	 * 已停用
	 */
	Disabled;

	public static List<EnumObject> getList()
	{
		ArrayList<EnumObject> result = new ArrayList<>();
		result.add(new EnumObject(SysMemberStatus.Applying.ordinal(), "申请中"));
		result.add(new EnumObject(SysMemberStatus.Refused.ordinal(), "已拒绝"));
        result.add(new EnumObject(SysMemberStatus.Approved.ordinal(), "已通过"));
        result.add(new EnumObject(SysMemberStatus.Disabled.ordinal(), "已停用"));
		return result;
	}
}