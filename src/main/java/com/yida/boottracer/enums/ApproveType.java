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
public enum ApproveType
{

	UnApprove(0),

	Approved(1),

	Reject(2);

	private int id; // Could be other data type besides int

	private ApproveType(int id)
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
			return "未审核";
		case 1:
			return "已审核";			
		case 2:
			return "已拒绝";

		default:
			return "未知";
		}
	}

	public static ApproveType fromId(int id)
	{
		for (ApproveType type : values())
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
		result.add(new EnumObject(ApproveType.UnApprove.getId(), ApproveType.UnApprove.getName()));
		result.add(new EnumObject(ApproveType.Approved.getId(), ApproveType.Approved.getName()));
		result.add(new EnumObject(ApproveType.Reject.getId(), ApproveType.Reject.getName()));
		return result;
	}
}