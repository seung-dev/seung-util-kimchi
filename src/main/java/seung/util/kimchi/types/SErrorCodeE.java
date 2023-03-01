package seung.util.kimchi.types;

public enum SErrorCodeE {

	S000("Success")
	, F999("Fail")
	, A100("사용중인 이메일입니다.")
	, A200("Token Verified")
	, A299("토큰 검증 실패")
	, A201("io.jsonwebtoken.security.SecurityException")
	, A202("io.jsonwebtoken.MalformedJwtException")
	, A203("io.jsonwebtoken.ExpiredJwtException")
	, A204("io.jsonwebtoken.UnsupportedJwtException")
	, A205("io.jsonwebtoken.IllegalArgumentException")
	, E200("org.springframework.jdbc.BadSqlGrammarException")
	, H400("Bad Request")
	, H401("Unauthorized")
	, H402("Payment Required Experimental")
	, H403("Forbidden")
	, H404("Not Found")
	, H405("Method Not Allowed")
	, H406("Not Acceptable")
	, H407("Proxy Authentication Required")
	, H408("Request Timeout")
	, H409("Conflict")
	, H200("OK")
	, H410("Gone")
	, H411("Length Required")
	, H412("Precondition Failed")
	, H413("Payload Too Large")
	, H414("URI Too Long")
	, H415("Unsupported Media Type")
	, H416("Range Not Satisfiable")
	, H417("Expectation Failed")
	, H418("I'm a teapot")
	, H421("Misdirected Request")
	, H422("Unprocessable Content")
	, H423("Locked")
	, H424("Failed Dependency")
	, H425("Too Early Experimental")
	, H426("Upgrade Required")
	, H428("Precondition Required")
	, H429("Too Many Requests")
	, H431("Request Header Fields Too Large")
	, H451("Unavailable For Legal Reasons")
	, H500("Internal Server Error")
	, H501("Not Implemented")
	, H502("Bad Gateway")
	, H503("Service Unavailable")
	, H504("Gateway Timeout")
	, H505("HTTP Version Not Supported")
	, H506("Variant Also Negotiates")
	, H507("Insufficient Storage")
	, H508("Loop Detected")
	, H510("Not Extended")
	, H511("Network Authentication Required")
	;
	
	public final String message;
	private SErrorCodeE(String message) {
		this.message = message;
	}
	
}
