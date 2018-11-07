package spring.reflect;

import java.lang.reflect.Constructor;

import spring.bean.Student;

/**
 * 反射测试类
 * **/
public final class ReflectionTest {

	public ReflectionTest() {
		
	}

	public static void main(String[] args) throws Exception {
		Constructor<Student> arrayObject =  Student.class.getConstructor();
		Student object_2 = arrayObject.newInstance();
		object_2.setAddress("江苏");
		System.out.println(object_2.getAddress());
	}
}

