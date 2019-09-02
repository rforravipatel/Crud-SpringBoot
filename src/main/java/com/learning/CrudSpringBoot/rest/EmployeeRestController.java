package com.learning.CrudSpringBoot.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.learning.CrudSpringBoot.entity.Employee;
import com.learning.CrudSpringBoot.exception.EmployeeNotfoundException;
import com.learning.CrudSpringBoot.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeService employeeService;

	private static Map<Integer, Employee> employeeMap = new HashMap<>();

	@Autowired
	private MessageSource messageSource;

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
//	@Scheduled(fixedDelay = 1000, initialDelay = 3000)
	public Resource<Employee> findById(@PathVariable int employeeId) {

		Employee thEmployee = employeeService.findById(employeeId);

		if (thEmployee == null) {
			throw new EmployeeNotfoundException("Employee id not found");
		}

//		all users, HATEOS, Send link back to User
		Resource<Employee> resource = new Resource<Employee>(thEmployee);

		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).findAll());

		resource.add(linkTo.withRel("all-employees"));

		return resource;
	}

	@PostMapping("/employees")
	public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee thEmployee) {

		thEmployee.setId(0);

		Employee employee = employeeService.save(thEmployee);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(employee.getId())
				.toUri();

		return ResponseEntity.created(location)
				.build();
	}

	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {

		List<Employee> employees = employeeService.findAll();
		for (Employee employee : employees) {
			employeeMap.put(employee.getId(), employee);
		}

		if (!employeeMap.containsKey(theEmployee.getId()))
			throw new EmployeeNotfoundException("Employee not found");

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

//	Demo I18N
	@GetMapping("/hello-I18N")
	public String helloI18N() {
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}

	// Dynamic filtering of object fields

//	@GetMapping("/employees/{employeeId}")
//	public MappingJacksonValue findById(@PathVariable int employeeId) {
//
//		Employee thEmployee = employeeService.findById(employeeId);
//
//		if (thEmployee == null) {
//			throw new EmployeeNotfoundException("Employee id not found");
//		}
//
//		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "firstName");
//
//		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("FilteredEmployee", filter);
//
//		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(thEmployee);
//
//		mappingJacksonValue.setFilters(filterProvider);
//
//		return mappingJacksonValue;
//	}
}
