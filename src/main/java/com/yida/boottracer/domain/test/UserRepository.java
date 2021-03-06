package com.yida.boottracer.domain.test;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long>
{

	User findByName(String name);

	User findByNameAndAge(String name, Integer age);

	@Query("from User u where u.name=:name")
	User findUser(@Param("name") String name);

}
