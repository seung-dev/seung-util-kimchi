package seung.util.kimchi.types;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import seung.util.kimchi.SText;

@Setter
@Getter
public class SRequestBody implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String stringify() {
		return stringify(false);
	}
	public String stringify(boolean is_pretty) {
		return SText.stringify(this, is_pretty);
	}
	
	@Size(max = 36)
	@NotBlank
	private String request_code;
	
	private SLinkedHashMap data;
	
}
