package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
//import java.util.Locale;


import bean.postePayBean;
import it.unisa.DriverManagerConnectionPool;

public class PostePayModel {

	/**Ritorna l'elenco delle PostePay intestate al cliente
	 * @param cf il codice fiscale del cliente
	 * @return ritorna l'ArrayList in cui ogni PostePayBean contiene informazioni su una PostePay
	 * */
	public synchronized ArrayList<postePayBean> cercaPerCf(String cf) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL = "SELECT * FROM apre WHERE clienteCF=?";
		//postePayBean postepay= new postePayBean();
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
		//System.out.println("sono uscito dal primo try +"+ iban.size());
		ArrayList<postePayBean>arrayPP= new ArrayList<>();
		
		for(int i = 0; i< iban.size(); i++) {
		String selectSQL2 = "SELECT * FROM postePay WHERE iban=?"; 
			
			try {
				connection= DriverManagerConnectionPool.getConnection();
				preparedStatement= connection.prepareStatement(selectSQL2);
				preparedStatement.setString(1, iban.get(i));
	
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next()) {
					postePayBean ppb= new postePayBean();
					 ppb.setIban(rs.getString("iban"));
					 ppb.setNumCarta(rs.getInt("numCarta"));
					 Date data= rs.getDate("scadenza");
					 java.util.Date dataUtil= new java.util.Date(data.getTime());
					 ppb.setScadenza(dataUtil);
					 ppb.setCodSicur(rs.getInt("codSicur"));
					 arrayPP.add(ppb);
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
		return arrayPP;
	}
	
	
	/**Ritorna il postePayBean con le informazioni sulla posrtePay con un dato iban
	 * @param postePay l'iban della PostePay
	 * @return Ritorna il postePayBean con le informazioni sulla posrtePay
	 * */
	public synchronized postePayBean cercaByIban(String postePay) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM postePay WHERE iban= ?";
		postePayBean ppBean= new postePayBean();
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, postePay);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				ppBean.setNumCarta(rs.getInt("numCarta"));
				ppBean.setCodSicur(rs.getInt("codSicur"));
				Date data= rs.getDate("scadenza");
				//System.out.println("data: " +data);
				java.util.Date dataUtil= new java.util.Date(data.getTime());
				//System.out.println("dataUtil:"+dataUtil);
				//DateFormat dateFormat =DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.ITALY);
				//java.util.Date dataUtil=rs.getDate("scadenza");
				//System.out.println("dateFormat:"+dateFormat.format(dataUtil));
				ppBean.setScadenza(dataUtil);
				ppBean.setIban(rs.getString("iban"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return ppBean;
		}
	
	
	/**Memorizza le informazioni su una nuova PostePay
	 * @param iban l'iban della nuova PostePay
	 * @param cod il codice della nuova PostePay
	 * */
	public synchronized void creaPostePay(String iban, int cod) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL= "INSERT INTO postePay(iban, codSicur, scadenza) VALUES(?,?,?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, iban);
			preparedStatement.setInt(2, cod);
			java.util.Date dataUtil=new java.util.Date();
			Calendar dataOggi= Calendar.getInstance();
			dataOggi.setTime(dataUtil);
			dataOggi.add(Calendar.YEAR, +5);
			dataUtil=dataOggi.getTime();
			Date prima= new Date(dataUtil.getTime());
			preparedStatement.setDate(3, prima);
			
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