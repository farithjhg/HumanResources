package com.wolfsoft.hr.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.wolfsoft.hr.entity.User;
import com.wolfsoft.hr.factory.ServicesFactory;
import com.wolfsoft.hr.service.UserService;
import com.wolfsoft.hr.util.Utility;

@ViewScoped
@ManagedBean
public class LoginBean implements Serializable {

    private static final long serialVersionUID = -6239437588285327644L;
    private UserService userService;
    private String userName;
    private String passWord;

    @PostConstruct
    public void postContruct() {
    	userService = ServicesFactory.getUserService();
    }

    public String login(){
    	String navegar="";
    	List<User> userList=userService.findByUserLogin(userName);
    	if(userList!=null && userList.size()>0){
    		User user=userList.get(0);
    		if(user.getUserPass().equals(passWord)){
    			navegar="pages/home";
    			Utility.subirASession("userBean", user);
    		}else{
    			Utility.setErrorMessage("Error: Invalid Password");
    		}
    	}else{
    		Utility.setErrorMessage("Error: Invalid Username");
    	}
    	return navegar;
    }
    
    public String logout(){
		return Utility.logout("LoginBean");
	}    
    
    private HttpServletRequest httpServletRequest=null;
    private FacesContext faceContext=null;
    private FacesMessage facesMessage;
    
    public String cerrarSession()
    {
    	faceContext=FacesContext.getCurrentInstance();
        httpServletRequest=(HttpServletRequest)faceContext.getExternalContext().getRequest();
        
        httpServletRequest.getSession().removeAttribute("loginBean");
        facesMessage=new FacesMessage(FacesMessage.SEVERITY_INFO, "Session cerrada correctamente", null);
        faceContext.addMessage(null, facesMessage);
        return "index";
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