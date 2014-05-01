package com.wolfsoft.hr.bean;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.richfaces.component.SortOrder;

import com.wolfsoft.hr.entity.Employees;
import com.wolfsoft.hr.factory.ServicesFactory;
import com.wolfsoft.hr.service.EmployeesService;
import com.wolfsoft.hr.util.HRUtility;

@ViewScoped
@ManagedBean
public class EmpleadoBean implements Serializable {

    private static final long serialVersionUID = -6239437588285327644L;
    private Employees employee;
    private List<SelectItem> departaments;
    private List<Employees> employees;
    private EmployeesService employeesService;
    private SortOrder employeeIdOrder = SortOrder.unsorted;
	private SortOrder firstNameOrder = SortOrder.unsorted;
	private SortOrder lastNameOrder = SortOrder.unsorted;    
    
    public EmpleadoBean() {
	}
    
    @PostConstruct
    public void postContruct() {
    	employee = new Employees();
    	departaments = HRUtility.listDepartaments();
    	employeesService = ServicesFactory.getEmployeesService();
    	Hashtable<Object, Object> camposWhere=null;
		String[] camposORDER_BY={};
		employees = employeesService.searchByParametros(camposWhere, camposORDER_BY);
		//employeesService.findAll();
    	//employees.add(new Employees());
    }

    public void sortByEmployeeId() {
    	firstNameOrder = SortOrder.unsorted;
    	lastNameOrder = SortOrder.unsorted;
        if (employeeIdOrder.equals(SortOrder.ascending)) {
        	employeeIdOrder = SortOrder.descending;
        } else {
        	employeeIdOrder = SortOrder.ascending;
        }
    }     
    
    public void sortByFirstName() {
    	employeeIdOrder = SortOrder.unsorted;
    	lastNameOrder = SortOrder.unsorted;
        if (firstNameOrder.equals(SortOrder.ascending)) {
        	firstNameOrder = SortOrder.descending;
        } else {
        	firstNameOrder = SortOrder.ascending;
        }
    } 
    
    public void sortByLastName() {
    	employeeIdOrder = SortOrder.unsorted;
    	firstNameOrder = SortOrder.unsorted;
        if (lastNameOrder.equals(SortOrder.ascending)) {
        	lastNameOrder = SortOrder.descending;
        } else {
        	lastNameOrder = SortOrder.ascending;
        }
    }        
    
	public Employees getEmployee() {
		return employee;
	}

	public void setEmployee(Employees employee) {
		this.employee = employee;
	}

	public List<SelectItem> getDepartaments() {
		return departaments;
	}

	public void setDepartaments(List<SelectItem> departaments) {
		this.departaments = departaments;
	}

	public List<Employees> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employees> employees) {
		this.employees = employees;
	}

	public SortOrder getEmployeeIdOrder() {
		return employeeIdOrder;
	}

	public void setEmployeeIdOrder(SortOrder employeeIdOrder) {
		this.employeeIdOrder = employeeIdOrder;
	}

	public SortOrder getFirstNameOrder() {
		return firstNameOrder;
	}

	public void setFirstNameOrder(SortOrder firstNameOrder) {
		this.firstNameOrder = firstNameOrder;
	}

	public SortOrder getLastNameOrder() {
		return lastNameOrder;
	}

	public void setLastNameOrder(SortOrder lastNameOrder) {
		this.lastNameOrder = lastNameOrder;
	}
 
}