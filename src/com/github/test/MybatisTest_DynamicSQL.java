package com.github.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.github.bean.Employee;
import com.github.dao.EmployeeMapperDynamicSQL;

public class MybatisTest_DynamicSQL {

	@Test
	public void test1() throws IOException {
		String resource = "config/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			EmployeeMapperDynamicSQL mapper = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);
			Employee employee = new Employee(1, "%m%", null, null);
			List<Employee> list = mapper.getEmpByIf(employee);
			for (Employee emp : list) {
				System.out.println(emp);
			}
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
			EmployeeMapperDynamicSQL mapper = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);
			Employee employee = new Employee(1, null, null, null);
			List<Employee> list = mapper.getEmpByTrim(employee);
			for (Employee emp : list) {
				System.out.println(emp);
			}
		} finally {
			sqlSession.close();
		}
	}
	
	@Test
	// choose label
	public void test3() throws IOException {
		String resource = "config/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			EmployeeMapperDynamicSQL mapper = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);
			Employee employee = new Employee(null, "%e%", null, null);
			List<Employee> list = mapper.getEmpByChoose(employee);
			for (Employee emp : list) {
				System.out.println(emp);
			}
		} finally {
			sqlSession.close();
		}
	}
	
	@Test
	// set label
	public void test4() throws IOException {
		String resource = "config/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			EmployeeMapperDynamicSQL mapper = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);
			Employee employee = new Employee(1, "tom", null, null);
			mapper.updateEmpBySetLabel(employee);
			// update needs commit
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}
	
	@Test
	// bind label
	public void test5() throws IOException {
		String resource = "config/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			EmployeeMapperDynamicSQL mapper = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);
			// Employee employee = new Employee();
			// employee.setLastName("e");
		    Employee employee = new Employee();
		    employee.setLastName("e");
			List<Employee> list = mapper.getEmpByIdByBind(employee);
			for (Employee employee2 : list) {
				System.out.println(employee2);
			}
		} finally {
			sqlSession.close();
		}
	}
}
