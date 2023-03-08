package seung.util.kimchi.types;

import java.util.Map;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import seung.util.kimchi.SText;

@Builder
@Getter
@Setter
public class SResponse extends SType {

	@NotBlank
	private String trace_id;
	
	@Builder.Default
	private long request_time = System.currentTimeMillis();
	
	@Builder.Default
	private long response_time = -1l;
	
	@Builder.Default
	private long elapsed_time = -1l;
	
	@Builder.Default
	private String error_code = SError.FAIL.code();
	
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
	
	public void error_code(String error_code) {
		this.error_code = error_code;
	}
	
	public void error_message(String error_message) {
		this.error_message = error_message;
	}
	
	public void error(SError s_error) {
		this.error_code(s_error.code());
		this.error_message(s_error.message());
	}
	
	public void success() {
		this.error(SError.SUCCESS);
	}
	
	public void exception(Exception exception) {
		this.error_message(SText.exception(exception));
	}
	
	public boolean has_error() {
		return this.error_code != SError.SUCCESS.code();
	}
	
	public SResponse done() {
		this.response_time = System.currentTimeMillis();
		this.elapsed_time = response_time - request_time;
		return this;
	}
	
	public SResponse done(SError s_error) {
		this.error(s_error);
		return this.done();
	}
	
}
