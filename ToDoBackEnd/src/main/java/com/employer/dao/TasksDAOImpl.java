package com.employer.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.employer.model.Tasks;

@SuppressWarnings("deprecation")
@Repository(value = "TasksDAO")
public class TasksDAOImpl implements TasksDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	public TasksDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	@Transactional
	public boolean save(Tasks tasks) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().save(tasks);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean update(Tasks tasks) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().update(tasks);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	

	@Transactional
	public boolean delete(Tasks tasks) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().delete(tasks);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public Tasks get(int task_id) {
		// TODO Auto-generated method stub
		String hql = "from Tasks where task_id=" + task_id ;

		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Tasks> list = (List<Tasks>) query.list();

		if (list != null && !list.isEmpty()) {
			System.out.println("task retrieved from DAOImpl");
			return list.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Tasks> getByUserInfo(int user_id) {
		String hql = "from Tasks where user_id=" + user_id;
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	
	@Transactional
	public List<Tasks> list() {
		// TODO Auto-generated method stub
		String hql = " from Tasks";
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();

	}


	
}
