package com.equality.springbootdemo2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.equality.springbootdemo2.entity.Blog;
import com.equality.springbootdemo2.service.BlogService;
import com.github.pagehelper.PageInfo;


@RestController
@RequestMapping("/blog")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping("/findById")
	public Blog findById(@RequestParam Integer id){
		Blog blog = blogService.findById(id);
		return blog;
	}
	
	@RequestMapping("/querylist")
	public PageInfo<Blog> querylist(@RequestParam(defaultValue="1") Integer pageNum,@RequestParam(defaultValue="10") Integer pageSize){
		PageInfo<Blog> blogs = blogService.querylist(pageNum, pageSize);
		return blogs;
	}
	
	@RequestMapping("/add")
	public Blog add(@RequestBody Blog blog){
		blogService.add(blog);
		return blog;
	}

}
