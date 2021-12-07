package br.com.elis.facade.impl;

import java.io.IOException;
import java.util.Scanner;

import br.com.elis.facade.InputFacade;

public class InputScannerFacadeImpl implements InputFacade {

	private static final Scanner SCANNER = new Scanner(System.in);

	@Override
	public long nextLong() {
		long value = SCANNER.nextLong();
		SCANNER.nextLine();
		return value;
	}

	@Override
	public int nextInt() {
		int value = SCANNER.nextInt();
		SCANNER.nextLine();
		return value;
	}

	@Override
	public String nextLine() {
		return SCANNER.nextLine();
	}

	@Override
	public void close() throws IOException {
		SCANNER.close();
	}

}
