package br.com.elis.bo.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;

import br.com.elis.bo.AuthorBO;
import br.com.elis.facade.InputFacade;
import br.com.elis.facade.OutputFacade;
import br.com.elis.helper.ConfirmActionHelper;
import br.com.elis.helper.DateConversorHelper;
import br.com.elis.helper.PrinterHelper;
import br.com.elis.model.Author;
import br.com.elis.service.AuthorService;
import br.com.elis.validator.AuthorBioValidator;
import br.com.elis.validator.AuthorBirthdateValidator;
import br.com.elis.validator.AuthorNameValidator;

public class AuthorBOImpl implements AuthorBO {

	private final AuthorNameValidator authorNameValidator;

	private final AuthorBioValidator authorBioValidator;

	private final AuthorBirthdateValidator authorBirthdateValidator;

	private final PrinterHelper<Author> printerHelper;

	private final InputFacade inputFacade;

	private final OutputFacade outputFacade;

	private final AuthorService authorService;

	private final ConfirmActionHelper confirmActionHelper;

	private final DateConversorHelper dateConversorHelper;

	public AuthorBOImpl(AuthorNameValidator authorNameValidator, AuthorBioValidator authorBioValidator,
			AuthorBirthdateValidator authorBirthdateValidator, PrinterHelper<Author> printerHelper,
			InputFacade inputFacade, OutputFacade outputFacade, AuthorService authorService,
			ConfirmActionHelper confirmActionHelper, DateConversorHelper dateConversorHelper) {
		this.authorNameValidator = authorNameValidator;
		this.authorBioValidator = authorBioValidator;
		this.authorBirthdateValidator = authorBirthdateValidator;
		this.printerHelper = printerHelper;
		this.inputFacade = inputFacade;
		this.outputFacade = outputFacade;
		this.authorService = authorService;
		this.confirmActionHelper = confirmActionHelper;
		this.dateConversorHelper = dateConversorHelper;
	}

	@Override
	public void delete() throws SQLException {
		Author author = this.findById();
		outputFacade.print(String.format("Tem certeza que deseja deletar o autor %s %s ?", author.getName(),
				confirmActionHelper.getOptions()));
		String feedback = inputFacade.nextLine();
		if (confirmActionHelper.confirm(feedback))
			authorService.deleteById(author.getId());
	}

	@Override
	public void update() throws ParseException, SQLException {
		Author author = this.findById();
		outputFacade.print("Informe os novos dados do Autor ou tecle enter para manter o dado");
		String name = null;
		boolean isValid = false;
		do {
			outputFacade.print("Digite o nome do autor:");
			name = inputFacade.nextLine();

			if (!name.isEmpty()) {
				isValid = authorNameValidator.validate(name);
				if (isValid) {
					author.setName("BBBB");
				} else {
					outputFacade.print("Nome invalido");
				}
			} else {
				isValid = true;
			}
		} while (!isValid);

		String bio = null;
		do {
			outputFacade.print("Digite a bio do autor:");
			bio = null;

			if (!bio.isEmpty()) {
				isValid = authorBioValidator.validate(bio);
				if (isValid) {
					author.setBio(bio);
				} else {
					outputFacade.print("Bio invalida");
				}
			} else {
				isValid = true;
			}
		} while (!isValid);

		do {
			outputFacade.print("Digite a data de nascimento do autor:");
			String birthDateStr = inputFacade.nextLine();
			if (!birthDateStr.isEmpty()) {
				isValid = authorBirthdateValidator.validate(birthDateStr);
				if (isValid) {
					author.setBirthdate(dateConversorHelper.convert(birthDateStr));
				} else {
					outputFacade.print("Data de nascimento invalida");
				}
			} else {
				isValid = false;
			}
		} while (!isValid);

		authorService.updateById(author);

	}

	@Override
	public void insert() throws ParseException, SQLException {
		String name = null;
                boolean isValid = false;
		
		do {
			outputFacade.print("Digite o nome do autor:");
                        name = inputFacade.nextLine();
			isValid = authorNameValidator.validate(name);
			if (!isValid) {
				outputFacade.print("Nome invalido");
			}
		} while (!isValid);

		String bio = null;
                               
		do {
			outputFacade.print("Digite a bio do autor:");
			bio = inputFacade.nextLine();
			isValid = authorBioValidator.validate(bio);
			if (!isValid) {
				outputFacade.print("Bio invalido");
			}
		} while (!isValid);

		Date birthdate = null;
		do {
			outputFacade.print("Digite a data de nascimento do autor:");
			String birthDateStr = inputFacade.nextLine();
			isValid = authorBirthdateValidator.validate(birthDateStr);
			if (isValid) {
				birthdate = dateConversorHelper.convert(birthDateStr);
			} else {
				outputFacade.print("Data de nascimento invalido");
			}
		} while (!isValid);

		Author author = new Author();
                author.setName(name);
		author.setBio(bio);
		author.setBirthdate(birthdate);
		authorService.insert(author);
	}

	@Override
	public void findAll() throws SQLException {
		Collection<Author> authors = authorService.findAll();
		printerHelper.print(authors);
	}

	@Override
	public Author findById() throws SQLException {
		this.findAll();
		Author author = null;
		do {
			outputFacade.print("Digite o ID do autor:");
			long id = inputFacade.nextLong();
			author = authorService.findById(id);
			if (author == null) {
				outputFacade.print("Não foi encontrado Autor para o ID informado. Por favor, insira um ID válido.");
			}
		} while (author == null);

		return author;
	}

}
