package seung.util.java.type;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import seung.util.java.SText;

@Builder
@Getter
@Setter
public class SEnv {

	public String stringify(boolean is_pretty) {
		return SText.stringify(this, is_pretty);
	}
	
	@Builder.Default
	private boolean shutdown = false;
	
	@Builder.Default
	private String shutdown_message = "";
	
	// os
	private String os_name;
	private String os_ver;
	@Builder.Default
	private boolean is_windows = false;
	@Builder.Default
	private boolean is_linux = false;
	@Builder.Default
	private boolean is_mac = false;
	
	// server
	private String host_name;
	private String public_ipv4;
	
	// app
	private int server_port;
	private String app_name;
	private String app_ver;
	private String build_time;
	private String app_path;
	
	// mode
	private String app_mode;
	@Builder.Default
	private boolean is_ops = false;
	@Builder.Default
	private boolean is_dev = false;
	@Builder.Default
	private boolean is_loc = false;
	
	public void shutdown(boolean shutdown, String shutdown_message) {
		this.shutdown = shutdown;
		this.shutdown_message = shutdown_message;
	}
	
}
