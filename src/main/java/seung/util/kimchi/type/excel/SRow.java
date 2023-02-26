package seung.util.kimchi.type.excel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import seung.util.kimchi.SText;

@Builder
@Setter
@Getter
public class SRow implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String stringify() {
		return stringify(false);
	}
	public String stringify(boolean is_pretty) {
		return SText.stringify(this, is_pretty);
	}
	
	private int row_no;
	
	@Builder.Default
	private List<SCell> cells = new ArrayList<>();
	
}
