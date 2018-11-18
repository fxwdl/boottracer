package com.yida.boottracer.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.*;

@Entity
@Table(name = "user")
public class User
{
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer age;

	public User()
	{

	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setAge(Integer age)
	{
		this.age = age;
	}

	public Integer getAge()
	{
		return this.age;
	}
}
