package com.contactos.sistema.services;

import com.contactos.sistema.model.Contacto;
import com.contactos.sistema.repository.ContactoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactoServicio implements IContactoServicio{


	@Autowired
	private  ContactoRepositorio contactoRepositorio;


	@Override
	public List<Contacto> listarContactos() {
		return contactoRepositorio.findAll();
	}

	@Override
	public Contacto buscarContactoPorId(Integer idContacto) {
		Contacto contacto = contactoRepositorio.findById(idContacto).orElse(null);
		return contacto;
	}

	@Override
	public void guardarContacto(Contacto contacto) {
		contactoRepositorio.save(contacto);
	}

	@Override
	public void eliminarContacto(Contacto contacto) {
		contactoRepositorio.delete(contacto);
	}
}
