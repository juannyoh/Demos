package com.ouye.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ouye.bean.UserEntity;

@Repository
public class MutiUserDaoImpl implements MutiUserDao {
	
	 @Autowired
	 private EntityManagerFactory emFactory;

	@Override
	public void saveUser(UserEntity user) {
		EntityManager em = emFactory.createEntityManager();
		em.persist(user);
	}

}
