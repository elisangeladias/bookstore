package br.com.elis.facade.impl;

import br.com.elis.facade.OutputFacade;

public class OutputSysOutFacadeImpl implements OutputFacade {

	@Override
	public void print(String value) {
		System.out.println(value);
	}

}
