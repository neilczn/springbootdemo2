package com.equality.springbootdemo2;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemo2ApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	
	@Autowired
    private ApplicationContext ac;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Resource(name="twoJdbcTemplate")
    private JdbcTemplate towJdbcTemplate;
	
	@Test
	public void jdbcTest() {
		DataSource dataSource = (DataSource)ac.getBean("dataSource");
		JdbcTemplate jdbcTemplate2 = new JdbcTemplate(dataSource);
		String title = jdbcTemplate.queryForObject("select title from blog where id =0", String.class);
		System.out.println(title);
		String title2 = jdbcTemplate2.queryForObject("select title from blog where id =0", String.class);
		System.out.println(title2);
	}
	
	@Test
	public void multiJdbcTest() {
		DataSource dataSource = (DataSource)ac.getBean("twoDataSource");
		JdbcTemplate jdbcTemplate2 = new JdbcTemplate(dataSource);
		String title = jdbcTemplate2.queryForObject("select title from blog where id =0", String.class);
		System.out.println(title);	
		String name = towJdbcTemplate.queryForObject("select name from user_info where id =1", String.class);
		System.out.println(name);			
	}
	
	@Test
	public void jdbcUpdateTest() {		
		String title = jdbcTemplate.queryForObject("select title from blog where id =0", String.class);
		System.out.println(title);
		title +="1";
		int i = jdbcTemplate.update("update blog set title='"+title+"' where id =0");
		System.out.println(i);
	}
	
	/**
	 * 回滚
	 */
	@Test
	@Transactional
    @Rollback
	public void jdbcUpdateTest2() {		
		String title = jdbcTemplate.queryForObject("select title from blog where id =0", String.class);
		System.out.println(title);
		title +="1234";
		int i = jdbcTemplate.update("update blog set title='"+title+"' where id =0");
		System.out.println(i);
	}

}
