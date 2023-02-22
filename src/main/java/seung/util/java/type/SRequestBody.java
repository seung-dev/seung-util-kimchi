package seung.util.java.type;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import seung.util.java.SText;

@Setter
@Getter
public class SRequestBody implements Serializable {

	private static final long serialVersionUID = -2713597337910316580L;
	
	public String stringify(boolean is_pretty) {
		return SText.stringify(this, is_pretty);
	}
	
	@Size(max = 36)
	@NotBlank
	private String request_code;
	
	private SLinkedHashMap data;
	
}
