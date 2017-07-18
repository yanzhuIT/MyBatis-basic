package com.github.bean;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;


//"Serializable" is for global cache
@Alias("emp") // alias for class name "Employee"
public class Employee implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String lastName;
	private String gender;
	private String email;
	Department department;
	
	private EmpStatus empStatus=EmpStatus.LOGIN;
	
	public EmpStatus getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(EmpStatus empStatus) {
		this.empStatus = empStatus;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	// want to use constructor with parameter, remember to add constructor
	// without parameter, avoiding some errors
	public Employee() {
		super();
	}
	
	

	public Employee(Integer id, String lastName, String gender, String email) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
	}

	public Employee(String lastName, String gender, String email) {
		super();
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", lastName=" + lastName + ", gender=" + gender + ", email=" + email + "]";
	}
	
	


	
   
}
