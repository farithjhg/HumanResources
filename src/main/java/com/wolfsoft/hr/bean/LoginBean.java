package com.wolfsoft.hr.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean
public class LoginBean implements Serializable {

    private static final long serialVersionUID = -6239437588285327644L;

    private String userName;
    private String passWord;

    @PostConstruct
    public void postContruct() {
    }

    public String login(){
    	return "MENU";
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