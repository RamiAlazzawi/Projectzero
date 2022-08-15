package com.revature.bean;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.revature.Auto;
import com.revature.Dao.AutoD;
import com.revature.connection.ConnToDb;

public class AutoDao implements AutoD{

	public  Auto getCar(String make) {
		Auto car = null;
		String sql = "select auto_id, make, year_made, price, ownedby from d_auto where make='"+make+"'";
		
		try(Connection dbConn=ConnToDb.getConn()){
			
			Statement statement = dbConn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			
			if(result.next()) { 
			
			car= new Auto();
			
			    car.setId(result.getInt("auto_id"));
			    
			    car.setMake(result.getString("make"));
			    
			    car.setPrice(result.getDouble("price"));
			    
			    car.setYear(result.getInt("year_made"));
				
				car.setOwner(result.getInt("ownedby"));
				
				
				
			}	
				
			
		} catch(Exception e) {
			System.out.println(e);
		}
		return car;
	}
	
	
	
	
	
	public void viewCar() {

		
		
		String sql = "select * from d_auto";
		
		
		try(Connection dbConn=ConnToDb.getConn()){
			
			Statement state = dbConn.createStatement();
			
			ResultSet result = state.executeQuery(sql);
			
			while(result.next()) { 
				
			
				System.out.println("Car_id / "+result.getInt("auto_id")+"  "+"Car Make / "+result.getString("make")+"    "
						+ "Year / "+result.getInt("year_made")+"    "
						+"Price befor tax /"+result.getDouble("price")+"$"+"\n");
				
			}	 
			
			System.out.println("********************* WELCOME TO RA DEALERSHIP ********************\n");
			
			
			
		} catch(Exception e) {
			System.out.println(e);
		}

	}
	
	public void addCar() {
		
try(Connection dbConn=ConnToDb.getConn()){
			
			Scanner scan=new Scanner(System.in);
			
					CallableStatement call=null;
			System.out.println("Enter the car make please -->");
			String make=scan.nextLine();
			
			System.out.println("Enter the price -->");
			Double price=scan.nextDouble();
			
			System.out.println("Enter the year made-->");
			int year=scan.nextInt();
			
			call=dbConn.prepareCall("{call Addcar(?,?,?)}");
			
			call.setString(1, make);
			call.setDouble(2, price);
			call.setInt(3, year);
			
			call.execute();
			System.out.println("Car added to the lot. \n");
			
			System.out.println("********************* WELCOME TO RA DEALERSHIP ********************\n");

	}catch(Exception e) {
		System.out.println(e);
	}
	
 }
	
	
	
	public void removeCar() {
		String sql = "select * from d_auto";
		String usql="select * from d_offers";
		
		
		try(Connection dbConn=ConnToDb.getConn()){
			
			Statement ste=dbConn.createStatement();
			
			Statement state = dbConn.createStatement();
			
			ResultSet result = state.executeQuery(sql);
			
			ResultSet re=ste.executeQuery(usql);
			
			if(re.next())
			
			while(result.next()) { 
			
				System.out.println("Car Make / "+result.getString("make")+"    "
						+ "Year : "+result.getInt("year_made")+"    "
						+"Price befor tax / "+result.getDouble("price")+"$"+"\n");
				
			}	 
			Scanner scan = new Scanner(System.in);
			
			System.out.println("Enter the ID of the car you want to delete.");
			int del=scan.nextInt();
			String uq="update d_offers set o_auto=null where o_auto='"+del+"'";
			re=ste.executeQuery(uq);
					
			String squery="delete from d_auto where auto_id='"+del+"'";
			
			result=state.executeQuery(squery);
			
			System.out.println("Car deleted form the lot successfully!\n");
			
			System.out.println("********************* WELCOME TO RA DEALERSHIP ********************\n");
			
			
			
		} catch(Exception e) {
			System.out.println(e);
		}
		
	
	}
	
	
}
