package it.polito.tdp.crimes.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.crimes.model.Adiacenti;
import it.polito.tdp.crimes.model.Event;


public class EventsDao {
	
	public List<Event> listAllEvents(){
		String sql = "SELECT * FROM events" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Event> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Event(res.getLong("incident_id"),
							res.getInt("offense_code"),
							res.getInt("offense_code_extension"), 
							res.getString("offense_type_id"), 
							res.getString("offense_category_id"),
							res.getTimestamp("reported_date").toLocalDateTime(),
							res.getString("incident_address"),
							res.getDouble("geo_lon"),
							res.getDouble("geo_lat"),
							res.getInt("district_id"),
							res.getInt("precinct_id"), 
							res.getString("neighborhood_id"),
							res.getInt("is_crime"),
							res.getInt("is_traffic")));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<Integer> getDate(){
		String sql = "SELECT DISTINCT YEAR(reported_date) AS dataReport "
				+ "FROM `events` "
				+ "order BY dataReport";
		
		List<Integer> result = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					result.add(res.getInt("dataReport"));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public List<String> getCategorie(){
		String sql = "SELECT DISTINCT offense_category_id "
				+ "FROM `events` "
				+ "order BY offense_category_id";
		List<String> result = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					result.add(res.getString("offense_category_id"));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;		
		
	}
	
	public List<String> getVertici(int anno, String categoria){
		String sql = "SELECT DISTINCT offense_type_id "
				+ "FROM `events` "
				+ "WHERE offense_category_id = ? AND "
				+ "YEAR(reported_date) = ?";
		
		List<String> result = new ArrayList<>();
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			st.setString(1, categoria);
			st.setInt(2, anno);
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					result.add(res.getString("offense_type_id"));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Adiacenti> getArchi(int anno, String categoria, List<String> vertici){
		String sql = "SELECT DISTINCT e1.offense_type_id, e2.offense_type_id, COUNT(distinct e1.district_id) AS peso "
				+ "FROM `events` AS e1, `events` AS e2 "
				+ "WHERE e1.offense_category_id = ? "
				+ "and YEAR(e1.reported_date) = ? AND "
				+ "e2.offense_category_id = e1.offense_category_id AND "
				+ "YEAR(e2.reported_date) = YEAR(e1.reported_date) AND "
				+ "e1.district_id = e2.district_id and "
				+ "e1.offense_type_id > e2.offense_type_id "
				+ "GROUP BY e1.offense_type_id, e2.offense_type_id "
				+ "ORDER BY peso desc";
		List<Adiacenti> result = new ArrayList<>();
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			st.setString(1, categoria);
			st.setInt(2, anno);
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					String r1 = res.getString("e1.offense_type_id");
					String r2 = res.getString("e2.offense_type_id");
					if(vertici.contains(r1) && vertici.contains(r2)) {
						result.add(new Adiacenti(r1,r2,res.getInt("peso")));
					}
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
