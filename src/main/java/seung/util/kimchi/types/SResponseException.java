package seung.util.kimchi.types;

public class SResponseException extends RuntimeException {

	private static final long serialVersionUID = -6769962024075607074L;
	
	private final SResponse s_response;
	
	public SResponseException(SResponse s_response) {
		this.s_response = s_response;
	}
	
	public SResponse s_response() {
		return this.s_response;
	}
	
	public String error_code() {
		return this.s_response.getError_code();
	}
	
	public SError s_error() {
		return SError.resolve(this.s_response.getError_code());
	}
	
	public String error_message() {
		return this.s_response.getError_message();
	}
	
}
