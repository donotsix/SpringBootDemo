package com.futurecode.springbootmybatis2;

import com.futurecode.springbootmybatis2.bean.RoncooUser;
import com.futurecode.springbootmybatis2.mapper.RoncooUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMybatis2ApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private RoncooUserMapper roncooUserMapper;

	@Test
	public void insert() {
		RoncooUser roncooUser = new RoncooUser();
		roncooUser.setName("测试5");
		roncooUser.setCreateTime(new Date());
		int result = roncooUserMapper.insert(roncooUser);
		System.out.println(result);
	}

	@Test
	public void select() {
		RoncooUser result = roncooUserMapper.selectByPrimaryKey(2);
		System.out.println(result);
	}
}
