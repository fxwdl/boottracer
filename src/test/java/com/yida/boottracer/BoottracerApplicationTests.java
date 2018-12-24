package com.yida.boottracer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.yida.boottracer.domain.test.Order;
import com.yida.boottracer.domain.test.OrderItem;
import com.yida.boottracer.domain.test.OrderRepository;
import com.yida.boottracer.domain.test.Product;
import com.yida.boottracer.domain.test.ProductRepository;
import com.yida.boottracer.domain.test.User;
import com.yida.boottracer.domain.test.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest

public class BoottracerApplicationTests
{
	private static final Logger log = LoggerFactory.getLogger(BoottracerApplicationTests.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductRepository productRepository;

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

	@Test
	public void testInsertOrder()
	{
		// 因为配置了级联，所以可以只处理order，而不需要处理orderitem，但是这里的deleteAll是使用的n+1，即先查出来再删，性能差
		// orderItemRepository.deleteAll();
		orderRepository.deleteAll();
		productRepository.deleteAll();

		Product p1 = new Product();
		p1.setName("电脑");

		Product np1 = productRepository.save(p1);
		Product p2 = new Product();
		p2.setName("办公桌");
		Product np2 = productRepository.save(p2);

		Order order = new Order();
		order.setOrderNumber("2018-001");

		OrderItem i1 = new OrderItem();
		i1.setOrder(order);
		i1.setQuantity(10);
		i1.setProduct(np1);

		OrderItem i2 = new OrderItem();
		i2.setOrder(order);
		i2.setQuantity(5);
		i2.setProduct(np2);

		order.getItems().add(i1);
		order.getItems().add(i2);

		orderRepository.save(order);

		// List<OrderItem> ls = orderItemRepository.saveAll(order.getItems());
		// order.getItems().clear();
		// order.getItems().addAll(ls);

		for (OrderItem n : order.getItems())
		{
			assertTrue(n.getId() != 0);
		}
	}

	@Test
	@Transactional // 需要加上事务才可以进行Lazy loading
	public void testFindOrder_Lazyloading()
	{
		Optional<Order> order = orderRepository.myFindByLazyloading("2018-001");
		order.map(o ->
		{
			for (OrderItem oi : o.getItems())
			{
				assertNotNull(oi);
				assertNotNull(oi.getProduct().getName());

			}
			return o;
		}).orElse(null);

		log.info("查询完成");
	}

	@Test
	@Transactional
	public void testFindOrder_AttributePaths()
	{
		Order order = orderRepository.myFindByAttributePaths("2018-001");
		assertNotNull(order);

		for (OrderItem oi : order.getItems())
		{
			assertNotNull(oi);
			assertNotNull(oi.getProduct().getName());
		}
	}

	@Test
	@Transactional
	public void testFindOrder_NamedEntityGraph()
	{
		Order order = orderRepository.myFindByNamedEntityGraph("2018-001");
		assertNotNull(order);

		for (OrderItem oi : order.getItems())
		{
			assertNotNull(oi);
			assertNotNull(oi.getProduct().getName());
		}
	}

	@Test
	@Transactional
	public void testFindOrder_Fetch()
	{
		Order order = orderRepository.myFindByFetch("2018-001");
		assertNotNull(order);

		for (OrderItem oi : order.getItems())
		{
			assertNotNull(oi);
			assertNotNull(oi.getProduct().getName());
		}
	}

	@Test
	@Transactional // 需要加上事务才可以进行Lazy loading
	public void testFindOrder_Paged()
	{

		log.info("查询完成");
	}
	
	@Test
	public void TestPassword() {
		BCryptPasswordEncoder b=new BCryptPasswordEncoder();
		String pString=b.encode("1");
		log.info(pString);
	}
}
