package seung.util.kimchi.types;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class SRequestHeader extends SType {

	private String uri_path;
	
	private String trace_id;
	
	private String remote_addr;
	
	@Builder.Default
	private SLinkedHashMap header = new SLinkedHashMap();
	
	@Builder.Default
	private SLinkedHashMap cookie = new SLinkedHashMap();
	
	@Builder.Default
	private SLinkedHashMap session = new SLinkedHashMap();
	
	public void header(String key, Object value) {
		this.header.add(key, value);
	}
	
	public String header(String key) {
		return this.header.get_text(key, "");
	}
	
	public void cookie(String key, Object value) {
		this.cookie.add(key, value);
	}
	
	public String cookie(String key) {
		return this.cookie.get_text(key, "");
	}
	
	@SuppressWarnings("unchecked")
	public void session(Object key, Object value) {
		this.session.put(key, value);
	}
	
	public Object session(Object key) {
		return this.session.get(key);
	}
	
	public String session_text(Object key) {
		return this.session.get_text(key, "");
	}
	
}
