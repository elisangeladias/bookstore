package br.com.elis.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.elis.mapper.AuthorRowMapper;
import br.com.elis.mapper.BookRowMapper;
import br.com.elis.model.Book;

public class BookResultSetRowMapperImpl implements BookRowMapper<ResultSet> {
	
	private final AuthorRowMapper<ResultSet> authorRowMapper;
	
	public BookResultSetRowMapperImpl(AuthorRowMapper<ResultSet> authorRowMapper) {
		this.authorRowMapper = authorRowMapper;
	}

	@Override
	public Book map(ResultSet resultSet) throws SQLException {
		Book book = new Book();
		book.setId(resultSet.getLong("ID"));
		book.setTitle(resultSet.getString("TITLE"));
		book.setDescription(resultSet.getString("DESCRIPTION"));
		book.setIsbn(resultSet.getString("ISBN"));
		book.setReleaseDate(resultSet.getDate("RELEASE_DATE"));
		book.setAuthor(authorRowMapper.map(resultSet));
		return book;
	}

}
