package com.libros.sistema.services;

import com.libros.sistema.model.Libro;
import java.util.List;


public interface ILibroServicio {

	public List<Libro> listarLibros();

	public Libro buscarLibroById(Integer idLibro);


	//Este mrtodo lo vamos a guardar un libro y acutilizar un libro
	//Si vamos a guardar un libro nuevo, el idLibro va a ser null
	//Si vamos a actualizar un libro, el idLibro va a ser el id del libro que vamos a actualizar
	public void guardarLibro(Libro libro);

	public void eliminarLibro(Libro libro);

}
