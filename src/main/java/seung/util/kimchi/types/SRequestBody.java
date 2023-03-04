package seung.util.kimchi.types;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SRequestBody extends SType {

	private SLinkedHashMap data;
	
}
