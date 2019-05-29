package com.learning.CrudSpringBoot.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.CrudSpringBoot.entity.Employee;
import com.learning.CrudSpringBoot.exception.EmployeeNotfoundException;
import com.learning.CrudSpringBoot.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeService employeeService;

	private static Map<Integer, Employee> employeeMap = new HashMap<>();

	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}

	@GetMapping("/employees")
	@CrossOrigin(origins = "http://localhost:8070")
	public List<Employee> findAll() {
		return employeeService.findAll();
	}

	@GetMapping("/employees/{employeeId}")
	public Employee findById(@PathVariable int employeeId) {

		Employee thEmployee = employeeService.findById(employeeId);

		if (thEmployee == null) {
			throw new RuntimeException("Employee id not found");
		}

		return thEmployee;
	}

	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee thEmployee) {

		thEmployee.setId(0);

		employeeService.save(thEmployee);

		return thEmployee;
	}

	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {

		List<Employee> employees = employeeService.findAll();
		for (Employee employee : employees) {
			employeeMap.put(employee.getId(), employee);
		}

		if (!employeeMap.containsKey(theEmployee.getId()))
			throw new EmployeeNotfoundException();

		employeeService.save(theEmployee);

		return theEmployee;
	}

	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {

		Employee thEmployee = employeeService.findById(employeeId);

		if (thEmployee == null) {
			return "Employee doesn't exist in database";
		}

		employeeService.deleteById(employeeId);

		return "Deleted employee id - " + employeeId;
	}
}
