package br.com.elis.validator.impl;

import java.text.ParseException;
import java.util.Date;

import br.com.elis.helper.DateConversorHelper;
import br.com.elis.validator.BookReleaseDateValidator;

public class BookReleaseDateValidatorImpl implements BookReleaseDateValidator {

	private final DateConversorHelper dateConversorHelper;

	public BookReleaseDateValidatorImpl(DateConversorHelper dateConversorHelper) {
		this.dateConversorHelper = dateConversorHelper;
	}

	public boolean validate(String value) {
		try {
			Date releaseDate = dateConversorHelper.convert(value);
			return releaseDate != null && releaseDate.getTime() < new Date().getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return false;

	}

}
