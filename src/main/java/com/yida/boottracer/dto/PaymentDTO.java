package com.yida.boottracer.dto;

import java.util.List;

import com.yida.boottracer.domain.BizPayment;
import com.yida.boottracer.domain.DictMemberPrice;

public class PaymentDTO
{
	private BizPayment bizPayment;

	private List<EnumObject> payTypeList;

	private List<DictMemberPrice> barcodePriceList;

	private List<DictMemberPrice> accountPeriod;

	private List<DictMemberPrice> accountBalance;

	public BizPayment getBizPayment()
	{
		return this.bizPayment;
	}

	public void setBizPayment(BizPayment bizPayment)
	{
		this.bizPayment = bizPayment;
	}

	public List<EnumObject> getPayTypeList()
	{
		return payTypeList;
	}

	public void setPayTypeList(List<EnumObject> payTypeList)
	{
		this.payTypeList = payTypeList;
	}

	public List<DictMemberPrice> getBarcodePriceList()
	{
		return this.barcodePriceList;
	}

	public void setBarcodePriceList(List<DictMemberPrice> barcodePriceList)
	{
		this.barcodePriceList = barcodePriceList;
	}

	public List<DictMemberPrice> getAccountPeriodList()
	{
		return this.accountPeriod;
	}

	public void setAccountPeriodList(List<DictMemberPrice> accountPeriod)
	{
		this.accountPeriod = accountPeriod;
	}

	public List<DictMemberPrice> getAccountBalanceList()
	{
		return this.accountBalance;
	}

	public void setAccountBalanceList(List<DictMemberPrice> accountBalance)
	{
		this.accountBalance = accountBalance;
	}
}
