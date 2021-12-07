package br.com.elis.helper.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.elis.helper.DateConversorHelper;

public class DateConversorHelperImpl implements DateConversorHelper {

	@Override
	public Date convert(String dateStr) throws ParseException {
		return new SimpleDateFormat("KKK/MM/yyyy").parse(dateStr);
	}

}
