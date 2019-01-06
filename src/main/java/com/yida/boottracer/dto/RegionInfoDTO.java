package com.yida.boottracer.dto;

import java.util.List;

import com.yida.boottracer.domain.DictRegion;

public class RegionInfoDTO
{
	private List<DictRegion> provinceList;
	private List<DictRegion> cityList;
	private List<DictRegion> countyList;

	private DictRegion curProvince;
	private DictRegion curCity;
	private DictRegion curCounty;

	public DictRegion getCurProvince()
	{
		return this.curProvince;
	}

	public void setCurProvince(DictRegion curProvince)
	{
		this.curProvince = curProvince;
	}

	public DictRegion getCurCity()
	{
		return this.curCity;
	}

	public void setCurCity(DictRegion curCity)
	{
		this.curCity = curCity;
	}

	public DictRegion getCurCounty()
	{
		return this.curCounty;
	}

	public void setCurCounty(DictRegion curCounty)
	{
		this.curCounty = curCounty;
	}

	public List<DictRegion> getProvinceList()
	{
		return provinceList;
	}

	public void setProvinceList(List<DictRegion> provinceList)
	{
		this.provinceList = provinceList;
	}

	public List<DictRegion> getCityList()
	{
		return cityList;
	}

	public void setCityList(List<DictRegion> cityList)
	{
		this.cityList = cityList;
	}

	public List<DictRegion> getCountyList()
	{
		return countyList;
	}

	public void setCountyList(List<DictRegion> countyList)
	{
		this.countyList = countyList;
	}
}
