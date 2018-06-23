package com.equality.springbootdemo2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equality.springbootdemo2.entity.two.UserInfo;
import com.equality.springbootdemo2.mapper.two.UserInfoMapper;
import com.equality.springbootdemo2.service.UserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserInfoServiceImpl implements UserInfoService {
	
	@Autowired
    protected UserInfoMapper userInfoMapper;

	@Override
	public void add(UserInfo userInfo) {
		userInfoMapper.add(userInfo);
		//测试事务
		/*int i = 0;
		int j = 10/i;*/
	}

	@Override
	public UserInfo findById(Integer id) {		
		return userInfoMapper.findById(id);
	}

	@Override
	public PageInfo<UserInfo> querylist(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo(userInfoMapper.querylist());
	}

	@Override
	public UserInfo findByName(String name) {
		return userInfoMapper.findByName(name);
	}

	
}
