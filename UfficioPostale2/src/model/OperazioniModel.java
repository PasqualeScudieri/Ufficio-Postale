package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.OperazioniBean;
import it.unisa.DriverManagerConnectionPool;

public class OperazioniModel {

	/**Restituisce l'elenco delle operazioni in cui è coinvolto un conto
	 * @param iban l'iban del conto
	 * @return Restituisce l'elenco delle operazioni in cui è coinvolto il conto
	 * */
	public synchronized ArrayList<OperazioniBean> cercaOperazioni(String iban) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL = "SELECT * FROM interessatoDa WHERE ibanConto=?";
		ArrayList<Integer> codici= new ArrayList<>();
		ArrayList<Character> segni= new ArrayList<>();
		try {
			connection= DriverManagerConnectionPool.getConnection();
			preparedStatement= connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, iban);

			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				 codici.add(rs.getInt("codOperazione"));
				 segni.add(rs.getString("segno").charAt(0));
			}
			
			if(codici.size() == 0) {
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
		
		ArrayList<OperazioniBean> arrayOpp= new ArrayList<>();
		
		for(int i = 0; i< codici.size(); i++) {
			String selectSQL1= "SELECT * FROM operazione WHERE codice=?";
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL1);
				preparedStatement.setInt(1, codici.get(i));
	
				ResultSet rs = preparedStatement.executeQuery();
				
				while (rs.next()) {
					  OperazioniBean operazioni= new OperazioniBean();
					  operazioni.setCodice(rs.getInt("codice"));
					  operazioni.setImporto(rs.getDouble("importo"));
					  Date data= rs.getDate("dataOper");
//					  java.util.Date dataUtil= new java.util.Date(data.getTime());
					  operazioni.setDataOper(data);
					  operazioni.setTipo(rs.getString("tipo"));
					  operazioni.setSegno(segni.get(i));
					  arrayOpp.add(operazioni);
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
		return arrayOpp;
		
	}
	
	/** Memorizza una nuova operazione
	 * @param ibanFrom l'iban del conto da cui sono prelevati i soldi
	 * @param ibanTo l'iban del conto su cui vengono versati i soldi
	 * @param importo l'importo trasferito
	 * */
	public synchronized void Operazione(String ibanFrom, String ibanTo, Double importo) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL="INSERT INTO operazione (importo, dataOper, tipo) VALUES(?,?,?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setDouble(1, importo);
			java.util.Date dataUtil=new java.util.Date();
			Date prima= new Date(dataUtil.getTime());
			preparedStatement.setDate(2, prima);
			preparedStatement.setString(3, "online");
			
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
		
		
		String selectSQL= "SELECT MAX(codice) FROM operazione";
		int codice = 0;
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();


			while (rs.next()) {
				codice=rs.getInt("MAX(codice)");
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		
		String insertSQL1= "INSERT INTO interessatoDa (ibanConto, codOperazione, segno) VALUES (?,?,?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL1);
			
			preparedStatement.setString(1, ibanFrom);
			preparedStatement.setInt(2, codice);
			preparedStatement.setString(3, "-");
			
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
		
		String insertSQL2= "INSERT INTO interessatoDa (ibanConto, codOperazione, segno) VALUES (?,?,?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL2);
			
			preparedStatement.setString(1, ibanTo);
			preparedStatement.setInt(2, codice);
			preparedStatement.setString(3, "+");
			
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
		
		String update1="UPDATE conto SET saldo=saldo-? WHERE iban=?";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(update1);			
			preparedStatement.setDouble(1, importo);
			preparedStatement.setString(2, ibanFrom);
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

		String update2="UPDATE conto SET saldo=saldo+? WHERE iban=?";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(update2);			
			preparedStatement.setDouble(1, importo);
			preparedStatement.setString(2, ibanTo);
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
