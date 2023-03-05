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
	private SLinkedHashMap token = new SLinkedHashMap();
	
	@Builder.Default
	private SLinkedHashMap cookie = new SLinkedHashMap();
	
	@Builder.Default
	private SLinkedHashMap session = new SLinkedHashMap();
	
	public void header(String key, Object value) {
		this.header.add(key, value);
	}
	
	public void header(SLinkedHashMap header) {
		this.header.merge(header);
	}
	
	public String header(String key) {
		return this.header.get_text(key, "");
	}
	
	public SLinkedHashMap header() {
		return this.header;
	}
	
	public void token(String key, Object value) {
		this.token.add(key, value);
	}
	
	public void token(SLinkedHashMap token) {
		this.token.merge(token);
	}
	
	public String token(String key) {
		return this.token.get_text(key, "");
	}
	
	public SLinkedHashMap token() {
		return this.token;
	}
	
	public void cookie(String key, Object value) {
		this.cookie.add(key, value);
	}
	
	public void cookie(SLinkedHashMap cookie) {
		this.cookie.merge(cookie);
	}
	
	public String cookie(String key) {
		return this.cookie.get_text(key, "");
	}
	
	public SLinkedHashMap cookie() {
		return this.cookie;
	}
	
	@SuppressWarnings("unchecked")
	public void session(Object key, Object value) {
		this.session.put(key, value);
	}
	
	public void session(SLinkedHashMap session) {
		this.session.merge(cookie);
	}
	
	public Object session(Object key) {
		return this.session.get(key);
	}
	
	public String session_text(Object key) {
		return this.session.get_text(key, "");
	}
	
	public SLinkedHashMap session() {
		return this.session;
	}
	
}
