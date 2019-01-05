package com.yida.boottracer.service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Service;

import com.yida.boottracer.domain.AuditModel;
import com.yida.boottracer.domain.EntDictCategory;
import com.yida.boottracer.domain.EntDictSupplier;
import com.yida.boottracer.domain.PagingModel;
import com.yida.boottracer.domain.SysMember;
import com.yida.boottracer.repo.EntDictCategoryRepository;
import com.yida.boottracer.repo.EntDictSupplierRepository;
import com.yida.web.exception.ResourceNotFoundException;

@Service
//@Transactional
public class EntDictService
{
	private JpaContext jpaContext;
	
    @PersistenceContext
    protected EntityManager em;

	@Autowired
	private EntDictCategoryRepository entDictCategoryRepository;
	@Autowired
	private EntDictSupplierRepository entDictSupplierRepository;
	
	@Autowired
	public EntDictService(JpaContext jpaContext)
	{
		this.jpaContext=jpaContext;
	}
	
	public <T> PagingModel<T> getDataPagination(Class<T> persistentClass,int limit, int offset, String search,
			String sort, String order)
	{		
		List<String> whereCause = new ArrayList<String>();
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder("FROM "+persistentClass.getName()+" d WHERE d.isDeleted=false");
		if (StringUtils.isNotEmpty(search))
		{
			whereCause.add("(d.code LIKE CONCAT('%',:search,'%') OR d.name LIKE CONCAT('%',:search,'%'))");
			paramaterMap.put("search", search);
		}

		if (whereCause.size() > 0)
		{
			sb.append(" AND " + StringUtils.join(whereCause, " and "));
		}

		if (StringUtils.isNotEmpty(sort))
		{
			sb.append(" ORDER BY d." + sort + " " + order);
		}
		
		TypedQuery<T> query = em.createQuery("SELECT d " + sb.toString(), persistentClass);
		Query query_c = em.createQuery("SELECT COUNT(d) " + sb.toString());
		for (String key : paramaterMap.keySet())
		{
			query.setParameter(key, paramaterMap.get(key));
			query_c.setParameter(key, paramaterMap.get(key));
		}
		PagingModel<T> paged = new PagingModel<>();
		paged.setRows(query.setFirstResult(offset).setMaxResults(limit).getResultList());
		paged.setTotal((long) query_c.getSingleResult());

		return paged;
	}

	@SuppressWarnings("unchecked")
	private <T> void delete(Class<T> persistentClass,SysMember ent, int id)
	{

		List<T> lst = (List<T>)em.createQuery("SELECT d FROM "+persistentClass.getName()+ " d WHERE d.sysMember=:ent AND d.id=:id")
			.setParameter("ent", ent)
			.setParameter("id", id).getResultList();
		if(lst.size()==0)
		{
			throw new ResourceNotFoundException("未找到指定的数据");
		}
		else 
		{
			em.remove(lst.get(0));
		}		
	}

	public void deleteEntCategoryItem(SysMember ent, int id)
	{
		Optional<EntDictCategory> item = entDictCategoryRepository.findBySysMemberAndId(ent, id);
		if (!item.isPresent())
		{
			throw new ResourceNotFoundException("未找到指定的数据");
		}
		else
		{
			entDictCategoryRepository.delete(item.get());
		}
	}

	public EntDictCategory saveEntCategoryItem(SysMember ent, EntDictCategory item)
	{
		item.setSysMember(ent);
		List<EntDictCategory> lst= entDictCategoryRepository.findBySysMemberAndCodeAndIsDeletedAndIdNot(ent, item.getCode(), false,item.getId());		
		if (lst.size()>0)
		{
			throw new RuntimeException("编号重复");
		}
		
		return entDictCategoryRepository.save(item);
	}
	
	public void deleteEntSupplierItem(SysMember ent, int id)
	{
		Optional<EntDictSupplier> item = entDictSupplierRepository.findBySysMemberAndId(ent, id);
		if (!item.isPresent())
		{
			throw new ResourceNotFoundException("未找到指定的数据");
		}
		else
		{
			entDictSupplierRepository.delete(item.get());
		}
	}

	public EntDictSupplier saveEntSupplierItem(SysMember ent, EntDictSupplier item)
	{
		item.setSysMember(ent);
		List<EntDictSupplier> lst = entDictSupplierRepository.findBySysMemberAndCodeAndIsDeletedAndIdNot(ent, item.getCode(), false,item.getId());
		
		if (lst.size()>0)
		{
			throw new RuntimeException("编号重复");
		}
		
		return entDictSupplierRepository.save(item);
	}
}
