package seung.util.kimchi.type;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import seung.util.kimchi.SText;

@Builder
@Getter
@Setter
public class SProperties {

	public String stringify() {
		return stringify(false);
	}
	public String stringify(boolean is_pretty) {
		return SText.stringify(this, is_pretty);
	}
	
	@Builder.Default
	private Properties environment = new Properties();
	
	@Builder.Default
	private Properties build = new Properties();
	
	@Builder.Default
	private List<Properties> datasource = new ArrayList<>();
	
	@Builder.Default
	private Properties security = new Properties();
	
	@Builder.Default
	private Properties app = new Properties();

}
