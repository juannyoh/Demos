package com.ouye.datasource;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;

public class SchemaBasedMultiTenantConnectionProvider implements
		MultiTenantConnectionProvider {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final DriverManagerConnectionProviderImpl connectionProvider = new DriverManagerConnectionProviderImpl();

	@Override
	public Connection getConnection(String tenantIdentifier)
			throws SQLException {
		final Connection connection = connectionProvider.getConnection();
		connection.createStatement().execute("USE " + tenantIdentifier);

		return connection;
	}

	@Override
	public void releaseConnection(String tenantIdentifier, Connection connection)
			throws SQLException {
		connection.createStatement().execute("USE egisp_dev");
		connectionProvider.closeConnection(connection);
	}

	@Override
	public boolean isUnwrappableAs(Class unwrapType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> unwrapType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connection getAnyConnection() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void releaseAnyConnection(Connection connection) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean supportsAggressiveRelease() {
		// TODO Auto-generated method stub
		return false;
	}

}