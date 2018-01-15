package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.BancopostaBean;
import it.unisa.DriverManagerConnectionPool;

public class BancoPostaModel {
/**
 * Questo metodo effettua la ricerca dei bancoposta intestati ad un cliente
 * @param cf il codice fiscale del cliente
 * @return ritrona l'array contiene i dati sui bancoposta intestati al cliente
 * */
	public synchronized ArrayList<BancopostaBean> cercaPerCf(String cf) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL = "SELECT * FROM apre WHERE clienteCF=?";
		ArrayList<String> iban= new ArrayList<>();
		try {
			connection= DriverManagerConnectionPool.getConnection();
			preparedStatement= connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, cf);

			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				 iban.add(rs.getString("ibanConto"));
			}
			
			if(iban.size() == 0) {
				return null;
			}
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}		
		}
		//
	
		ArrayList<BancopostaBean>arrayBanc= new ArrayList<>();
		
		for(int i = 0; i< iban.size(); i++) {
		String selectSQL2 = "SELECT * FROM bancoposta WHERE iban=?"; 
			
			try {
				connection= DriverManagerConnectionPool.getConnection();
				preparedStatement= connection.prepareStatement(selectSQL2);
				preparedStatement.setString(1, iban.get(i));
	
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next()) {
					 BancopostaBean bpb= new BancopostaBean();
					 bpb.setTasso(rs.getDouble("tassoInt"));
					 bpb.setCarta(rs.getString("carta").charAt(0));
					 bpb.setCosto(rs.getDouble("costo"));
					 bpb.setServInternet(rs.getString("servInternet").charAt(0));
					 bpb.setIban(rs.getString("iban"));
					 arrayBanc.add(bpb);
				}
				
			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}		
			}
		
		}
		return arrayBanc;
	}
	
	/**ritorna le informazioni sul Bancoposta con quell'iban
	 * @param bancoposta l'IBAN del BancoPosta
	 * @return ritorna il BancopostaBean con le informazioni sul Bancoposta
	 * */
	public synchronized BancopostaBean cercaByIban(String bancoposta) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM bancoposta WHERE iban= ?";
		BancopostaBean bpBean= new BancopostaBean();
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, bancoposta);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				bpBean.setCarta(rs.getString("carta").charAt(0));
				bpBean.setCosto(rs.getDouble("costo"));
				bpBean.setServInternet(rs.getString("servInternet").charAt(0));
				bpBean.setTasso(rs.getDouble("tassoInt"));
				bpBean.setIban(rs.getString("iban"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return bpBean;
	}


	/**Memorizza le informazioni passate come un BancoPsota
	 * @param iban l'iban del nuovo BancoPosta
	 * @param tasso il tasso del nuovo BancoPosta
	 * @param costo il costo annuo del nuovo BancoPosta
	 * @param carta indica se il BancoPosta ha o meno associata una carta bancomat
	 * @param servInternet indica se i servizi internet sono attivi o meno
	 * */
	public synchronized void creaBancoPosta(String iban, double tasso, double costo, char carta, char servInternet ) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	
		String insertSQL= "INSERT INTO bancoposta(iban,tassoInt,costo,servInternet, carta) VALUES(?,?,?,?,?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, iban);
			preparedStatement.setDouble(2, tasso);
			preparedStatement.setDouble(3, costo);
			preparedStatement.setString(4, ""+servInternet);
			preparedStatement.setString(5, ""+carta);
			
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
	
	/**Attiva i servizi internet su un conto BancoPosta
	 * @param iban l'iban del conto BancoPosta di cui si vogliono attivare i servizi internet
	 * */
	public synchronized void siServiziInternet(String iban) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String update="UPDATE bancoposta SET servInternet='y' WHERE iban=?";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(update);			
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
	}	
	
	/**Associa una carta al conto BancoPosta
	 * @param iban l'iban del conto BancoPosta a cui vogliamo associare la carta
	 * */
	public synchronized void siCarta(String iban) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String update="UPDATE bancoposta SET carta='y' WHERE iban=?";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(update);			
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
	}	
	
	/**Modifica il costo annuo di un BancoPosta
	 * @param costo il nuovo costo annuo
	 * @param iban l'iban del BancoPosta a cui va aggiornato il costo
	 * */
	public synchronized void aggiornaCosto(Double costo, String iban) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String update="UPDATE bancoposta SET costo=? WHERE iban=?";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(update);			
			preparedStatement.setDouble(1, costo);
			preparedStatement.setString(2, iban);
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