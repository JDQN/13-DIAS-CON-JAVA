package com.empleados.sistema.services;

import com.empleados.sistema.model.Empleado;

import java.util.List;

public interface IEmpleadoServicio {

	public List<Empleado> listarEmpleados();

	public Empleado buscarEmpleadoPorId(Integer idEmpleado);

	//Con el metodo guardarEmpleado() podemos guardar y actualizar un empleado
	//ya que si el id del empleado existe en la base de datos, se actualiza el registro
	//Pero si el Id no existe, se crea un nuevo registro
	public void guardarEmpleado(Empleado empleado);

	public void eliminarEmpleado(Empleado empleado);
}
