package seung.util.java.type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import seung.util.java.SText;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SSignPriKey {

	public String stringify(boolean is_pretty) {
		return SText.stringify(this, is_pretty);
	}
	
	private String private_key_algorythm_oid;
	
	private String encryption_algorithm_oid;
	
	private byte[] salt;
	
	private int iteration_count;
	
	private int key_length;
	
	private String prf_algorithm_oid;
	
	private byte[] iv;
	
	private byte[] private_key;
	
}
