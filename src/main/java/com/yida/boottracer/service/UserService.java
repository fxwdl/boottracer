package com.yida.boottracer.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.invocation.ReactiveReturnValueHandler;
import org.springframework.stereotype.Service;

import com.yida.boottracer.domain.DictSystemFunction;
import com.yida.boottracer.domain.SysMember;
import com.yida.boottracer.domain.SysUser;
import com.yida.boottracer.domain.test.User;
import com.yida.boottracer.domain.test.UserRepository;
import com.yida.boottracer.repo.DictSystemFunctionRepository;
import com.yida.boottracer.repo.SysMemberRepository;
import com.yida.web.exception.ResourceNotFoundException;

@Service
public class UserService
{
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DictSystemFunctionRepository dictSystemFunctionRepository;

	@Autowired
	private SysMemberRepository sysMemberRepository;

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

	//此方法应该试图只读到菜单一级
	public List<DictSystemFunction> GetSystemMenu(String userName)
	{
		return dictSystemFunctionRepository.getMenuByUserName(userName);
	}
	
	public List<DictSystemFunction> GetAllFunction(String userName)
	{
		return dictSystemFunctionRepository.getAllByUserName(userName);
	}	
	
	public List<String> GetAllUrlFunction(String userName)
	{
		return dictSystemFunctionRepository.getAllUrlByUserName(userName);
	}		
	
	public SysMember getSysMemberByUser(Integer id)
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
}
