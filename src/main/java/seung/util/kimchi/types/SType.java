package seung.util.kimchi.types;

import seung.util.kimchi.SText;

public class SType {

	public String stringify() {
		return stringify(false);
	}
	
	public String stringify(boolean is_pretty) {
		return SText.stringify(this, is_pretty);
	}
	
}
