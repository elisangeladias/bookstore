package br.com.elis.helper.impl;

import br.com.elis.helper.ConfirmActionHelper;

public class ConfirmActionHelperImpl implements ConfirmActionHelper {

	@Override
	public boolean confirm(String value) {
		return value.isEmpty() || "S".equalsIgnoreCase(value);
	}

	@Override
	public String getOptions() {
		return "[S/n]";
	}

}
