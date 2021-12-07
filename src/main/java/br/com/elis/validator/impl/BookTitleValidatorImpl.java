package br.com.elis.validator.impl;

import br.com.elis.validator.BookTitleValidator;

public class BookTitleValidatorImpl implements BookTitleValidator {

	public boolean validate(String value) {
		return value != null && !value.isEmpty() && value.length() <= 1000;
	}

}
