package com.example.demo.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DocumentDAO {

	Connection conn = null;
	Statement st = null;


	public void insertDocument(String id, String value, String emp_dept) throws ClassNotFoundException, IOException {
		
		
		
		 String SQL_INSERT = "INSERT INTO document (id, value,emp_dept) VALUES (?,?, ?)";

		 
		Properties p = getprop();
		Class.forName(p.getProperty("CLASS_NAME"));

		try (Connection conn = DriverManager.getConnection(p.getProperty("DB_URL"), p.getProperty("DB_UN"),
				p.getProperty("DB_PASS")); PreparedStatement st = conn.prepareStatement(SQL_INSERT);) {
			
			st.setString(1, id);
			st.setString(2, value);
			st.setString(3, emp_dept);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	
	
	public void updateDocument(String id, String value, String emp_dept) throws ClassNotFoundException, IOException {
		String SQL_UPDATE = null;
		if( value != null && emp_dept != null) {
			 SQL_UPDATE = "UPDATE DOCUMENT SET value=? AND emp_dept=? WHERE id=?";
		}
		else if( value == null && emp_dept != null) {
			 SQL_UPDATE = "UPDATE DOCUMENT SET emp_dept=? WHERE id=?";
		}
		if( value != null && emp_dept == null) {
			 SQL_UPDATE = "UPDATE DOCUMENT SET value=? WHERE id=?";
		}
		
		
		Properties p = getprop();
		Class.forName(p.getProperty("CLASS_NAME"));

		try (Connection conn = DriverManager.getConnection(p.getProperty("DB_URL"), p.getProperty("DB_UN"),
				p.getProperty("DB_PASS")); PreparedStatement st = conn.prepareStatement(SQL_UPDATE);) {
			
			st.setString(1, id);
			if( value != null && emp_dept != null) {
				st.setString(2, value);
				st.setString(3, emp_dept);
			}
			else if( value == null && emp_dept != null) {
				st.setString(2, emp_dept);
			}
			if( value != null && emp_dept == null) {
				st.setString(2, value);
			}
			
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public void deleteDocument(String id, String value) throws ClassNotFoundException, SQLException, IOException {
		
		String SQL_DELETE = "delete from document where id =?";

		Properties p = getprop();
		Class.forName(p.getProperty("CLASS_NAME"));

		try (Connection conn = DriverManager.getConnection(p.getProperty("DB_URL"), p.getProperty("DB_UN"),
				p.getProperty("DB_PASS")); PreparedStatement st = conn.prepareStatement(SQL_DELETE);) {
			
			st.setString(1,id);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public void retriveDocument() throws ClassNotFoundException, SQLException, IOException {
		
		String sql = "select * from document";

		Properties p = getprop();
		Class.forName(p.getProperty("CLASS_NAME"));

		try (Connection conn = DriverManager.getConnection(p.getProperty("DB_URL"), p.getProperty("DB_UN"),
				p.getProperty("DB_PASS")); Statement st = conn.createStatement();) {
			st.executeQuery(sql);
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				System.out.println(rs.getString("id") + " " + rs.getString("value"));
			}

			if (rs != null) {
				rs.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Properties getprop() throws IOException {

		InputStream input = new FileInputStream("src/main/resources/h2db.properties");
		Properties p = new Properties();
		p.load(input);

		return p;

	}

}
