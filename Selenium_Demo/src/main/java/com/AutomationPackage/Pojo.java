package com.AutomationPackage;

public class Pojo {
	private String vegetable_name ;
	private String price;
	private String weight;   
;
	
	public String getvegetable_name() {
		return vegetable_name;
	}
	public String getprice() {
		
		return price;
	}
	public String getweight() {
		if(weight == "") {
			return "";
		}
		else {
		return weight;
		}
		}
	public void setvegetable_name(String vegetable_name) {
		this.vegetable_name = vegetable_name;
	}
	public void setprice(String price) {
		this.price = price;
	}
	public void setweight(String weight) {
		this.weight = weight;
	}
	
}
