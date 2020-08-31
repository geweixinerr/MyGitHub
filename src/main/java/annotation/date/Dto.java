package annotation.date;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Dto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@DateFormat(required = true)
	private String date;
}
