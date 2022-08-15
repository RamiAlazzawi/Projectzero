package com.revature;

import com.revature.bean.AutoDao;

public class Auto extends AutoDao  {
	
	
	private static int id;
	private String make;
	private static int year;
	private double price;
	private int owner;
	
	public void setOwner(int owner) {
		this.owner=owner;
	}
	
	public int getOwner() {
		return owner;
	}
	
	public void setId(int id) {
		this.id=id;
	}
	
	public static int getId() {
		return id;
	}
	
public void setMake(String make) {
		this.make=make;
	}
	
	public  String getMake() {
		return make;
	}
	
public void setPrice(double price) {
		this.price=price;
	}
	
	public double getPrice() {
		return price;
	}

public void setYear(int year) {
		this.year=year;
	}
	
	public static int getYear() {
		return year;
	}
	
	
	
}