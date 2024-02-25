package FoodApp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import jdbc.connection;

public class foodManagement {
	
	private static final int NULL = 0;
	static Connection con=connection.getConnection();
	static String sql="";
	public static boolean createAccount(int id,String name ,String pwd) {
		try {
			// validation
			if (id == NULL || pwd == "" || name=="") {
				System.out.println("All Field Required!");
				return false;
			}
			// query
			Statement st = con.createStatement();
			sql = "INSERT INTO customers(c_id,c_name,c_pwd) values(" 
			+id+",'"+name +"',"+"'"+pwd+"')";
				

			// Execution
			if (st.executeUpdate(sql) == 1) {
				System.out.println(name
								+ ", Now You Login!");
				return true;
			}
			// return
		}
		catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Username Not Available!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void displayMenu() throws SQLException {
		sql="select * from menu";
		PreparedStatement st1
		= con.prepareStatement(sql);
		ResultSet rs1 = st1.executeQuery();
		System.out.println("s.no \t Item \t \t\t     Price");
		System.out.println("-------------------------------------------------------------");
		while(rs1.next()) {
			System.out.printf(" %d\t%-25s %s\n",rs1.getInt("m_id"),rs1.getString("m_name"),rs1.getString("m_price"));
			
		}
	}
	public static boolean makeOrder(int id,int item) throws SQLException {
		sql = "INSERT INTO orders(c_id,m_id) values(" 
				+id+","+item+")";
		Statement st2 = con.createStatement();
		if(st2.executeUpdate(sql)==0) {
			return false;
		}
		return true;
	}
	public static void displayCustomerHistory() throws SQLException {
		sql="select orders.o_id,menu.m_id,menu.m_name from orders,menu where menu.m_id=orders.m_id";
		PreparedStatement st3
		= con.prepareStatement(sql);
	
			ResultSet rs3 = st3.executeQuery();
			int count =1;
			System.out.println("s.no \t order id\t Item name");
			System.out.println("-------------------------------------------------------------");
			while(rs3.next()) {
				System.out.println(count+"\t"+rs3.getInt("orders.o_id")+"\t\t"+rs3.getString("menu.m_name")+"\t");
				count++;
			}
	}
	public static boolean
	loginAccount(int id, String pwd) {
		try {
			if(id==NULL || pwd=="" ) {
				System.out.println("All Field Required!");
				return false;
			}
			sql = "select * from customers where c_id="
					+ id + " and c_pwd='" + pwd+"'";
				PreparedStatement st
					= con.prepareStatement(sql);
				
				ResultSet rs = st.executeQuery();
				// Execution
				BufferedReader sc = new BufferedReader(
					new InputStreamReader(System.in));
				
				if(rs.next()) {
					int ch=5;
					while(true) {
						
						try {
							
							System.out.println(
									"Hallo, "
									+ rs.getString("c_name"));
								System.out.println(
									"1)New Order");
								System.out.println("2)View order History");
								System.out.println("5)LogOut");
								System.out.print("Enter Choice:");
							ch=Integer.parseInt(sc.readLine());
							switch(ch) {
							case 1:
								
								displayMenu();
								try {
									System.out.println("--->> Select an Item  <<----");
									int item=Integer.parseInt(sc.readLine());
									
									if(makeOrder(id,item)) {
										System.out.print("YOUR order is successful\n");
									}else{
										System.out.print("YOUR order is unsuccessful\n");
									}
								}catch(Exception e) {
									System.out.println("Enter valid item"+e);
								}
								break;  
							case 2:
								displayCustomerHistory();
								
								break;
							}
						}catch(Exception e) {
							
						}
					}
					
					
				}else {
					System.out.println("Account not found");
					return false;
				}
			
		}catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Username Not Available!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

}
