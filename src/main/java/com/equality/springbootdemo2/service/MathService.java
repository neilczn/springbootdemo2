package com.equality.springbootdemo2.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class MathService {

	/**
	 * 取反
	 * @param i
	 * @return
	 */
	@Cacheable("negation") //
	public int negation(int i) {
		System.out.println("input:" + i + ";output:"+(-i));
	    return -i;
	}
	
	/**
	 * 删除取反--手动触发，直接在radis是删除不了的
	 * @param i
	 * @return
	 */
	@CacheEvict(cacheNames = "negation")
	public String negationEvict(int id) {
		return "success";
	}
	
	/**
	 * 加1
	 * @param i
	 * @return
	 */
	@Cacheable("plus") //
	public int plus(int i) {
		System.out.println("input:" + i + ";output:"+(i+1));
	    return i+1;
	}
	
	/**
	 * 删除加1--手动触发，直接在radis是删除不了的
	 * @param i
	 * @return
	 */
	@CacheEvict(cacheNames = "plus")
	public String plusEvict(int id) {
		return "success";
	}
	
}
