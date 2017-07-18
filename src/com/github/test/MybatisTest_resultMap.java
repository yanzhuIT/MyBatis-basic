package com.github.test;

import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.github.bean.Employee;
import com.github.dao.EmployeeMapperPlus;

public class MybatisTest_resultMap {

	@Test
	public void getEmpById() throws IOException {
		String resource = "config/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			EmployeeMapperPlus mapper = sqlSession.getMapper(EmployeeMapperPlus.class);
			Employee employee = mapper.getEmpById(5);
			System.out.println(employee);
		} finally {
			sqlSession.close();
		}
	}
}
