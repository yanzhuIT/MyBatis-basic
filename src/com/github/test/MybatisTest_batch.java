package com.github.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.github.bean.Employee;
import com.github.dao.EmployeeMapper;

public class MybatisTest_batch {

	@Test
	public void test() throws IOException {
		String resource = "config/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		// ExecutorType.BATCH is more efficient than normal loop
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		try {
			EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
			for (int i = 0; i < 10; i++) {
				mapper.addEmps(new Employee("abc", "1", "a@gmail.com"));
			}
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}
}
