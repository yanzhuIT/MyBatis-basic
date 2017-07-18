package com.github.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.github.bean.Employee;
import com.github.dao.EmployeeMapper;

public class MybatisTest_queryAdvance {

	// like query, return a collection "List"
	@Test
	public void likeQuery() throws IOException {
		String resource = "config/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
			List<Employee> employees = mapper.getEmpsByLastNameLike("%a%");
			for (Employee employee : employees) {
				System.out.println(employee);
			}
		} finally {
			sqlSession.close();
		}
	}

	
	// return a collection "Map"
		@Test
		public void returnOneRecordByMap() throws IOException {
			String resource = "config/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession sqlSession = sqlSessionFactory.openSession();
			try {
				EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
				Map<String, Object> map = mapper.getEmpByIdReturnMap(5);
				System.out.println(map);
			} finally {
				sqlSession.close();
			}
		}
		
		// return a collection "Map", and this map has one object as value
		@Test
		public void getEmpByLastNameLikeReturnMap() throws IOException {
			String resource = "config/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession sqlSession = sqlSessionFactory.openSession();
			try {
				EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
				Map<Integer, Employee> map = mapper.getEmpByLastNameLikeReturnMap("%a%");
				System.out.println(map);
			} finally {
				sqlSession.close();
			}
		}


}
