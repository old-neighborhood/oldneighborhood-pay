package com.oldneighborhood.demo.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016091100483995";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC9x9yjPf/SDq4IsMyPcafE7/+L8RK6qXqz4KwRKEBvHjNSwgkVxuLa2gamwW3Vpz5URT5Nf641FtkL3QG8ZNHm9KXWT6mho4Diu8+/HisFzBp/Tso9uJXGIOUJyA1Y92FWgtAxahslxXr5waEGq6NVS/5i/UXjIYw55tgvthqxk0ILTF5UhiijJp1oqQ5KJvnvtOLRd5TbBpF9a5XaPWYiD9HSa7Z5JWvXj85FkQYi7AiyF1VATpWZiVHdk54A/Ev/atU+T1BCHDrIeuPuYfz3j7Q+NVRKGrElHIQPTMamZABQM+k1DOhkRJsrd/Q5FJ03QGJlg/pmz/8b4WyF2K3DAgMBAAECggEBAJQSosYDk9yTc5p4R8MMDLiOATdxCwn1PjWm8NoaK9wHFkhV+LyzB0F2p4Xomgf/i5Q4ypmxK31krEijUb1qdJPdAp+qXFIvvlop67fUlADb9TirgwSRRtrlw47UNgXaTmover1zamcYJBFG0JrhxHjC6GLX9nV3MW/j+32OelldkTjaNXRYYrrR+X3j8xDVv6kZ1CJmPAbuJtjWsZMDAsYYmnWnzzshqGyY9X+WWS+5RrxI9gaV74yJQXEWOYPtjS87ECQBOMiFXu/3GbqVMheY6qNFbmtJzptXmYlHRw3stgB6vPYfgxn7JkA6wH7txKX3qZ8KRkRfnnFDLUBxuXECgYEA7qTKGcECsgSOkHgAemLIOqq4Xgm+LATdq3y/TAJ20nh29ovpllJTOWAFYqJcB/AmzAMkcqeE6LPFrAWiRMb5FrXyemUc6BRiv/J8RvzavjT0uezW2UCXapOwFGKxsjQsJZh/x5STawEMFQh6c6jZ7cLZh/wHwyg4Powbe0AlBV8CgYEAy5VP4aLLRUKFkK45xSdCJdZi6hwSNjxKX2oH9u1uQQfKqHBH4taAy19uxjtIp4lHl8DDZHJMYhlG0ShbrC7K8KeUiMrU4egk4YgYDrESEhvQudVy8EPBugf5saUXjwSl6qb1VpSv36/UCxTG419TK0aPpdc8ujhYZX1kNDNQLh0CgYBHoRDEUCmV80YzWMGT+ohtC4cROs1o+wDqEyR8FRVDMUNL100CgK1YQFj9dQw4mb2baKoM+/kpu6PucTZ2fF6RkYU5dZYKluvY/1iMGiMyCkUWdV87kGaiZ7fnNoRtn7lMg4x/BDqHWKYevg+7HYhkFCMk7OILksvnAjjRcrxp8wKBgQCxQbIpvbrK9R6iWHBIbuQzWbUKLaLVwiznYsTIhPZofMZBXnHY2feOG+uxgdDCvs6tHNOD09kZ2PRGDKO1CZD9dIvphuFQSPZDwGFh+V7IkZttEqJe/D/jICqSODKI/f0QkIow9okjdE5lz5NUSZ16opdUSFHBummNBF6I7Ny1KQKBgQDK7peeGb/b0LPxQPiREprC3vUGoyOq60b5FMcNrQqkAiOce2/v3SsYDMZmpHLqxF+LOftZAl8BTX+qT6mg0zLP8/G1tnNUW/xSOZRv4ZQu6apdGkJPh7o5Ulpdqn6G56f4HG9CyzIo4y1RIvbH8503Mf+QqFoLy5utNhDkC4qdxg==";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqNvQgc2V17GsbrS42nGVlOMQ1HP+KFig0K4qUaKKESq70vhM1PAif3fk9jCFM5MW2q9h/uRJJc84ntaMBMZDhkok8W80UwV48tTpApeXcS6mQF1BvADzDAi469D2CSDfN8Q2aerCjvtMUZHmfXc3U1b9ewfc5HYrohaG9RzyDEopXMInfYCWkXX/7qEVSlHCm51+PCYbbqr7TC6UI3Sq9tZ6zjLn77X+IQvUo9cY85/TcKlroKzKUKfyeyrxu/36EahQV2J7K/bnz+WIFd0ANtK8a6w8nqMmJAZlxtb0VKHN2UncQxOuJsEJnAD7G+qv4kx0FPkD6hVJnhAwr/NBtwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8803/oldneighborhood/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

