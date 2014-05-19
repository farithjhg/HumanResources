package com.wolfsoft.hr.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.richfaces.component.UIDataTable;

import com.wolfsoft.hr.entity.Countries;
import com.wolfsoft.hr.entity.Regions;
import com.wolfsoft.hr.factory.ServicesFactory;
import com.wolfsoft.hr.service.CountriesService;
import com.wolfsoft.hr.service.RegionsService;
import com.wolfsoft.hr.util.HRUtility;

/**
 * <p>
 * Title: ...</p>
 *
 * <p>
 * Description: Managed Bean
 * 
* </p>
 *
 * <p>
 * Copyright: Copyright (c) 2008</p>
 * <p>
 * Company: Wolfsoft Co.</p>
 *
 * @author Farith José Heras García
 * @version 1.0
 */

@ViewScoped
@ManagedBean
public class ListaCountriesBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<Countries> listaDatos;
    private Countries countries;
    private boolean edicion = false;
    private UIDataTable dtItems;
    private int tipoEdicion = 1;
    private int rowSelected = 0;
    private CountriesService countriesService;
    private RegionsService regionsService;
    private List<SelectItem> regions;

    @PostConstruct
    public void postContruct() {
        try {
            countriesService = ServicesFactory.getCountriesService();
            regionsService = ServicesFactory.getRegionsService();
            listRegions(regionsService.findAll());
            listaDatos = countriesService.findAll();
        } catch (Exception e) {
            HRUtility.asignarMensajeError(HRUtility.ERROR_MENSAJE_MANAGED_BEAN + this.getClass().getName() + ", Error: " + e.getMessage());
        }
    }

    public void remove() {
        try {
            countries = (Countries) listaDatos.get(rowSelected);
            countriesService.delete(countries);
            listaDatos.remove(countries);
            HRUtility
                    .asignarMensajeInfo("Registro Eliminado Satisfactoriamente!");
        } catch (Exception e) {
            HRUtility.asignarMensajeError(HRUtility.ERROR_MENSAJE_ELIMINAR + e);
        }
    }

    public void cancelar(ActionEvent ae) {
        tipoEdicion = 1;
        edicion = false;
        countries = null;
    }

    public String regresar() {
        return HRUtility.regresar("listaCountriesBean", "HOME");
    }

    public void nuevo(ActionEvent ae) {
        tipoEdicion = 1;
        edicion = true;
        countries = new Countries();
    }

    public void modificar(ActionEvent ae) {
        tipoEdicion = 2;
        edicion = true;
        rowSelected = dtItems.getRowIndex();
        countries = (Countries) listaDatos.get(dtItems.getRowIndex());
    }

    public void eliminar(ActionEvent ae) {
        try {
            countries = (Countries) listaDatos.get(dtItems.getRowIndex());
            countriesService.delete(countries);
            listaDatos.remove(dtItems.getRowIndex());
            HRUtility.asignarMensajeInfo("Registro Eliminado Satisfactoriamente!");
        } catch (Exception e) {
            HRUtility.asignarMensajeError(HRUtility.ERROR_MENSAJE_ELIMINAR + e);
        }
    }

    public void grabar(ActionEvent ae) {
        try {
            countries.setRegions(regionsService.findByPK(countries.getRegions().getRegionId()));
            countriesService.save(countries);
            if (tipoEdicion == 1) {
                listaDatos.add(countries);
            } else {
                listaDatos.set(rowSelected, countries);
            }
            cancelar(ae);
        } catch (Exception e) {
            HRUtility.asignarMensajeError(HRUtility.ERROR_MENSAJE_GRABAR + e);
        }
    }

    private void listRegions(List<Regions> regionsList) {
        regions = new ArrayList<SelectItem>();
        for (Regions region : regionsList) {
            regions.add(new SelectItem(region.getRegionId(), region.getRegionName()));
        }
    }

    public boolean isEdicion() {
        return edicion;
    }

    public void setEdicion(boolean edicion) {
        this.edicion = edicion;
    }

    public List<Countries> getListaDatos() {
        return listaDatos;
    }

    public void setListaDatos(List<Countries> lista) {
        this.listaDatos = lista;
    }

    public int getTipoEdicion() {
        return tipoEdicion;
    }

    public void setTipoEdicion(int tipoEdicion) {
        this.tipoEdicion = tipoEdicion;
    }

    public Countries getCountries() {
        return countries;
    }

    public void setCountries(Countries countries) {
        this.countries = countries;
    }

    public UIDataTable getDtItems() {
        return dtItems;
    }

    public void setDtItems(UIDataTable dtItems) {
        this.dtItems = dtItems;
    }

    public int getRowSelected() {
        return rowSelected;
    }

    public void setRowSelected(int rowSelected) {
        this.rowSelected = rowSelected;
    }

    public List<SelectItem> getRegions() {
        return regions;
    }

    public void setRegions(List<SelectItem> regions) {
        this.regions = regions;
    }
}
