package com.github.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.bean.Employee;

public interface EmployeeMapperDynamicSQL {
	
	List<Employee> getEmpByIf(Employee employee);
	
	List<Employee> getEmpByTrim(Employee employee);
	
	List<Employee> getEmpByChoose(Employee employee);
	
	void updateEmpBySetLabel(Employee employee);
	
	void addEmps(@Param("emps")List<Employee> employees);
	
	List<Employee> getEmpByIdByBind(Employee employee);

}
