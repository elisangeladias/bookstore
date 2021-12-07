package br.com.elis.validator.impl;

import br.com.elis.validator.BookDescriptionValidator;

public class BookDescriptionValidatorImpl implements BookDescriptionValidator {

	public boolean validate(String value) {
		return value != null && !value.isEmpty();
	}

}
