package annotation.date;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Dto implements Serializable {

	private static final long serialVersionUID = 1L;

	@DateFormat(required = false, value = "yyyy/MM/dd",message = "日期格式不匹配,支持的格式为:yyyy/MM/dd~")
	private String date;
}
