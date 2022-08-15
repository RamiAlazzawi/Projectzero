package com.revature;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.bean.UserDao;
import com.revature.connection.ConnToDb;
import com.revature.offer.Offeres;

public class P0Drive  {
	
	private static Logger log=Logger.getLogger(P0Drive.class);
	public static void main(String[] args) {
    
		log.info("main");
		Scanner f=new Scanner(System.in);
		System.out.println("********************* WELCOME TO RA DEALERSHIP ********************\n");
		
		System.out.println("1- Login.");
		System.out.println("2- Register.");
		
		UserDao p= new UserDao();
		
		int a = f.nextInt();
		
		if(a==1) {
			login();
		}else {
			p.addUser();
		}
		
	login();

		
	}
	
	
	public static  D_User getUser(String username, String password) {
		D_User x = null;
		String sql = "select id, first_name, last_name,"
				+ " user_name, passwords, usertype from "
				+ "d_user where user_name =? "
				+ "and passwords = ?";
		
		
		try(Connection dbConn=ConnToDb.getConn()){
			
			PreparedStatement statement = dbConn.prepareStatement(sql);
			
			statement.setString(1, username);
			statement.setString(2, password);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) { 
			
			x= new D_User();
			x.setId(result.getInt("id"));
			    x.setFirstName(result.getString("first_name"));
			    
			    x.setLastName(result.getString("last_name"));
			    
			    x.setUserName(result.getString("user_name"));
				
				x.setPassWord(result.getString("passwords"));
				
				x.setUserType(result.getString("usertype"));
				
			}	
				
			
		} catch(Exception e) {
			System.out.println(e);
		}
		return x;
	  }
	
	
	
	public static void login() {
		 String username1 = null;  String password1 = null;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter Username: ");
		
		username1 = scan.nextLine();
		
		System.out.println("Enter Password: ");
		
		password1 = scan.nextLine();
		
		
		D_User user=getUser(username1, password1);
		
		
		
		if(user != null && user.getUserType().equals("manager")) {
		
			System.out.println("Hi "+user.getFirstName()+" "+user.getLastName()+"\n");
			
			System.out.println("1- View the car lot. (Press No.1)");
			System.out.println("2- Add car to the lot. (Press No.2)");
			System.out.println("3- Remove car from the lot. (Press No.3)");
			System.out.println("4- Add employee. (Press No.4)");
			System.out.println("5- View customers offers. (Press No.5)");
			System.out.println("6- Manage offers. (Press No.6)");
			System.out.println("Exit ?    (Press No.7)\n");
			
			int choscar=scan.nextInt();
			Auto vcar=new Auto();
			UserDao ademp=new UserDao();
			Offeres offer=new Offeres();
			
				if(choscar==1) {
					
				vcar.viewCar();
				
					System.out.println("2- Add car to the lot. (Press No.2)");
					System.out.println("3- Remove car from the lot. (Press No.3)");
					System.out.println("4- Manage offers. (Press No.4)");
					System.out.println("Exit?   (Press No.5)\n");
					
				int a=scan.nextInt();

					if(a==2) {
					
						vcar.addCar();
						login();
					
					}else if(a==3) {
						
						vcar.removeCar();
						vcar.viewCar();
						login();
				}else if (a==4) {
					ademp.manageOffers();
				}else if(a==5) {
					login();
				}
			
			
			
			
				}else if(choscar==2) {
			
				vcar.addCar();
				
				System.out.println("1- View the car lot. (Press No.1)");
				System.out.println("3- Remove car from the lot. (Press No.3)");
                System.out.println("Exit?   (Press No.4)\n");
				
				
				
				int b=scan.nextInt();

				if(b==1) {
				
					vcar.viewCar();
				login();
				
				}else if(b==3) {
					
					vcar.removeCar();
					vcar.viewCar();
					login();
				
				}else if(b==4) {
					login();
				}
			
				
				
				
				
				
			}else if(choscar==3) {
			
				vcar.removeCar();
				System.out.println("1- View the car lot. (Press No1)");
				System.out.println("2- Add car to the lot. (Press No2)");
				System.out.println("Exit?   (Press No.4)\n");
				
				
				int c =scan.nextInt();
			
				if(c==1) {
					vcar.viewCar();
					
					login();
					
				}else if(c==2) {
					vcar.addCar();
					
					login();
				}
			
			
			}else if(choscar==4) {
				ademp.addEmp();
			}else if (choscar==5) {
				offer.viewOffer();
				login();
			}else if(choscar==6) {
				ademp.manageOffers();
			}else if(choscar==7) {
				login();
			}
			

			
		
			
			login();
			
		
		
		
		
		
		
		
		
		
		
		} else if(user!=null && user.getUserType().equals("employee")) {
		
			System.out.println("Hi "+user.getFirstName()+" "+user.getLastName()+"\n");
			
			System.out.println("1- View the car lot. (Press No.1)");
			System.out.println("2- Add car to the lot. (Press No.2)");
			System.out.println("3- Remove car from the lot. (Press No.3)");
			System.out.println("4- View customers offers. (Press No.4)");
			System.out.println("5- Manage offers. (Press No.5)");
			 System.out.println("Exit?   (Press No.6)\n");
			
			int choscar=scan.nextInt();
			Auto vcar=new Auto();
			D_User manage=new D_User();
			
				if(choscar==1) {
				vcar.viewCar();
				
					System.out.println("2- Add car to the lot. (Press No.2)");
					System.out.println("3- Remove car from the lot. (Press No.3)");
					System.out.println("4- Manage offers. (Press No.5)");
					System.out.println("Exit?   (Press No.5)\n");
					
				int a=scan.nextInt();

					if(a==2) {
					
						vcar.addCar();
						login();
					
					}else if(a==3) {
						
						vcar.removeCar();
						vcar.viewCar();
						login();
				}else if (a==4) {
					manage.manageOffers();
				}else if(a==5) {
					login();
				}
			
			
			
			
				}else if(choscar==2) {
			
				vcar.addCar();
				
				System.out.println("1- View the car lot. (Press No.1)");
				System.out.println("3- Remove car from the lot. (Press No.3)");
                System.out.println("Exit?   (Press No.4)\n");
				
				
				
				int b=scan.nextInt();

				if(b==1) {
				
					vcar.viewCar();
				login();
				
				}else if(b==3) {
					
					vcar.removeCar();
					vcar.viewCar();
					login();
				
				}else if(b==4) {
					login();
				}
			
				
				
				
				
				
			}else if(choscar==3) {
			
				vcar.removeCar();
				System.out.println("1- View the car lot. (Press No1)");
				System.out.println("2- Add car to the lot. (Press No2)");
				System.out.println("Exit?   (Press No.4)\n");
				
				
				int c =scan.nextInt();
			
				if(c==1) {
					vcar.viewCar();
					
					login();
					
				}else if(c==2) {
					vcar.addCar();
					
					login();
				}
			
			
			}
			

			
		
		
			
			
			
			
			
			
			
			
			
			}else if(user!=null && user.getUserType().equals("customer")){
			
			System.out.println("Hi "+user.getFirstName()+" "+user.getLastName()+"\n");
			
			System.out.println("1- You are welcome to view the car lot. (Press No.1)");
			System.out.println("2- Would you like to make offer. (Press No.2)");
			System.out.println("Exit?   (Press No.3)\n");
			int choose=scan.nextInt();
			
			if(choose==1) {
			
				Auto car = new Auto();
			
			car.viewCar();
			
			System.out.println("\n");
			
			for(int s=0; s<3;s++) {
			
			System.out.println("2- Would you like to make offer. (Press No.2)");
			
			int x =scan.nextInt();
			
			if(x==2) {
				Offeres offer=new Offeres();
				offer.makeOffer();
				
				login();
				
			}else {
				System.out.println("Invalid option!");
				
			}
			
			}	
			
			
			}else if (choose==2) {
			
				Offeres offer=new Offeres();
				offer.makeOffer();
				
				login();
				
			}else if(choose==3) {
				login();
				
			}else {
				System.out.println("Invalid option!");
			}
			
		
		
		
		}else {
			System.out.println("Invalid Username/Password");
		}
	}

	

}	