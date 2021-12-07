package br.com.elis.validator.impl;

import br.com.elis.validator.AuthorBioValidator;

public class AuthorBioValidatorImpl implements AuthorBioValidator {

	public boolean validate(String value) {
		return value != null && !value.isEmpty();
	}

}
