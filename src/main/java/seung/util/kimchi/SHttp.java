package seung.util.kimchi;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.HashMap;

import kong.unirest.HttpRequest;
import kong.unirest.HttpRequestWithBody;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SHttp {

	public static String nslookup(
			String host
			) throws UnknownHostException {
		
		String ipv4 = "";
		
		InetAddress[] inetAddresses = InetAddress.getAllByName(host);
		
		for(InetAddress inetAddress : inetAddresses) {
			ipv4 = inetAddress.getHostAddress();
			break;
		}
		
		return ipv4;
	}
	
	public static String encode_uri_component(String data, Charset charset) {
		return encode_uri_component(data, charset.name());
	}
	public static String encode_uri_component(String data, String charset_name) {
		String encoded = "";
		try {
			encoded = URLEncoder
					.encode(data, charset_name)
					.replaceAll("\\+", "%20")
					.replaceAll("\\%21", "!")
					.replaceAll("\\%27", "'")
					.replaceAll("\\%28", "(")
					.replaceAll("\\%29", ")")
					.replaceAll("\\%7E", "~")
					;
		} catch (UnsupportedEncodingException e) {
			log.error("Failed to convert to url encoded text.", e);
		}
		return encoded;
	}
	
	public static HttpResponse<byte[]> post(
			int try_size
			, long sleep
			, String url
			, int connect_timeout
			, int socket_timeout
			, String proxy_host
			, int proxy_port
			, HashMap<String, String> cookie
			, HashMap<String, String> header
			, String body
			) throws InterruptedException {
		
		HttpRequestWithBody httpRequestWithBody = null;
		HttpResponse<byte[]> httpResponse = null;
		
		int try_no = 0;
		while(try_no++ < try_size) {
			try {
				
				httpRequestWithBody = Unirest.post(url);
				httpRequestWithBody.connectTimeout(connect_timeout);
				httpRequestWithBody.socketTimeout(socket_timeout);
				
				// proxy
				if(proxy_host != null && !"".equals(proxy_host) && proxy_port != -1) {
					httpRequestWithBody.proxy(proxy_host, proxy_port);
				}
				
				// cookie
				for(String key : cookie.keySet()) {
					httpRequestWithBody.cookie(key, cookie.get(key));
				}
				
				// header
				for(String key : header.keySet()) {
					httpRequestWithBody.header(key, header.get(key));
				}
				
				httpResponse = httpRequestWithBody.body(body).asBytes();
				if(200 == httpResponse.getStatus()) {
					break;
				} else {
					Thread.sleep(sleep);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Failed to request.({}/{})", try_no, try_size, e);
				if(sleep > 0) {
					Thread.sleep(sleep);
				}
			}// end of try
		}// end of while
		
		return httpResponse;
	}
	
	public static HttpResponse<byte[]> post(
			int try_size
			, long sleep
			, String url
			, int connect_timeout
			, int socket_timeout
			, String proxy_host
			, int proxy_port
			, HashMap<String, String> cookie
			, HashMap<String, String> header
			, HashMap<String, Object> field
			) throws InterruptedException {
		
		HttpRequestWithBody httpRequestWithBody = null;
		HttpResponse<byte[]> httpResponse = null;
		
		int try_no = 0;
		while(try_no++ < try_size) {
			try {
				
				httpRequestWithBody = Unirest.post(url);
				httpRequestWithBody.connectTimeout(connect_timeout);
				httpRequestWithBody.socketTimeout(socket_timeout);
				
				// proxy
				if(proxy_host != null && !"".equals(proxy_host) && proxy_port != -1) {
					httpRequestWithBody.proxy(proxy_host, proxy_port);
				}
				
				// cookie
				for(String key : cookie.keySet()) {
					httpRequestWithBody.cookie(key, cookie.get(key));
				}
				
				// header
				for(String key : header.keySet()) {
					httpRequestWithBody.header(key, header.get(key));
				}
				
				// field
				httpRequestWithBody.fields(field);
				
				httpResponse = httpRequestWithBody.fields(field).asBytes();
				if(200 == httpResponse.getStatus()) {
					break;
				} else {
					Thread.sleep(sleep);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Failed to request.({}/{})", try_no, try_size, e);
				if(sleep > 0) {
					Thread.sleep(sleep);
				}
			}// end of try
		}// end of while
		
		return httpResponse;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static HttpResponse<byte[]> get(
			int try_size
			, long sleep
			, String url
			, int connect_timeout
			, int socket_timeout
			, String proxy_host
			, int proxy_port
			, HashMap<String, String> cookie
			, HashMap<String, String> header
			, HashMap<String, String> query
			) throws InterruptedException {
		
		HttpRequest httpRequest = null;
		HttpResponse<byte[]> httpResponse = null;
		
		int try_no = 0;
		while(try_no++ < try_size) {
			try {
				
				httpRequest = Unirest.get(url);
				httpRequest.connectTimeout(connect_timeout);
				httpRequest.socketTimeout(socket_timeout);
				
				// proxy
				if(proxy_host != null && !"".equals(proxy_host) && proxy_port != -1) {
					httpRequest.proxy(proxy_host, proxy_port);
				}
				
				// cookie
				for(String key : cookie.keySet()) {
					httpRequest.cookie(key, cookie.get(key));
				}
				
				// header
				for(String key : header.keySet()) {
					httpRequest.header(key, header.get(key));
				}
				
				// query
				for(String key : query.keySet()) {
					httpRequest.queryString(key, query.get(key));
				}
				
				httpResponse = httpRequest.asBytes();
				if(200 == httpResponse.getStatus()) {
					break;
				} else {
					Thread.sleep(sleep);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Failed to request.({}/{})", try_no, try_size, e);
				if(sleep > 0) {
					Thread.sleep(sleep);
				}
			}// end of try
		}// end of while
		
		return httpResponse;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static HttpResponse<byte[]> request(
			HttpRequest httpRequest
			, int try_max
			, long sleep
			) throws InterruptedException {
		
		HttpResponse<byte[]> httpResponse = null;
		
		int try_no = 0;
		while(try_no++ < try_max) {
			try {
				httpResponse = httpRequest.asBytes();
				if(200 == httpResponse.getStatus()) {
					break;
				} else {
					Thread.sleep(sleep);
				}
			} catch (Exception e) {
				log.error("Failed to request.({}/{})", try_no, try_max, e);
				if(sleep > 0) {
					Thread.sleep(sleep);
				}
			}// end of try
		}// end of while
		
		return httpResponse;
	}
	
	public static String content_disposition(
			String user_agent
			, String file_name
			) throws UnsupportedEncodingException {
		
		String prefix = "attachment; file_name=";
		String suffix = "";
		
		switch(browser(user_agent)) {
			case "MSIE":
				suffix = URLEncoder.encode(file_name, "UTF-8").replaceAll("\\+", "%20");
				break;
			case "Chrome":
				StringBuffer sb = new StringBuffer();
				for(int i = 0; i < file_name.length(); i++) {
					char c = file_name.charAt(i);
					if(c > '~') {
						sb.append(URLEncoder.encode("" + c, "UTF-8"));
					} else {
						sb.append(c);
					}
				}
				suffix = sb.toString();
				break;
			case "Opera":
			case "Firefox":
			default:
				suffix = "\"" + new String(file_name.getBytes("UTF-8"), "8859_1") +"\"";
				break;
		}
		
		return prefix.concat(suffix);
	}
	
	public static String browser(String user_agent) {
		
		String browser = "";
		
		if(user_agent.indexOf("MSIE") > -1) {
			browser = "MSIE";
		} else if(user_agent.indexOf("Trident") > -1) {
			browser = "MSIE";
		} else if(user_agent.indexOf("Chrome") > -1) {
			browser = "Chrome";
		} else if(user_agent.indexOf("Opera") > -1) {
			browser = "Opera";
		} else {
			browser = "Firefox";
		}
		
		return browser;
	}
	
	public static String ipv4() throws InterruptedException, UnsupportedEncodingException {
		return ipv4("restful.kr/public/ipv4");
	}
	public static String ipv4(String url) throws InterruptedException, UnsupportedEncodingException {
		
		String ip4v = "";
		
		HttpResponse<byte[]> httpResponse = request(
				Unirest
					.get(url)
					.connectTimeout(1000 * 3)
					.socketTimeout(1000 * 60)
				, 3
				, 1000
				);
		
		if(200 == httpResponse.getStatus() && httpResponse.getBody() != null) {
			ip4v = new String(httpResponse.getBody(), "UTF-8");
		}
		
		return ip4v;
	}
	
}
