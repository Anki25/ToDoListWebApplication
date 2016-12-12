package com.employer.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.employer.model.UserInfo;

@SuppressWarnings("deprecation")
@Repository(value = "UserInfoDAO")
public class UserInfoDAOImpl implements UserInfoDAO{

	@Autowired
	private SessionFactory sessionFactory;

	public UserInfoDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public boolean save(UserInfo userInfo) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().save(userInfo);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean update(UserInfo userInfo) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().update(userInfo);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean delete(UserInfo userInfo) {
		// TODO Auto-generated method stub
		try {
			//System.out.println(userID);
			//String y=Integer.toString(userID);
			sessionFactory.getCurrentSession().delete(userInfo);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public UserInfo get(int userID) {
		// TODO Auto-generated method stub
		String hql = "from UserInfo where user_id=" + userID ;

		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<UserInfo> list = (List<UserInfo>) query.list();

		if (list != null && !list.isEmpty()) {
			System.out.println("user retrieved from DAOImpl");
			return list.get(0);
		} else {
			return null;
		}
	}

	@Transactional
	public UserInfo getName(String name) {
		// TODO Auto-generated method stub
		String hql = "from UserInfo where userID=" + "'" + name + "'";
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<UserInfo> list = (List<UserInfo>) query.list();

		if (list != null && !list.isEmpty()) {
			System.out.println("username retrieved from DAOImpl");
			return list.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<UserInfo> list() {
		// TODO Auto-generated method stub
		String hql = " from UserInfo";
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();

	}

	@Transactional
	public UserInfo isValidUser(String email, String password) {
		// TODO Auto-generated method stub
		String hql = "from UserInfo where email = '" + email + "' and password='" + password + "'";
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<UserInfo> list = (List<UserInfo>) query.list();

		if (list != null && !list.isEmpty()) {
			return list.get(0);
		} else {
			return null;
		}
	}

}
