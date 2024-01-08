package com.libros.sistema.services;

import com.libros.sistema.model.Libro;
import com.libros.sistema.repository.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroServicio implements ILibroServicio{

	@Autowired
	private LibroRepositorio libroRepositorio;

	@Override
	public List<Libro> listarLibros() {
		return libroRepositorio.findAll();
	}

	@Override
	public Libro buscarLibroById(Integer idLibro) {
		Libro libro = libroRepositorio.findById(idLibro).orElse(null);
		return libro;
	}

	@Override
	public void guardarLibro(Libro libro) {
		libroRepositorio.save(libro);
	}

	@Override
	public void eliminarLibro(Libro libro) {
		libroRepositorio.delete(libro);
	}
}
