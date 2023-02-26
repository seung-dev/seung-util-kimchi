package seung.util.kimchi.type;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import seung.util.kimchi.SText;

@Builder
@Setter
@Getter
public class SSignCertDer {

	public String stringify() {
		return stringify(false);
	}
	public String stringify(boolean is_pretty) {
		return SText.stringify(this, is_pretty);
	}
	
	private String type;
	
	private int version;
	
	private String serial_number;
	
	private String signiture_algorithm_oid;
	
	private String signiture_algorithm_name;
	
	private String issuer_dn;
	
	private String subject_dn;
	
	private long not_before_epoch;
	
	private String not_before_local;
	
	private long not_after_epoch;
	
	private String not_after_local;
	
	private List<String> key_usage;
	
	private String certificate_policy_oid;
	
	private String crl_distribution_point;
	
	private String subject_alternative_name_oid;
	
	private String vid_oid;
	
	private String vid_hash_algorithm_oid;
	
	private String vid;
	
}
