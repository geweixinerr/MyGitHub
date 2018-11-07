package serializable;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 序列化测试DTO
 * **/
public class Student implements Serializable {

	/**
	 */
	private static final long serialVersionUID = -4881371175178374314L;

	private String userName;

	private String address;

	private Integer age;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
		ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
		builder.append("userName", this.userName).append("age", this.age).append("address", this.address);

		return builder.build();
	}

}
