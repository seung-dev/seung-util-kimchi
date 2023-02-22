package seung.util.java;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

import lombok.extern.slf4j.Slf4j;
import seung.util.java.type.SLinkedHashMap;

@Slf4j
public class SConvert {

	public SConvert() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * <h1>Description</h1>
	 * <pre>{@code
	 * Available Charset.
	 * }</pre>
	 * <h1>Usage</h1>
	 * <pre>{@code
	 * for(String charset : SConvert.availableCharset()) {
	 *   System.out.println(charset);
	 * }
	 * }</pre>
	 * <hr>
	 * @see Charset#availableCharsets()
	 * @author seung
	 * @since 2020.12.21
	 * @version 1.0.0
	 */
	public static String[] availableCharset() {
		return Charset.availableCharsets().keySet().toArray(new String[0]);
	}
	
	@SuppressWarnings("unchecked")
	public static List<SLinkedHashMap> to_list_slinkedhashmap(String data) {
		List<SLinkedHashMap> items = null;
		try {
			if(data == null || "".equals(data)) {
				log.error("Field data is empty.");
			}
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.getSerializerProvider().setNullKeySerializer(new JsonSerializer<Object>() {
				@Override
				public void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
					jsonGenerator.writeFieldName("");
				}
			});
			items = objectMapper
					.registerModule(
							new SimpleModule("seung", Version.unknownVersion())
							.addAbstractTypeMapping(Map.class, SLinkedHashMap.class)
							)
					.readValue(data, List.class)
					;
		} catch (JsonParseException e) {
			log.error("Failed to convert to SLinkedHashMap.", e);
		} catch (JsonMappingException e) {
			log.error("Failed to convert to SLinkedHashMap.", e);
		} catch (IOException e) {
			log.error("Failed to convert to SLinkedHashMap.", e);
		}
		return items;
	}
	public static SLinkedHashMap to_slinkedhashmap(String data) {
		SLinkedHashMap sLinkedHashMap = null;
		try {
			if(data == null || "".equals(data)) {
				log.error("Field data is empty.");
			}
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.getSerializerProvider().setNullKeySerializer(new JsonSerializer<Object>() {
				@Override
				public void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
					jsonGenerator.writeFieldName("");
				}
			});
			sLinkedHashMap = objectMapper
					.registerModule(
							new SimpleModule("seung", Version.unknownVersion())
							.addAbstractTypeMapping(Map.class, SLinkedHashMap.class)
							)
					.readValue(data, SLinkedHashMap.class)
					;
		} catch (JsonParseException e) {
			log.error("Failed to convert to SLinkedHashMap.", e);
		} catch (JsonMappingException e) {
			log.error("Failed to convert to SLinkedHashMap.", e);
		} catch (IOException e) {
			log.error("Failed to convert to SLinkedHashMap.", e);
		}
		return sLinkedHashMap;
	}
	
	/**
	 * <h1>Description</h1>
	 * <pre>{@code
	 * Convert to base64 decoded data.
	 * }</pre>
	 * <h1>Usage</h1>
	 * <pre>{@code
	 * byte[] encoded = SConvert.encodeBase64(
	 *   "data".getBytes()
	 * );
	 * for(byte b : encoded) {
	 *   System.out.println(b >= 0 ? String.format("%8s", Integer.toBinaryString(b)).replaceAll("\s", "0") : Integer.toBinaryString(b).substring(24));
	 * }
	 * }</pre>
	 * <hr>
	 * @param data
	 * @see Base64#encodeBase64(byte[])
	 * @author seung
	 * @since 2020.12.21
	 * @version 1.0.0
	 */
	public static byte[] encode_base64(byte[] data) {
		return Base64.encodeBase64(data);
	}
	
	/**
	 * <h1>Description</h1>
	 * <pre>{@code
	 * Convert to base64 decoded data.
	 * }</pre>
	 * <h1>Usage</h1>
	 * <pre>{@code
	 * byte[] decoded = SConvert.decode_base64(
	 *   "ZGF0YQ==".getBytes()
	 * );
	 * for(byte b : decoded) {
	 *   System.out.println(b >= 0 ? String.format("%8s", Integer.toBinaryString(b)).replaceAll("\s", "0") : Integer.toBinaryString(b).substring(24));
	 * }
	 * }</pre>
	 * <hr>
	 * @param encoded
	 * @see Base64#decode_base64(byte[])
	 * @author seung
	 * @since 2020.12.21
	 * @version 1.0.0
	 */
	public static byte[] decode_base64(byte[] encoded) {
		return Base64.decodeBase64(encoded);
	}
	
	/**
	 * <h1>Description</h1>
	 * <pre>{@code
	 * Convert to hex encoded data.
	 * }</pre>
	 * <h1>Usage</h1>
	 * <pre>{@code
	 * String encoded = SConvert.encodeHexString(
	 *   "data".getBytes()
	 * );
	 * System.out.println(encoded);
	 * }</pre>
	 * <hr>
	 * @param data
	 * @author seung
	 * @since 2020.12.21
	 * @version 1.0.0
	 */
	public static String encode_hex(BigInteger data) {
		return encode_hex(data.toByteArray(), true);
	}
	public static String encode_hex(byte[] data) {
		return encode_hex(data, true);
	}
	public static String encode_hex(byte[] data, boolean to_lower_case) {
		return Hex.encodeHexString(data, to_lower_case);
	}
	
	/**
	 * <h1>Description</h1>
	 * <pre>{@code
	 * Convert to hex decoded data.
	 * }</pre>
	 * <h1>Usage</h1>
	 * <pre>{@code
	 * byte[] decoded = SConvert.decodeHexString(
	 *   "64617461"
	 * );
	 * for(byte b : decoded) {
	 *   System.out.println(b >= 0 ? String.format("%8s", Integer.toBinaryString(b)).replaceAll("\s", "0") : Integer.toBinaryString(b).substring(24));
	 * }
	 * }</pre>
	 * <hr>
	 * @param encoded
	 * @see Hex#decodeHex(String)
	 * @author seung
	 * @since 2020.12.21
	 * @version 1.0.0
	 */
	public static byte[] decode_hex(String encoded) {
		byte[] decoded = null;
		try {
			decoded = Hex.decodeHex(encoded);
		} catch (DecoderException e) {
			log.error("Failed to convert to hex decoded data.", e);
		}
		return decoded;
	}
	
	public static byte[] compress(byte[] data) {
		return compress(data, Deflater.BEST_COMPRESSION, true);
	}
	public static byte[] compress(byte[] data, int level, boolean nowrap) {
		
		byte[] deflated = null;
		
		Deflater deflater = new Deflater(level, nowrap);
		
		deflater.setInput(data);
		deflater.finish();
		
		byte[] b = new byte[1024];
		int len;
		try (
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				) {
			
			while(!deflater.finished()) {
				len = deflater.deflate(b);
				byteArrayOutputStream.write(b, 0, len);
			}
			
			deflated = byteArrayOutputStream.toByteArray();
			deflater.end();
			
		} catch (IOException e) {
			log.error("Failed to compress data.", e);
		}
		
		return deflated;
	}
	
	public static byte[] decompress(byte[] data) {
		return decompress(data, true);
	}
	public static byte[] decompress(byte[] data, boolean nowrap) {
		
		byte[] inflated = null;
		
		Inflater inflater = new Inflater(nowrap);
		
		inflater.setInput(data);
		
		byte[] b = new byte[1024];
		int len;
		try (
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				) {
			
			while(!inflater.finished()) {
				len = inflater.inflate(b);
				byteArrayOutputStream.write(b, 0, len);
			}
			
			inflated = byteArrayOutputStream.toByteArray();
			inflater.end();
			
		} catch (IOException e) {
			log.error("Failed to decompress data.", e);
		} catch (DataFormatException e) {
			log.error("Failed to decompress data.", e);
		}
		
		return inflated;
	}
	
}
