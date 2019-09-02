package com.learning.CrudSpringBoot.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Employee information")
@Entity
@Table(name = "customer")
//@JsonIgnoreProperties(value = { "email" }) -------To ignore properties in response----------
//@JsonFilter("FilteredEmployee") // --------------To ignore properties uiong dynamic filtering--------
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ApiModelProperty(notes = "At least 2 character required.")
	@Size(min = 2, message = "At least 2 character required")
	@Column(name = "first_name")
	private String firstName;

	@ApiModelProperty(notes = "At least 2 character required.")
	@Size(min = 2, message = "At least 2 character required")
	@Column(name = "last_name")
	private String lastName;

//	@JsonIgnore      -----To ignore property in response----------
	@Column(name = "email")
	private String email;

	@ApiModelProperty(notes = "Birth date should be in the past.")
	@Past
	@Column(name = "DOB")
	private Date dateOfBirth;

	public Employee() {

	}

	public Employee(String firstName, String lastName, String email, Date dateOfBirth) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", DateOfBirth=" + dateOfBirth + "]";
	}

}
