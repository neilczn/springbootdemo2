package com.equality.springbootdemo2.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.equality.springbootdemo2.entity.Blog;
import com.equality.springbootdemo2.service.BlogService;
import com.github.pagehelper.PageInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogServiceTest {

	@Autowired
	private BlogService blogService;

	@Test
	/*@Transactional
    @Rollback*/
	public void testAdd() {
		Blog blog = new Blog(16,"test16",7);
		blogService.add(blog);
	}

	@Test
	public void testFindById() {
		Blog blog = blogService.findById(1);
		System.out.println(blog);
	}
	
	@Test
	public void testFindById2() {
		//MockHttpServletRequest request = new MockHttpServletRequest();
		//request.addParameter("id", "10");
		//request.setAttribute("test", "10");
		Blog blog = blogService.findById(1);
		System.out.println(blog);
	}

	@Test
	public void testQuerylist() {
		/*List<Blog> blogs = blogService.querylist(2, 3);
		for (Blog blog : blogs) {
			System.out.println(blog);
		}*/		
		PageInfo<Blog> page = blogService.querylist(2, 3);
		System.out.println(page);
		List<Blog> blogs = page.getList();
		for (Blog blog : blogs) {
			System.out.println(blog);
		}
	}

}
