package com.github.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.github.bean.Department;
import com.github.bean.Employee;
import com.github.dao.DepartmentMapper;
import com.github.dao.EmployeeMapperPlus;

public class MybatisTest_cascadeQuery {

	@Test
	public void test() throws IOException {
		String resource = "config/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			// get proxy class for implementation
			EmployeeMapperPlus mapper = sqlSession.getMapper(EmployeeMapperPlus.class);
			Employee employee = mapper.getEmpAndDep(5);
			System.out.println(employee);
			System.out.println(employee.getDepartment());
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void test2() throws IOException {
		String resource = "config/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			// get proxy class for implementation
			EmployeeMapperPlus mapper = sqlSession.getMapper(EmployeeMapperPlus.class);
			Employee employee = mapper.getEmpByIdStep(5);
			System.out.println(employee.getLastName());
			// using lazy loading
			// System.out.println(employee.getDepartment());
		} finally {
			sqlSession.close();
		}
	}

	@Test
	// cascade query: one to many
	public void test3() throws IOException {
		String resource = "config/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			// get proxy class for implementation
			DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
			Department department = mapper.getDepByIdPlus(1);
			System.out.println(department);
			System.out.println(department.getList());
		} finally {
			sqlSession.close();
		}
	}
	
	@Test
	// cascade query step by step: one to many
	public void test4() throws IOException {
		String resource = "config/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			// get proxy class for implementation
			DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
			Department department = mapper.getDepByIdStep(1);
			System.out.println(department);
			System.out.println(department.getList());
		} finally {
			sqlSession.close();
		}
	}
	
	@Test
	// using discriminator
	public void test5() throws IOException {
		String resource = "config/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			// get proxy class for implementation
			EmployeeMapperPlus mapper = sqlSession.getMapper(EmployeeMapperPlus.class);
			Employee employee = mapper.getEmpByIdDis(1);
			System.out.println(employee);
			System.out.println(employee.getDepartment());
		} finally {
			sqlSession.close();
		}
	}


}
