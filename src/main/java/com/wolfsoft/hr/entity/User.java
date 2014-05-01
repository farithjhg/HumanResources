package com.wolfsoft.hr.entity; 

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.TableGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQuery(name = "User.findAll", query = "select o from User o") 
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="id", nullable = false)
    private Long id;
    @Column(name="user_login")
    private String userLogin;
    @Column(name="user_pass")
    private String userPass;
    @Column(name="user_nicename")
    private String userNicename;
    @Column(name="user_email")
    private String userEmail;
    @Column(name="user_url")
    private String userUrl;
    @Column(name="user_registered")
    private Timestamp userRegistered;
    @Column(name="user_activation_key")
    private String userActivationKey;
    @Column(name="user_status")
    private Integer userStatus;
    @Column(name="display_name")
    private String displayName;
    @Column(name="role_id", insertable = false, updatable = false)
    private Integer roleId;
    @ManyToOne(cascade = { }, fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id" , insertable=false, updatable=false)
    private Rol rol;

    public User() {
        rol = new Rol();
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getUserLogin(){
        return userLogin;
    }

    public void setUserLogin(String userLogin){
        this.userLogin = userLogin;
    }

    public String getUserPass(){
        return userPass;
    }

    public void setUserPass(String userPass){
        this.userPass = userPass;
    }

    public String getUserNicename(){
        return userNicename;
    }

    public void setUserNicename(String userNicename){
        this.userNicename = userNicename;
    }

    public String getUserEmail(){
        return userEmail;
    }

    public void setUserEmail(String userEmail){
        this.userEmail = userEmail;
    }

    public String getUserUrl(){
        return userUrl;
    }

    public void setUserUrl(String userUrl){
        this.userUrl = userUrl;
    }

    public Timestamp getUserRegistered(){
        return userRegistered;
    }

    public void setUserRegistered(Timestamp userRegistered){
        this.userRegistered = userRegistered;
    }

    public String getUserActivationKey(){
        return userActivationKey;
    }

    public void setUserActivationKey(String userActivationKey){
        this.userActivationKey = userActivationKey;
    }

    public Integer getUserStatus(){
        return userStatus;
    }

    public void setUserStatus(Integer userStatus){
        this.userStatus = userStatus;
    }

    public String getDisplayName(){
        return displayName;
    }

    public void setDisplayName(String displayName){
        this.displayName = displayName;
    }

    public Integer getRoleId(){
        return roleId;
    }

    public void setRoleId(Integer roleId){
        this.roleId = roleId;
    }

    public Rol  getRol(){
        return rol;
    }

    public void setRol(Rol rol){
        this.rol = rol;
    }

}
