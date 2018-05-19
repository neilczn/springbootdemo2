package com.equality.springbootdemo2.service;



import org.springframework.transaction.annotation.Transactional;

import com.equality.springbootdemo2.entity.two.UserInfo;
import com.github.pagehelper.PageInfo;

public interface UserInfoService {
	
	@Transactional(value="twoTransactionManager")
	void add(UserInfo userInfo);
	
	UserInfo findById(Integer id);
	
	PageInfo<UserInfo> querylist(Integer pageNum,Integer pageSize);

}
