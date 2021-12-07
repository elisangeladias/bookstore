package br.com.elis.mapper.impl;

import java.sql.SQLException;
import java.sql.ResultSet;

import br.com.elis.mapper.AuthorRowMapper;
import br.com.elis.model.Author;

public class AuthorResultSetRowMapperImpl implements AuthorRowMapper<ResultSet> {

	@Override
	public Author map(ResultSet resultSet) throws SQLException {
		Author author = new Author();
		author.setId(resultSet.getLong("ID"));
		author.setName(resultSet.getString("NAME"));
		author.setBio(resultSet.getString("BIO"));
		author.setBirthdate(resultSet.getDate("BIRTHDATE"));
		return author;
	}

}
