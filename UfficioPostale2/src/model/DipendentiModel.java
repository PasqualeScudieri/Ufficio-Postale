package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.DipendentiBean;
import it.unisa.DriverManagerConnectionPool;

public class DipendentiModel {
	public synchronized DipendentiBean cercaByCf(String cf) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM dipendenti WHERE cf= ?";
		DipendentiBean dipendente= new DipendentiBean();
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, cf);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				dipendente.setNome(rs.getString("nome"));
				dipendente.setCognome(rs.getString("cognome"));
				dipendente.setCf(rs.getString("CF"));
				dipendente.setLuogonascita(rs.getString("luogonascita"));
				dipendente.setIndirizzo(rs.getString("indirizzo"));
				Date data= rs.getDate("datanascita");
				java.util.Date dataUtil= new java.util.Date(data.getTime());
				dipendente.setDataNascita(dataUtil);
				dipendente.setMatricola(rs.getInt("matricola"));
				dipendente.setTipo(rs.getString("tipo"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return dipendente;
	}

	public synchronized DipendentiBean cercaByMatricola(int matricola) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM dipendenti WHERE matricola= ?";
		DipendentiBean dipendente= new DipendentiBean();
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, matricola);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				dipendente.setNome(rs.getString("nome"));
				dipendente.setCognome(rs.getString("cognome"));
				dipendente.setCf(rs.getString("CF"));
				dipendente.setLuogonascita(rs.getString("luogonascita"));
				dipendente.setIndirizzo(rs.getString("indirizzo"));
				Date data= rs.getDate("datanascita");
				java.util.Date dataUtil= new java.util.Date(data.getTime());
				dipendente.setDataNascita(dataUtil);
				dipendente.setMatricola(rs.getInt("matricola"));
				dipendente.setTipo(rs.getString("tipo"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return dipendente;
	}

	public synchronized void inserisciDipendente(DipendentiBean dipendente) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL="INSERT INTO dipendenti (nome, cognome ,CF, luogonascita, datanascita, indirizzo, tipo, matricola) VALUES(?,?,?,?,?,?,?,?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, dipendente.getNome());
			preparedStatement.setString(2, dipendente.getCognome());
			preparedStatement.setString(3, dipendente.getCf());
			preparedStatement.setString(4, dipendente.getLuogonascita());
			Date date= new Date(dipendente.getDataNascita().getTime());
			preparedStatement.setDate(5, date);
			preparedStatement.setString(6, dipendente.getIndirizzo());
			preparedStatement.setString(7, dipendente.getTipo());
			preparedStatement.setInt(8, dipendente.getMatricola());
			
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
