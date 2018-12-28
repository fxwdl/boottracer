package com.yida.boottracer.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yida.boottracer.domain.DictSystemFunction;

public interface DictSystemFunctionRepository extends JpaRepository<DictSystemFunction, String>
{
	@Query("SELECT DISTINCT f FROM DictSystemFunction f JOIN f.roles r JOIN r.users u WHERE u.userName=?1 AND f.funType IN (0,1) ORDER BY f.seq ASC")
	List<DictSystemFunction> getMenuByUserName(String userName);
	
	@Query("SELECT DISTINCT f FROM DictSystemFunction f JOIN f.roles r JOIN r.users u WHERE u.userName=?1 ORDER BY f.seq ASC")
	List<DictSystemFunction> getAllByUserName(String userName);
	
	@Query("SELECT DISTINCT f.page FROM DictSystemFunction f JOIN f.roles r JOIN r.users u WHERE u.userName=?1 ORDER BY f.seq ASC")
	List<String> getAllUrlByUserName(String userName);
}
