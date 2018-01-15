package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unisa.DriverManagerConnectionPool;

public class ContoModel {
	
	private static int numero=10001;

	/**ritorna il saldo di un conto
	 * @param iban l'iban del conto
	 * @return ritorna il saldo del conto
	 * */
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

	/**ritorna true se esiste un conto con quell'iban, false altrimenti
	 * @param iban l'iban del conto
	 * @return ritorna true se esiste un conto con quell'iban, false altrimenti
	 * */
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

	/**Memorizza informazioni su un nnuovo conto intestato ad un cliente
	 * @param cf il codice fiscale del cliente
	 * @return ritorna l'iban del nuovo conto
	 * */
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
	
	/**Ritorna la somma dei saldi dei conti intestati al cliente
	 * @param cf il codice fiscale del cliente
	 * @return Ritorna la somma dei saldi dei conti intestati al cliente
	 * */
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