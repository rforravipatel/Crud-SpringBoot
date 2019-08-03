package com.learning.CrudSpringBoot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.CrudSpringBoot.dao.EmployeeDAO;
import com.learning.CrudSpringBoot.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDAO employeeDao;

	@Autowired
	public EmployeeServiceImpl(EmployeeDAO theEmployeeDAO) {
		employeeDao = theEmployeeDAO;
	}

	@Override
	@Transactional
	public List<Employee> findAll() {

		return employeeDao.findAll();
	}

	@Override
	@Transactional
	public Employee findById(int theId) {

		return employeeDao.findById(theId);
	}

	@Override
	@Transactional
	public Employee save(Employee theEmployee) {

		employeeDao.save(theEmployee);

		return theEmployee;
	}

	@Override
	@Transactional
	public void deleteById(int theId) {

		employeeDao.deleteById(theId);
	}

}
