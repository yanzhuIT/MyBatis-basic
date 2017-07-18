package com.github.test;


import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.github.bean.Employee;
import com.github.dao.EmployeeMapper;

public class MybatisTest_MultiplePara {

	@Test
	public void multiplePara() throws IOException {
		String resource = "config/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
			Employee employee = mapper.getEmpByIdAndLastName(4, "Hellen");
			System.out.println(employee);
		
		} finally {
			sqlSession.close();
		}
	}
	
	//  pass map as param 
	@Test
	public void mapPara() throws IOException {
		String resource = "config/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
			Map<String, Object> map = new HashMap<>();
			map.put("id", 4);
			map.put("lastName", "Hellen");
			// use ${} to receive param
			map.put("tableName", "tbl_employee");
			Employee employee = mapper.getEmpByMap(map);
			System.out.println(employee);
		
		} finally {
			sqlSession.close();
		}
	}

}
