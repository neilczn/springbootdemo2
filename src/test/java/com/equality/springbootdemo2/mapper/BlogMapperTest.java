package com.equality.springbootdemo2.mapper;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.equality.springbootdemo2.entity.Blog;
import com.equality.springbootdemo2.mapper.one.BlogMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

//注意这两部分
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogMapperTest {

	/**
	 * 注意测试类上的注解 @RunWith(SpringRunner.class) @SpringBootTest
	 * 注意需要在程序入口函数处@MapperScan,同时需要在属性文件中配置好对应的xml文件扫描路径
	 */
	@Autowired
	private BlogMapper blogMapper;
	
	@Before
	public void setUp() throws Exception {
		System.out.println(blogMapper);
	}

	@Test
	public void testFindById() {
		//fail("Not yet implemented");
		 Blog blog = blogMapper.findById(0);
		 System.out.println(blog);
	}

	@Test
	public void testQuerylist() {
		//fail("Not yet implemented");
		PageHelper.startPage(2, 3);
		List<Blog> list2 =  blogMapper.querylist();
		for (Blog blog : list2) {
			System.out.println(blog);
		}
		//用PageInfo对结果进行包装
		PageInfo page = new PageInfo(list2);
		System.out.println(page);
	}

	@Test
	@Transactional
    @Rollback
	public void testAdd() {
		//fail("Not yet implemented");
		Blog blog = new Blog(7,"test7",12);
		blogMapper.add(blog);
	}

}
