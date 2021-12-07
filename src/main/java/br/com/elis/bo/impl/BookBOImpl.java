package br.com.elis.bo.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import br.com.elis.bo.BookBO;
import br.com.elis.bo.CrudBO;
import br.com.elis.facade.InputFacade;
import br.com.elis.facade.OutputFacade;
import br.com.elis.helper.ConfirmActionHelper;
import br.com.elis.helper.DateConversorHelper;
import br.com.elis.helper.PrinterHelper;
import br.com.elis.model.Author;
import br.com.elis.model.Book;
import br.com.elis.service.BookService;
import br.com.elis.validator.BookDescriptionValidator;
import br.com.elis.validator.BookIsbnValidator;
import br.com.elis.validator.BookReleaseDateValidator;
import br.com.elis.validator.BookTitleValidator;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookBOImpl implements BookBO {

	private final BookTitleValidator bookTitleValidator;

	private final BookDescriptionValidator bookDescriptionValidator;

	private final BookIsbnValidator bookIsbnValidator;

	private final BookReleaseDateValidator bookReleaseDateValidator;

	private final BookService bookService;

	private final CrudBO<Author> authorBO;

	private final InputFacade inputFacade;

	private final OutputFacade outputFacade;
        
        private final PrinterHelper<Book> printerHelper;
        
        private final ConfirmActionHelper confirmActionHelper;

	
	private final DateConversorHelper dateConversorHelper;


	public BookBOImpl(BookTitleValidator bookTitleValidator, BookDescriptionValidator bookDescriptionValidator,
			BookIsbnValidator bookIsbnValidator, BookReleaseDateValidator bookReleaseDateValidator,
			 InputFacade inputFacade, OutputFacade outputFacade,
			BookService bookService, CrudBO<Author> authorBO, 
			DateConversorHelper dateConversorHelper, PrinterHelper<Book> printerHelper, ConfirmActionHelper confirmActionHelper) {
		this.bookTitleValidator = bookTitleValidator;
		this.bookDescriptionValidator = bookDescriptionValidator;
		this.bookIsbnValidator = bookIsbnValidator;
		this.bookReleaseDateValidator = bookReleaseDateValidator;
		this.inputFacade = inputFacade;
		this.outputFacade = outputFacade;
		this.bookService = bookService;
		this.authorBO = authorBO;
		this.dateConversorHelper = dateConversorHelper;
                this.printerHelper = printerHelper;
                this.confirmActionHelper = confirmActionHelper;
	}

	@Override
	public void findAll() throws SQLException {
            Collection<Book> books = bookService.findAll();
		printerHelper.print(books);
                    
	}

	@Override
	public void delete() throws SQLException {
		
                Book book = this.findById();
		outputFacade.print(String.format("Tem certeza que deseja deletar o livro %s %s ?", book.getTitle(),
				confirmActionHelper.getOptions()));
		String feedback = inputFacade.nextLine();
		if (confirmActionHelper.confirm(feedback))
			bookService.deleteById(book.getId());
                //outputFacade.print("Termine a funcionalidade....");
	}

	@Override
	public void update() throws ParseException, SQLException {
		Book book = this.findById();
		boolean isValid = false;
		String title = null;
		do {
			outputFacade.print("Digite o titulo do livro:");
			title = inputFacade.nextLine();
			if (!title.isEmpty()) {
				isValid = bookTitleValidator.validate(title);
				if (isValid) {
					book.setTitle(title);
				} else {
					outputFacade.print("Titulo invalido");
				}
			} else {
				isValid = true;
			}
		} while (!isValid);

		String description = null;
		do {
			outputFacade.print("Digite a descrição do livro:");
			description = inputFacade.nextLine();
			if (!description.isEmpty()) {
				isValid = bookDescriptionValidator.validate(description);
				if (isValid) {
					book.setDescription(description);
				} else {
					outputFacade.print("Descrição invalida");
				}
			} else {
				isValid = true;
			}
		} while (!isValid);

		String isbn = null;
		do {
			outputFacade.print("Digite o ISBN do livro (Ex: 978-83-731-9172-3):");
			isbn = inputFacade.nextLine();
			if (!isbn.isEmpty()) {
				isValid = bookIsbnValidator.validate(isbn);
				if (isValid) {
					book.setIsbn(isbn);
				} else {
					outputFacade.print("ISBN invalido");
				}
			}

		} while (!isValid);

		do {
			outputFacade.print("Digite a data de nascimento do autor (Ex: 01/01/2020):");
			String releaseDateStr = inputFacade.nextLine();
			if (!releaseDateStr.isEmpty()) {
				isValid = bookReleaseDateValidator.validate(releaseDateStr);
				if (isValid) {
					book.setReleaseDate(dateConversorHelper.convert(releaseDateStr));
				} else {
					outputFacade.print("Data de nascimento invalido");
				}
			} else {
				isValid = true;
			}
		} while (!isValid);

		book.setAuthor(authorBO.findById());
		bookService.updateById(book);
	}

	@Override
	public void insert() throws ParseException, SQLException {
		String title = null;
		boolean isValid = false;
		do {
			outputFacade.print("Digite o titulo do livro:");
			title = inputFacade.nextLine();
			isValid = bookTitleValidator.validate(title);
			if (!isValid) {
				outputFacade.print("Titulo invalido");
			}
		} while (!isValid);

		String description = null;
		do {
			outputFacade.print("Digite a descrição do livro:");
			description = inputFacade.nextLine();
			isValid = bookDescriptionValidator.validate(description);
			if (!isValid) {
				outputFacade.print("Descrição invalida");
			}
		} while (!isValid);

		String isbn = null;
		do {
			outputFacade.print("Digite a ISBN do livro (Ex: 978-83-731-9172-3):");
			isbn = inputFacade.nextLine();
			isValid = bookIsbnValidator.validate(isbn);
			if (!isValid) {
				outputFacade.print("Isbn invalido");
			}
		} while (!isValid);

		Date releaseDate = null;
		do {
			outputFacade.print("Digite a data de nascimento do autor (Ex: 01/01/2020):");
			String releaseDateStr = inputFacade.nextLine();
			isValid = bookReleaseDateValidator.validate(releaseDateStr);
			if (!isValid) {
				outputFacade.print("Data de lançamento invalida");
			} else {
				releaseDate = new SimpleDateFormat("dd/MM/yyyy").parse(releaseDateStr);
			}
		} while (!isValid);

		Book book = new Book();
		book.setTitle(title);
		book.setDescription(description);
		book.setIsbn(isbn);
		book.setReleaseDate(releaseDate);
		book.setAuthor(authorBO.findById());
		bookService.insert(book);
	}

	@Override
	public Book findById() throws SQLException {
		this.findAll();
		Book book = null;
		do {
			outputFacade.print("Digite o ID do Livro:");
			long id = inputFacade.nextLong();
			book = bookService.findById(id);
			if (book == null) {
				outputFacade.print("Não foi encontrado Livro para o ID informado. Por favor, insira um ID válido.");
			}
		} while (book == null);

		return book;

	}

}
