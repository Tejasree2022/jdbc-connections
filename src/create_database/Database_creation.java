package create_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Database_creation {
	private static final String driver ="com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/";
	private static final String userName = "root";
	private static final String password = "root";
	private static PreparedStatement pmst;
	private static Connection conn;
	public static void main(String[] args) {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userName, password);
			System.out.println("Enter database name:");
			String sql = "create database "+src.nextLine();
			pmst = conn.prepareStatement(sql);
			int i = pmst.executeUpdate();
			if(i > 0) {
				System.out.println("Database is created");
			}
			else {
				System.out.println("Database is not created");
			}
			pmst.close();
			conn.close();
			src.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
