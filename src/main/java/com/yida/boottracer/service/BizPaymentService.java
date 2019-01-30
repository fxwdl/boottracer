package com.yida.boottracer.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Service;

import com.yida.boottracer.domain.BizPayment;
import com.yida.boottracer.domain.EntDictProduct;
import com.yida.boottracer.domain.PagingModel;
import com.yida.boottracer.domain.SysMember;
import com.yida.boottracer.domain.SysUser;
import com.yida.boottracer.enums.ApproveType;
import com.yida.boottracer.enums.PayType;
import com.yida.boottracer.repo.BizPaymentRepository;
import com.yida.boottracer.repo.SysMemberRepository;
import com.yida.web.exception.ResourceNotFoundException;

@Service
public class BizPaymentService
{
	@PersistenceContext
	protected EntityManager em;

	@Autowired
	private BizPaymentRepository bizPaymentRepository;

	@Autowired
	private UserService userService;
	
	@Autowired
	private SysMemberRepository sysMemberRepository;

	private JpaContext jpaContext;

	@Autowired
	public BizPaymentService(JpaContext jpaContext)
	{
		this.jpaContext = jpaContext;
	}

	// 企业显示数据用
	public PagingModel<BizPayment> getListWithPagination(SysMember ent, int limit, int offset, String sort,
			String order)
	{
		return getListWithPagination(ent, limit, offset, sort, order, null, null, null);
	}
	
	//审批列表用
	public  PagingModel<BizPayment> getListWithPagination(int limit, int offset, String sort,
			String order, Integer payType, String entName, Integer approved)
	{
		return getListWithPagination(null,limit,offset,sort,order,payType,entName,approved);
	}
	
	protected PagingModel<BizPayment> getListWithPagination(SysMember ent, int limit, int offset, String sort,
			String order, Integer payType, String entName, Integer approved)
	{

		List<String> whereCause = new ArrayList<String>();
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder("FROM BizPayment d WHERE 1=1");
		if (ent != null)
		{
			whereCause.add("d.sysMember=:ent");
			paramaterMap.put("ent", ent);
		}
		if (payType != null)
		{
			whereCause.add("payType=:payType");
			paramaterMap.put("payType", payType);
		}
		if (!StringUtils.isBlank(entName))
		{
			whereCause.add("(d.sysMember.name LIKE CONCAT('%',:entName,'%'))");
			paramaterMap.put("entName", entName);
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

		TypedQuery<BizPayment> query = em.createQuery("SELECT d " + sb.toString(), BizPayment.class);
		EntityGraph<?> graph = em.getEntityGraph("BizPayment.sysMember.approveUser");
		query.setHint("javax.persistence.loadgraph", graph);
		Query query_c = em.createQuery("SELECT COUNT(d) " + sb.toString());
		for (String key : paramaterMap.keySet())
		{
			query.setParameter(key, paramaterMap.get(key));
			query_c.setParameter(key, paramaterMap.get(key));
		}
		PagingModel<BizPayment> paged = new PagingModel<>();
		paged.setRows(query.setFirstResult(offset).setMaxResults(limit).getResultList());
		paged.setTotal((long) query_c.getSingleResult());

		return paged;
	}

	public String getNewPayCode()
	{
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		String r = sdf.format(dt);
		Random random = new Random();
		for (int i = 0; i < 6; i++)
		{
			r += random.nextInt(10);
		}
		return r;
	}

	public BizPayment getNewItem(SysUser u)
	{
		BizPayment item = new BizPayment();
		if (u != null && u.getSysMember() != null && u.IsEnterpriseMember())
		{
			item.setSysMember(userService.getSysMemberByUser(u.getSysMember().getId()));
			item.getSysMember().getDictMemberType().getName();
			item.setPayCode(getNewPayCode());
			item.setPayDate(new Date());
			item.setPayType(PayType.Barcode.getId());
		}
		return item;
	}

	public BizPayment save(SysUser user, BizPayment item)
	{
		if (item == null)
		{
			throw new NullPointerException("参数不能为空");
		}
		if (item.getId() > 0)
		{
			if (item.getApproved() != ApproveType.UnApprove.getId())
			{
				throw new RuntimeException("当前数据状态为：" + ApproveType.fromId(item.getApproved()).getName() + ",不允许修改");
			}
		}

		if (user.IsEnterpriseMember())
		{
			if (user.getSysMember().getId() != item.getSysMember().getId())
			{
				throw new RuntimeException("非法请求");
			}
		}

		item = bizPaymentRepository.save(item);

		return item;
	}

	public void delete(SysUser user, Integer id)
	{
		Optional<BizPayment> r = bizPaymentRepository.findById(id);
		if (!r.isPresent())
		{
			throw new ResourceNotFoundException("未找到要删除的数据");
		}
		BizPayment item = r.get();
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

		bizPaymentRepository.deleteById(id);
	}
	
	public BizPayment findById(SysUser user, Integer id)
	{
		Optional<BizPayment> r = bizPaymentRepository.findById(id);
		if (!r.isPresent())
		{
			throw new ResourceNotFoundException("未找到数据");
		}
		BizPayment item = r.get();
		if (user.IsEnterpriseMember())
		{
			if (user.getSysMember().getId() != item.getSysMember().getId())
			{
				throw new RuntimeException("非法请求");
			}
		}
		return item;
	}
	
	@Transactional
	public BizPayment approve(SysUser user, BizPayment item)
	{
		if (item == null)
		{
			throw new NullPointerException("参数不能为空");
		}
		if (item.getId() ==0)
		{
			throw new RuntimeException("数据异常");
		}
		BizPayment dbItem=findById(user, item.getId());
		if(dbItem.getApproved()!=ApproveType.UnApprove.getId())
		{
			throw new RuntimeException("当前数据状态为：" + ApproveType.fromId(item.getApproved()).getName() + ",不允许审核");
		}
		
		item.setApproved(ApproveType.Approved.getId());
		item.setApproveUser(user);
		item.setAppproveTime(new Date());
		
		item = bizPaymentRepository.save(item);
		
		SysMember dbMember = sysMemberRepository.getOne(item.getSysMember().getId());
		
		switch(PayType.fromId(item.getPayType()))
		{
		case Barcode:
			dbMember.setBarcodeQty(dbMember.getBarcodeQty()+item.getPayQty());
			break;
		case AccountPeriod:
			Calendar ca = Calendar.getInstance();
			ca.setTime(dbMember.getEndDate());
			ca.add(Calendar.MONTH, item.getPayQty());			
			dbMember.setEndDate(ca.getTime());
			break;
		case AccountBalance:			
			dbMember.setAccountBalance(dbMember.getAccountBalance().add(item.getPayMoney()));
			break;
		default:
			throw new RuntimeException("未知的续费类型");
		}
		
		sysMemberRepository.save(dbMember);
		
		return item;
	}
}
