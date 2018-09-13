package com.equality.springbootdemo2;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

	@Before
	public void setUp() throws Exception {
	}

	@Autowired
	private StringRedisTemplate template;
	
	@Test
	public void test() {
		ValueOperations<String, String> ops = this.template.opsForValue();
		ops.set("age", "11", 1, TimeUnit.MINUTES);
		String age = ops.get("age");
		System.out.println(age);
	}

}
