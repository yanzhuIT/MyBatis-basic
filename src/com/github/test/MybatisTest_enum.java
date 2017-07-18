package com.github.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.github.bean.EmpStatus;
import com.github.bean.Employee;
import com.github.dao.EmployeeMapper;

public class MybatisTest_enum {

	// test enum class
	@Test
	public void Test() {
		EmpStatus empStatus = EmpStatus.LOGIN;
		System.out.println(empStatus.ordinal());
		System.out.println(empStatus.name());
		System.out.println("----------------------");
		System.out.println(empStatus.getCode());
		System.out.println(empStatus.getMsg());
	}

	// insert record with enum value
	@Test
	public void test2() throws IOException {
		String resource = "config/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
			Employee employee = new Employee("test_enum", "test_enum", "enum@gmail.com");
			mapper.addEmpHasEnum(employee);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}

	}

	// change defualt type handler for enum type, setting in mybatis-config.xml "typeHandler"
	@Test
	public void test3() throws IOException {
		String resource = "config/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
			Employee employee = new Employee("test_enum1", "test_enum1", "enum1@gmail.com");
			mapper.addEmpHasEnum(employee);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	// insert code for the enem using self-defined type handler in mybatis-config.xml "typeHandler"
	@Test
	public void test4() throws IOException {
		String resource = "config/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
			Employee employee = new Employee("test_enum2", "test_enum2", "enum2@gmail.com");
			mapper.addEmpHasEnum(employee);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}

	}

}
