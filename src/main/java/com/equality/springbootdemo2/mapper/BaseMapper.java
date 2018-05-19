/**
 * 
 */
package com.equality.springbootdemo2.mapper;

import java.util.List;

import com.equality.springbootdemo2.entity.BaseEntity;



/**
 * @author asus
 *
 */
public interface BaseMapper<T extends BaseEntity> {

	public T findById(Integer id);
	
	public List<T> querylist();

	public void add(T t);
	
}
