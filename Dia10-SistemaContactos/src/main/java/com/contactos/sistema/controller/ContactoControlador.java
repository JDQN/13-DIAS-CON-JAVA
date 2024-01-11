package com.contactos.sistema.controller;

import com.contactos.sistema.model.Contacto;
import com.contactos.sistema.services.ContactoServicio;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class ContactoControlador {

	private static final Logger logger = LoggerFactory.getLogger(ContactoControlador.class);

	@Autowired
	ContactoServicio contactoServicio;

	@GetMapping(value="/")
	public String iniciar(ModelMap modelo){
		List<Contacto> contactos = contactoServicio.listarContactos();
		contactos.forEach((contacto) -> logger.info(contacto.toString()));

		//Compartimos el modelo con la vista
		modelo.put("contactos", contactos);
		return "index"; //index.html
	}

	@GetMapping(value="/agregar")
	public String mostrarAgregar(){
		return "agregar"; //agregar.html
	}

	@PostMapping(value="/agregar")
	public String agregar(@ModelAttribute("contactoForma") Contacto contacto){
		logger.info("Contacto a agregar: " + contacto);
		contactoServicio.guardarContacto(contacto);
		return "redirect:/"; //redirige al controlador del path "/"
	}

	@GetMapping(value="/editar/{id}")
	public String mostrarEditar(@PathVariable(value = "id") int idContacto, ModelMap modelo){
		Contacto contacto = contactoServicio.buscarContactoPorId(idContacto);
		logger.info("Contacto a editar (mostrar): " + contacto);
		modelo.put("contacto", contacto);
		return "editar"; //mostrar editar.html
	}

	@PostMapping(value="/editar")
	public String editar(@ModelAttribute("contactoForma") Contacto contacto){
		logger.info("Contacto a guardar (editar): " + contacto);
		contactoServicio.guardarContacto(contacto);
		return "redirect:/"; //redirige al controlador del path "/"
	}


	@DeleteMapping(value="/eliminar/{id}")
	public String eliminar(@RequestParam int idContacto){
		Contacto contacto = new Contacto();
		logger.info("Contacto a eliminar (eliminar): " + contacto);
		contacto.setIdContacto(idContacto);
		contactoServicio.eliminarContacto(contacto);
		return "redirect:/"; //redirige al controlador del path "/"
	}

}
