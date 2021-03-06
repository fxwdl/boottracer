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
public enum PayType
{

	Barcode(0),

	AccountPeriod(1),
	
	AccountBalance(2);

	private int id; // Could be other data type besides int

	private PayType(int id)
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
			return "生码续费";	
		case 1:
			return "账户有效期续费";
		case 2:
			return "账户余额充值";		
		default:
			return "未知";
		}
	}

	public static PayType fromId(int id)
	{
		for (PayType type : values())
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
		result.add(new EnumObject(PayType.Barcode.getId(), PayType.Barcode.getName()));		
		result.add(new EnumObject(PayType.AccountPeriod.getId(), PayType.AccountPeriod.getName()));
		result.add(new EnumObject(PayType.AccountBalance.getId(), PayType.AccountBalance.getName()));
		return result;
	}
}