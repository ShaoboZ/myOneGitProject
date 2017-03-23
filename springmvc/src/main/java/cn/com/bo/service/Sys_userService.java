package cn.com.bo.service;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.bo.dao.Sys_userDao;
import cn.com.bo.entity.Sys_user;

@Service
public class Sys_userService {
	
	@Autowired
	private Sys_userDao sys_userDao;
	
	@Transactional
	public Integer save(Sys_user param) {
		return sys_userDao.save(param);
	}
	
	@Transactional
	public List<Sys_user> getList(Sys_user param){
		Criteria dc = sys_userDao.createCriteria();
		dc.addOrder(Order.desc("id"));
		return sys_userDao.list(dc);
	}
}
