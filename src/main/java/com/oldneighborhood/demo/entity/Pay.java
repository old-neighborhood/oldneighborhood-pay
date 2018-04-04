package com.oldneighborhood.demo.entity;




public class Pay {
	
	 public BizContent Content;
	public Pay(String pay_ID, String pay_amount, String site_id, String pay_code, String u_ID, double pay_price) {
		super();
		Content = new BizContent(pay_ID, pay_amount, site_id, pay_code, u_ID, pay_price);
	}
	 
}
