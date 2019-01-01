package com.yida.boottracer.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.messaging.handler.invocation.ReactiveReturnValueHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.yida.boottracer.domain.DictCommon;
import com.yida.boottracer.domain.DictSystemFunction;
import com.yida.boottracer.domain.PagingModel;
import com.yida.boottracer.domain.SysMember;
import com.yida.boottracer.domain.SysUser;
import com.yida.boottracer.domain.test.User;
import com.yida.boottracer.domain.test.UserRepository;
import com.yida.boottracer.enums.SysMemberStatus;
import com.yida.boottracer.repo.DictSystemFunctionRepository;
import com.yida.boottracer.repo.SysMemberRepository;
import com.yida.web.exception.ResourceNotFoundException;

@Service
public class UserService
{
	private final EntityManager entityManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DictSystemFunctionRepository dictSystemFunctionRepository;

	@Autowired
	private SysMemberRepository sysMemberRepository;

	@Autowired
	public UserService(JpaContext context)
	{
		this.entityManager = context.getEntityManagerByManagedType(SysUser.class);
	}

	public Optional<User> getUser()
	{
		Long id = 1L;
		return userRepository.findById(id);
	}

	// RuntimeException的异常才会引发回滚
	@Transactional
	public void insertTwo()
	{
		User u = new User();
		u.setName("a");
		u.setAge(1);

		userRepository.save(u);

		u = new User();
		u.setName("b");
		u.setAge(2);

		userRepository.save(u);
	}

	public List<DictSystemFunction> getSystemMenu(String userName)
	{
		return dictSystemFunctionRepository.getMenuByUserName(userName);
	}

	public List<DictSystemFunction> getAllFunction(String userName)
	{
		return dictSystemFunctionRepository.getAllByUserName(userName);
	}

	public List<String> getAllUrlFunction(String userName)
	{
		return dictSystemFunctionRepository.getAllUrlByUserName(userName);
	}

	public SysMember getSysMemberByUser(Long id)
	{
		SysMember m = null;

		if (id != null)
		{
			Optional<SysMember> r = this.sysMemberRepository.findById(id);
			if (!r.isPresent())
			{
				throw new ResourceNotFoundException("未找到指定的企业信息");
			}
			else
			{
				m = r.get();
			}
		}
		else
		{
			throw new RuntimeException("id不能为空");
		}

		return m;
	}

	/**
	 * 注册企业会员
	 * 
	 * @param item
	 * @return
	 */
	public SysMember registryMember(SysMember item)
	{
		throw new NotImplementedException("注册企业会员");
	}

	/**
	 * 企业用户自行编辑企业信息
	 * 
	 * @param item
	 * @return
	 * @throws NotFoundException
	 */
	public SysMember editMember(SysMember item)
	{
		return editSysMemberInfo(item, false);
	}

	protected SysMember editSysMemberInfo(SysMember item, boolean forApprove)
	{
		Optional<SysMember> or = sysMemberRepository.findById(item.getId());
		if (!or.isPresent())
		{
			throw new ResourceNotFoundException(String.format("未找到ID为%d的企业会员信息", item.getId()));
		}
		SysMember result = or.get();

		item.getDictCompanyType().setVersion(0L);
		item.getDictIndustry().setVersion(0L);
		item.getDictMemberType().setVersion(0L);
		item.getDictRegion().setVersion(0L);

		result.setDictRegion(item.getDictRegion());
		result.setName(item.getName());
		result.setNameEn(item.getNameEn());
		result.setShortName(item.getShortName());
		result.setSocialCreditCode(item.getSocialCreditCode());
		result.setLegalPerson(item.getLegalPerson());
		result.setRequirement(item.getRequirement());
		result.setDictIndustry(item.getDictIndustry());
		result.setDictCompanyType(item.getDictCompanyType());
		result.setWebsite(item.getWebsite());
		result.setRegAddress(item.getRegAddress());
		result.setExpressAddress(item.getExpressAddress());
		result.setPostcode(item.getPostcode());
		result.setLinkman(item.getLinkman());
		result.setTel(item.getTel());
		result.setMobile(item.getMobile());
		result.setFax(item.getFax());
		result.setEmail(item.getEmail());
		result.setQq(item.getQq());
		result.setWebChat(item.getWebChat());

		if (forApprove)
		{
			result.setStatus(item.getStatus());
			
			switch (SysMemberStatus.fromId(item.getStatus()))
			{
			case Applying:
				result.getSysUsers().forEach(p->{
					p.setIsApproved(false);
				});
				break;
			case Refused:
				result.getSysUsers().forEach(p->{
					p.setIsApproved(false);
				});				
				break;
			case Approved:
				result.getSysUsers().forEach(p->{
					p.setIsApproved(true);
				});				
				break;
			case Disabled:
				result.getSysUsers().forEach(p->{
					p.setIsApproved(false);
				});
				break;
			default:
				break;

			}
			result.setComment(item.getComment());
		}
		result = sysMemberRepository.save(result);
		return result;
	}

	/**
	 * 管理端审核会员
	 * 
	 * @param item
	 * @return
	 * @throws NotFoundException
	 */
	public SysMember ApproveMember(SysMember item)
	{
		return editSysMemberInfo(item, true);
	}
	
	public void delete(long id)
	{
		sysMemberRepository.deleteById(id);
	}

	public PagingModel<SysMember> getListWithPagination(int limit, int offset, String search, String sort, String order,
			Integer status, Date start, Date end)
	{
		List<String> whereCause = new ArrayList<String>();
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder("FROM SysMember as d ");
		if (StringUtils.isNotEmpty(search))
		{
			whereCause.add(
					"(d.name LIKE CONCAT('%',:search,'%') OR d.legalPerson LIKE CONCAT('%',:search,'%') OR d.linkman LIKE CONCAT('%',:search,'%'))");
			paramaterMap.put("search", search);
		}
		if(start!=null && end !=null)
		{
			Calendar cal = Calendar.getInstance();
			cal.setTime(end);
			cal.add(Calendar.DATE, 1);
			end=cal.getTime();
			whereCause.add("(d.createdAt BETWEEN :start AND :end)");
			paramaterMap.put("start", start);
			paramaterMap.put("end", end);
		}
		if (status != null)
		{
			whereCause.add("d.status=:status");
			paramaterMap.put("status", status);
		}

		if (whereCause.size() > 0)
		{
			sb.append(" WHERE " + StringUtils.join(whereCause, " and "));
		}

		if (StringUtils.isNotEmpty(sort))
		{
			sb.append(" ORDER BY d." + sort + " " + order);
		}

		TypedQuery<SysMember> query = entityManager.createQuery("SELECT d " + sb.toString(), SysMember.class);
		Query query_c = entityManager.createQuery("SELECT COUNT(d) " + sb.toString());
		for (String key : paramaterMap.keySet())
		{
			query.setParameter(key, paramaterMap.get(key));
			query_c.setParameter(key, paramaterMap.get(key));
		}
		PagingModel<SysMember> paged = new PagingModel<>();

		List<SysMember> list = query
				.setHint("javax.persistence.loadgraph",
						entityManager
								.getEntityGraph("SysMember.dictCompanyType-dictIndustry-dictRegion-dictMemberType"))
				.setFirstResult(offset).setMaxResults(limit).getResultList();

		paged.setRows(list);
		paged.setTotal((long) query_c.getSingleResult());

		return paged;
	}
}
