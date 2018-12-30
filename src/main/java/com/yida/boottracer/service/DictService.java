package com.yida.boottracer.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Service;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphs;
import com.yida.boottracer.domain.DictCommon;
import com.yida.boottracer.domain.DictMemberPrice;
import com.yida.boottracer.domain.DictMemberType;
import com.yida.boottracer.domain.DictRegion;
import com.yida.boottracer.domain.PagingModel;
import com.yida.boottracer.enums.DictCommomType;
import com.yida.boottracer.repo.DictCommonRepository;
import com.yida.boottracer.repo.DictMemberTypeRepository;
import com.yida.boottracer.repo.DictRegionRepository;
import com.yida.boottracer.repo.SysMemberRepository;
import com.yida.infrastructure.MyUtils;

@Service
public class DictService
{
	private final EntityManager dictCommon_em;

	@Autowired
	private DictCommonRepository dictCommonRepository;
	@Autowired
	private DictMemberTypeRepository dictMemberTypeRepository;
	@Autowired
	private SysMemberRepository sysMemberRepository;
	@Autowired
	private DictRegionRepository dictRegionRepository;

	@Autowired
	public DictService(JpaContext context)
	{
		this.dictCommon_em = context.getEntityManagerByManagedType(DictCommon.class);
	}

	public List<DictCommon> getCommonListByType(DictCommomType type)
	{
		return dictCommonRepository.findByDictTypeAndIsDeleted(type.ordinal(),false,Sort.by("code"));
	}
	
	public List<DictRegion> getRegionListByParentId(int id)
	{
		Optional<DictRegion> p=dictRegionRepository.findById(id);
		if (p.isPresent())
		{
			return p.get().getChildren().stream().sorted(Comparator.comparing(DictRegion::getCode)).collect(Collectors.toList());
		}
		else 
		{
			return dictRegionRepository.findByParent(null,Sort.by("code"));
		}
		
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
			sb.append(" AND " + StringUtils.join(whereCause, " and "));
		}

		if (StringUtils.isNotEmpty(sort))
		{
			sb.append(" ORDER BY d." + sort + " " + order);
		}

		TypedQuery<DictCommon> query = dictCommon_em.createQuery("SELECT d " + sb.toString(), DictCommon.class);
		Query query_c = dictCommon_em.createQuery("SELECT COUNT(d) " + sb.toString());
		for (String key : paramaterMap.keySet())
		{
			query.setParameter(key, paramaterMap.get(key));
			query_c.setParameter(key, paramaterMap.get(key));
		}
		PagingModel<DictCommon> paged = new PagingModel<>();
		paged.setRows(query.setFirstResult(offset).setMaxResults(limit).getResultList());
		paged.setTotal((long) query_c.getSingleResult());

		return paged;
	}

	public void deleteCommonItem(int id)
	{
		try
		{
			dictCommonRepository.deleteById(id);
		}
		catch (Exception e)
		{
			Optional<DictCommon> fItem = dictCommonRepository.findById(id);
			if (fItem.isPresent())
			{
				fItem.get().setIsDeleted(true);
				dictCommonRepository.saveAndFlush(fItem.get());
			}
		}

	}

	public Optional<DictCommon> getCommonById(int id)
	{
		return dictCommonRepository.findById(id);
	}

	public DictCommon saveCommonItem(DictCommon item)
	{
		// 只做update不做select，提高性能的方式
		// Session session = dictCommon_em.unwrap( Session.class );
		// Transaction tx= session.beginTransaction();
		// if (item.getId()==0) {
		// session.persist(item);
		// }
		// else {
		// session.update(item);
		// }
		// session.flush();
		// tx.commit();
		// DictCommon newItem=item;

		// save执行的是persit或者merge，前者执行Insert,后者会先selectbyId，然后再执行update，效率较低，目前只找到上面的session的方式可以只做update(还需要用@Version配合才行，即乐观锁)
		DictCommon newItem = dictCommonRepository.save(item);

		return newItem;
	}

	public PagingModel<DictMemberType> getDictMemberTypeWithPagination(int limit, int offset, String search,
			String sort, String order)
	{

		DictMemberType p = new DictMemberType();
		p.setIsDeleted(false);
		// https://www.cnblogs.com/rulian/p/6533109.html
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues() // 设置默认忽略空值，避免查询search为空的情况
				.withMatcher("isDelete", GenericPropertyMatchers.exact());

		if (!StringUtils.isBlank(search))
		{
			p.setName(search);
			matcher = matcher.withMatcher("name", GenericPropertyMatchers.contains());
		}
		matcher = matcher.withIgnorePaths("freeCodeQty", "holdTime", "id", "isDefault", "version"); // 设置忽略不查询的的属性值

		Example<DictMemberType> example = Example.of(p, matcher);

		Direction direction = Direction.fromString(order);
		PageRequest pageInfo = PageRequest.of(offset, limit, direction, sort);

		Page<DictMemberType> lst = dictMemberTypeRepository.findAll(example, pageInfo,
				EntityGraphs.named("DictMemberType.dictMemberPrices"));

		PagingModel<DictMemberType> paged = new PagingModel<>();
		paged.setRows(lst.getContent());
		paged.setTotal(lst.getTotalElements());

		return paged;
	}

	public void deleteMemberTypeItem(int id)
	{
		int c = sysMemberRepository.myCountBydictCompanyType(id);
		if (c == 0)
		{
			dictMemberTypeRepository.deleteById(id);
		}
		else
		{
			Optional<DictMemberType> fItem = dictMemberTypeRepository.findById(id);
			if (fItem.isPresent())
			{
				fItem.get().setIsDeleted(true);
			}
		}

	}

	public DictMemberType saveMemberTypeItem(DictMemberType item)
	{

		item.getDictMemberPrices().removeIf(p -> p.isIsDeleted());

		List<DictMemberPrice> list  = item.getDictMemberPrices().stream().filter(p -> p.getType() == DictMemberPrice.PLATFORM).collect(Collectors.toList());
		
		if (list.size() > list.stream().filter(MyUtils.distinctByKey(p -> p.getQty())).count())
		{
			throw new RuntimeException("平台价格中月数有重复");
		}
		
		list  = item.getDictMemberPrices().stream().filter(p -> p.getType() == DictMemberPrice.BARCODE).collect(Collectors.toList());
		if (list.size() > list.stream().filter(MyUtils.distinctByKey(p -> p.getQty())).count())
		{
			throw new RuntimeException("码量价格中数量有重复");
		}
		
		DictMemberType newItem = dictMemberTypeRepository.save(item);

		return newItem;
	}
}
