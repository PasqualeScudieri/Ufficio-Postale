package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.PacchiBean;
import it.unisa.DriverManagerConnectionPool;

public class PacchiModel {
	public synchronized PacchiBean cercaPacco(int codice) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL= "SELECT * FROM pacchi WHERE codice=?";
		PacchiBean pacco=new PacchiBean();
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, codice);

			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				pacco.setCodice(rs.getInt("codice"));
				pacco.setPeso(rs.getDouble("peso"));
				pacco.setVolume(rs.getDouble("volume"));
			}
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}		
		}
	
		return pacco;
	}

	public synchronized void inserisciInPa(PacchiBean pacchi) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL="INSERT INTO pacchi (peso, volume, codice) VALUES(?,?,?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setDouble(1, pacchi.getPeso());
			preparedStatement.setDouble(2, pacchi.getVolume());
			preparedStatement.setInt(3, pacchi.getCodice());
			
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
