package com.oldneighborhood.demo.entity;

import org.hibernate.annotations.Entity;

@Entity
public class Pay {
	
	 public BizContent Content;
	public Pay(String pay_ID, String pay_amount, String site_id, String pay_code, String u_ID, double pay_price) {
		super();
		Content.setPay_amount(pay_amount);
		Content.setPay_code(pay_code);
		Content.setPay_ID(pay_ID);
		Content.setPay_price(pay_price);
		Content.setSite_id(site_id);
		Content.setU_ID(u_ID);
	}
	 
}
