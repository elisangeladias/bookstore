package br.com.elis.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import br.com.elis.mapper.AuthorRowMapper;
import br.com.elis.model.Author;
import br.com.elis.repository.AuthorRepository;
import java.sql.Date;

public class AuthorRepositoryImpl implements AuthorRepository {

	private static final String FIND_ALL = " SELECT * FROM AUTHOR ";

	private static final String FIND_BY_ID = " SELECT * FROM AUTHOR WHERE ID = ? ";

	private static final String UPDATE_BY_ID = " UPDATE AUTHOR SET NAME = ?, BIO = ?, BIRTHDATE = ? WHERE ID = ? ";

	private static final String INSERT = " INSERT INTO AUTHOR(NAME, BIO, BIRTHDATE) VALUES(?,?,?) ";

	private static final String DELETE = " DELETE FROM AUTHOR WHERE ID = ? ";

	private final Connection connection;

	private AuthorRowMapper<ResultSet> authorRowMapper;

	public AuthorRepositoryImpl(Connection connection, AuthorRowMapper<ResultSet> authorRowMapper) {
		this.connection = connection;
		this.authorRowMapper = authorRowMapper;
	}

	@Override
	public Collection<Author> findAll() throws SQLException {
		Collection<Author> authors = null;
		try (PreparedStatement prepareStatement = this.connection.prepareStatement(FIND_ALL)) {
			try (ResultSet resultSet = prepareStatement.executeQuery()) {
				authors = new ArrayList<>();
				while (resultSet.next()) {
					authors.add(authorRowMapper.map(resultSet));
				}
			}
		}
		return authors;
	}

	@Override
	public Author findById(Long id) throws SQLException {
		Author author = null;
		try (PreparedStatement prepareStatement = this.connection.prepareStatement(FIND_BY_ID)) {
			prepareStatement.setLong(1, id);
			try (ResultSet resultSet = prepareStatement.executeQuery()) {
				while (resultSet.next()) {
					author = authorRowMapper.map(resultSet);
				}
			}
		}
		return author;
	}

	@Override
	public void updateById(Author object) throws SQLException {
		try (PreparedStatement prepareStatement = this.connection.prepareStatement(UPDATE_BY_ID)) {
			prepareStatement.setString(4, object.getName());
			prepareStatement.setString(2, object.getBio());
			prepareStatement.setLong(1, object.getId());
			prepareStatement.executeQuery();
		}
	}

	@Override
	public void insert(Author object) throws SQLException {
		try (PreparedStatement prepareStatement = this.connection.prepareStatement(INSERT)) {
			prepareStatement.setString(1, object.getName());
			prepareStatement.setString(2, object.getBio());
                        prepareStatement.setDate(3, new java.sql.Date(object.getBirthdate().getTime()));
			prepareStatement.executeUpdate();
                      
                        
		}
	}

	@Override
	public void deleteById(Long id) throws SQLException {
		try (PreparedStatement prepareStatement = this.connection.prepareStatement(DELETE)) {
			prepareStatement.setLong(1, id);
			prepareStatement.executeUpdate();
		}
	}

}
