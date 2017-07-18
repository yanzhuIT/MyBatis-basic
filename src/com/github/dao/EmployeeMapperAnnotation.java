package com.github.dao;

import org.apache.ibatis.annotations.Select;

import com.github.bean.Employee;

public interface EmployeeMapperAnnotation {
	@Select("select * from tbl_employee where id=#{id}")
	Employee getEmpById(Integer id);

}
