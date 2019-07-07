package lambda;

import java.io.Serializable;

public class MessageBody implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MessageBody() {
	}

	private String custName;

	private String certNo;
	
	private Integer age;

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	@Override
	public String toString() {
		return "MessageBody [custName=" + custName + ", certNo=" + certNo + ", age=" + age + "]";
	}
}
