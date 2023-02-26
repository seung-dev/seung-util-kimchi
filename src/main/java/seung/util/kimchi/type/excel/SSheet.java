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
public class SSheet implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String stringify() {
		return stringify(false);
	}
	public String stringify(boolean is_pretty) {
		return SText.stringify(this, is_pretty);
	}
	
	private String sheet_name;
	
	private int physical_number_of_rows;
	
	@Builder.Default
	private List<SRow> rows = new ArrayList<>();
	
}
