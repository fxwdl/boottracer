package com.yida.boottracer.dto;

import java.util.ArrayList;
import java.util.List;

public class ImageResultDto
{
	private int errno=0;
	private List<String> data=new ArrayList<>();
	
	public int getErrno() {
		return errno;
	}
	public void setErrno(int errno)
	{
		this.errno=errno;
	}
	
	public List<String> getData()
	{		
		return this.data;
	}
	
	public void setData(List<String> data)
	{
		this.data=data;
	}
}
