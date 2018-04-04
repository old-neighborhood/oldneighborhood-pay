package com.oldneighborhood.demo.controller;


import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.oldneighborhood.demo.entity.Pay;
import com.oldneighborhood.demo.entity.PayInfo;
import com.oldneighborhood.demo.service.PayService;

import net.guerlab.sdk.alipay.controller.AlipayAbstractController;

@Controller 
public class PayController extends AlipayAbstractController{
	
	@Autowired
	private PayService payService;
	
	
	    /** 
	     * 对支付宝支付信息进行签名 
	     *  
	     * @param info 
	     *            数据类 
	     * @return 
	     * @throws AlipayApiException 
	     * @throws UnsupportedEncodingException 
	     */  
	
	   @RequestMapping("/")
		public String get() {
			return "/pay";
		   
	   }
	   
	    @RequestMapping(path= {"/sign"} , method = {RequestMethod.POST})  
	    public Object sign(@RequestBody PayInfo payInfo) throws UnsupportedEncodingException, AlipayApiException  {
			System.out.println("<<<<<<<<<<<<");
			System.out.println(payInfo.toString());
			System.out.println(String.valueOf(payInfo.getPay_amount()));
			Pay pay = new Pay(payInfo.getPay_ID(), 
					String.valueOf(payInfo.getPay_amount()), 
					payInfo.getSite_ID(), 
					payInfo.getPay_code(), 
					payInfo.getU_ID(),
					payInfo.getPay_price());
	    	return payService.aliPayPc(pay);
	    }  
	  
	    /** 
	     * 支付宝支付成功后.会回调该接口 
	     *  
	     * @param request 
	     * @return 
	     * @throws UnsupportedEncodingException 
	     */  
	    @PostMapping("/notify")  
	    public String notify(HttpServletRequest request) throws UnsupportedEncodingException {
			return payService.notify(request);  
	        
	    }

		
	}  

