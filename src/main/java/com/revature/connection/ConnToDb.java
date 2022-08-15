package com.revature.connection;
import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.Logger;

import com.revature.D_User;

public class ConnToDb{
	private static Logger log = Logger.getLogger(D_User.class);
	 static Connection dbConn=null;


	
	private ConnToDb() {
		
	}
	
	public static Connection getConn() {
		
		try {
			
			if(dbConn==null)
	Class.forName("oracle.jdbc.driver.OracleDriver");
	dbConn=DriverManager.getConnection("jdbc:oracle:thin:@ramydb.cbdeil3fme5d.us-east-2.rds.amazonaws.com:1521:orcl","RaAdmin","ramialazzawi");
		
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
		return dbConn;
	}
	 
	
}
