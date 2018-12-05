package com.yida.boottracer.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yida.boottracer.domain.DictSystemFunction;
import com.yida.boottracer.domain.test.User;
import com.yida.boottracer.domain.test.UserRepository;
import com.yida.boottracer.repo.DictSystemFunctionRepository;

@Service
public class UserService
{
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DictSystemFunctionRepository dictSystemFunctionRepository;

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

	public List<DictSystemFunction> GetSystemMenu(String userName)
	{
		return dictSystemFunctionRepository.getByUserName(userName);
	}
}
