package com.yida.boottracer.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;

import com.yida.boottracer.repo.BizPaymentRepository;

public class BizPaymentService
{
	@PersistenceContext
	protected EntityManager em;

	@Autowired
	private BizPaymentRepository bizPaymentRepository;

	private JpaContext jpaContext;

	@Autowired
	public BizPaymentService(JpaContext jpaContext)
	{
		this.jpaContext = jpaContext;
	}
}
