package com.contactos.sistema.services;

import com.contactos.sistema.model.Contacto;

import java.util.List;

public interface IContactoServicio {

	public List<Contacto> listarContactos();

	public Contacto buscarContactoPorId(Integer idContacto);

	//Con el metodo guardarContacto() podemos guardar y actualizar un empleado
	//ya que si el id del empleado existe en la base de datos, se actualiza el registro
	//Pero si el Id no existe, se crea un nuevo registro
	public void guardarContacto(Contacto contacto);

	public void eliminarContacto(Contacto contacto);

}
