package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unisa.DriverManagerConnectionPool;
import bean.UtenteBean;

public class UtenteModel {
	/*
	 * ma qua non dovrei fare il controllo del se esite il cliente con quel cf
	 * oppure mi faccio il controllo sulla esl exception? ammesso che si possa fare
	 * */
	public synchronized void doSave(UtenteBean utente) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL="INSERT INTO utenteRegistrato (cliente,user,password) VALUES(?,?,?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, utente.getCliente());
			preparedStatement.setString(2, utente.getUser());
			preparedStatement.setString(3, utente.getPassword());

			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
	
	public synchronized UtenteBean esisteUser(String user, String password) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM utenteRegistrato WHERE user= ? AND password=?";
		UtenteBean utente= new UtenteBean();
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, user);
			preparedStatement.setString(2, password);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				utente.setCliente(rs.getString("cliente"));
				utente.setUser(rs.getString("USER"));
				utente.setPassword(rs.getString("password"));
				utente.setTipo(rs.getString("tipo"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return utente;
	}
	
	/*
	 * Qua devo fare il controllo su null caso mai nn esiste?
	 * */
	public synchronized UtenteBean doRetrieveByUser(String user) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM utenteRegistrato WHERE user= ?";
		UtenteBean utente= new UtenteBean();
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, user);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				utente.setCliente(rs.getString("cliente"));
				utente.setUser(rs.getString("USER"));
				utente.setPassword(rs.getString("password"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return utente;
	}

	public synchronized boolean esisteCf(String cf) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM utenteRegistrato WHERE cliente= ?";
		boolean esiste=false;
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, cf);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				esiste=true;
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return esiste;
	}

	
	public synchronized void setPasswordDip(String name, String password) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String update="UPDATE utenteRegistrato SET password=? WHERE user=?";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(update);			
			preparedStatement.setString(1, password);
			preparedStatement.setString(2, name);
			preparedStatement.executeUpdate();
			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
	
	
}
