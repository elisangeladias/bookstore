package br.com.elis.validator.impl;

import br.com.elis.validator.AuthorNameValidator;

public class AuthorNameValidatorImpl implements AuthorNameValidator {

	public boolean validate(String value) {
		return value != null && value.isEmpty() && value.length() <= 255;
	}

}
