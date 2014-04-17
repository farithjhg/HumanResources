package com.wolfsoft.hr.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wolfsoft.hr.model.entity.User;
import com.wolfsoft.hr.repository.UserRepository;
import com.wolfsoft.hr.service.UserService;

@Service("userService")
@Repository
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@PersistenceContext
	private EntityManager em;
	
	@Transactional(readOnly=true)
	public User findByPK(String id) {
		return userRepository.findOne(id);
	}

	@Transactional(readOnly=true)
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

	public User save(User entity) {
		if(entity.getId()==null){  //Insert SessionLog
			userRepository.save(entity);
		}else{  					  //Update SessionLog
			em.merge(entity);
		}
		return entity;
	}

	@Override
	public void delete(User entity) {
		em.remove(findByPK(entity.getId()));
	}

	@Override
	public List<User> findByUserLogin(String userLogin) {
		return userRepository.findByUserLogin(userLogin);
	}

}
