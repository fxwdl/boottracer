package com.yida.boottracer.domain.test;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//参考：https://gist.github.com/thjanssen/a751574e4f973debf8a4#file-order-java   https://thoughts-on-java.org/jpa-21-entity-graph-part-2-define/
public interface OrderRepository extends JpaRepository<Order, Long>
{

	Page<Order> findByOrderNumber(String orderNumber, Pageable pageable);

	@Query(value = "FROM Order o WHERE o.orderNumber=?1")
	Optional<Order> myFindByLazyloading(String orderNumber);

	// 通过JPQL（JPA的查询语法）的FETCH语法实现加载
	@Query(value = "SELECT o FROM Order o LEFT JOIN FETCH o.items it LEFT JOIN FETCH it.product WHERE o.orderNumber=:orderNumber")
	Order myFindByFetch(@Param("orderNumber") String orderNumber);

	// 通过attributePaths指定的加载路径，不必预先在Order上通过@NamedEntityGraph定义，也就是说所有的相关属性可以直接加载,更牛的是可以.来指定多级关系
	@EntityGraph(attributePaths = { "items", "items.product" }, type = EntityGraphType.FETCH)
	@Query(value = "SELECT o FROM Order o WHERE o.orderNumber=:orderNumber")
	Order myFindByAttributePaths(@Param("orderNumber") String orderNumber);

	// 通过已经命名好的EntityGraph来加载，名称可以按需求配置多个
	@EntityGraph(value = "graph.Order.items", type = EntityGraphType.FETCH)
	@Query(value = "SELECT o FROM Order o WHERE o.orderNumber=:orderNumber")
	Order myFindByNamedEntityGraph(@Param("orderNumber") String orderNumber);
}
