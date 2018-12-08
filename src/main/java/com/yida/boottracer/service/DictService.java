package com.yida.boottracer.service;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.commons.lang3.StringUtils;
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
	private DictCommonRepository dictCommonRepository;

	@Autowired
	public DictService(JpaContext context)
	{
		this.dictCommon_em = context.getEntityManagerByManagedType(DictCommon.class);
	}

	public PagingModel<DictCommon> getCommonListWithPagination(int limit, int offset, String search, String sort,
			String order, int type)
	{		
		List<String> whereCause = new ArrayList<String>();
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder("FROM DictCommon d WHERE d.isDeleted=false");
		if (StringUtils.isNotEmpty(search))
		{
			whereCause.add("(d.code LIKE CONCAT('%',:search,'%') OR d.name LIKE CONCAT('%',:search,'%'))");
			paramaterMap.put("search", search);
		}
		if (type != -1)
		{
			whereCause.add("d.dictType=:type");
			paramaterMap.put("type", type);
		}

		if (whereCause.size() > 0)
		{
			sb.append(" AND "+StringUtils.join(whereCause, " and "));			
		}

		if (StringUtils.isNotEmpty(sort))
		{
			sb.append(" ORDER BY d." + sort + " " + order);	
		}

		TypedQuery<DictCommon> query = dictCommon_em.createQuery("SELECT d "+sb.toString(),DictCommon.class);
		Query query_c = dictCommon_em.createQuery("SELECT COUNT(d.id) "+sb.toString());
		for(String key : paramaterMap.keySet())
		{
			query.setParameter(key, paramaterMap.get(key));
			query_c.setParameter(key, paramaterMap.get(key));
		}
		PagingModel<DictCommon> paged = new PagingModel<>();
		paged.setRows(query.setFirstResult(offset).setMaxResults(limit).getResultList());
		paged.setTotal((long)query_c.getSingleResult());
		
		return paged;
	}
}
