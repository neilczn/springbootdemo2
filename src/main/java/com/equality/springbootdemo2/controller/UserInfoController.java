package com.equality.springbootdemo2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.equality.springbootdemo2.entity.two.UserInfo;
import com.equality.springbootdemo2.service.UserInfoService;



@RestController
@RequestMapping("/user")
public class UserInfoController {
	
	@Autowired
	private UserInfoService userService;
	
	@RequestMapping("/findById")
	public UserInfo findById(@RequestParam Integer id){
		return userService.findById(id);
	}

}
