package spring.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author gewx dto类
 * **/
public class Student {

	public Student() {

	}

	private String name;
	
	private String address;
	
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE);
		builder.append("name", this.name);
		builder.append("age",this.age);
		builder.append("address",this.address);
		return builder.toString();
	}
	
}
