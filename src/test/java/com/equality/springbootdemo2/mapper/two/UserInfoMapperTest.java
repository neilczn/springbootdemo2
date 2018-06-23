package com.equality.springbootdemo2.mapper.two;

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
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

//注意这两部分
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoMapperTest {
	
	@Autowired
	private UserInfoMapper userInfoMapper;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFindById() {
		UserInfo userInfo = userInfoMapper.findById(1);
		System.out.println(userInfo);
	}
	
	@Test
	public void testFindByName() {
		UserInfo userInfo = userInfoMapper.findByName("admin");
		System.out.println(userInfo);
	}

	@Test
	public void testQuerylist() {
		PageHelper.startPage(2, 3);
		List<UserInfo> list2 =  userInfoMapper.querylist();
		for (UserInfo user : list2) {
			System.out.println(user);
		}
		//用PageInfo对结果进行包装
		PageInfo page = new PageInfo(list2);
		System.out.println(page);
	}

	@Test
	@Transactional(value="twoTransactionManager")//指定具体的名称的DataSourceTransactionManager
    @Rollback
	public void testAdd() {
		userInfoMapper.add(new UserInfo(9, "9", "dfalj"));
	}

}
