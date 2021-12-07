package br.com.elis.init.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.elis.bo.AuthorBO;
import br.com.elis.bo.BookBO;
import br.com.elis.bo.MenuBO;
import br.com.elis.bo.impl.AuthorBOImpl;
import br.com.elis.bo.impl.BookBOImpl;
import br.com.elis.bo.impl.MenuBOImpl;
import br.com.elis.facade.InputFacade;
import br.com.elis.facade.OutputFacade;
import br.com.elis.facade.impl.InputScannerFacadeImpl;
import br.com.elis.facade.impl.OutputSysOutFacadeImpl;
import br.com.elis.factory.ConnectionFactory;
import br.com.elis.factory.impl.ConnectionFactoryImpl;
import br.com.elis.helper.ConfirmActionHelper;
import br.com.elis.helper.DateConversorHelper;
import br.com.elis.helper.PrinterHelper;
import br.com.elis.helper.impl.ConfirmActionHelperImpl;
import br.com.elis.helper.impl.DateConversorHelperImpl;
import br.com.elis.helper.impl.PrinterHelperImpl;
import br.com.elis.init.Init;
import br.com.elis.mapper.AuthorRowMapper;
import br.com.elis.mapper.BookRowMapper;
import br.com.elis.mapper.impl.AuthorResultSetRowMapperImpl;
import br.com.elis.mapper.impl.BookResultSetRowMapperImpl;
import br.com.elis.model.Author;
import br.com.elis.model.Book;
import br.com.elis.repository.AuthorRepository;
import br.com.elis.repository.BookRepository;
import br.com.elis.repository.impl.AuthorRepositoryImpl;
import br.com.elis.repository.impl.BookRepositoryImpl;
import br.com.elis.service.AuthorService;
import br.com.elis.service.BookService;
import br.com.elis.service.impl.AuthorServiceImpl;
import br.com.elis.service.impl.BookServiceImpl;
import br.com.elis.validator.AuthorBioValidator;
import br.com.elis.validator.AuthorBirthdateValidator;
import br.com.elis.validator.AuthorNameValidator;
import br.com.elis.validator.BookDescriptionValidator;
import br.com.elis.validator.BookIsbnValidator;
import br.com.elis.validator.BookReleaseDateValidator;
import br.com.elis.validator.BookTitleValidator;
import br.com.elis.validator.impl.AuthorBioValidatorImpl;
import br.com.elis.validator.impl.AuthorBirthdateValidatorImpl;
import br.com.elis.validator.impl.AuthorNameValidatorImpl;
import br.com.elis.validator.impl.BookDescriptionValidatorImpl;
import br.com.elis.validator.impl.BookIsbnValidatorImpl;
import br.com.elis.validator.impl.BookReleaseDateValidatorImpl;
import br.com.elis.validator.impl.BookTitleValidatorImpl;

public class InitImpl implements Init {

	public void execute() {

		// connection
		ConnectionFactory connectionFactory = new ConnectionFactoryImpl();
		try (Connection connection = connectionFactory.get(); InputFacade inputFacade = new InputScannerFacadeImpl()) {
			OutputFacade outputFacade = new OutputSysOutFacadeImpl();
			// mapper
			AuthorRowMapper<ResultSet> authorRowMapper = new AuthorResultSetRowMapperImpl();
			BookRowMapper<ResultSet> bookRowMapper = new BookResultSetRowMapperImpl(authorRowMapper);

			// repository
			AuthorRepository authorRepository = new AuthorRepositoryImpl(connection, authorRowMapper);
			BookRepository bookRepository = new BookRepositoryImpl(connection, bookRowMapper);

			// service
			AuthorService authorService = new AuthorServiceImpl(authorRepository);
			BookService bookService = new BookServiceImpl(bookRepository);

			// convertDate
			DateConversorHelper dateConvertConversorHelper = new DateConversorHelperImpl();

			// validator
			// author
			AuthorNameValidator authorNameValidator = new AuthorNameValidatorImpl();
			AuthorBioValidator authorBioValidator = new AuthorBioValidatorImpl();
			AuthorBirthdateValidator authorBirthdateValidator = new AuthorBirthdateValidatorImpl(
					dateConvertConversorHelper);

			// book
			BookTitleValidator bookTitleValidator = new BookTitleValidatorImpl();
			BookDescriptionValidator bookDescriptionValidator = new BookDescriptionValidatorImpl();
			BookIsbnValidator bookIsbnValidator = new BookIsbnValidatorImpl();
			BookReleaseDateValidator bookReleaseDateValidator = new BookReleaseDateValidatorImpl(
					dateConvertConversorHelper);

			// printer
			PrinterHelper<Author> printerAuthorHelper = new PrinterHelperImpl<Author>();
			PrinterHelper<Book> printerBookHelper = new PrinterHelperImpl<Book>();

			// confirm action
			ConfirmActionHelper confirmActionHelper = new ConfirmActionHelperImpl();

			// bo
			AuthorBO authorBO = new AuthorBOImpl(authorNameValidator, authorBioValidator, authorBirthdateValidator,
					printerAuthorHelper, inputFacade, outputFacade, authorService, confirmActionHelper,
					dateConvertConversorHelper);

			BookBO bookBO = new BookBOImpl(bookTitleValidator, bookDescriptionValidator, bookIsbnValidator, 
					bookReleaseDateValidator,  inputFacade, outputFacade, bookService, authorBO,
					 dateConvertConversorHelper, printerBookHelper, confirmActionHelper);

			MenuBO menuBO = new MenuBOImpl(inputFacade, outputFacade, authorBO, bookBO);

			menuBO.loadMenu();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
