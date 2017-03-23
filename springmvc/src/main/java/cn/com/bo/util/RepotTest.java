package cn.com.bo.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.apache.log4j.Logger;


public class RepotTest
{
	private static Logger log = Logger.getLogger(RepotTest.class.getName());
	public static void main(String[] args)
	{
		getReportTest();
//		add_planTest();
	}
	
	public static void add_planTest(){
		HttpClient client = HttpConnectionManager.getDefaultHttpClient();
        HttpPost post = new HttpPost("http://192.168.1.217:8020/dsp/foreign_addPlan?token=123456");
        // 填充表单
        try
        {
        	String a = "{'planDataList': [{ 'planId' : '1', 'startDate' : '2016-11-15','endDate' : '2016-11-17','dailyBalance':'2000.00','adClickUrl': 'http://click.juwoso.com/','creativeList':[{'creativeId':1, 'adWidth':640, 'adHeight':100,'imageUrl':'http://migu.ggfeng.com/migu%2F20161114%2F1479105304348.jpg', 'creativeTitle':'男时尚……泳裤', 'creativeType': 1},{'creativeId':2, 'adWidth':640,'adHeight':200,'imageUrl':'http://migu.ggfeng.com/migu%2F20161115%2F1479193592217.jpg','creativeTitle':'男时尚……泳衣','creativeType': 1}]},{ 'planId' : '1', 'startDate' : '2016-11-09','endDate' : '2016-11-09','dailyBalance':'2000.00','adClickUrl': 'http://click.juwoso.com/','creativeList':[{'creativeId':1, 'adWidth':640, 'adHeight':100,'imageUrl':'http://migu.ggfeng.com/migu%2F20161115%2F1479193592217.jpg', 'creativeTitle':'男时尚……泳裤', 'creativeType': 1},{'creativeId':2, 'adWidth':640,'adHeight':200,'imageUrl':'http://migu.ggfeng.com/migu%2F20161115%2F1479193592217.jpg','creativeTitle':'男时尚……泳衣','creativeType': 1}]}]}";
            post.addHeader(HTTP.CONTENT_TYPE, "text/html");
            // 测试内容是请求内容，12345678为secret
            byte[] bytereq = AesUtil.encrypt(a, "12345678");
            // 加密内容转换为16进制
            String req = AesUtil.parseByte2HexStr(bytereq);
            log.info("请求加密后内容：" + req);
            StringEntity se = new StringEntity(URLEncoder.encode(req, HTTP.UTF_8));
            post.setEntity(se);
            HttpResponse response = client.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
            	log.info("statusCode:" + statusCode);
                HttpEntity entity2 = response.getEntity();
                BufferedReader reader2 = new BufferedReader(
                        new InputStreamReader(entity2.getContent(), "UTF-8"));
                String buffer2;
                StringBuffer html = new StringBuffer();
                while ((buffer2 = reader2.readLine()) != null) {    
                    html.append(buffer2);
                }
                // 返回内容位16进制
                log.info("返回原始内容:"+html);
                // 返回内容转换为2进制byte数组
                byte[] res = AesUtil.parseHexStr2Byte(html.toString());
                // 内容解密
                res = AesUtil.decrypt(res, "12345678");
                // byte转换为String可操作内容
                String resStr = new String(res);
                log.info("返回报文：" + resStr);
                return ;
            } else {
            	log.error("statusCode:" + statusCode);
                return ;
            }
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            log.error(e.getMessage());
            return ;
        }
	}
	public static void getReportTest(){
		HttpClient client = HttpConnectionManager.getDefaultHttpClient();
        HttpPost post = new HttpPost("http://192.168.1.217:8020/dsp/foreign_getReport?token=123456");
        // 填充表单
        try
        {
        	String a = "{'date':'2016-11-16'}";
            post.addHeader(HTTP.CONTENT_TYPE, "text/html");
            // 测试内容是请求内容，12345678为secret
            byte[] bytereq = AesUtil.encrypt(a, "12345678");
            // 加密内容转换为16进制
            String req = AesUtil.parseByte2HexStr(bytereq);
            log.info("请求加密后内容：" + req);
            StringEntity se = new StringEntity(URLEncoder.encode(req, HTTP.UTF_8));
            post.setEntity(se);
            HttpResponse response = client.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
            	log.info("statusCode:" + statusCode);
                HttpEntity entity2 = response.getEntity();
                BufferedReader reader2 = new BufferedReader(
                        new InputStreamReader(entity2.getContent(), "UTF-8"));
                String buffer2;
                StringBuffer html = new StringBuffer();
                while ((buffer2 = reader2.readLine()) != null) {    
                    html.append(buffer2);
                }
                // 返回内容位16进制
                log.info("返回原始内容:"+html);
                // 返回内容转换为2进制byte数组
                byte[] res = AesUtil.parseHexStr2Byte(html.toString());
                // 内容解密
                res = AesUtil.decrypt(res, "12345678");
                // byte转换为String可操作内容
                String resStr = new String(res);
                log.info("返回报文：" + resStr);
                return ;
            } else {
            	log.error("statusCode:" + statusCode);
                return ;
            }
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            log.error(e.getMessage());
            return ;
        }
	}
}

