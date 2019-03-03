package com.yida.boottracer.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.management.RuntimeErrorException;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphs;
import com.jayway.jsonpath.Option;
import com.yida.boottracer.domain.BizCode;
import com.yida.boottracer.domain.BizCodeApply;
import com.yida.boottracer.domain.BizPayment;
import com.yida.boottracer.domain.EntDictProduct;
import com.yida.boottracer.domain.PagingModel;
import com.yida.boottracer.domain.SysMember;
import com.yida.boottracer.domain.SysUser;
import com.yida.boottracer.enums.ApproveType;
import com.yida.boottracer.enums.PayType;
import com.yida.boottracer.repo.BizCodeApplyRepository;
import com.yida.web.exception.ResourceNotFoundException;

@Service
public class BizCodeService
{
	@PersistenceContext
	protected EntityManager em;

	private JpaContext jpaContext;

	@Autowired
	private BizCodeApplyRepository bizCodeApplyRepository;

	@Autowired
	private EntDictService entDictService;

	@Autowired
	public BizCodeService(JpaContext jpaContext)
	{
		this.jpaContext = jpaContext;
	}

	public PagingModel<BizCodeApply> getListWithPagination(SysMember ent, int limit, int offset, String sort,
			String order, String productName, String batchCode, Date start, Date end, Integer approved)
	{
		List<String> whereCause = new ArrayList<String>();
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder("FROM BizCodeApply d WHERE 1=1");
		if (ent != null)
		{
			whereCause.add("d.sysMember=:ent");
			paramaterMap.put("ent", ent);
		}
		if (!StringUtils.isBlank(productName))
		{
			whereCause.add("(d.entDictProduct.name LIKE CONCAT('%',:productName,'%'))");
			paramaterMap.put("productName", productName);
		}
		if (!StringUtils.isBlank(batchCode))
		{
			whereCause.add("(d.batchCode LIKE CONCAT('%',:batchCode,'%'))");
			paramaterMap.put("batchCode", batchCode);
		}
		if (start != null && end != null)
		{
			Calendar cal = Calendar.getInstance();
			cal.setTime(end);
			cal.add(Calendar.DATE, 1);
			end = cal.getTime();
			whereCause.add("(d.createdAt BETWEEN :start AND :end)");
			paramaterMap.put("start", start);
			paramaterMap.put("end", end);
		}

		if (approved != null)
		{
			whereCause.add("approved=:approved");
			paramaterMap.put("approved", approved);
		}

		if (whereCause.size() > 0)
		{
			sb.append(" AND " + StringUtils.join(whereCause, " and "));
		}

		if (StringUtils.isNotEmpty(sort))
		{
			sb.append(" ORDER BY d." + sort + " " + order);
		}

		TypedQuery<BizCodeApply> query = em.createQuery("SELECT d " + sb.toString(), BizCodeApply.class);
		EntityGraph<?> graph = em.getEntityGraph("BizCodeApply|sysMember|entDictProduct");
		query.setHint("javax.persistence.loadgraph", graph);
		Query query_c = em.createQuery("SELECT COUNT(d) " + sb.toString());
		for (String key : paramaterMap.keySet())
		{
			query.setParameter(key, paramaterMap.get(key));
			query_c.setParameter(key, paramaterMap.get(key));
		}
		PagingModel<BizCodeApply> paged = new PagingModel<>();
		paged.setRows(query.setFirstResult(offset).setMaxResults(limit).getResultList());
		paged.setTotal((long) query_c.getSingleResult());

		return paged;
	}

	public Optional<BizCodeApply> getById(SysMember ent, int id)
	{
		Optional<BizCodeApply> item = bizCodeApplyRepository.findById(id,
				EntityGraphs.named("BizCodeApply|sysMember|entDictProduct"));
		if (item.isPresent() && item.get().getSysMember().getId() == ent.getId())
		{
			return item;
		}
		else
		{
			return null;
		}
	}

	public BizCodeApply newBizCodeApp(SysUser u)
	{
		BizCodeApply item = new BizCodeApply();
		if (u != null && u.getSysMember() != null && u.IsEnterpriseMember())
		{
			item.setApplyQty(0);
			item.setId(0);
			item.setVersion(0L);
			item.setEntDictProduct(new EntDictProduct());
			item.setSysMember(u.getSysMember());
			item.setApproved(ApproveType.UnApprove.getId());
		}
		return item;
	}

	public BizCodeApply save(SysMember ent, BizCodeApply item)
	{
		if (item == null)
		{
			throw new NullPointerException("参数不能为空");
		}
		BizCodeApply nItem = null;
		if (item.getId() > 0)
		{
			Optional<BizCodeApply> oItem = bizCodeApplyRepository.findById(item.getId());
			if (!oItem.isPresent())
			{
				throw new RuntimeException("未找到数据");
			}
			nItem = oItem.get();
			if (nItem.getApproved() != ApproveType.UnApprove.getId())
			{
				throw new RuntimeException("当前数据状态为：" + ApproveType.fromId(nItem.getApproved()).getName() + ",不允许保存");
			}

			if (ent.getId() != nItem.getSysMember().getId())
			{
				throw new RuntimeException("非法请求");
			}

			nItem.setBatchCode(item.getBatchCode());
			nItem.setApplyQty(item.getApplyQty());
			nItem.setComment(item.getComment());
		}
		else
		{
			nItem = item;
			nItem.setActivatedQty(0);
			nItem.setApproved(ApproveType.UnApprove.getId());
			nItem.setDeliveredQty(0);
			nItem.setDownloaded(false);
			nItem.setSysMember(ent);
		}

		Optional<EntDictProduct> product = entDictService.getProductById(ent, item.getEntDictProduct().getId());
		if (!product.isPresent())
		{
			throw new RuntimeException("未找到产品");
		}

		nItem.setEntDictProduct(product.get());

		nItem = bizCodeApplyRepository.save(nItem);

		return nItem;
	}

	public void delete(SysUser user, Integer id)
	{
		Optional<BizCodeApply> r = bizCodeApplyRepository.findById(id);
		if (!r.isPresent())
		{
			throw new ResourceNotFoundException("未找到要删除的数据");
		}
		BizCodeApply item = r.get();
		if (user.IsEnterpriseMember())
		{
			if (item.getApproved() != ApproveType.UnApprove.getId())
			{
				throw new RuntimeException("当前数据状态为：" + ApproveType.fromId(item.getApproved()).getName() + ",不允许删除");
			}

			if (user.getSysMember().getId() != item.getSysMember().getId())
			{
				throw new RuntimeException("非法请求");
			}
		}

		bizCodeApplyRepository.deleteById(id);
	}
}
