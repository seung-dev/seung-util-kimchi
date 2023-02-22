package seung.util.java;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang3.time.DateUtils;

public class SDate {

	public static final String _TIMEUNIT_MILLISECOND = "ms";
	public static final String _TIMEUNIT_SECOND = "s";
	public static final String _TIMEUNIT_MINUTE = "m";
	public static final String _TIMEUNIT_HOUR = "h";
	public static final String _TIMEUNIT_WEEK = "w";
	public static final String _TIMEUNIT_DAY = "day";
	public static final String _TIMEUNIT_MONTH = "month";
	public static final String _TIMEUNIT_YEAR = "year";
	
	public SDate() {
		// TODO Auto-generated constructor stub
	}
	
	public static String epoch(String prefix) {
		return String.format("%s.%s", prefix, epoch());
	}
	public static String epoch() {
		return Long.toString(new Date().getTime());
	}
	
	public static String to_text() {
		return to_text("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", new Date(), TimeZone.getDefault());
	}
	public static String to_text(Date date) {
		return to_text("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", date, TimeZone.getDefault());
	}
	public static String to_text(String pattern) {
		return to_text(pattern, new Date());
	}
	public static String to_text(String pattern, Date date) {
		return to_text(pattern, date, TimeZone.getDefault());
	}
	public static String to_text(String pattern, Date date, String time_zone) {
		return to_text(pattern, date, TimeZone.getTimeZone(time_zone));
	}
	public static String to_text(String pattern, Date date, TimeZone time_zone) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		simpleDateFormat.setTimeZone(time_zone);
		return simpleDateFormat.format(date);
	}
	public static String to_text(String pattern, Date date, Locale locale) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, locale);
		return simpleDateFormat.format(date);
	}
	
	public static Date to_date(String source, String pattern) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.parse(source);
	}
	
	public static int to_int() {
		return Integer.parseInt(to_text("yyyyMMdd", new Date()).replaceAll("[^0-9]", ""));
	}
	public static int to_int(String pattern) {
		return Integer.parseInt(to_text(pattern, new Date()).replaceAll("[^0-9]", ""));
	}
	public static int to_int(String pattern, Date date) {
		return Integer.parseInt(to_text(pattern, date, TimeZone.getDefault()).replaceAll("[^0-9]", ""));
	}
	public static int to_int(String pattern, Date date, TimeZone time_zone) {
		return Integer.parseInt(to_text(pattern, date, time_zone).replaceAll("[^0-9]", ""));
	}
	
	public static Long diff(Date date_from, Date date_to) {
		return Math.abs(date_to.getTime() - date_from.getTime());
	}
	
	public static Date add(Date date, String time_unit, int amount) {
		
		Date addedDate = null;
		
		switch (time_unit) {
			case _TIMEUNIT_MILLISECOND:
				addedDate = DateUtils.addMilliseconds(date, amount);
				break;
			case _TIMEUNIT_SECOND:
				addedDate = DateUtils.addSeconds(date, amount);
				break;
			case _TIMEUNIT_MINUTE:
				addedDate = DateUtils.addMinutes(date, amount);
				break;
			case _TIMEUNIT_HOUR:
				addedDate = DateUtils.addHours(date, amount);
				break;
			case _TIMEUNIT_WEEK:
				addedDate = DateUtils.addWeeks(date, amount);
				break;
			case _TIMEUNIT_DAY:
				addedDate = DateUtils.addDays(date, amount);
				break;
			case _TIMEUNIT_MONTH:
				addedDate = DateUtils.addMonths(date, amount);
				break;
			case _TIMEUNIT_YEAR:
				addedDate = DateUtils.addYears(date, amount);
				break;
			default:
				break;
		}
		
		return addedDate;
	}
	
}
