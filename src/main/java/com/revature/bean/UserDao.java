package com.revature.bean;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

import com.revature.Dao.UserD;
import com.revature.connection.ConnToDb;
import com.revature.offer.Offeres;

public class UserDao implements UserD{

public void manageOffers() {
		
		Scanner f =new Scanner(System.in);
		System.out.println("1- Accept offer");
		System.out.println("2- Reject offer");
		
		int s=f.nextInt();
		
		if(s==1) {
			
			Offeres cv=new Offeres();
			cv.viewOffer();
		
	try(Connection dbConn=ConnToDb.getConn()){
			
		
		System.out.println("Enter the customer-id to accept the offer\n");
		int cid=f.nextInt();
		f.nextLine();
		System.out.println("Enter the id of the car to approve");
		int id=f.nextInt();
		f.nextLine();
		
			
					CallableStatement addstate=null;
		
					
		            addstate=dbConn.prepareCall("{call accept_offers(?,?)}");
			
		            addstate.setInt(1, cid);
		            addstate.setInt(2, id);
		            
			addstate.execute();
			System.out.println("Offer accepted successfully!. \n");
			
			
			String owner="update d_auto set ownedby='"+cid+"' where auto_id='"+id+"'";
			Statement sta=dbConn.createStatement();

			int rs=sta.executeUpdate(owner);
			
	}catch(Exception e) {
		System.out.println(e);
	}
		}else if(s==2) {
			Offeres cview=new Offeres();
			cview.viewOffer();
			
			try(Connection dbConn=ConnToDb.getConn()){
				
				
				System.out.println("Enter the id of the car to reject");
				int id2=f.nextInt();
					
							CallableStatement addstate=null;
				
					
					addstate=dbConn.prepareCall("{call reject_offers(?)}");
					
					addstate.setInt(1, id2);
					
					
					
					addstate.execute();
					System.out.println("Offer rejected successfully!. \n");

			}catch(Exception e) {
				System.out.println(e);
			}
		}
	
	}
	
	public void addEmp() {
		

		try(Connection dbConn=ConnToDb.getConn()){
			
			Scanner scan=new Scanner(System.in);
			
					CallableStatement addstate=null;
			System.out.println("Enter first name");
			String first=scan.nextLine();
			
			System.out.println("Enter last name");
			String last=scan.nextLine();
			
			System.out.println("Create username");
			String create=scan.nextLine();
			
			System.out.println("Create password");
			String createpass=scan.nextLine();
			
			String usertype="employee";
				
			
			
			addstate=dbConn.prepareCall("{call Adduser(?,?,?,?,?)}");
			
			addstate.setString(1, first);
			addstate.setString(2, last);
			addstate.setString(3, create);
			addstate.setString(4, createpass);
			addstate.setString(5, usertype);
			
			
			
			addstate.execute();
			System.out.println("Employee Added successfully!. \n");

	}catch(Exception e) {
		System.out.println(e);
	}
}
		
		public void addUser() {
			

			try(Connection dbConn=ConnToDb.getConn()){
				
				Scanner scan=new Scanner(System.in);
				
						CallableStatement addstate=null;
				System.out.println("Enter first name");
				String first=scan.nextLine();
				
				System.out.println("Enter last name");
				String last=scan.nextLine();
				
				System.out.println("Create username");
				String create=scan.nextLine();
				
				System.out.println("Create password");
				String createpass=scan.nextLine();
				
				
					String usertype="customer";
				
				
				addstate=dbConn.prepareCall("{call Adduser(?,?,?,?,?)}");
				
				addstate.setString(1, first);
				addstate.setString(2, last);
				addstate.setString(3, create);
				addstate.setString(4, createpass);
				addstate.setString(5, usertype);
				
				
				
				addstate.execute();
				System.out.println("You are registerd successfully!. \n");
				
				System.out.println("********************* WELCOME TO RA DEALERSHIP ********************\n");

		}catch(Exception e) {
			System.out.println(e);
		}
		
	 }
}
