package com.yida.boottracer.repo.impl.mybatis.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.yida.boottracer.domain.BizCode;

@Component
public interface BizCodeMapper {
    int deleteByPrimaryKey(Integer ID,@Param("tablename") String tablename);

    int insert(BizCode record,@Param("tablename") String tablename);

    int insertSelective(BizCode record,@Param("tablename") String tablename);

    BizCode selectByPrimaryKey(Integer ID,@Param("tablename") String tablename);
    
    //BizCode selectByPrimaryKey(Map<String, Object> params);
    
    
    int updateByPrimaryKeySelective(BizCode record,@Param("tablename") String tablename);

    int updateByPrimaryKey(BizCode record,@Param("tablename") String tablename);
        
    int existTable(@Param("tableName") String tableName);
    
    int dropTable(@Param("tableName") String tableName);
    
    int createCodeTable(@Param("tableName") String tableName);
}