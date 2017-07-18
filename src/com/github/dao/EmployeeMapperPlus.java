package com.github.dao;

import java.util.List;

import com.github.bean.Employee;

public interface EmployeeMapperPlus {
	
	Employee getEmpById(Integer id);
	
	Employee getEmpAndDep(Integer id);
	
	Employee getEmpByIdStep(Integer id);
	
	List<Employee> getEmpsByDeptId(Integer deptId);
	
	Employee getEmpByIdDis(Integer id);

}
