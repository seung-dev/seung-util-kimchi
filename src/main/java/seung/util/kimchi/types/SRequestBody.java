package seung.util.kimchi.types;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SRequestBody extends SType implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Size(max = 36)
	@NotBlank
	private String request_code;
	
	private SLinkedHashMap data;
	
}
