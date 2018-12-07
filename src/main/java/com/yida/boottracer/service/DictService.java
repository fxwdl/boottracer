package com.yida.boottracer.service;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Service;

import com.yida.boottracer.domain.DictCommon;
import com.yida.boottracer.domain.PagingModel;
import com.yida.boottracer.repo.DictCommonRepository;

@Service
public class DictService
{
	private final EntityManager dictCommon_em;
	
	@Autowired
	public DictService(JpaContext context)
	{
		this.dictCommon_em = context.getEntityManagerByManagedType(DictCommon.class);
	}
	
	public PagingModel<DictCommon> getCommonListWithPagination(int limit,int offset,String search,String sort,String order, int type)
	{
		return new PagingModel<DictCommon>();
	}
}
