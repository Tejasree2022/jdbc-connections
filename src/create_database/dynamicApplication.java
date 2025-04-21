package create_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;


public class dynamicApplication {
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String username = "root";
	private static final String password = "root";
	private static Connection conn;
	private static PreparedStatement pmst;
	public static void main(String[] args) {
		int choice;
		do {
			Scanner src = new Scanner(System.in);
			System.out.println("choose your choice:");
			displayMenu();
			choice = Integer.parseInt(src.next());
			switch (choice) {
			case 1:
				create_database();
				break;
			case 2:
				drop_database();
				break;
			case 3:
				data_insertion();
				break;
			case 4:
				delete_by_email();
				break;
			case 5:
				updata_data();
				break;
			case 6:
				getby_email();
				break;
			case 7:
				getAll();
				break;
			case 8:
				System.exit(0);
				break;
			//case 9:
				//show_db();
				//break;
			case 10:
				login();
				break;
			default:
				System.out.println("Invalid");
			}
		} while (choice > 0);
	}

	private static void login() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			System.out.println("enter database name:");
			String url = "jdbc:mysql://localhost:3306/"+src.next();
			conn = DriverManager.getConnection(url, username, password);
			String sql = "select * from order1 where order_id = ? and order_pincode = ?";
			pmst = conn.prepareStatement(sql);	
			System.out.println("enter order id:");
			pmst.setInt(1, src.nextInt());
			System.out.println("enter the order pincode");
			pmst.setInt(2, src.nextInt());
			ResultSet rs = pmst.executeQuery();
			if (rs.next()) {
				System.out.println("login successfull...!");
			}
			else {
				System.out.println("Invalid order id or order pincode..!");
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/*private static void show_db() {
		// TODO Auto-generated method stub
		try {
			Class.forName(driver);
			String url = "jdbc:mysql://localhost:3306/";
			conn = DriverManager.getConnection(url, username, password);
			
			Scanner src = new Scanner(System.in);
			String sql = "show databases";
			pmst = conn.prepareStatement(sql);
			ResultSet rs = pmst.executeQuery();
			while (rs.next()) {
				System.out.println("order id :"+ rs.getLong("order_id"));
				System.out.println("order name :"+ rs.getString("order_name"));
				System.out.println("order pincode :"+ rs.getInt("order_pincode"));
				System.out.println("order address :"+ rs.getString("order_address"));
			}
			conn.close();
			pmst.close();
			src.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}*/

	private static void getAll() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			System.out.println("enter database name:");
			String url = "jdbc:mysql://localhost:3306/"+src.next();
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("enter table name:");
			String sql = "select * from "+ src.next();
			pmst = conn.prepareStatement(sql);
			ResultSet rs = pmst.executeQuery();
			while (rs.next()) {
				System.out.println("order id :"+ rs.getLong("order_id"));
				System.out.println("order name :"+ rs.getString("order_name"));
				System.out.println("order pincode :"+ rs.getInt("order_pincode"));
				System.out.println("order address :"+ rs.getString("order_address"));
			}
			pmst.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void getby_email() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			System.out.println("enter database name:");
			String url = "jdbc:mysql://localhost:3306/"+ src.next();
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("enter table name:");
			String sql ="select * from "+src.next()+" where order_id=?";
			pmst = conn.prepareStatement(sql);
			System.out.println("enter order email:");
			pmst.setString(1,  src.next());
			ResultSet rs = pmst.executeQuery();
			while(rs.next()) {
				System.out.println("order id :"+ rs.getLong("order_id"));
				System.out.println("order name :"+ rs.getString("order_name"));
				System.out.println("order pincode :"+ rs.getInt("order_pincode"));
				System.out.println("order address :"+ rs.getString("order_address"));
			}
			conn.close();
			pmst.close();
			} catch (Exception e) {
				e.printStackTrace();
		    
		}

		
	}

	private static void updata_data() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			System.out.println("enter database name:");
			String url = "jdbc:mysql://localhost:3306/" + src.next();
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("enter table name");
			String sql = "update " + src.next() + " set order_name = ?,order_pincode = ?,order_address = ? where order_id = ?";
			pmst = conn.prepareStatement(sql);
			System.out.println("enter order name:");
			pmst.setString(1, src.next());
			System.out.println("enter order pincode:");
			pmst.setInt(2, src.nextInt());
			System.out.println("enter order address:");
			pmst.setString(3, src.next());
			System.out.println("enter order id:");
			pmst.setLong(4, src.nextLong());
			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("database updated");
			}
			else {
				System.out.println("database is not updated");
			}
			conn.close();
			pmst.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

	private static void delete_by_email() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			System.out.println("enter database name:");
			String url = "jdbc:mysql://localhost:3306/" + src.next();
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("enter table name");
			String sql = "delete from " + src.next() + " where order_id = ?";
			pmst = conn.prepareStatement(sql);
			System.out.println("enter order id:");
			pmst.setLong(1, src.nextLong());
			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("database deleted");
			}
			else {
				System.out.println("database is not deleted");
			}
			conn.close();
			pmst.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

	private static void data_insertion() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			System.out.println("enter database name:");
			String url = "jdbc:mysql://localhost:3306/" +src.next();
			conn = DriverManager.getConnection(url, username, password);
			String sql ="insert into order1(order_id,order_name,order_pincode,order_address,email) values(?,?,?,?,?)";
			pmst = conn.prepareStatement(sql);
			System.out.println("enter the order id:");
			pmst.setInt(1, src.nextInt());
			System.out.println("enter the order name:");
			pmst.setString(2 ,src.next());
			System.out.println("enter the order pincode");
			pmst.setInt(3, src.nextInt());
			System.out.println("enter the order address");
			pmst.setString(4, src.next());
			System.out.println("enter email:");
			pmst.setString(5, src.next());
			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("data is inserted");
			}
			else {
				System.out.println("data is not inserted");
			}
			conn.close();
			pmst.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	private static void drop_database() {
		try {
			Class.forName(driver);
			String url = "jdbc:mysql://localhost:3306/";
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("enter database name");
			Scanner src = new Scanner(System.in);
			String sql = "drop database " + src.next();
			pmst = conn.prepareStatement(sql);
			int i = pmst.executeUpdate();
			if (i == 0) {
				System.out.println("database droped");
			}
			else {
				System.out.println("database is not droped");
			}
			conn.close();
			pmst.close();
			src.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	private static void create_database() {
		try {
			Class.forName(driver);
			String url = "jdbc:mysql://localhost:3306/";
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("enter database name");
			Scanner src = new Scanner(System.in);
			String sql = "create database " + src.next();
			pmst = conn.prepareStatement(sql);
			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("database created");
			}
			else {
				System.out.println("database is not created");
			}
			conn.close();
			pmst.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	private static void displayMenu() {
		System.out.println("\t1.create database");
		System.out.println("\t2.drop database");
		System.out.println("\t9.show database");
		System.out.println("\t3.data insertion");
		System.out.println("\t4.delete by email");
		System.out.println("\t5.updata data");
		System.out.println("\t6.getby email");
		System.out.println("\t7.getAll");
		System.out.println("\t8.Exit");
		System.out.println("\t9.show database");
		System.out.println("\t10.login");
		
	}

}
