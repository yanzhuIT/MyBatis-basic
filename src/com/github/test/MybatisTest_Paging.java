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
import com.github.dao.EmployeeMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class MybatisTest_Paging {

	@Test
	public void test1() throws IOException {
		String resource = "config/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
			// first paging, then query
			Page<Object> page = PageHelper.startPage(1, 3);
			List<Employee> list = mapper.getEmps();
			// alternative method, using PageInfo....
			PageInfo<Employee> pageInfo = new PageInfo<>(list);
			for (Employee employee : list) {
				System.out.println(employee);
			}
			System.out.println("current page: " + page.getPageNum());
			System.out.println("total records: " + page.getTotal());
			System.out.println("total pages: " + page.getPages());
			System.out.println("records each page: " + page.getPageSize());
			System.out.println("-------------------------------------");
			System.out.println("if first page: " + pageInfo.isIsFirstPage());
			System.out.println("if has others: " + pageInfo.isHasNextPage());
		} finally {
			sqlSession.close();
		}
	}

}
