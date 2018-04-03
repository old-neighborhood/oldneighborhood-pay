package com.oldneighborhood.demo.entity;


public class BizContent {
	public String body = "";  
    public String subject = "";  
    public String  pay_ID= "";  
    public String timeout_express = "";  
    public String pay_amount = "";  
    public String site_id = "";  
    public String pay_code = "";  
    public String u_ID = "";  
    public double pay_price=0;
	public String getPay_ID() {
		return pay_ID;
	}
	public void setPay_ID(String pay_ID) {
		this.pay_ID = pay_ID;
	}
	public String getPay_amount() {
		return pay_amount;
	}
	public void setPay_amount(String pay_amount) {
		this.pay_amount = pay_amount;
	}
	public String getSite_id() {
		return site_id;
	}
	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}
	public String getPay_code() {
		return pay_code;
	}
	public void setPay_code(String pay_code) {
		this.pay_code = pay_code;
	}
	public String getU_ID() {
		return u_ID;
	}
	public void setU_ID(String u_ID) {
		this.u_ID = u_ID;
	}
	public double getPay_price() {
		return pay_price;
	}
	public void setPay_price(double pay_price) {
		this.pay_price = pay_price;
	}
    
}
