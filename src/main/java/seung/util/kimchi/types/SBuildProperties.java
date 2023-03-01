package seung.util.kimchi.types;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SBuildProperties extends SType {

	private String build_group;
	
	private String build_artifact;
	
	private String build_name;
	
	private String build_version;
	
	private String build_time;
	
}
