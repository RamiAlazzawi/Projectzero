package com.revature.offer;

import java.sql.Statement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import javax.naming.spi.DirStateFactory.Result;

import org.apache.log4j.Logger;

import com.revature.Auto;
import com.revature.D_User;
import com.revature.bean.AutoDao;
import com.revature.connection.ConnToDb;

public class Offeres {
	private static Logger log = Logger.getLogger(D_User.class);
	
	private String status;
	private int id;
	private String make;
	
	public void setStatus(String status) {
		this.status=status;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setId(int id) {
		this.id=id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setMake(String make) {
		this.make=make;
	}
	
	public String getMake() {
		return make;
	}
	
	public  Offeres getOffers(String make) {
		Offeres offer = null;
		String sql = "select offer_id, status, make from d_offers where make='"+make+"'";
		
		
		try(Connection dbConn=ConnToDb.getConn()){
			
			PreparedStatement statement = dbConn.prepareStatement(sql);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) { 
			
			offer= new Offeres();
			
			    offer.setStatus(result.getString("status"));
			    
			    offer.setId(result.getInt("offer_id"));
			    
			    offer.setMake(result.getString("make"));
				
			}	
				
			
		} catch(Exception e) {
			System.out.println(e);
		}
		return offer;
	  }
	
	
	public void makeOffer() {
		
		try(Connection dbConn=ConnToDb.getConn()){
			
			Scanner scan=new Scanner(System.in);
			
					CallableStatement calstate=null;
			System.out.println("Enter the car make please -->");
			String make=scan.nextLine();
			
			
			System.out.println("Enter the offer price -->");
			Double offerprice=scan.nextDouble();
			
			AutoDao carm=new AutoDao();
			AutoDao vm=carm.getCar(make);
			
			String msql="select auto_id from d_auto where auto_id="+Auto.getId()+"";
			String osql="select id from d_user where id="+D_User.getId()+"";
			
			Statement st=dbConn.createStatement();
			ResultSet r=st.executeQuery(osql);
			Statement st2=dbConn.createStatement();
			ResultSet r2=st2.executeQuery(msql);
			
			if(r.next());
			int cus=r.getInt("id");
			
			if(r2.next());
			int cid=r2.getInt("auto_id");
			
			
			calstate=dbConn.prepareCall("{call makeoffer(?,?,?,?)}");
			
			calstate.setString(1, make);
			calstate.setDouble(2, offerprice);
			calstate.setInt(3, cid);
			calstate.setInt(4,cus);
			
			
			calstate.execute();
			System.out.println("Offer recieved, thank you. \n");
			
			
			for(int i=0;i<3;i++) {
			
			System.out.println("3- View your offer status, Press No.3");
			int s =scan.nextInt();
			
			
			if(s==3) {
			
			String query="select * from d_offers where offer_id=(select max(offer_id) from d_offers)";
			
			Statement ostate= dbConn.createStatement();
			ResultSet ores=ostate.executeQuery(query);
			
			while(ores.next()) {
        System.out.println("Car ID / "+ores.getInt("offer_id")+"  "+"Car Make / "+ores.getString("make")+"   "+""
		+ ""+"Offer price / "+ores.getDouble("price")+"   "+""
		+ ""+"Status / "+ores.getString("status")+"   "+ores.getInt("customer_id")+"\n");
			}
			break;
			}else {
				System.out.println("Invalid choice, enter number 3 please");
			}
			}
			
			
				
				}catch(Exception e) {
			System.out.println(e);
		}
		
		
		System.out.println("********************* WELCOME TO RA DEALERSHIP ********************\n");
		
	}
	
	
	
	
	public void viewOffer() {
		try(Connection dbConn=ConnToDb.getConn()){
			
			String quOffer="select * from d_offers";
			
			Statement ostate=dbConn.createStatement();
			ResultSet oset=ostate.executeQuery(quOffer);
			
			while(oset.next()) {
				System.out.println("Car ID / "+oset.getInt("o_auto")+"  "+"Car make / "+oset.getString("make")+"   "+"price / "+oset.getString("price")+
						"   "+"status / "+oset.getString("status")+"   "+"customer_id / "+oset.getString("customer_id")+"\n");
			}
			
			System.out.println("********************* WELCOME TO RA DEALERSHIP ********************\n");
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	
	
}
