package seung.util.kimchi.type;

import java.util.Date;
import java.util.Map;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import seung.util.kimchi.SText;

@Builder
@Getter
@Setter
public class SResponse {

	public String stringify() {
		return stringify(false);
	}
	public String stringify(boolean is_pretty) {
		return SText.stringify(this, is_pretty);
	}
	
	@NotBlank
	private String request_code;
	
	@Builder.Default
	private long request_time = -1l;
	
	@Builder.Default
	private long response_time = -1l;
	
	@Builder.Default
	private long elapsed_time = -1l;
	
	@Builder.Default
	private String error_code = "E999";
	
	@Builder.Default
	private String error_message = "";
	
	@Builder.Default
	private SLinkedHashMap data = new SLinkedHashMap();
	
	@SuppressWarnings("unchecked")
	public SResponse add(Object key, Object value) {
		this.data.put(key, value);
		return this;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SResponse merge(Map map) {
		if(map != null) {
			this.data.putAll(map);
		}
		return this;
	}
	
	public void success() {
		success("S000");
	}
	
	public void success(String error_code) {
		this.error_code = error_code;
	}
	
	public void error_code(String error_code) {
		this.error_code = error_code;
	}
	
	public void error_message(String error_message) {
		this.error_message = error_message;
	}
	
	public void exception(Exception exception) {
		error_message(SText.exception(exception));
	}
	
	public void error(String error_code, String error_message) {
		error_code(error_code);
		error_message(error_message);
	}
	
	public boolean has_error() {
		return !"S000".equals(error_code);
	}
	
	public void done() {
		this.response_time = new Date().getTime();
		this.elapsed_time = response_time - request_time;
	}
	
}
