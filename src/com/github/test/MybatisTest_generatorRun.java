package com.github.test;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import com.github.generator.bean.Employee;
import com.github.generator.bean.EmployeeExample;
import com.github.generator.bean.EmployeeExample.Criteria;
import com.github.generator.dao.EmployeeMapper;


public class MybatisTest_generatorRun {

	@Test
	public void test() throws Exception {
		  List<String> warnings = new ArrayList<String>();
		   boolean overwrite = true;
		   File configFile = new File("mbg.xml");
		   ConfigurationParser cp = new ConfigurationParser(warnings);
		   Configuration config = cp.parseConfiguration(configFile);
		   DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		   MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		   myBatisGenerator.generate(null);
	}
	
	// test simple CRUD
	@Test
	public void test1() throws Exception {
		String resource = "config/mybatis-config-generator.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
			mapper.deleteByPrimaryKey(1);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}
	
	// test complex CRUD
		@Test
		public void test2() throws Exception {
			String resource = "config/mybatis-config-generator.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession sqlSession = sqlSessionFactory.openSession();

			try {
				EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
				EmployeeExample employeeExample = new EmployeeExample();
				Criteria criteria = employeeExample.createCriteria();
				criteria.andGenderEqualTo("1");
				criteria.andLastNameLike("%e%");
				List<Employee> list = mapper.selectByExample(employeeExample);
				for (Employee employee : list) {
					System.out.println(employee.getLastName());
				}
				sqlSession.commit();
			} finally {
				sqlSession.close();
			}
		}


}
