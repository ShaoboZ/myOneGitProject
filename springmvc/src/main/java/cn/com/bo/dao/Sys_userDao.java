package cn.com.bo.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import cn.com.bo.entity.Sys_user;

@Component
public class Sys_userDao extends BaseDao<Sys_user,Integer> {
	
	public Integer save(Sys_user param){
		return super.save(param);
	}

	public List<Sys_user> getList(Sys_user param){
		return super.list();
	}
	
}
