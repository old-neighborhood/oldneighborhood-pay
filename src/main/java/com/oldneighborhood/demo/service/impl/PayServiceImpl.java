package com.oldneighborhood.demo.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.oldneighborhood.demo.config.AlipayConfig;
import com.oldneighborhood.demo.dao.PayDao;
import com.oldneighborhood.demo.entity.BizContent;
import com.oldneighborhood.demo.entity.Pay;
import com.oldneighborhood.demo.entity.PayInfo;
import com.oldneighborhood.demo.service.PayService;
import com.opensymphony.xwork2.ActionContext;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayResponse;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayDataDataserviceBillDownloadurlQueryRequest;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.model.ExtendParams;
import com.alipay.demo.trade.model.builder.AlipayTradePrecreateRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradeRefundRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.model.result.AlipayF2FRefundResult;
import com.alipay.demo.trade.utils.ZxingUtils;

@Service
public class PayServiceImpl implements PayService {
	private static final Logger logger = LoggerFactory.getLogger(PayServiceImpl.class);
	
//	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	
	@Autowired
	private PayDao payDao;
	
	@Autowired
	private static ServletActionContext servletActionContext;
	
//	@Value("${sdk.alipay.notify-url}")
//	private String notify_url;
	
	@Value("${sdk.alipay.app-id}")
	private  String APP_ID ; 

	private static String CHARSET = "utf-8";  
	
	@Value("${sdk.alipay.alipay-public-key}")
	private  String ALIPAY_PUBLIC_KEY;
	
	@Value("${sdk.alipay.private-key}")
	private  String APP_PRIVATE_KEY;
	
	@Value("${druid.url}")
	private  String druid;
	
	
	
	@Override
	public String aliPayPc(Pay pay) throws AlipayApiException, UnsupportedEncodingException {
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	        String appID = APP_ID;  
//	        String bizContent = toJson(pay.Content);  
	        String charset = CHARSET;  
	        String method = "alipay.trade.app.pay";  
	        String signType = "RSA2";  
	        String timestamp = sdf.format(new Date());  
	        String version = "1.0";  
	        String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	        System.out.println(APP_ID+ALIPAY_PUBLIC_KEY+APP_PRIVATE_KEY+druid);
	        String notify_url = "http://localhost:8803/oldneighborhood";// 增加支付异步通知回调,记住上下notify_url的位置,全在sign_type之前,很重要,同样放在最后都不行  
	        String return_url = "http://localhost:8803/oldneighborhood/return_url.jsp";
//	        String content = "app_id=" + appID + "&biz_content=" + bizContent + "&charset=" + charset + "&method=" + method  
//	                + "&ify_url=" + notify_url + "&sign_type=" + signType + "&tamp=" + timestamp + "&version="  
//	                + version;  
	    	AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl, APP_ID, APP_PRIVATE_KEY, "json", charset, ALIPAY_PUBLIC_KEY, signType);
	    	AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
	    	alipayRequest.setReturnUrl(return_url);
	    	alipayRequest.setNotifyUrl(notify_url);
	    	alipayRequest.setBizContent("{\"out_trade_no\":\""+ pay.Content.getPay_ID() +"\"," 
	    			+ "\"total_amount\":\""+ pay.Content.getPay_price()+"\"," 
	    			+ "\"subject\":\""+ pay.Content.getPay_code() +"\"," 
	    			+ "\"body\":\""+ pay.Content.body +"\"," 
	    			+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");  
	    	String form = alipayClient.pageExecute(alipayRequest).getBody();
	    	System.out.println(alipayClient.pageExecute(alipayRequest).getBody());
	    	HttpServletResponse httpResponse =  getResponse();
	    	httpResponse.setContentType("text/html;charset=" + charset);
	    	try {
				httpResponse.getWriter().write(form);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//直接将完整的表单html输出到页面
	    	try {
				httpResponse.getWriter().flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	        return "";
//{\"Result\": \"app_id=" + encode(appID) + "&biz_content=" + encode(bizContent) + "&charset="  
//	                + encode(charset) + "&method=" + encode(method) + "&ify_url=" + encode(notify_url) + "&sign_type="  
//	                + encode(signType) + "×tamp=" + encode(timestamp) + "&version=" + encode(version) + "&sign="  
//	                + encode(sign) + "\"}";  
	    }  
	
	private static HttpServletResponse getResponse() {
		HttpServletResponse response=servletActionContext.getResponse();
		return response;
	}
	  
//	    private String encode(String sign) throws UnsupportedEncodingException {  
//	        return URLEncoder.encode(sign, "utf-8").replace("+", "%20");  
//	    }  
//	  
//	    private String toJson(BizContent content) {  
//	        String context = "";  
//	        context += "{" + "\"timeout_express\":\"" + content.timeout_express + "\"," + "\"seller_id\":\""  
//	                + content.site_id + "\"," + "\"product_code\":\"" + content.pay_code + "\","  
//	                + "\"total_amount\":\"" + content.pay_amount + "\"," + "\"subject\":\"" + content.subject + "\","  
//	                + "\"body\":\"" + content.body + "\"," + "\"out_trade_no\":\"" + content.pay_ID + "\","
//	                +"\"u_ID\":\""+content.u_ID+"\","+"\"pay_price\":\""+content.pay_price+"\"}";  
//	  
//	        return context;  
//	}


	@Override
	public String notify(HttpServletRequest request) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		Map<String, String> params = new HashMap<String, String>();  
        Map<String, String[]> requestParams = request.getParameterMap();  
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {  
            String name = iter.next();  
            String[] values = requestParams.get(name);  
            String valueStr = "";  
            for (int i = 0; i < values.length; i++) {  
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";  
            }  
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化  
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");  
            params.put(name, valueStr);  
        }  
        String out_trade_no = request.getParameter("out_trade_no");// 商户订单号  
        boolean signVerified = false;  
        try {  
            signVerified = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, CHARSET);  
        } catch (AlipayApiException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
            return ("fail");// 验签发生异常,则直接返回失败  
        }  
        // 调用SDK验证签名  
        if (signVerified) {  
            // TODO 验签成功后  
            // 按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure  
            boolean result = updateALiPayOrderStatus(request);  
            System.out.println("验证成功,去更新状态 \t订单号:" + out_trade_no + "来自支付宝支付,更新结果:" + result);    
            if (result == true) {  
                return ("success");  
            } else {  
                return ("fail");// 更新状态失败  
            }  
        } else {  
            // TODO 验签失败则记录异常日志，并在response中返回failure.  
            System.out.println("验证失败,不去更新状态");  
            return ("fail");  
        }  
    
	}

	private boolean updateALiPayOrderStatus(HttpServletRequest request) {
		// TODO Auto-generated method stub
		PayInfo pay =new PayInfo();
		pay.setPay_ID(request.getParameter("out_trade_no"));
		pay.setSite_ID(request.getParameter("seller_id"));
		pay.setU_ID(request.getParameter("u_ID"));
		pay.setPay_amount(Integer.parseInt(request.getParameter("total_amount")));
		pay.setPay_code(request.getParameter("product_code"));
		pay.setPay_price(Double.parseDouble(request.getParameter("pay_price")));
		
		try{
			payDao.save(pay);
			return true;
		}catch(Exception e) {
			return false;
		}
	}




}
