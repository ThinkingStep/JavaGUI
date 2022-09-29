package jtable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DAO {
	Connection conn = null;
	ResultSet rs = null;
	Statement st = null;
	PreparedStatement ps = null;
	
	DAO(){
		
		try {
			String user = "root";
			String pw = "123456";
			String url = "jdbc:mysql://localhost:3306/project";
			
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(url, user, pw);
			System.out.println("success");

		} catch (ClassNotFoundException cnfe) {
			System.out.println("DB 드라이버 로딩 실패  :" + cnfe.toString());
		} catch (SQLException sqle) {
			System.out.println("DB 접속실패 : " + sqle.toString());
		} catch (Exception e) {
			System.out.println("Unkonwn error");
			e.printStackTrace();
		}
		
	}
	
		public void dbClose() {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				if (ps != null)
					ps.close();
			} catch (Exception e) {
				System.out.println(e + "=> dbClose fail");
			}
		}

		// Create
		public void insertData(People people) {
			try {
				String sql = "INSERT INTO ENTRYLIST(visitDate, agree, address, tel, sign) values(?, ?, ?, ?, ?)";
				LocalDateTime now = LocalDateTime.now();
				ps = conn.prepareStatement(sql);
				ps.setTimestamp(1, Timestamp.valueOf(now));
				ps.setBoolean(2, people.isAgree());
				ps.setString(3, people.getAddress());
				ps.setString(4, people.getTel());
				ps.setString(5, people.getSign());
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				dbClose();
			}
		}
		
		public ArrayList<People> selectPeople(){
			ArrayList<People> arr = new ArrayList<>();
			try {
				st = conn.createStatement();
				String sql = "SELECT * FROM ENTRYlIST ORDER BY VISITDATE ASC";
				rs = st.executeQuery(sql);
				
				while (rs.next()) {
					Timestamp timestamp = rs.getTimestamp("visitDate");
					LocalDateTime visitDate = timestamp.toLocalDateTime();
					arr.add(new People(visitDate, rs.getBoolean(2), rs.getString(3), rs.getString(4),rs.getString(5)));
				}
				
			} catch (Exception e) {
				
			}
			return arr;
		}
		public ArrayList<People> selectPeopleByTel(String tel){
			ArrayList<People> arr = new ArrayList<>();
			try {
				st = conn.createStatement();
				String sql = "SELECT * FROM ENTRYlIST WHERE TEL=" + tel;
				rs = st.executeQuery(sql);
				
				while (rs.next()) {
					Timestamp timestamp = rs.getTimestamp("visitDate");
					LocalDateTime visitDate = timestamp.toLocalDateTime();
					arr.add(new People(visitDate, rs.getBoolean(2), rs.getString(3), rs.getString(4),rs.getString(5)));
				}
				
			} catch (Exception e) {
				
			}
			return arr;
		}
		
		public int updateData(People people) {
			int result = 0;
			try {
				String sql = "UPDATE ENTRYlIST SET AGREE=?, ADDRESS=? WHERE TEL=?";
				ps = conn.prepareStatement(sql);
				ps.setBoolean(1, people.isAgree());
				ps.setString(2, people.getAddress());
				ps.setString(3, people.getTel());
				result = ps.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				dbClose();
			}
			return result;
		}
		public int updateSign(People people) {
			int result = 0;
			try {
				String sql = "UPDATE ENTRYlIST SET AGREE=?, ADDRESS=?, SIGN=? WHERE TEL=?";
				ps = conn.prepareStatement(sql);
				ps.setBoolean(1, people.isAgree());
				ps.setString(2, people.getAddress());
				ps.setString(3, people.getSign());
				ps.setString(4, people.getTel());
				result = ps.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				dbClose();
			}
			return result;
		}
}
