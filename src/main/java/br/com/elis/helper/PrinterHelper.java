package br.com.elis.helper;

import java.util.Collection;

import br.com.elis.model.Printeable;

public interface PrinterHelper<T extends Printeable> {
	
	void print(Collection<T> printeable);

}
