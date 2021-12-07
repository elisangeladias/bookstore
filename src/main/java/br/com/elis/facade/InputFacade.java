package br.com.elis.facade;

import java.io.Closeable;

public interface InputFacade extends Closeable {
	
	long nextLong();
	
	int nextInt();
	
	String nextLine();

}
