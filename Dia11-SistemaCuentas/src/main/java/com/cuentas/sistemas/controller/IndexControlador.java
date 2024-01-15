package com.cuentas.sistemas.controller;

import com.cuentas.sistemas.model.Cuenta;
import com.cuentas.sistemas.service.CuentaServicio;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@ViewScoped
public class IndexControlador {

	@Autowired
	CuentaServicio cuentaServicio;
	private List<Cuenta> cuentas;

	private static final Logger logger = LoggerFactory.getLogger(IndexControlador.class);
	private Cuenta cuentaSeleccionada;

	@PostConstruct
	public void init() {
		cargarDatos();
	}

	public void cargarDatos() {
		this.cuentas = cuentaServicio.listarCuentas();
		cuentas.forEach(cuenta -> logger.info(cuenta.toString()));
	}

	public void agregarCuenta(){
		this.cuentaSeleccionada = new Cuenta();
	}

	public void guardarCuenta(){
		logger.info("Cuenta a guardar: " + this.cuentaSeleccionada.toString());

		//Aqui agregamos la cuenta a la lista de cuentas
		if(this.cuentaSeleccionada.getIdCuenta() == null){
			this.cuentaServicio.guardarCuenta(this.cuentaSeleccionada);
			this.cuentas.add(this.cuentaSeleccionada);
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(("Cuenta Agregada")));
		}else{//Aqui modificamos la cuenta
			this.cuentaServicio.guardarCuenta(this.cuentaSeleccionada);
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(("Cuenta Actualizada")));
		}
		//Aqui ocultamos la ventana modal
		PrimeFaces.current().executeScript("PF('dialogoCuenta').hide()");
		//Aqui actualizamos la tabla
		PrimeFaces.current().ajax().update("form:-cuentas:mesajes", "forma-cuentas:cuentas-tabla");
		//Reset
		this.cuentaSeleccionada = null;
	}


	public  void eliminarCuenta(){
		logger.info("Cuenta a eliminar: " + this.cuentaSeleccionada.toString());
		this.cuentaServicio.eliminarCuenta(this.cuentaSeleccionada);
		//Aqui eliminamos el reguistro de la lista de cuentas
		this.cuentas.remove(this.cuentaSeleccionada);
		//Aqui relizamos un Reset del objeto seleccionado de la tabla
		this.cuentaSeleccionada = null;
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(("Cuenta Eliminada")));
		PrimeFaces.current().ajax().update("form:-cuentas:mesajes", "forma-cuentas:cuentas-tabla");
	}


}
