package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.ClienteBean;
import bean.ContoCliente;
import it.unisa.DriverManagerConnectionPool;

public class ClienteModel {

	public synchronized void doSave(ClienteBean cliente) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL="INSERT INTO cliente (nome, cognome ,CF, luogonascita, datanascita, indirizzo) VALUES(?,?,?,?,?,?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, cliente.getNome());
			preparedStatement.setString(2, cliente.getCognome());
			preparedStatement.setString(3, cliente.getCf());
			preparedStatement.setString(4, cliente.getLuogoNascita());
			Date date= new Date(cliente.getDataNascita().getTime());
			preparedStatement.setDate(5, date);
			preparedStatement.setString(6, cliente.getIndirizzo());

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
	
	
	public synchronized ClienteBean cercaByCliente(String cliente) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM cliente WHERE CF= ?";
		ClienteBean clienteBean= new ClienteBean();
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, cliente);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				clienteBean.setNome(rs.getString("nome"));
				clienteBean.setCognome(rs.getString("cognome"));
				clienteBean.setCf(rs.getString("CF"));
				clienteBean.setLuogoNascita(rs.getString("luogonascita"));
				clienteBean.setIndirizzo(rs.getString("indirizzo"));
				Date data= rs.getDate("datanascita");
				java.util.Date dataUtil= new java.util.Date(data.getTime());
				clienteBean.setDataNascita(dataUtil);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return clienteBean;
	}
	
	public synchronized ArrayList<ClienteBean> cercaPerNomeECognome(String nome, String cognome) throws SQLException{

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "select * from cliente where nome=? AND cognome=? ";
		ArrayList<ClienteBean> array=new ArrayList<>();
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, nome);
			preparedStatement.setString(2, cognome);
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				//System.out.println(" sono in modelC nel while " +rs.getString("CF"));
				ClienteBean clienteBean= new ClienteBean();
				clienteBean.setNome(rs.getString("nome"));
				clienteBean.setCognome(rs.getString("cognome"));
				clienteBean.setCf(rs.getString("CF"));
				clienteBean.setLuogoNascita(rs.getString("luogonascita"));
				clienteBean.setIndirizzo(rs.getString("indirizzo"));
				Date data= rs.getDate("datanascita");
				java.util.Date dataUtil= new java.util.Date(data.getTime());
				clienteBean.setDataNascita(dataUtil);
				array.add(clienteBean);
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
	
	
	public synchronized ArrayList<ClienteBean> cercaNoPostePay() throws SQLException{

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "select * from cliente where cliente.CF NOT IN (select clienteCF from apre, postePay where postePay.iban=apre.ibanConto)";
		ArrayList<ClienteBean> array=new ArrayList<>();
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ClienteBean clienteBean= new ClienteBean();
				clienteBean.setNome(rs.getString("nome"));
				clienteBean.setCognome(rs.getString("cognome"));
				clienteBean.setCf(rs.getString("CF"));
				clienteBean.setLuogoNascita(rs.getString("luogonascita"));
				clienteBean.setIndirizzo(rs.getString("indirizzo"));
				Date data= rs.getDate("datanascita");
				java.util.Date dataUtil= new java.util.Date(data.getTime());
				clienteBean.setDataNascita(dataUtil);
				array.add(clienteBean);
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
	
	public synchronized ArrayList<ClienteBean> cercaNoBancoPosta() throws SQLException{

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "select * from cliente where cliente.CF NOT IN (select clienteCF from apre, bancoposta where bancoposta.iban=apre.ibanConto)";
		ArrayList<ClienteBean> array=new ArrayList<>();
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ClienteBean clienteBean= new ClienteBean();
				clienteBean.setNome(rs.getString("nome"));
				clienteBean.setCognome(rs.getString("cognome"));
				clienteBean.setCf(rs.getString("CF"));
				clienteBean.setLuogoNascita(rs.getString("luogonascita"));
				clienteBean.setIndirizzo(rs.getString("indirizzo"));
				Date data= rs.getDate("datanascita");
				java.util.Date dataUtil= new java.util.Date(data.getTime());
				clienteBean.setDataNascita(dataUtil);
				array.add(clienteBean);
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
	
	
	public synchronized ArrayList<ContoCliente> cercaNoServiziInternet() throws SQLException{

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT *  FROM cliente, apre, bancoposta WHERE servInternet='n' AND bancoposta.iban=apre.ibanConto AND cliente.CF=apre.clienteCF";
		ArrayList<ContoCliente> arrayC=new ArrayList<>();
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ClienteBean clienteBean= new ClienteBean();
				clienteBean.setNome(rs.getString("nome"));
				clienteBean.setCognome(rs.getString("cognome"));
				clienteBean.setCf(rs.getString("CF"));
				clienteBean.setLuogoNascita(rs.getString("luogonascita"));
				clienteBean.setIndirizzo(rs.getString("indirizzo"));
				Date data= rs.getDate("datanascita");
				java.util.Date dataUtil= new java.util.Date(data.getTime());
				clienteBean.setDataNascita(dataUtil);
				ContoCliente cc=new ContoCliente();
				cc.setCliente(clienteBean);;
				cc.setIban(rs.getString("iban"));
				arrayC.add(cc);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

		return arrayC;
	}
	
	public synchronized ArrayList<ContoCliente> cercaNoCarta() throws SQLException{

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM cliente, apre, bancoposta WHERE carta='n' AND bancoposta.iban=apre.ibanConto AND cliente.CF=apre.clienteCF";
		ArrayList<ContoCliente> Carray=new ArrayList<>();
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ClienteBean clienteBean= new ClienteBean();
				clienteBean.setNome(rs.getString("nome"));
				clienteBean.setCognome(rs.getString("cognome"));
				clienteBean.setCf(rs.getString("CF"));
				clienteBean.setLuogoNascita(rs.getString("luogonascita"));
				clienteBean.setIndirizzo(rs.getString("indirizzo"));
				Date data= rs.getDate("datanascita");
				java.util.Date dataUtil= new java.util.Date(data.getTime());
				clienteBean.setDataNascita(dataUtil);
				ContoCliente cc=new ContoCliente();
				cc.setCliente(clienteBean);
				cc.setIban(rs.getString("iban"));
				Carray.add(cc);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

		return Carray;
	}
}