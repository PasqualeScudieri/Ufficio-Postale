package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import bean.PostaBean;
import it.unisa.CalcolaDataSped;
import it.unisa.DriverManagerConnectionPool;

public class PostaModel {
	
	public static LocalDateTime spedizioneDataOra=CalcolaDataSped.domaniAlle15(); 
	
	/**Inserisce la data di cosnegna della posta
	 * @param data la data di consegna
	 * @param codice il codice della posta consegnata
	 * @param dipendente la matricola del postino che ha effettuato la consegna
	 * */
	public synchronized void aggiornaConsegna(java.util.Date data, int codice, int dipendente) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String update="UPDATE posta SET dataCons=? , dipendente=? WHERE codice=?";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(update);			
			Date dataCons=new  Date(data.getTime());
			preparedStatement.setDate(1, dataCons);
			preparedStatement.setInt(2, dipendente);
			preparedStatement.setInt(3, codice);
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
	
	/**Ritorna le informazioni sulla posta con quel codice
	 * @param codice il codice della posta
	 * @return un PostaBean con le informazioni sulla posta con quel codice
	 * */
	public synchronized PostaBean cercaByCodice(int codice) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM posta WHERE codice= ?";
		PostaBean posta= new PostaBean();
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, codice);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				posta.setCodice(rs.getInt("codice"));
				posta.setDipendente(rs.getInt("dipendente"));
				posta.setMittente(rs.getString("mittente"));
				posta.setTipo(rs.getString("tipo"));
				posta.setDestinatario(rs.getString("destinatario"));
				posta.setIndirizzo(rs.getString("indirizzo"));
				try {
					Date data= rs.getDate("dataCons");
					java.util.Date dataUtil= new java.util.Date(data.getTime());
					posta.setDataConsegna(dataUtil);
				}catch (SQLException e) {
					posta.setDataConsegna(null);
				}catch(NullPointerException e) {
					posta.setDataConsegna(null);
				}
				Date data=rs.getDate("dataSped");
				//System.out.println("dataSped: " + data);
				java.util.Date dataUtil= new java.util.Date(data.getTime());
				dataUtil=new java.util.Date(data.getTime());
				posta.setDataSpedizione(dataUtil);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return posta;
	}
	
	/**Ritorna l'elenco della posta mandata da un cliente
	 * @param cf il codice fiscale del cliente
	 * @return Ritorna l'elenco della posta mandata da un cliente
	 * */
	public synchronized ArrayList<PostaBean> cercaPerCfMit(String cf) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL = "SELECT * FROM posta WHERE mittente=?";
		
		ArrayList<PostaBean>arrayPosta= new ArrayList<>(); 
			
			try {
				connection= DriverManagerConnectionPool.getConnection();
				preparedStatement= connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, cf);
	
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next()) {
					 PostaBean pb= new PostaBean();
					 pb.setCodice(rs.getInt("codice"));
					 
					 try {
				          Date data= rs.getDate("dataCons");
				          java.util.Date dataUtil= new java.util.Date(data.getTime());
				          pb.setDataConsegna(dataUtil);
				        }catch (SQLException e) {
				          pb.setDataConsegna(null);
				        }catch(NullPointerException e) {
				          pb.setDataConsegna(null);
				        }
					 
					 Date data= rs.getDate("dataSped");
					 java.util.Date dataUtil= new java.util.Date(data.getTime());
					 pb.setDataSpedizione(dataUtil);
					
					 pb.setTipo(rs.getString("tipo"));
					 pb.setDipendente(rs.getInt("dipendente"));
					 pb.setDestinatario(rs.getString("destinatario"));
					 pb.setMittente(rs.getString("mittente"));
					 pb.setIndirizzo(rs.getString("indirizzo"));
					 
					 arrayPosta.add(pb);
				}
				
			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}		
			}
		
		
		return arrayPosta;
	}

	
	
	/*public synchronized ArrayList<PostaBean> cercaPerCfDest(String cf) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL1 = "SELECT * FROM posta WHERE destinatario=?";
		
		ArrayList<PostaBean>arrayPosta= new ArrayList<>(); 
			
			try {
				connection= DriverManagerConnectionPool.getConnection();
				preparedStatement= connection.prepareStatement(selectSQL1);
				preparedStatement.setString(1, cf);
	
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next()) {
					 PostaBean pb= new PostaBean();
					 pb.setCodice(rs.getInt("codice"));
					 
					 try {
				          Date data= rs.getDate("dataCons");
				          java.util.Date dataUtil= new java.util.Date(data.getTime());
				          pb.setDataConsegna(dataUtil);
				        }catch (SQLException e) {
				          pb.setDataConsegna(null);
				        }catch(NullPointerException e) {
				          pb.setDataConsegna(null);
				        }
					 
					 Date data= rs.getDate("dataSped");
					 java.util.Date dataUtil= new java.util.Date(data.getTime());
					 pb.setDataSpedizione(dataUtil);
					
					 pb.setTipo(rs.getString("tipo"));
					 pb.setDipendente(rs.getInt("dipendente"));
					 pb.setDestinatario(rs.getString("destinatario"));
					 pb.setMittente(rs.getString("mittente"));
					
					 
					 arrayPosta.add(pb);
				}
				
			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}		
			}
		
		return arrayPosta;
	}
*/
	
	/**Memorizza le informazioni su una nuova posta spedita
	 * @param posta il bean con le informazioni sulla posta
	 * @return ritorna il codice della posta
	 * */
	public synchronized int inserisciInPo(PostaBean posta) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL="INSERT INTO posta (codice, dataSped , tipo, destinatario, mittente, indirizzo) VALUES(?,?,?,?,?,?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, posta.getCodice());
			Date date= new Date(posta.getDataSpedizione().getTime());
			preparedStatement.setDate(2, date);
			preparedStatement.setString(3, posta.getTipo());
			preparedStatement.setString(4, posta.getDestinatario());
			preparedStatement.setString(5, posta.getMittente());
			preparedStatement.setString(6, posta.getIndirizzo());
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
		
		String selectSQL= "SELECT MAX(codice) FROM posta";
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
		
		return codice;
		
	}

}
