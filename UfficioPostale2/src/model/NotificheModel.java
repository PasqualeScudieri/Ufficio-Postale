package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.NotificheBean;
import it.unisa.DriverManagerConnectionPool;

public class NotificheModel {

	/**Ritorna l'elenco delle notifiche di un cliente
	 * @param cf il dofice fiscale del cliente
	 * @return l'arrayList in cui ogni NotificaBean contine informazioni su una notifica
	 * */
	public synchronized ArrayList<NotificheBean> cercaByCf(String cf) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM notifiche WHERE cliente= ?";

		ArrayList<NotificheBean> array= new ArrayList<>();
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, cf);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				NotificheBean notifica=new NotificheBean();
				notifica.setCf(rs.getString("cliente"));
				notifica.setMatricola(rs.getInt("dipendente"));
				notifica.setTipo(rs.getString("tipo"));
				notifica.setCodice(rs.getInt("codice"));
				notifica.setIban(rs.getString("iban"));
				notifica.setCodPosta(rs.getInt("codPosta"));
				array.add(notifica);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return array;
	}
	
	/**Memorizza le informazioni su una notifica
	 * @param cliente il codice fiscale del cliente
	 * @param matricola la matricola del dipendente
	 * @param tipo il tipo di notifica
	 * @param iban l'iban del conto a cui è riferita la notifica
	 * @param codPosta il codice della posta a cui è riferita la notifica 
	 * */
	public synchronized void inserisciNotifica(String cliente, int matricola, String tipo, String iban, int codPosta) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL="INSERT INTO notifiche (cliente, dipendente,tipo,iban, codPosta) VALUES(?,?,?,?,?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, cliente);
			preparedStatement.setInt(2, matricola);
			preparedStatement.setString(3, tipo);
			preparedStatement.setString(4, iban);
			preparedStatement.setInt(5, codPosta);
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

	/**Rimuove la notifica con quel codice
	 * @param codice il codice della notifica
	 * */
	public synchronized void cancellaNotifica(int codice) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String deleteSQL="delete  from notifiche where codice=?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, codice);

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
