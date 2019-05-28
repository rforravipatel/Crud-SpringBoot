package com.learning.CrudSpringBoot.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.learning.CrudSpringBoot.entity.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	private EntityManager entityManager;

	@Autowired
	public EmployeeDAOImpl(EntityManager thEntityManager) {
		entityManager = thEntityManager;
	}

	@Override
	public List<Employee> findAll() {

		Session currentSession = entityManager.unwrap(Session.class);

		Query<Employee> theQuery = currentSession.createQuery("from Employee", Employee.class);

		List<Employee> employees = theQuery.getResultList();

		return employees;
	}

	@Override
	public Employee findById(int theId) {

		Session currentSession = entityManager.unwrap(Session.class);

		Employee employee = currentSession.get(Employee.class, theId);

		return employee;
	}

	@Override
	public void save(Employee thEmployee) {

		entityManager.clear();
		Session currentSession = entityManager.unwrap(Session.class);

		currentSession.saveOrUpdate(thEmployee);

	}

	@Override
	public void deleteById(int theId) {

		Session currentSession = entityManager.unwrap(Session.class);

		Query<Employee> theQuery = currentSession.createQuery("delete from Employee where id=:employeeId");

		theQuery.setParameter("employeeId", theId);

		theQuery.executeUpdate();

	}

}
