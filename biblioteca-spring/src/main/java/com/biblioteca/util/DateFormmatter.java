package com.biblioteca.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateFormmatter {
	
	/**
	 * 
	 * Te devuelve una cadena de caracteres del tipo yyyy-MM-dd en base a la fecha
	 * introducida. Por ejemplo: 2023-07-21
	 * 
	 * @param LocalDate La fecha
	 * @return La cadena de caracteres con el formato de tipo yyyy-MM-dd
	 */
	public static String formatDate(LocalDate localDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedString = localDate.format(formatter);
		return formattedString;
	}

}
