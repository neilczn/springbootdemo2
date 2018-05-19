package com.equality.springbootdemo2.service.impl;

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
import com.equality.springbootdemo2.entity.two.UserInfo;
import com.equality.springbootdemo2.service.UserInfoService;
import com.github.pagehelper.PageInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoServiceImplTest {
	
	@Autowired
	private UserInfoService userInfoService;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAdd() {
		UserInfo userInfo = new UserInfo(8,"777","777");
		userInfoService.add(userInfo);		
	}

	@Test
	public void testFindById() {
		userInfoService.findById(1);
	}

	@Test
	public void testQuerylist() {
		PageInfo<UserInfo> page = userInfoService.querylist(2, 3);
		System.out.println(page);
		List<UserInfo> userInfos = page.getList();
		for (UserInfo userInfo : userInfos) {
			System.out.println(userInfo);
		}
	}

}
