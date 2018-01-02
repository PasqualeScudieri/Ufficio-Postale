package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unisa.DriverManagerConnectionPool;

public class ContoModel {
	
	private static int numero=10001;

	public synchronized int cercaSaldo(String iban) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL= "SELECT * FROM conto WHERE iban=?";
	
		int saldo=0;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, iban);

			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				saldo = rs.getInt("saldo");
				
			}
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}		
		}
	
		return saldo;
	}

	public synchronized boolean cercaConto(String iban) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL= "SELECT * FROM conto WHERE iban=?";
	
		boolean trovato=false;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, iban);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				trovato=true;
			}
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}		
		}
	
		return trovato;
	}

	
	public synchronized String creaConto(String cf) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL= "INSERT INTO conto(IBAN) VALUES(?)";
		
		String iban= "IT76P07601034001000000"+numero;
		
		numero++;
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, iban);
		
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
		
		String insertSQL1= "INSERT INTO apre(ibanConto ,clienteCf) VALUES(?, ?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL1);
			preparedStatement.setString(1, iban);
			preparedStatement.setString(2, cf);
		
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
		
		return iban;
	}
	
	public synchronized int cercaPerCf(String cf) throws SQLException{
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    
	    String selectSQL = "SELECT sum(conto.saldo) FROM apre, conto WHERE apre.ibanConto=conto.iban && apre.clienteCF=?;";
	    
	    int saldo=0;
	    try {
	      connection= DriverManagerConnectionPool.getConnection();
	      preparedStatement= connection.prepareStatement(selectSQL);
	      preparedStatement.setString(1, cf);

	      ResultSet rs = preparedStatement.executeQuery();
	      
	      while(rs.next()) {
	         saldo=rs.getInt("sum(conto.saldo)");
	      }
	      
	      
	    } finally {
	      try {
	        if (preparedStatement != null)
	          preparedStatement.close();
	      } finally {
	        DriverManagerConnectionPool.releaseConnection(connection);
	      }    
	    }
	    
	    return saldo;
	}
}