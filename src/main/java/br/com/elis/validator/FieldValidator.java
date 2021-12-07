package br.com.elis.validator;

public interface FieldValidator<T> {

	boolean validate(T value);

}
