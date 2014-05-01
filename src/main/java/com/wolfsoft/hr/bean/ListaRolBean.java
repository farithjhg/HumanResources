package com.wolfsoft.hr.bean; 

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.richfaces.component.SortOrder;

import com.wolfsoft.hr.entity.Rol;
import com.wolfsoft.hr.factory.ServicesFactory;
import com.wolfsoft.hr.service.RolService;
import com.wolfsoft.hr.util.HRUtility;
/**
* <p>Title: ...</p>
*
* <p>Description: Managed Bean 
* 
* </p>
*
* <p>Copyright: Copyright (c) 2008</p>
* <p>Company: Wolfsoft Co.</p>
* @author Farith José Heras García
* @version 1.0
*/

@SessionScoped
@ManagedBean
public class ListaRolBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Rol> listaDatos;
    private Rol rol;
    private int rowSelected=-1;
    private RolService rolService;
    private SortOrder roleIdOrder = SortOrder.unsorted;
	private SortOrder roleNameOrder = SortOrder.unsorted;
    
    @PostConstruct
    public void postContruct() {
    	try {
        	rolService = ServicesFactory.getRolService();
        	listaDatos = rolService.findAll();
        	rol = new Rol();
        } catch (Exception e) {
            HRUtility.asignarMensajeError(HRUtility.ERROR_MENSAJE_MANAGED_BEAN+this.getClass().getName()+ ", Error: " +e.getMessage());
        }    	
    }    
    
    public void sortByRoleId() {
    	roleNameOrder = SortOrder.unsorted;
        if (roleIdOrder.equals(SortOrder.ascending)) {
        	roleIdOrder = SortOrder.descending;
        } else {
        	roleIdOrder = SortOrder.ascending;
        }
    }   

    public void sortByRoleName() {
    	roleIdOrder = SortOrder.unsorted;
        if (roleNameOrder.equals(SortOrder.ascending)) {
        	roleNameOrder = SortOrder.descending;
        } else {
        	roleNameOrder = SortOrder.ascending;
        }
    }   
   
    public String regresar(){
        return HRUtility.regresar("listaRolBean","HOME");
    }

    public void nuevo(){
    	rowSelected = -1;
        rol = new Rol();
    }

    public void modificar(){
        rol = listaDatos.get(rowSelected);
    }

    public void delete(){
        try {
            rol = listaDatos.get(rowSelected);
            rolService.delete(rol);
            listaDatos.remove(rol);
            HRUtility.asignarMensajeInfo("Registro ["+rol.getRoleId()+":"+rol.getRoleName()+"] Eliminado Satisfactoriamente!");
        } catch (Exception e) {
        	HRUtility.asignarMensajeError(HRUtility.ERROR_MENSAJE_ELIMINAR+"\n"+HRUtility.getRootErrorMessage(e));
        }
    }

    public String grabar(){
        try {
            if(rowSelected < 0){
            	rolService.save(rol);
                listaDatos.add(rol);
                HRUtility.asignarMensajeInfo("Row ["+rol.getRoleId()+","+rol.getRoleName()+"] Added Satisfactorily!");
            }else{
            	 rolService.save(rol);
                 listaDatos.set(rowSelected, rol);
                 HRUtility.asignarMensajeInfo("Row ["+rol.getRoleId()+","+rol.getRoleName()+"] Updated Satisfactorily!");
            }
            rowSelected = -1;
            
        } catch (Exception e) {
        	HRUtility.asignarMensajeError(HRUtility.ERROR_MENSAJE_GRABAR+e);
        }
        return null;
    }

    public Rol getRol() {
            return rol;
    }

    public void setRol(Rol rol) {
            this.rol = rol;
    }

	public int getRowSelected() {
		return rowSelected;
	}

	public void setRowSelected(int rowSelected) {
		this.rowSelected = rowSelected;
	}

	public List<Rol> getListaDatos() {
		return listaDatos;
	}

	public void setListaDatos(List<Rol> listaDatos) {
		this.listaDatos = listaDatos;
	}

	public SortOrder getRoleIdOrder() {
		return roleIdOrder;
	}

	public void setRoleIdOrder(SortOrder roleIdOrder) {
		this.roleIdOrder = roleIdOrder;
	}

	public SortOrder getRoleNameOrder() {
		return roleNameOrder;
	}

	public void setRoleNameOrder(SortOrder roleNameOrder) {
		this.roleNameOrder = roleNameOrder;
	}
}
