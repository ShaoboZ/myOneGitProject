package com.bo.spider.service;

import java.io.IOException;  
import java.util.ArrayList;  
import java.util.List;  
import org.jsoup.Connection;  
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;  
import org.jsoup.nodes.Element;  
import org.jsoup.select.Elements;  
  



import com.bo.spider.entity.LinkTypeData;
import com.bo.spider.entity.Rule;
import com.bo.spider.entity.RuleException;
  
/** 
 *  
 * @author zhy 
 *  
 */  
public class ExtractService  
{  
    /** 
     * @param rule 
     * @return 
     */  
    public static List<LinkTypeData> extract(Rule rule)  
    {  
  
        // 进行对rule的必要校验  
        validateRule(rule);  
  
        List<LinkTypeData> datas = new ArrayList<LinkTypeData>();  
        LinkTypeData data = null;  
        try  
        {  
            /** 
             * 解析rule 
             */  
            String url = rule.getUrl();  
            String[] params = rule.getParams();  
            String[] values = rule.getValues();  
            String resultTagName = rule.getResultTagName();  
            int type = rule.getType();  
            int requestType = rule.getRequestMoethod();  
  
            Connection conn = Jsoup.connect(url);
            // 设置查询参数  
  
            if (params != null)  
            {  
                for (int i = 0; i < params.length; i++)  
                {  
                    conn.header(params[i], values[i]);  
                }  
            }  
  
            // 设置请求类型  
            Document doc = null;  
            switch (requestType)  
            {  
            case Rule.GET:  
                doc = conn.timeout(100000).get();  
                break;  
            case Rule.POST:  
                doc = conn.timeout(100000).post();  
                break;  
            }  
  
            //处理返回数据  
            Elements results = new Elements();  
            switch (type)  
            {  
            case Rule.CLASS:  
                results = doc.getElementsByClass(resultTagName);  
                break;  
            case Rule.ID:  
                Element result = doc.getElementById(resultTagName);  
                results.add(result);  
                break;  
            case Rule.SELECTION:  
                results = doc.select(resultTagName);  
                break;  
            default:  
                //当resultTagName为空时默认去body标签  
                if (!isEmpty(resultTagName))  
                {  
                    results = doc.getElementsByTag("body");  
                }  
            }  
  
            for (Element result : results)  
            {  
                Elements links = result.getElementsByTag("a");  
  
                for (Element link : links)  
                {  
                    //必要的筛选  
                    String linkHref = link.attr("href");  
                    String linkText = link.text();  
  
                    data = new LinkTypeData();  
                    data.setUrl(linkHref);  
                    data.setContent(linkText);  
  
                    datas.add(data);  
                }  
            }  
  
        } catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
  
        return datas;  
    }  
  
    /** 
     * 对传入的参数进行必要的校验 
     */  
    private static void validateRule(Rule rule)  
    {  
        String url = rule.getUrl();  
        if (!isEmpty(url))  
        {  
            throw new RuleException("url不能为空！");  
        }  
        if (!url.startsWith("http://") && !url.startsWith("https://"))  
        {  
            throw new RuleException("url的格式不正确！");  
        }  
  
        if (rule.getParams() != null && rule.getValues() != null)  
        {  
            if (rule.getParams().length != rule.getValues().length)  
            {  
                throw new RuleException("参数的键值对个数不匹配！");  
            }  
        }  
  
    }  
    
    /** 
     * 不为空且不为null
     */  
    private static boolean isEmpty(String param)  
    {  
    	if(!param.equals("") && param != null){
    		return true;
    	}
    	return false;
    } 
    
    public static void main(String[] args) {
    	Rule rule = new Rule("http://bj.5i5j.com/broker/changping",  
                new String[] {"User-Agent"}, 
                new String[] {"Mozilla/5.0 (Windows NT 10.0; WOW64; rv:51.0) Gecko/20100101 Firefox/51.0"},  
                "zhiye_content", Rule.CLASS, Rule.POST);  
        List<LinkTypeData> extracts = ExtractService.extract(rule);  
        printf(extracts);  
        
//        try {
//			Document doc = Jsoup.connect("http://blog.csdn.net/zq710727244/article/details/53883659")
//					.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:51.0) Gecko/20100101 Firefox/51.0")
//					.timeout(5000)
//					.get();
//			Elements results = doc.select(".markdown_views");
//			System.out.println(results.text());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        
	}
    
    public static void printf(List<LinkTypeData> datas)  
    {  
        for (LinkTypeData data : datas)  
        {  
            System.out.println(data.getContent());  
            System.out.println(data.getUrl());  
            System.out.println("***********************************");  
        }  
  
    }  
  
  
}  
