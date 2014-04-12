package com.wolfsoft.hr.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import com.wolfsoft.hr.model.entity.Employee;
import com.wolfsoft.hr.util.HRUtility;

@ViewScoped
@ManagedBean
public class EmpleadoBean implements Serializable {

    private static final long serialVersionUID = -6239437588285327644L;
    private Employee employee;
    private List<SelectItem> departaments;
    private List<Employee> employees;
    
    public EmpleadoBean() {
	}
    
    @PostConstruct
    public void postContruct() {
    	employee = new Employee();
    	departaments = HRUtility.listDepartaments();
    	employees = new ArrayList<Employee>();
    	employees.add(new Employee());
    }

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<SelectItem> getDepartaments() {
		return departaments;
	}

	public void setDepartaments(List<SelectItem> departaments) {
		this.departaments = departaments;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
 
}