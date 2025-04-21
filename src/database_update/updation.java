package database_update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class updation {
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
			String sql = "update login set email = ? where password = ?";
			pmst = conn.prepareStatement(sql);
			System.out.println("enter email to update:");
			pmst.setString(1, src.nextLine());
			System.out.println("enter password:");
			pmst.setString(2, src.nextLine());
			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("Updation is done");
			}
			else {
				System.out.println("Updation is not done");
			}
			pmst.close();
			conn.close();
			src.close();
			
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}

}
