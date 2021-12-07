package br.com.elis.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import br.com.elis.mapper.BookRowMapper;
import br.com.elis.model.Book;
import br.com.elis.repository.BookRepository;

public class BookRepositoryImpl implements BookRepository {
    
       private static final String FIND_ALL = " SELECT * FROM BOOK B INNER JOIN AUTHOR A ";

	private static final String FIND_BY_ID = " SELECT * FROM BOOK B INNER JOIN AUTHOR A ON B.AUTHOR_ID = A.ID WHERE B.ID = ? ";

	private static final String UPDATE_BY_ID = " UPDATE BOOK SET TITLE = ?, ISBN = ?, DESCRIPTION = ?, RELEASE_DATE = ?, AUTHOR_ID = ? WHERE ID = ? ";

	private static final String INSERT = " INSERT INTO BOOK(TITLE, ISBN, DESCRIPTION, RELEASE_DATE, AUTHOR_ID) VALUES(?,?,?,?,?) ";


	private final Connection connection;

	private final BookRowMapper<ResultSet> bookRowMapper;

	public BookRepositoryImpl(Connection connection, final BookRowMapper<ResultSet> bookRowMapper) {
		this.connection = connection;
		this.bookRowMapper = bookRowMapper;
	}

	public Collection<Book> findAll() throws SQLException {
		
                Collection<Book> books = null;
		try (PreparedStatement prepareStatement = this.connection.prepareStatement(FIND_ALL)) {
			try (ResultSet resultSet = prepareStatement.executeQuery()) {
				books = new ArrayList<>();
				while (resultSet.next()) {
					books.add(bookRowMapper.map(resultSet));
				}
			}
		}
		return books;
                //return null;
	}

	public Book findById(Long id) throws SQLException {
		Book book = null;
		try (PreparedStatement prepareStatement = this.connection.prepareStatement(FIND_BY_ID)) {
			prepareStatement.setLong(1, id);
			try (ResultSet resultSet = prepareStatement.executeQuery()) {
				while (resultSet.next()) {
					book = bookRowMapper.map(resultSet);
				}
			}
		}
		return book;
	}

	public void updateById(Book object) throws SQLException {
		try (PreparedStatement prepareStatement = this.connection.prepareStatement(UPDATE_BY_ID)) {
			prepareStatement.setString(1, object.getTitle());
			prepareStatement.setString(2, object.getIsbn());
			prepareStatement.setString(3, object.getDescription());
			prepareStatement.setDate(4, new java.sql.Date(object.getReleaseDate().getTime()));
			prepareStatement.setLong(5, object.getAuthor().getId());
			prepareStatement.setLong(6, object.getId());
			prepareStatement.executeUpdate();
		}
	}

	public void insert(Book object) throws SQLException {
		try (PreparedStatement prepareStatement = this.connection.prepareStatement(INSERT)) {
			prepareStatement.setString(1, object.getTitle());
			prepareStatement.setString(2, object.getIsbn());
			prepareStatement.setString(3, object.getDescription());
			prepareStatement.setDate(4, new java.sql.Date(object.getReleaseDate().getTime()));
			prepareStatement.setLong(5, object.getAuthor().getId());
			prepareStatement.executeUpdate();
		}
	}

	public void deleteById(Long id) throws SQLException {
	}

}
