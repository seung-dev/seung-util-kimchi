package seung.util.java.type;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;
import seung.util.java.SConvert;
import seung.util.java.SText;

@SuppressWarnings("rawtypes")
@Slf4j
public class SLinkedHashMap extends LinkedHashMap {

	private static final long serialVersionUID = 1L;
	
	private static final List<String> _EXCLUDE_REFLECTION_FIELDS = Arrays.asList("log", "serialVersionUID");
	
	public SLinkedHashMap() {
		// TODO Auto-generated constructor stub
	}
	
	public String stringify(boolean is_pretty) {
		return SText.stringify(this, is_pretty);
	}
	
	public SLinkedHashMap(Map data) {
		merge(data);
	}
	public SLinkedHashMap(Object data) {
		merge(data);
	}
	public SLinkedHashMap(String data) {
		merge(data);
	}
	public SLinkedHashMap(List data) {
		merge(data);
	}
	
	public boolean is_equal(Object key, Object object) {
		return get(key) == object;
	}
	
	public boolean is_null(Object key) {
		return get(key) == null;
	}
	
	public boolean is_blank(Object key) {
		return "".equals(get(key));
	}
	
	public boolean is_empty(Object key) {
		return is_null(key) || is_blank(key);
	}
	
	public List<String> keys() {
		List<String> keyList = new ArrayList<>();
		for(Object key : this.keySet()) {
			keyList.add(key == null ? "" : "" + key);
		}
		return keyList;
	}
	
	public Object get(Object key) {
		return get(key, null);
	}
	public Object get(Object key, Object default_value) {
		return is_null(key) ? default_value : get(key);
	}
	
	public String get_text(Object key) {
		return get_text(key, null);
	}
	public String get_text(Object key, String default_value) {
		if(is_null(key)) {
			return default_value;
		}
		Object value = get(key);
		if(value instanceof String) {
			return "" + value;
		}
		if(value instanceof String[]) {
			String[] items = (String[]) value;
			return items[0];
		}
		if(value instanceof List) {
			List items = (List) value;
			if(items.size() == 0) {
				return default_value;
			}
			return "" + items.get(0);
		}
		return "" + get(key);
	}
	
	public Boolean get_boolean(Object key) {
		return get_boolean(key, null);
	}
	public Boolean get_boolean(Object key, Boolean default_value) {
		if(is_empty(key)) {
			return default_value;
		}
		Object value = get(key);
		if(value instanceof Boolean) {
			return (boolean) value;
		}
		switch (get_text(key, "")) {
			case "1":
			case "true":
				return true;
			case "0":
			case "false":
				return false;
			default:
				break;
		}
		throw new ClassCastException("The value cannot be cast to Boolean.");
	}
	
	public Integer get_int(Object key) {
		return get_int(key, null);
	}
	public Integer get_int(Object key, Integer default_value) {
		if(is_empty(key)) {
			return default_value;
		}
		String value = get_text(key);
		if(!Pattern.matches("[0-9+-]+", value)) {
			throw new NumberFormatException(String.format("Failed to cast to Integer. value=%s", value));
		}
		return Integer.parseInt(value);
	}
	
	public Long get_long(Object key) {
		return get_long(key, null);
	}
	public Long get_long(Object key, Long default_value) {
		if(is_empty(key)) {
			return default_value;
		}
		String value = get_text(key);
		if(!Pattern.matches("[0-9+-]+", value)) {
			throw new NumberFormatException(String.format("Failed to cast to Long. value=%s", value));
		}
		return Long.parseLong(value);
	}
	
	public BigInteger get_bigint(Object key) {
		return get_bigint(key, null);
	}
	public BigInteger get_bigint(Object key, BigInteger default_value) {
		if(is_empty(key)) {
			return default_value;
		}
		String value = get_text(key);
		if(!Pattern.matches("[0-9+-]+", value)) {
			throw new NumberFormatException(String.format("Failed to cast to BigInteger. value=%s", value));
		}
		return BigInteger.valueOf(get_long(key));
	}
	
	public Double get_double(Object key) {
		return get_double(key, null);
	}
	public Double get_double(Object key, Double default_value) {
		if(is_empty(key)) {
			return default_value;
		}
		String value = get_text(key);
		if(!Pattern.matches("[0-9+-.]+", value)) {
			throw new NumberFormatException(String.format("Failed to cast to Double. value=%s", value));
		}
		return Double.parseDouble(value);
	}
	
	public BigDecimal get_decimal(Object key, BigDecimal default_value) {
		if(is_empty(key)) {
			return default_value;
		}
		String value = get_text(key);
		if(!Pattern.matches("[0-9+-.]+", value)) {
			throw new NumberFormatException(String.format("Failed to cast to BigInteger. value=%s", value));
		}
		return BigDecimal.valueOf(get_double(key));
	}
	
	public Map get_map(Object key) {
		if(is_null(key)) {
			return null;
		}
		Object value = get(key);
		if(value instanceof Map) {
			return (Map) value;
		}
		throw new ClassCastException("Failed to cast to Map.");
	}
	
	public SLinkedHashMap get_slinkedHashMap(Object key) {
		if(is_null(key)) {
			return null;
		}
		Object value = get(key);
		if(value instanceof SLinkedHashMap) {
			return (SLinkedHashMap) value;
		}
		throw new ClassCastException("Failed to cast to SLinkedHashMap.");
	}
	
	public List get_list(Object key) {
		if(is_null(key)) {
			return null;
		}
		Object value = get(key);
		if(value instanceof List) {
			return (List) value;
		}
		throw new ClassCastException("Failed to cast to List.");
	}
	
	@SuppressWarnings("unchecked")
	public List<SLinkedHashMap> get_list_slinkedhashmap(Object key) {
		if(is_null(key)) {
			return null;
		}
		Object value = get(key);
		if(value instanceof List) {
			return (List<SLinkedHashMap>) value;
		}
		throw new ClassCastException("Failed to cast to List<SLinkedHashMap>.");
	}
	
	@SuppressWarnings("unchecked")
	public String[] get_array_string(Object key) {
		if(is_empty(key)) {
			return null;
		}
		Object value = get(key);
		if(value instanceof String) {
			String[] array = {
					"" + value
			};
			return array;
		}
		if(value instanceof String[]) {
			return (String[]) value;
		}
		if(value instanceof List) {
			List<String> items = (List) value;
			return items.stream().toArray(String[]::new);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public int[] get_array_int(Object key) {
		if(is_empty(key)) {
			return null;
		}
		Object value = get(key);
		if(value instanceof Integer) {
			int[] array = {
					(int) value
			};
			return array;
		}
		if(value instanceof int[]) {
			return (int[]) value;
		}
		if(value instanceof List) {
			List<Integer> values = (List) value;
			return values.stream().mapToInt(Integer::intValue).toArray();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public long[] get_array_long(Object key) {
		if(is_empty(key)) {
			return null;
		}
		Object value = get(key);
		if(value instanceof Integer) {
			long[] array = {
					(long) value
			};
			return array;
		}
		if(value instanceof long[]) {
			return (long[]) value;
		}
		if(value instanceof List) {
			List<Long> values = (List) value;
			return values.stream().mapToLong(Long::longValue).toArray();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public double[] get_array_double(Object key) {
		if(is_empty(key)) {
			return null;
		}
		Object value = get(key);
		if(value instanceof double[]) {
			return (double[]) value;
		}
		if(value instanceof Double) {
			double[] array = {
					(double) value
			};
			return array;
		}
		if(value instanceof List) {
			List<Double> values = (List) value;
			return values.stream().mapToDouble(Double::doubleValue).toArray();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> get_list_string(Object key) {
		if(is_empty(key)) {
			return null;
		}
		Object value = get(key);
		if(value instanceof String) {
			return Arrays.asList("" + value);
		}
		if(value instanceof String[]) {
			return Arrays.asList((String[]) value);
		}
		if(value instanceof List) {
			return (List<String>) value;
		}
		return null;
	}
	
	public byte[] get_byte_array(Object key) {
		if(is_empty(key)) {
			return null;
		}
		Object value = get(key);
		if(value instanceof byte[]) {
			return (byte[]) value;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public SLinkedHashMap merge(Map data) {
		if(data != null) {
			super.putAll(data);
		}
		return this;
	}
	@SuppressWarnings("unchecked")
	public SLinkedHashMap merge(Object data) {
		try {
			String field_name = "";
			for(Field field : data.getClass().getSuperclass().getDeclaredFields()) {
				field.setAccessible(true);
				field_name = field.getName();
				if(_EXCLUDE_REFLECTION_FIELDS.contains(field_name)) {
					continue;
				}
				this.put(field_name, field.get(data));
			}
			for(Field field : data.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				field_name = field.getName();
				if(_EXCLUDE_REFLECTION_FIELDS.contains(field_name)) {
					continue;
				}
				this.put(field_name, field.get(data));
			}
		} catch (IllegalArgumentException e) {
			log.error("Failed to convert to SLinkedHashMap.", e);
		} catch (IllegalAccessException e) {
			log.error("Failed to convert to SLinkedHashMap.", e);
		}
		return this;
	}
	@SuppressWarnings("unchecked")
	public SLinkedHashMap merge(String data) {
		this.putAll(SConvert.to_slinkedhashmap(data));
		return this;
	}
	@SuppressWarnings("unchecked")
	public SLinkedHashMap merge(List data) {
		if(data != null) {
			for(int index = 0; index < data.size(); index++) {
				this.put(index, data.get(index));
			}
		}
		return this;
	}
	@SuppressWarnings("unchecked")
	public SLinkedHashMap add(Object key, Object value) {
		super.put(key, value);
		return this;
	}
	
}
