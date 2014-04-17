package com.wolfsoft.hr.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;

import com.wolfsoft.hr.factory.HrFactory;
import com.wolfsoft.hr.model.entity.User;
import com.wolfsoft.hr.service.UserService;
import com.wolfsoft.hr.util.Utility;

@ViewScoped
@ManagedBean
public class LoginBean implements Serializable {

    private static final long serialVersionUID = -6239437588285327644L;
    @Autowired
    private UserService userService = HrFactory.getUserService();
    private String userName;
    private String passWord;

    @PostConstruct
    public void postContruct() {
    }

    public String login(){
    	String navegar="";
    	List<User> userList=userService.findByUserLogin(userName);
    	if(userList!=null && userList.size()>0){
    		User user=userList.get(0);
    		if(user.getUserPass().equals(passWord)){
    			navegar="pages/employee";
    		}else{
    			Utility.setErrorMessage("Error: Invalid Password");
    		}
    	}else{
    		Utility.setErrorMessage("Error: Invalid Username");
    	}
    	return navegar;
    }
    
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
  
}