package com.learning.CrudSpringBoot.dao;

import java.util.List;

import com.learning.CrudSpringBoot.entity.Employee;

public interface EmployeeDAO {

	public List<Employee> findAll();

	public Employee findById(int theId);

	public void save(Employee thEmployee);

	public void deleteById(int theId);
}
