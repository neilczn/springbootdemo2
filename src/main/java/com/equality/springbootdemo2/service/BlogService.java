/**
 * 
 */
package com.equality.springbootdemo2.service;

import java.util.List;

import com.equality.springbootdemo2.entity.Blog;
import com.github.pagehelper.PageInfo;



/**
 * @author asus
 *
 */
public interface BlogService {

  void add(Blog blog);
  
  Blog findById(Integer id);
  
  PageInfo<Blog> querylist(Integer pageNum,Integer pageSize);
  
}
