package seung.util.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Random;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;

import lombok.extern.slf4j.Slf4j;

/**
 * <h1>Description</h1>
 * <pre>{@code
 * Text Data Type Handler.
 * }</pre>
 * <hr>
 * @author seung
 * @since 2020.12.21
 * @version 1.0.0
 */
@Slf4j
public class SText {

	public SText() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * <h1>Description</h1>
	 * <pre>{@code
	 * Generate UUID.
	 * }</pre>
	 * <h1>Usage</h1>
	 * <pre>{@code
	 * System.out.println(SText.uuid());
	 * }</pre>
	 * <h1>Equal</h1>
	 * <pre>{@code
	 * SText.uuid("-", "");
	 * }</pre>
	 * <hr>
	 * @see SText#uuid(String, String)
	 * @author seung
	 * @since 2020.12.21
	 * @version 1.0.0
	 */
	public static String uuid() {
		return uuid("-", "");
	}
	/**
	 * <h1>Description</h1>
	 * <pre>{@code
	 * Generate UUID.
	 * }</pre>
	 * <h1>Usage</h1>
	 * <pre>{@code
	 * String uuid = SText.uuid("-", "");
	 * System.out.println(uuid);
	 * }</pre>
	 * <hr>
	 * @param regex
	 * @param replacement
	 * @see UUID#randomUUID()
	 * @author seung
	 * @since 2020.12.21
	 * @version 1.0.0
	 */
	public static String uuid(String regex, String replacement) {
		return UUID.randomUUID().toString().replaceAll(regex, replacement);
	}
	
	public static int random(int min, int max) {
		return new Random().nextInt(max - min + 1) + min;
	}
	
	/**
	 * <h1>Description</h1>
	 * <pre>{@code
	 * Padding.
	 * }</pre>
	 * <h1>Usage</h1>
	 * <pre>{@code
	 * System.out.println(SText.pad_right("1", 6, "0");
	 * System.out.println(SText.pad_left("1", 6, "0");
	 * }</pre>
	 * <hr>
	 * @param data
	 * @param max_length
	 * @param pad_char
	 * @author seung
	 * @since 2021.01.04
	 * @version 1.0.0
	 */
	public static String pad_right(int data, int max_length, String pad_char) {
		return pad_right(String.valueOf(data), max_length, pad_char);
	}
	public static String pad_right(String data, int max_length, String pad_char) {
		if(data == null) {
			log.info("Data is null.");
			return data;
		}
		if(data.length() > max_length) {
			log.info("Data length is bigger than maxLength.");
			return data;
		}
		StringBuffer format = new StringBuffer();
		format.append("%");
		format.append(max_length);
		format.append("s");
		return String
				.format(format.toString(), data)
				.replace(" ", pad_char)
				;
	}
	public static String pad_left(int data, int max_length, String pad_char) {
		return pad_left(String.valueOf(data), max_length, pad_char);
	}
	public static String pad_left(String data, int max_length, String pad_char) {
		if(data == null) {
			log.info("Data is null.");
			return data;
		}
		if(data.length() > max_length) {
			log.info("Data length is bigger than maxLength.");
			return data;
		}
		StringBuffer format = new StringBuffer();
		format.append("%-");
		format.append(max_length);
		format.append("s");
		return String
				.format(format.toString(), data)
				.replace(" ", pad_char)
				;
	}
	
	public static String trim(String data) {
		if(data == null) {
			return "";
		}
		return data.replaceAll("^\\s+|\\s+$", "");
	}
	
	public static String exception(Exception exception) {
		StringWriter stringWriter = new StringWriter();
		exception.printStackTrace(new PrintWriter(stringWriter));
		return stringWriter.toString();
	}
	
	/**
	 * <h1>Description</h1>
	 * <pre>{@code
	 * Convert to json format text.
	 * }</pre>
	 * <h1>Usage</h1>
	 * <pre>{@code
	 * Map<String, String> map = new HashMap<>();
	 * map.put("key1", "value1");
	 * map.put("key2", "value2");
	 * System.out.println(SConvert.stringify(map));
	 * }</pre>
	 * <h1>Equal</h1>
	 * <pre>{@code
	 * Map<String, String> map = new HashMap<>();
	 * map.put("key1", "value1");
	 * map.put("key2", "value2");
	 * System.out.println(SConvert.stringify(map, 0, "  "));
	 * }</pre>
	 * <hr>
	 * @param data
	 * @author seung
	 * @since 2021.01.04
	 * @version 1.0.0
	 */
	public static String stringify(Object data) {
		return stringify(data, false);
	}
	/**
	 * <h1>Description</h1>
	 * <pre>{@code
	 * Convert to json format text.
	 * }</pre>
	 * <h1>Usage</h1>
	 * <pre>{@code
	 * Map<String, String> map = new HashMap<>();
	 * map.put("key1", "value1");
	 * map.put("key2", "value2");
	 * System.out.println(SConvert.stringify(map, 1, "    "));
	 * System.out.println(SConvert.stringify(map, 1, "\t"));
	 * }</pre>
	 * <hr>
	 * @param data
	 * @param isPretty - {@link Boolean#FALSE}, {@link Boolean#TRUE}
	 * @param indent - default: 2 spaces
	 * @author seung
	 * @since 2021.01.04
	 * @version 1.0.0
	 */
	public static String stringify(Object data, boolean isPretty) {
		String json = "";
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.getSerializerProvider().setNullKeySerializer(new JsonSerializer<Object>() {
				@Override
				public void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
					jsonGenerator.writeFieldName("");
				}
			});
			json = objectMapper
					.setSerializationInclusion(Include.ALWAYS)
					.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
					.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true)
					.configure(SerializationFeature.INDENT_OUTPUT, isPretty)
					.writeValueAsString(data)
					;
		} catch (JsonProcessingException e) {
			log.error("Failed to convert to json format text.", e);
		}
		return json;
	}
	
	/**
	 * <h1>Description</h1>
	 * <pre>{@code
	 * Convert to Full Width Characters.
	 * }</pre>
	 * <h1>Usage</h1>
	 * <pre>{@code
	 * System.out.println(SText.to_half_width("()"));
	 * }</pre>
	 * <hr>
	 * @param text
	 * @author seung
	 * @since 2022.08.08
	 * @version 1.0.0
	 */
	public static String to_full_width(String text) {
		
		if(text == null) {
			return text;
		}
		
		if("".equals(text)) {
			return text;
		}
		
		char[] characters = text.toCharArray();
		char[] full_width = new char[characters.length];
		
		for(int i = 0; i < characters.length; i++) {
			if(characters[i] == 32) {
				full_width[i] = (char) 12288;
				continue;
			}
			if(characters[i] < 127) {
				full_width[i] = (char) (characters[i] + 65248);
				continue;
			}
			full_width[i] = characters[i];
		}// end of characters
		
		return new String(full_width);
	}
	
	/**
	 * <h1>Description</h1>
	 * <pre>{@code
	 * Convert to Half Width Characters.
	 * }</pre>
	 * <h1>Usage</h1>
	 * <pre>{@code
	 * System.out.println(SText.to_half_width("（）"));
	 * }</pre>
	 * <hr>
	 * @param text
	 * @author seung
	 * @since 2022.08.08
	 * @version 1.0.0
	 */
	public static String to_half_width(String text) {
		
		if(text == null) {
			return text;
		}
		
		if("".equals(text)) {
			return text;
		}
		
		char[] characters = text.toCharArray();
		char[] half_width = new char[characters.length];
		
		for(int i = 0; i < characters.length; i++) {
			if(characters[i] == 12288) {
				half_width[i] = (char) 32;
				continue;
			}
			if(characters[i] > 65280  && characters[i] < 65375) {
				half_width[i] = (char) (characters[i] - 65248);
				continue;
			}
			half_width[i] = characters[i];
		}// end of characters
		
		return new String(half_width);
	}
	
	/**
	 * <h1>Description</h1>
	 * <pre>{@code
	 * Generate java code.
	 * }</pre>
	 * <h1>Usage</h1>
	 * <pre>{@code
	 * System.out.println(SText.toJavaCode("password".getBytes());
	 * }</pre>
	 * <hr>
	 * @author seung
	 * @since 2020.12.21
	 * @version 1.0.0
	 */
	public static String byte_array(byte[] data) {
		String javaCode = "";
		if(data == null) {
			javaCode = "byte[] byteArray = null;";
		} else if(0 == data.length) {
			javaCode = "byte[] byteArray = null;";
		} else {
			StringBuffer stringBuffer = new StringBuffer();
			for(byte b : data) {
				stringBuffer.append(String.format(", (byte) 0x%02x", b));
			}
			javaCode = String.format("byte[] byteArray = {%s};", stringBuffer.toString().substring(2));
		}
		return javaCode;
	}
	
}
