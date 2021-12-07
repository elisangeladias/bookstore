package br.com.elis.service.impl;

import java.sql.SQLException;
import java.util.Collection;

import br.com.elis.model.Book;
import br.com.elis.repository.BookRepository;
import br.com.elis.service.BookService;

public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;

	public BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public Collection<Book> findAll() throws SQLException {
            return bookRepository.findAll();
            //return null;
	}

	@Override
	public Book findById(Long id) throws SQLException {
		return this.bookRepository.findById(id);
	}

	@Override
	public void updateById(Book object) throws SQLException {
		this.bookRepository.updateById(object);
	}

	@Override
	public void insert(Book object) throws SQLException {
		this.bookRepository.insert(object);

	}

	@Override
	public void deleteById(Long id) throws SQLException {
	}

}
