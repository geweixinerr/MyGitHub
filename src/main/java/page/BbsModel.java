package page;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public final class BbsModel implements Serializable {

	private static final long serialVersionUID = 1869015183543800819L;

	private String id;
	
	private String themeId;
 
	private String themeName;
	
	private String title;
	
	private String content;
	
	private String author;
	
	//com.fasterxml.jackson.annotation.JsonFormat
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
}
