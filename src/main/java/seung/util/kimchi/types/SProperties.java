package seung.util.kimchi.types;

import java.util.Properties;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SProperties extends SType {

	@Builder.Default
	private Properties environment = new Properties();
	
	@Builder.Default
	private Properties build = new Properties();
	
	@Builder.Default
	private Properties datasource = new Properties();
	
	@Builder.Default
	private Properties security = new Properties();
	
	@Builder.Default
	private Properties app = new Properties();

}
