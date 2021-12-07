package br.com.elis.service.impl;

import java.sql.SQLException;
import java.util.Collection;

import br.com.elis.model.Author;
import br.com.elis.repository.AuthorRepository;
import br.com.elis.service.AuthorService;

public class AuthorServiceImpl implements AuthorService {

	private final AuthorRepository authorRepository;

	public AuthorServiceImpl(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	@Override
	public Collection<Author> findAll() throws SQLException {
		return authorRepository.findAll();
	}

	@Override
	public Author findById(Long id) throws SQLException {
		return this.authorRepository.findById(id);
	}

	@Override
	public void updateById(Author object) throws SQLException {
		Author author = new Author();
		this.authorRepository.updateById(author);
	}

	@Override
	public void insert(Author object) throws SQLException {
		this.authorRepository.insert(object);
	}

      	@Override
	public void deleteById(Long id) throws SQLException {
		this.authorRepository.deleteById(id);
	}

}
