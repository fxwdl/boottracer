package com.yida.boottracer.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yida.boottracer.domain.User;
import com.yida.boottracer.domain.UserRepository;

@Service
public class UserService
{
	@Autowired
	private UserRepository userRepository;

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
		if (true)
			throw new RuntimeException();
		userRepository.save(u);
	}
}
