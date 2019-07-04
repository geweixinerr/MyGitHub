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

}
