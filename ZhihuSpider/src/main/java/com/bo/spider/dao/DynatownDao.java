package com.bo.spider.dao;

import java.util.List;

import com.bo.spider.entity.Dynatown;


/**
 * 
 * @author Zhang Shaobo
 *
 */
public class DynatownDao extends BaseDao<Dynatown>{

    
	public void saveDynatown(Dynatown param){
		try {
			save(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean emptyDynatown(Dynatown param){
		try {
			String sql = "select * from dynatown where phone = '"+param.getPhone() +"' and name = '"+param.getName()+"'";
			System.out.println(sql);
			List<Dynatown> dys = super.findListBysql(sql);
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
