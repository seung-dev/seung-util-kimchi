package seung.util.java.type.excel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import seung.util.java.SText;

@Builder
@Setter
@Getter
public class SExcel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String stringify(boolean is_pretty) {
		return SText.stringify(this, is_pretty);
	}
	
	private String error_code;
	
	private String error_message;
	
	private int number_of_sheets;
	
	@Builder.Default
	private List<SSheet> sheets = new ArrayList<>();
	
	public void success() {
		success("S000");
	}
	
	public void success(String error_code) {
		this.error_code = error_code;
	}
	
	public void error_code(String error_code) {
		this.error_code = error_code;
	}
	
	public void error_message(String error_message) {
		this.error_message = error_message;
	}
	
	public void exception(Exception exception) {
		error_message(SText.exception(exception));
	}
	
	public void error(String error_code, String error_message) {
		error_code(error_code);
		error_message(error_message);
	}
	
	public boolean hasError() {
		return !"S000".equals(error_code);
	}
	
}
