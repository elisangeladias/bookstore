package br.com.elis.bo;

import java.sql.SQLException;
import java.text.ParseException;

public interface CrudBO<T> {

	void delete() throws SQLException;

	void update() throws ParseException, SQLException;

	void insert() throws ParseException, SQLException;

	void findAll() throws SQLException;
	
	T findById() throws SQLException;


}
