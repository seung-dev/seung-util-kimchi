package seung.util.kimchi.types;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SEnv extends SType {

	// os
	private String os_name;
	private String os_ver;
	@Builder.Default
	private boolean is_windows = false;
	@Builder.Default
	private boolean is_linux = false;
	@Builder.Default
	private boolean is_mac = false;
	
	// mode
	private String app_mode;
	@Builder.Default
	private boolean is_ops = false;
	@Builder.Default
	private boolean is_dev = false;
	@Builder.Default
	private boolean is_loc = false;
	
	// server
	private String host_name;
	private String public_ipv4;
	private int server_port;
	
	// app
	private String app_name;
	
	// nas
	private String nas_path;
	
	@Builder.Default
	private boolean shutdown = false;
	
	@Builder.Default
	private String shutdown_message = "";
	
	public void shutdown(boolean shutdown, String shutdown_message) {
		this.shutdown = shutdown;
		this.shutdown_message = shutdown_message;
	}
	
}
