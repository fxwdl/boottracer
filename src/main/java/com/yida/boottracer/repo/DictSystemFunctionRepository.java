package com.yida.boottracer.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yida.boottracer.domain.DictSystemFunction;

public interface DictSystemFunctionRepository extends JpaRepository<DictSystemFunction, String>
{
	@Query("SELECT DISTINCT f FROM DictSystemFunction f JOIN f.roles r JOIN r.users u WHERE u.userName=?1 ORDER BY f.seq ASC")
	List<DictSystemFunction> getByUserName(String userName);
}
