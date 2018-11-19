package com.yida.boottracer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yida.boottracer.domain.User;
import com.yida.boottracer.domain.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoottracerApplicationTests
{

	@Autowired
	private UserRepository userRepository;

	@Test
	public void contextLoads()
	{

	}

	@Test
	public void testFirst()
	{
		User u = new User();
		u.setName("a");
		u.setAge(1);

		User nu = userRepository.save(u);

		assertEquals(1l, (long) nu.getId());

		long l = userRepository.count();

		assertEquals(1l, l);
	}
}
