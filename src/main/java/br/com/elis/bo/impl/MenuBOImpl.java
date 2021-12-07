package br.com.elis.bo.impl;

import br.com.elis.bo.AuthorBO;
import br.com.elis.bo.BookBO;
import br.com.elis.bo.MenuBO;
import br.com.elis.facade.InputFacade;
import br.com.elis.facade.OutputFacade;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class MenuBOImpl implements MenuBO {

	private final InputFacade inputFacade;
	
	private final OutputFacade outputFacade;
	
	private final AuthorBO authorBO;

	private final BookBO bookBO;
	
	public MenuBOImpl(InputFacade inputFacade, OutputFacade outputFacade, AuthorBO authorBO, BookBO bookBO) {
		this.inputFacade = inputFacade;
		this.outputFacade = outputFacade;
		this.authorBO = authorBO;
		this.bookBO = bookBO;
	}
	
	public void loadMenu() {
		int option;

		try {

			do {
				outputFacade.print( "____________________________________________\n"
		        		+ "__BookStore__\n"
		        		+ "\n"
		        		+ "Digite a opção desejada: 			 \n"
		        		+ "(1)  Mostrar Todos os Livros	 	     \n"
		        		+ "(2)  Cadastrar Novo Livro 		     \n"
		        		+ "(3)  Alterar Dados do Livro	         \n"
		        		+ "(4)  Deletar Cadastro de Livro	     \n"
		        		+ "(5)  Mostrar todos os Autores 	     \n"
		        		+ "(6)  Cadastrar Autores 			     \n"
		        		+ "(7)  Alterar Dados Autores 		     \n"
		        		+ "(8)  Deletar Autor 			         \n"
		        		+ "(0) Sair								 \n"
		        		+ "\n"
		        		+ "Opção: "
		        );
				option = inputFacade.nextInt();
				switch (option) {
				case 1:
					bookBO.findAll();
                                        break;                  
                                     					
				case 2:
					try {
						System.out.print("\n##### Cadastrar Novo Livro ######\n\n");
						bookBO.insert();
						System.out.print("LIVRO CADASTRADO COM SUCESSO.\n\n");

					} catch (Exception e) {
						System.out.print("Ocorreu um erro ao cadastrar novo livro.\n");
						e.printStackTrace();
						option = 0;
					}
					break;

				case 3:
					try {
						System.out.print("\n##### Alterar Dados do Livro ###### \n\n");
						bookBO.update();
						System.out.print("LIVRO ALTERADO COM SUCESSO.\n\n");
					} catch (Exception e) {
						System.out.print("Ocorreu um erro ao atualizar as informações do livro.\n");
						e.printStackTrace();
						option = 0;
					}
					break;
				case 4:
					try {
						System.out.print("\n##### Deletar Livro ##### \n\n");
						bookBO.delete();
						System.out.println("LIVRO DELETADO COM SUCESSO.\n\n");
					} catch (Exception e) {
						System.out.print("Ocorreu um erro ao deletar o funcionário.");
						e.printStackTrace();
						option = 0;
					}
					break;
				case 5:
					try {
						authorBO.findAll();
					} catch (Exception e) {
						System.out.print("Ocorreu um erro ao exibir os autores.");
						e.printStackTrace();
						option = 0;
					}
					break;
				case 6:
					authorBO.insert();
					break;
				case 7:
					try {
						System.out.print("\n##### Alterar Dados do Autor ###### \n\n");
						authorBO.update();
						System.out.print("AUTOR ALTERADO COM SUCESSO.\n\n");
					} catch (Exception e) {
						System.out.print("Ocorreu um erro ao atualizar as informações do autor.\n");
						e.printStackTrace();
						option = 0;
					}
					break;
				case 8:
					try {
						System.out.print("\n##### Deletar Autor ###### \n\n");
						authorBO.delete();
						System.out.print("AUTOR DELETADO COM SUCESSO.\n\n");
					} catch (Exception e) {
						System.out.print("Ocorreu um erro ao deletar o Autor.\n");
						e.printStackTrace();
						option = 0;
					}
					break;
				case 0:
					System.out.println("Saindo...");
					break;
				default:
					System.out.println("Digite uma opção válida:");
					break;
				}

			} while (option != 0);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
