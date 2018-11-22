package commons;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class HashCodeEquals {

	public HashCodeEquals() {
	}

	private String name;
	
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder hashBuilder = new HashCodeBuilder(); //17, 37[入参可默认不给,默认构造参数自带].
		hashBuilder.append(this.name);
		hashBuilder.append(this.age);		
		return hashBuilder.toHashCode();
	}

	@Override
	public boolean equals(Object otherObject) {
		if (otherObject == null) {//比较引用是否为NULL
			return false;
		}
		
		if (this == otherObject) { //比较引用是否为同一个对象
			return true;
		}
		
		if (!(otherObject instanceof HashCodeEquals)) { //比较是否可以转换为当前对象[推荐]
			return false;
		}
		
		/*
		if (!(getClass() == otherObject.getClass())) { //比较运行时对象是否一致[涉及到父类与子类继承会存在问题]
			return false;
		}*/
		
		HashCodeEquals hashObject = (HashCodeEquals)otherObject;

		EqualsBuilder builder = new EqualsBuilder();
		builder.append(hashObject.name, this.name).append(hashObject.age, this.age);
		return builder.isEquals();
		/*
		if (hashObject.name.equals(this.name) & hashObject.age.equals(this.age)) {
			return true;
		} else {
			return false;
		}
		*/
	}
	
}
