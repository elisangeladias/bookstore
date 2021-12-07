package br.com.elis;

import br.com.elis.init.Init;
import br.com.elis.init.impl.InitImpl;

public class App {
	
	public static void main(String[] args) {
		Init init = new InitImpl();
		init.execute();
	}
}
