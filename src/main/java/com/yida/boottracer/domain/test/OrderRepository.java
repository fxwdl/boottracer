package com.yida.boottracer.domain.test;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long>
{

	Order findByLazyloading(String orderNumber);

	// 通过JPQL（JPA的查询语法）的FETCH语法实现加载
	@Query(value = "SELECT o FROM Order o LEFT JOIN FETCH o.items it LEFT JOIN FETCH it.product WHERE o.orderNumber=:orderNumber")
	Order findByFetch(@Param("orderNumber") String orderNumber);

	// 通过attributePaths指定的加载路径，不必预先在Order上通过@NamedEntityGraph定义，也就是说所有的相关属性可以直接加载,更牛的是可以.来指定多级关系
	@EntityGraph(attributePaths = { "items", "items.product" }, type = EntityGraphType.FETCH)
	@Query(value = "SELECT o FROM Order o WHERE o.orderNumber=:orderNumber")
	Order findByAttributePaths(@Param("orderNumber") String orderNumber);

	// 通过已经命名好的EntityGraph来加载，名称可以按需求配置多个
	@EntityGraph(value = "graph.Order.items", type = EntityGraphType.FETCH)
	@Query(value = "SELECT o FROM Order o WHERE o.orderNumber=:orderNumber")
	Order findByNamedEntityGraph(@Param("orderNumber") String orderNumber);
}
