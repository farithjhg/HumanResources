package com.wolfsoft.hr.service;


import java.util.List;

import com.wolfsoft.hr.model.entity.User;

public interface UserService {
	 /**
     * Return User's entity by PK 
     */
    public User findByPK(String id) ;

    /**
     * Return User rows
     */
    List<User> findAll() ;;

      
    /**
     * Add a row in the User Table
     * @return User 
     */
    public User save(User entity) ;

    /**
    * Delete a row in the UserList Table by PK
    */
    public void delete(User entity) ;
    
    /**
     * Return User row by User Login
     * @param userLogin
     * @return User
     */
    public List<User> findByUserLogin(String userLogin);
}
