package com.oldneighborhood.demo.service;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.oldneighborhood.demo.entity.Pay;
import com.oldneighborhood.demo.entity.PayInfo;


public interface PayService {
	/**
	 * 网站支付
	 * @Author  
	 * @param product
	 * @return  String
	 * @throws AlipayApiException 
	 * @throws UnsupportedEncodingException 
	 * @Date	
	 */
	String aliPayPc(Pay pay) throws AlipayApiException, UnsupportedEncodingException;
	
	public String notify(HttpServletRequest request) throws UnsupportedEncodingException;



}
