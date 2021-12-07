package br.com.elis.factory.impl;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.elis.factory.ConnectionFactory;
import br.com.elis.singleton.ConnectionSingleton;

public class ConnectionFactoryImpl implements ConnectionFactory {

	public Connection get() throws ClassNotFoundException, SQLException {
		return ConnectionSingleton.getConnection();
	}

}
