package com.github.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.github.bean.Employee;
import com.github.dao.EmployeeMapper;
import com.github.dao.EmployeeMapperAnnotation;

public class MybatisTest_basic {

	
	// old method, not being recommended
	@Test
	public void test() throws IOException {
		String resource = "config/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			Employee employee = sqlSession.selectOne("com.alec.mybatis.EmployeeMapper.selectEmp", "1");
			System.out.println(employee);
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
			EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
			Employee employee = mapper.getEmpById(1);
			System.out.println(employee);
		} finally {
			sqlSession.close();
		}

	}
	
	// using annotation, no mapper xml
	@Test
	public void test3() throws IOException {
		String resource = "config/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			// get proxy class for implementation
			EmployeeMapperAnnotation mapper = sqlSession.getMapper(EmployeeMapperAnnotation.class);
			Employee employee = mapper.getEmpById(1);
			System.out.println(employee);
		} finally {
			sqlSession.close();
		}

	}
	// add, update, delete
	@Test
	public void test4() throws IOException {
		String resource = "config/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// this openSession is auto commit
		// sqlSessionFactory.openSession(true);

		try {
			EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
			Employee employee = new Employee(null, "Candy", "0", "candy@gmail.com");
			mapper.addEmp(employee);
			
			
			// Employee employee = new Employee(1, "tom", "1", "tom@gmail.com");
			// boolean update = mapper.updateEmp(employee);
			
			// mapper.deleteEmpById(2);
			System.out.println(employee.getId());
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}

	}

}
