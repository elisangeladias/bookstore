package br.com.elis.helper;

import java.text.ParseException;
import java.util.Date;

public interface DateConversorHelper {
	
	Date convert(String dateStr) throws ParseException;

}
