package com.yida.boottracer.service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.sql.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Service;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphs;
import com.yida.boottracer.domain.AuditModel;
import com.yida.boottracer.domain.DictCommon;
import com.yida.boottracer.domain.EntDictCategory;
import com.yida.boottracer.domain.EntDictCoder;
import com.yida.boottracer.domain.EntDictCoderDetail;
import com.yida.boottracer.domain.EntDictDealer;
import com.yida.boottracer.domain.EntDictProduct;
import com.yida.boottracer.domain.EntDictSupplier;
import com.yida.boottracer.domain.PagingModel;
import com.yida.boottracer.domain.SysMember;
import com.yida.boottracer.enums.CoderType;
import com.yida.boottracer.repo.EntDictCategoryRepository;
import com.yida.boottracer.repo.EntDictCoderRepository;
import com.yida.boottracer.repo.EntDictDealerRepository;
import com.yida.boottracer.repo.EntDictProductRepository;
import com.yida.boottracer.repo.EntDictSupplierRepository;
import com.yida.web.exception.ResourceNotFoundException;

@Service
// @Transactional
public class EntDictService
{
	/**
	 * 默认编码规则的编号
	 */
	public static final String DEFAULT_CODER_CODE = "01";

	private JpaContext jpaContext;

	@PersistenceContext
	protected EntityManager em;

	@Autowired
	private EntDictCategoryRepository entDictCategoryRepository;
	@Autowired
	private EntDictSupplierRepository entDictSupplierRepository;
	@Autowired
	private EntDictDealerRepository entDictDealerRepository;
	@Autowired
	private EntDictCoderRepository entDictCoderRepository;
	
	@Autowired
	private EntDictProductRepository entDictProductRepository;

	@Autowired
	public EntDictService(JpaContext jpaContext)
	{
		this.jpaContext = jpaContext;
	}

	public <T> PagingModel<T> getDataPagination(Class<T> persistentClass, String entityGraph, int limit, int offset,
			String search, String sort, String order)
	{
		List<String> whereCause = new ArrayList<String>();
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder("FROM " + persistentClass.getName() + " d WHERE d.isDeleted=false");
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

		EntityGraph<?> graph = null;

		if (!StringUtils.isBlank(entityGraph))
		{
			graph = this.em.getEntityGraph(entityGraph);
			// hints.put("javax.persistence.fetchgraph", graph);
		}
		TypedQuery<T> query = em.createQuery("SELECT d " + sb.toString(), persistentClass);
		Query query_c = em.createQuery("SELECT COUNT(d) " + sb.toString());
		for (String key : paramaterMap.keySet())
		{
			query.setParameter(key, paramaterMap.get(key));
			query_c.setParameter(key, paramaterMap.get(key));
		}
		PagingModel<T> paged = new PagingModel<>();
		if (graph != null)
		{
			query = query.setHint("javax.persistence.loadgraph", graph);
		}
		paged.setRows(query.setFirstResult(offset).setMaxResults(limit).getResultList());
		paged.setTotal((long) query_c.getSingleResult());

		return paged;
	}

	public <T> PagingModel<T> getDataPagination(Class<T> persistentClass, int limit, int offset, String search,
			String sort, String order)
	{
		return getDataPagination(persistentClass, null, limit, offset, search, sort, order);
	}

	@SuppressWarnings("unchecked")
	private <T> void delete(Class<T> persistentClass, SysMember ent, int id)
	{

		List<T> lst = (List<T>) em
				.createQuery("SELECT d FROM " + persistentClass.getName() + " d WHERE d.sysMember=:ent AND d.id=:id")
				.setParameter("ent", ent).setParameter("id", id).getResultList();
		if (lst.size() == 0)
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
		List<EntDictCategory> lst = entDictCategoryRepository.findBySysMemberAndCodeAndIsDeletedAndIdNot(ent,
				item.getCode(), false, item.getId());
		if (lst.size() > 0)
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
		List<EntDictSupplier> lst = entDictSupplierRepository.findBySysMemberAndCodeAndIsDeletedAndIdNot(ent,
				item.getCode(), false, item.getId());

		if (lst.size() > 0)
		{
			throw new RuntimeException("编号重复");
		}

		return entDictSupplierRepository.save(item);
	}

	public void deleteEntDealerItem(SysMember ent, int id)
	{
		Optional<EntDictDealer> item = entDictDealerRepository.findBySysMemberAndId(ent, id);
		if (!item.isPresent())
		{
			throw new ResourceNotFoundException("未找到指定的数据");
		}
		else
		{
			entDictDealerRepository.delete(item.get());
		}
	}

	public EntDictDealer saveEntDealerItem(SysMember ent, EntDictDealer item)
	{
		item.setSysMember(ent);
		List<?> lst = entDictDealerRepository.findBySysMemberAndCodeAndIsDeletedAndIdNot(ent, item.getCode(), false,
				item.getId());

		if (lst.size() > 0)
		{
			throw new RuntimeException("编号重复");
		}

		return entDictDealerRepository.save(item);
	}

	/**
	 * 创建默认的编码规则数据
	 * 
	 * @param ent
	 * @return
	 */
	@Transactional
	public EntDictCoder createDefaultCoder(SysMember ent)
	{
		Optional<EntDictCoder> r = entDictCoderRepository.findBySysMemberAndCode(ent, DEFAULT_CODER_CODE);
		if (r.isPresent())
		{
			return r.get();
		}
		else
		{
			EntDictCoder newItem = new EntDictCoder();
			newItem.setApproved(true);

			newItem.setCode(DEFAULT_CODER_CODE);
			newItem.setComment("本规则为系统默认生成规则");
			newItem.setIsDeleted(false);
			newItem.setName("默认规则");
			newItem.setSysMember(ent);

			EntDictCoderDetail detail = new EntDictCoderDetail();
			detail.setSeq(1);
			detail.setName(CoderType.EntCode.getName());
			detail.setEntDictCoder(newItem);
			detail.setFieldSize(ent.getDisplayId().length());
			detail.setFieldValue(ent.getDisplayId());
			detail.setType(CoderType.EntCode.getId());

			newItem.getDetails().add(detail);

			detail = new EntDictCoderDetail();
			detail.setSeq(2);
			detail.setName(CoderType.ProductCode.getName());
			detail.setEntDictCoder(newItem);
			detail.setFieldSize(2);
			detail.setFieldValue("自动识别");
			detail.setType(CoderType.ProductCode.getId());

			newItem.getDetails().add(detail);

			detail = new EntDictCoderDetail();
			detail.setSeq(3);
			detail.setName(CoderType.SeqCode.getName());
			detail.setEntDictCoder(newItem);
			detail.setFieldSize(7);
			detail.setFieldValue("自动生成");
			detail.setType(CoderType.SeqCode.getId());

			newItem.getDetails().add(detail);

			detail = new EntDictCoderDetail();
			detail.setSeq(4);
			detail.setName(CoderType.CheckCode.getName());
			detail.setEntDictCoder(newItem);
			detail.setFieldSize(5);
			detail.setFieldValue("自动生成");
			detail.setType(CoderType.CheckCode.getId());

			newItem.getDetails().add(detail);

			detail = new EntDictCoderDetail();
			detail.setSeq(5);
			detail.setName(CoderType.DateCode.getName());
			detail.setEntDictCoder(newItem);
			detail.setFieldSize(8);
			detail.setFieldValue("YYYYMMDD");
			detail.setType(CoderType.DateCode.getId());

			newItem.getDetails().add(detail);

			newItem.setDescription(
					String.format("%s+%s+%s+%s+%s", CoderType.EntCode.getName(), CoderType.ProductCode.getName(),
							CoderType.SeqCode.getName(), CoderType.CheckCode.getName(), CoderType.DateCode.getName()));

			newItem = entDictCoderRepository.saveAndFlush(newItem);
		
			return newItem;
		}
	}

	public List<EntDictCategory> findEntDictCategoryList(SysMember ent)
	{
		return entDictCategoryRepository.findFirstLevel(ent.getId());
	}

	public List<EntDictCoder> findEntDictCoderList(SysMember ent)
	{
		return entDictCoderRepository.findByEntID(ent.getId());
	}

	public PagingModel<EntDictProduct> getProductListWithPagination(SysMember ent, int limit, int offset, String search,
			String sort, String order, Integer category)
	{
		List<String> whereCause = new ArrayList<String>();
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder("FROM EntDictProduct d WHERE d.isDeleted=false");
		whereCause.add("d.sysMember=:ent");
		paramaterMap.put("ent", ent);

		if (StringUtils.isNotEmpty(search))
		{
			whereCause.add(
					"(d.code LIKE CONCAT('%',:search,'%') OR d.name LIKE CONCAT('%',:search,'%')) OR d.spec LIKE CONCAT('%',:search,'%'))");
			paramaterMap.put("search", search);
		}
		if (category != null)
		{
			whereCause.add("d.entDictCategory.id=:category");
			paramaterMap.put("category", category.intValue());
		}

		if (whereCause.size() > 0)
		{
			sb.append(" AND " + StringUtils.join(whereCause, " and "));
		}

		if (StringUtils.isNotEmpty(sort))
		{
			sb.append(" ORDER BY d." + sort + " " + order);
		}

		TypedQuery<EntDictProduct> query = em.createQuery("SELECT d " + sb.toString(), EntDictProduct.class);
		EntityGraph<?> graph = em.getEntityGraph("EntDictProduct.entDictCategory-entDictCoder");
		query.setHint("javax.persistence.loadgraph", graph);
		Query query_c = em.createQuery("SELECT COUNT(d) " + sb.toString());
		for (String key : paramaterMap.keySet())
		{
			query.setParameter(key, paramaterMap.get(key));
			query_c.setParameter(key, paramaterMap.get(key));
		}
		PagingModel<EntDictProduct> paged = new PagingModel<>();
		paged.setRows(query.setFirstResult(offset).setMaxResults(limit).getResultList());
		paged.setTotal((long) query_c.getSingleResult());

		return paged;
	}

	public Optional<EntDictProduct> getProductById(SysMember ent, int id)
	{
		Optional<EntDictProduct> item = entDictProductRepository.findById(id);
		if (item.isPresent() && item.get().getSysMember().getId() == ent.getId())
		{
			return item;
		}
		else
		{
			return null;
		}
	}

	public EntDictProduct saveEntDictProductItem(SysMember ent, EntDictProduct item)
	{
		if (item.getId() > 0)
		{
			Optional<EntDictProduct> tmp = getProductById(ent, item.getId());
			if (!tmp.isPresent())
			{
				throw new ResourceNotFoundException("未找到产品");
			}
		}

		item.setSysMember(ent);
		if (item.getEntDictCategory() != null)
		{
			item.getEntDictCategory().setVersion(0L);
		}
		if (item.getEntDictCoder() != null)
		{
			item.getEntDictCoder().setVersion(0L);
		}
		List<?> lst = entDictProductRepository.findBySysMemberAndCodeAndIsDeletedAndIdNot(ent, item.getCode(), false,
				item.getId());

		if (lst.size() > 0)
		{
			throw new RuntimeException("编号重复");
		}

		return entDictProductRepository.save(item);
	}

	public void deleteEntDictProductItem(SysMember ent,int id)
	{
		Optional<EntDictProduct> tmp = getProductById(ent, id);
		if (!tmp.isPresent())
		{
			throw new ResourceNotFoundException("未找到产品");
		}
		
		try
		{
			entDictProductRepository.deleteById(id);
		}
		catch (Exception e)
		{
			Optional<EntDictProduct> fItem = entDictProductRepository.findById(id);
			if (fItem.isPresent())
			{
				fItem.get().setIsDeleted(true);
				entDictProductRepository.saveAndFlush(fItem.get());
			}
		}
	}
	
	public EntDictCoder findCoderByProduct(int productId)
	{
		Optional<EntDictProduct> optProduct = entDictProductRepository.findById(productId, EntityGraphs.named("EntDictProduct.entDictCategory-entDictCoder"));
		
		if (optProduct.isPresent())
		{
			int id=optProduct.get().getEntDictCoder().getId();
			return entDictCoderRepository.myFindById(id);
		}
		else 
		{
			return null;
		}
	}
}
