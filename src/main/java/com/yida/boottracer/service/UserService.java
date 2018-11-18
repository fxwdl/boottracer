package com.yida.boottracer.service;

import java.util.Optional;

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
}
