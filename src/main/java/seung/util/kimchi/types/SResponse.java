package seung.util.kimchi.types;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import seung.util.kimchi.SText;

@Builder
@Setter
@Getter
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
		return !SError.SUCCESS.code().equals(this.error_code);
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
	
	public String get_text(String key, String default_value) {
		return this.data.get_text(key, default_value);
	}
	
	public Integer get_int(String key, Integer default_value) {
		return this.data.get_int(key, default_value);
	}
	
	public Long get_long(String key, Long default_value) {
		return this.data.get_long(key, default_value);
	}
	
	public BigInteger get_bigint(String key, BigInteger default_value) {
		return this.data.get_bigint(key, default_value);
	}
	
	public Double get_double(String key, Double default_value) {
		return this.data.get_double(key, default_value);
	}
	
	public SLinkedHashMap get_slinkedhashmap(String key) {
		return this.data.get_slinkedhashmap(key);
	}
	
	public List<SLinkedHashMap> get_list_slinkedhashmap(String key) {
		return this.data.get_list_slinkedhashmap(key);
	}
	
	public String[] get_array_string(String key) {
		return this.data.get_array_string(key);
	}
	
	public List<String> get_list_string(String key) {
		return this.data.get_list_string(key);
	}
	
	public byte[] get_byte_array(String key) {
		return this.data.get_byte_array(key);
	}
	
}
