package database_insertion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class insertion {
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/employee";
	private static final String username = "root";
	private static final String password = "root";
	private static Connection conn;
	private static PreparedStatement pmst;
	public static void main(String[] args) {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			String sql = "insert into login(id,email,password) values(?,?,?)";
			pmst = conn.prepareStatement(sql);
			System.out.println("enter login id:");
			pmst.setString(1,  src.nextLine());
			System.out.println("enter login email:");
			pmst.setString(2,  src.nextLine());
			System.out.println("enter login password:");
			pmst.setString(3, src.nextLine());
			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("Data is inserted");
			}
			else {
				System.out.println("Data isn't inserted");
			}
			pmst.close();
			conn.close();
			src.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
