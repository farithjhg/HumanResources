package com.wolfsoft.hr.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.event.ActionEvent;

import org.richfaces.component.SortOrder;
import org.richfaces.component.UIDataTable;

import com.wolfsoft.hr.entity.Regions;
import com.wolfsoft.hr.factory.ServicesFactory;
import com.wolfsoft.hr.service.RegionsService;
import com.wolfsoft.hr.util.HRUtility;

/**
 * <p>
 * Title: ...
 * </p>
 * 
 * <p>
 * Description: Managed Bean
 * 
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: Wolfsoft Co.
 * </p>
 * 
 * @author Farith José Heras García
 * @version 1.0
 */

@ViewScoped
@ManagedBean
public class ListaRegionsBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private List listaDatos;
	private Regions regions;
	private boolean edicion = false;
	private UIDataTable dtItems;
	private int tipoEdicion = 1;
	private int rowSelected = 0;
	private RegionsService regionsService;
	private int page = 1;
	private SortOrder regionIdOrder = SortOrder.ascending;
	private SortOrder regionNameOrder = SortOrder.ascending;

	public ListaRegionsBean() {
		try {
			regionsService = ServicesFactory.getRegionsService();
			listaDatos = regionsService.findAll();
		} catch (Exception e) {
			HRUtility.asignarMensajeError(HRUtility.ERROR_MENSAJE_MANAGED_BEAN
					+ this.getClass().getName() + ", Error: " + e.getMessage());
		}
	}

	public void sortByRegionId() {
		regionNameOrder = SortOrder.unsorted;
		if (regionIdOrder.equals(SortOrder.ascending)) {
			regionIdOrder = SortOrder.descending;
		} else {
			regionIdOrder = SortOrder.ascending;
		}
	}

	public void remove() {
		try {
			regions = (Regions) listaDatos.get(rowSelected);
			regionsService.delete(regions);
			listaDatos.remove(regions);
			HRUtility
					.asignarMensajeInfo("Registro Eliminado Satisfactoriamente!");
		} catch (Exception e) {
			HRUtility.asignarMensajeError(HRUtility.ERROR_MENSAJE_ELIMINAR + e);
		}
	}

	public void cancelar(ActionEvent ae) {
		tipoEdicion = 1;
		edicion = false;
		regions = null;
	}

	public String regresar() {
		return HRUtility.regresar("listaRegionsBean", "HOME");
	}

	public void nuevo(ActionEvent ae) {
		rowSelected = -1;
		regions = new Regions();
	}

	public void modificar(ActionEvent ae) {
		tipoEdicion = 2;
		edicion = true;
		rowSelected = dtItems.getRowIndex();
		regions = (Regions) listaDatos.get(dtItems.getRowIndex());
	}

	public void eliminar(ActionEvent ae) {
		try {
			regions = (Regions) listaDatos.get(dtItems.getRowIndex());
			regionsService.delete(regions);
			listaDatos.remove(dtItems.getRowIndex());
			HRUtility
					.asignarMensajeInfo("Registro Eliminado Satisfactoriamente!");
		} catch (Exception e) {
			HRUtility.asignarMensajeError(HRUtility.ERROR_MENSAJE_ELIMINAR + e);
		}
	}

	public void grabar() {
		try {
			regionsService.save(regions);
			if (rowSelected < 0) {
				listaDatos.add(regions);
			} else {
				listaDatos.set(rowSelected, regions);
			}
			// cancelar(ae);
		} catch (Exception e) {
			HRUtility.asignarMensajeError(HRUtility.ERROR_MENSAJE_GRABAR + e);
		}
	}

	public boolean isEdicion() {
		return edicion;
	}

	public void setEdicion(boolean edicion) {
		this.edicion = edicion;
	}

	public void setDtItems(UIDataTable dtItems) {
		this.dtItems = dtItems;
	}

	public UIDataTable getDtItems() {
		return dtItems;
	}

	public List getListaDatos() {
		return listaDatos;
	}

	public void setListaDatos(List lista) {
		this.listaDatos = lista;
	}

	public int getTipoEdicion() {
		return tipoEdicion;
	}

	public void setTipoEdicion(int tipoEdicion) {
		this.tipoEdicion = tipoEdicion;
	}

	public Regions getRegions() {
		return regions;
	}

	public void setRegions(Regions regions) {
		this.regions = regions;
	}

	public int getRowSelected() {
		return rowSelected;
	}

	public void setRowSelected(int rowSelected) {
		this.rowSelected = rowSelected;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public SortOrder getRegionIdOrder() {
		return regionIdOrder;
	}

	public void setRegionIdOrder(SortOrder regionIdOrder) {
		this.regionIdOrder = regionIdOrder;
	}

	public SortOrder getRegionNameOrder() {
		return regionNameOrder;
	}

	public void setRegionNameOrder(SortOrder regionNameOrder) {
		this.regionNameOrder = regionNameOrder;
	}

}
