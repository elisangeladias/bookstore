package br.com.elis.validator.impl;

import java.text.ParseException;
import java.util.Date;

import br.com.elis.helper.DateConversorHelper;
import br.com.elis.validator.AuthorBirthdateValidator;

public class AuthorBirthdateValidatorImpl implements AuthorBirthdateValidator {

	private final DateConversorHelper dateConversorHelper;

	public AuthorBirthdateValidatorImpl(DateConversorHelper dateConversorHelper) {
		this.dateConversorHelper = dateConversorHelper;
	}

	public boolean validate(String value) {
		try {
			Date birthdate = dateConversorHelper.convert(value);
			return birthdate != null && birthdate.getTime() < new Date().getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

}
