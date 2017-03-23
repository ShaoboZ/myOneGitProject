package com.bo.spider.service;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.omg.CORBA.portable.RemarshalException;

import com.bo.spider.dao.DynatownDao;
import com.bo.spider.dao.RentRoomDao;
import com.bo.spider.entity.Dynatown;
import com.bo.spider.entity.RentRoom;

public class Spider5i5jService {

	private static DynatownDao dynatownDao = new DynatownDao();
	private static RentRoomDao rentRoomDao = new RentRoomDao();
	public Document post5i5j(String url) {
		try {
			/**
			 * 解析rule
			 */
			Connection conn = Jsoup.connect(url);
			// 设置查询参数
			conn.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:51.0) Gecko/20100101 Firefox/51.0");

			// 设置请求类型
			Document doc = conn.timeout(100000).post();
			return doc;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void parse5i5jDynatownHTML(Document doc){
		Elements zhiyes = doc.select(".zhiye_content");
		for (Element element : zhiyes) {
			Dynatown dynatown = new Dynatown();
			Element conts = element.select(".conts_left").get(0);
			// 姓名
			dynatown.setName(conts.getElementsByTag("span").text());
			// 手機號
			dynatown.setPhone(conts.getElementsByTag("b").text());
			
			Element leftfuwu = element.getElementsByClass("leftfuwusty").get(0);
			
			Elements dds = leftfuwu.getElementsByTag("dd");
			for (Element dd : dds) {
				String className = dd.className();
				if (className == null || className.equals("")) {
					Elements service_areas = dd.getElementsByTag("a");
					String service_area = "";
					for (Element area : service_areas) {
						service_area += area.text() + "  ";
					}
					dynatown.setService_area(service_area);
				} else if (className.equals("asty")){
					Elements familiar_cells = dd.getElementsByTag("a");
					String familiar_cell = "";
					for (Element cell : familiar_cells) {
						familiar_cell += cell.text() + "  ";
					}
					dynatown.setFamiliar_cell(familiar_cell);
				} else if (className.contains("shouzu houseNum")){
					int sell_num = Integer.parseInt(dd.getElementsByClass("f3d").text());
					dynatown.setSell_num(sell_num);
					int rent_num = Integer.parseInt(dd.getElementsByClass("fcf0").text());
					dynatown.setRent_num(rent_num);
				}
			}
			if (dynatownDao.emptyDynatown(dynatown)) {
				dynatownDao.saveDynatown(dynatown);
			} else {
				System.out.println(dynatown.getName()+"已经存在！");
			}
			
		}
		
		
	}
	
	
	public static void parse5i5jRentHTML(Document doc){
		Elements ul = doc.select("ul.list-body");
		Elements lis = ul.get(0).getElementsByTag("li");
		for (Element element : lis) {
			if (element.getAllElements().size() < 24 ) {
				continue;
			} 
			RentRoom rentRoom = new RentRoom();
			String path = element.select("img").get(0).attr("src");
			rentRoom.setImg_path(path);
			Elements divlist = element.select("div.list-info");
			String title = divlist.select("h2").select("a").text();
			rentRoom.setTitle(title);
			String cell_name = divlist.select("li").get(0).select("h3.rent-font").text();
			rentRoom.setCell_name(cell_name);
			Elements spans = divlist.select("li").get(1).getElementsByTag("span");
			rentRoom.setPattern(spans.get(0).text());
			rentRoom.setSize(spans.get(1).text());
			rentRoom.setRoom_orientation(spans.get(2).text());
			rentRoom.setFloor(spans.get(3).text());
			Elements a = element.select("div.list-info-r").select("h3");
			String money = element.select("div.list-info-r").select("h3").text();
			rentRoom.setMoney(money);
			String rent_type = element.select("div.list-info-r").select("p").text();
			rentRoom.setRent_type(rent_type.split("：")[1]);
			if (rentRoomDao.emptyRentRoom(rentRoom)) {
				rentRoomDao.saveRentRoom(rentRoom);
			} else {
				System.out.println(rentRoom.getTitle() + "已经存在");
			}
			
		}
		
		
	}
	public static void main(String[] args) {
		Spider5i5jService sp = new Spider5i5jService();
//		for (int i = 1; i < 42; i++) {
//			System.out.println("http://bj.5i5j.com/broker/huoying/n"+i);
//			Document doc = sp.post5i5j("http://bj.5i5j.com/broker/huoying/n"+i);
//			sp.parse5i5jDynatownHTML(doc);
//		}
//		sp.dynatownDao.closeCon();
		for (int i = 1; i < 8; i++) {
			System.out.println("http://bj.5i5j.com/rent/huoying/n"+i);
			Document doc = sp.post5i5j("http://bj.5i5j.com/rent/huoying/n"+i);
			sp.parse5i5jRentHTML(doc);
		}
		
		sp.dynatownDao.closeCon();
		sp.rentRoomDao.closeCon();
//		RentRoom rentRoom = new RentRoom();
//		rentRoom.setCell_name("天通苑北一区   ");
//		rentRoom.setFloor("楼层下部/11层");
//		rentRoom.setImg_path("http://image.5i5j.com/picture/slpic/l/house/3683/36831454/shinei/kfnnaafa3a71d39f.jpg.jpg");
//		rentRoom.setMoney("2500元/月");
//		rentRoom.setPattern("3室2厅");
//		rentRoom.setRent_type("合租");
//		rentRoom.setTitle("天通苑正规主卧带独立卫生！现代简约式装修！随时看随时住！");
//		rentRoom.setRoom_orientation("南北");
//		rentRoom.setSize("143平");
//		sp.rentRoomDao.saveRentRoom(rentRoom);
	}
}
