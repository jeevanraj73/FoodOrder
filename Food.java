package FoodApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import jdbc.bankManagement;

public class Food {
	public static void main(String arg[])throws IOException{
		BufferedReader sc = new BufferedReader(
				new InputStreamReader(System.in));
		String name="";
		int id;
		String pwd="";
		int ch;
		while(true) {
			System.out.println(
					"\n \t Welcome to Biryani house\t \n");
			System.out.println("1)Create Account");
			System.out.println("2)Login Account");
			try {
				System.out.print("\n Enter Input:"); //user input 
				ch = Integer.parseInt(sc.readLine());
				switch(ch) {
				case 1:
					try {
						System.out.print(
								"Enter Unique User_id:");
						id=Integer.parseInt(sc.readLine());
						
						System.out.print(
								"Enter Unique UserName:");
						name = sc.readLine();
						System.out.print(
							"Enter New Password:");
						pwd = sc.readLine();
						if(foodManagement.createAccount(id,name,pwd)) {
							System.out.print("Your Account is created Succesully\n");
						}else {
							System.out.println(
									"ERR : Account Creation Failed!\n");
						}
						
					}catch(Exception e) {
						System.out.println(
								" ERR : Enter Valid Data::Insertion Failed!\n");
					}
					break;
				case 2:
					try {
						System.out.print(
							"Enter User_id:");
						id = Integer.parseInt(
								sc.readLine());
						System.out.print(
							"Enter Password:");
						
						pwd = sc.readLine();
						if (foodManagement.loginAccount(
								id, pwd)) {
							System.out.println(
								"MSG : Logout Successfully!\n");
						}
						else {
							System.out.println(
								"ERR : login Failed!\n");
						}
					}
					catch (Exception e) {
						System.out.println(
							" ERR : Enter Valid Data::Login Failed!\n");
					}
					
					break;
					default:
						System.out.println("Invalid Entry!\n");
				
					
					
				}
				if (ch == 5) {
					System.out.println(
						"Exited Successfully!\n\n Thank You :)");
					break;
				}
				
			}catch(Exception e) {
				System.out.println("\nEnter valid input!");
				
			}
		}
	}

}
