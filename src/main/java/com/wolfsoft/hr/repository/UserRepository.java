package com.wolfsoft.hr.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.wolfsoft.hr.model.entity.User;

public interface UserRepository extends CrudRepository<User, String> {
	@Transactional(readOnly=true)
	public List<User> findByUserLogin(String userLogin);
}
