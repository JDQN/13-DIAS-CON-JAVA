package com.cuentas.sistemas.service;

import com.cuentas.sistemas.model.Cuenta;
import org.springframework.stereotype.Service;

import java.util.List;



public interface ICuentaServicio {

	public List<Cuenta> listarCuentas();

	public Cuenta buscarCuentaById(Integer idCuenta);

	public void guardarCuenta(Cuenta cuenta);

	public void eliminarCuenta(Cuenta cuenta);

}