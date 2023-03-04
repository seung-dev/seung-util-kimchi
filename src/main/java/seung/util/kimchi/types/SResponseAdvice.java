package seung.util.kimchi.types;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SResponseAdvice extends SType {

	private long timestamp;
	
	private int status;
	
	private String error;
	
	private String message;
	
	private String path;
	
	private String filter;
	
}
