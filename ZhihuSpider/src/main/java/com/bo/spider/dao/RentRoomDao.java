package com.bo.spider.dao;

import java.util.List;

import com.bo.spider.entity.Dynatown;
import com.bo.spider.entity.RentRoom;


/**
 * 
 * @author Zhang Shaobo
 *
 */
public class RentRoomDao extends BaseDao<RentRoom>{

    
	public void saveRentRoom(RentRoom param){
		try {
			save(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean emptyRentRoom(RentRoom param){
		try {
			String sql = "select * from rentroom where title = '"+param.getTitle() +"' and cell_name = '"+param.getCell_name()+"'";
			System.out.println(sql);
			List<RentRoom> dys = super.findListBysql(sql);
			if (dys != null && dys.size()>0) {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
