package com;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring.bean.Student;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:SpringTest.xml"})
public class SpringRunWithTest {

	@Autowired
	private Student studentId;
	
	@Before
	public void before() {
		System.out.println("测试前准备!");
	}
	
	@Test
	public void testServer() {
		System.out.println("测试主体函数---> " + studentId.getName());
	}
	
	@After
	public void after() {
		System.out.println("测试后清理!");
	}
}
