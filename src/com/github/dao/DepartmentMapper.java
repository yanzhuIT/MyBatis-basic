package com.github.dao;

import com.github.bean.Department;

public interface DepartmentMapper {
	
	Department getDepById(Integer id);
	
	// get all employee related to one department
	Department getDepByIdPlus(Integer id);
	
	// using "step by step" method
	Department getDepByIdStep(Integer id);

}
