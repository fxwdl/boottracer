package com.yida.boottracer.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;

public abstract class BaseService
{
	@PersistenceContext
	protected EntityManager em;
	
	private JpaContext jpaContext;
	
	public BaseService(JpaContext jpaContext)
	{
		this.jpaContext = jpaContext;
	}
}
