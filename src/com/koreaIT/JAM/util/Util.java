package com.koreaIT.JAM.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {
	public static String getDateStr() {
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		return dateTime.format(formatter);
	}
}
