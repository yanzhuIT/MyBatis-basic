package com.github.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import com.github.bean.Employee;

public interface EmployeeMapper {

	
	// batch insert
	void addEmps(Employee employee);
	
	List<Employee> getEmps();
	
	// here key in Map is column name, value is field value in database
	Map<String, Object> getEmpByIdReturnMap(Integer id);

	// here key in Map is primary key of one employee, value is one Employee
	// object
	@MapKey("id")
	Map<Integer, Employee> getEmpByLastNameLikeReturnMap(String lastName);

	Employee getEmpById(Integer id);

	// here @Param specifies the param name for the key name in the mapper .xml
	Employee getEmpByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);

	// pass parameter which is a map
	Employee getEmpByMap(Map<String, Object> map);

	void addEmp(Employee employee);

	// the return value can be used here, if update occurs, boolean is true
	// Integer/Boolean/Long/void is OK
	boolean updateEmp(Employee employee);

	void deleteEmpById(Integer id);

	// "like" query, return a List
	List<Employee> getEmpsByLastNameLike(String lastName);
	
	void addEmpHasEnum(Employee employee);

}
