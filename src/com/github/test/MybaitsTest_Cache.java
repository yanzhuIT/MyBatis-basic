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

public class MybaitsTest_Cache {
	
	
	// first level cache is for sqlSession level, different sqlSession has their own first level cache
	// actually, sqlSession cache is a Map data
	// first level cache in one sqlSession will oes not work if update, delete, insert action happen
	@Test
	public void testFirstCache() throws IOException{
		String resource = "config/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			
			EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
			Employee employee = mapper.getEmpById(6);
			System.out.println(employee);
			
			// test first cache, the result shows just one sql, just one object in cache
			Employee employee2 = mapper.getEmpById(6);
			System.out.println(employee2);
			System.out.println(employee == employee2);
			
			// clear cache
			sqlSession.clearCache();

		} finally {
			sqlSession.close();
		}
		
	}
	
	
	// test global cache
	@Test
	public void testSecondCache() throws IOException{
		String resource = "config/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		SqlSession sqlSession2 = sqlSessionFactory.openSession();
		try {
			
			EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
			EmployeeMapper mapper2 = sqlSession2.getMapper(EmployeeMapper.class);
			
			Employee employee = mapper.getEmpById(6);
			System.out.println(employee);
			// sqlSession.close();
			
			Employee employee2 = mapper2.getEmpById(6);
			System.out.println(employee2);
			System.out.println(employee == employee2);
			// after sqlSession closed or committed, the data from first-level cache to second-level
			sqlSession.close();
			sqlSession2.close();
			
			// clear cache, just for first-level cache, no relation with second-level cache
			sqlSession.clearCache();

		} finally {
		}
		
	}

}
