package org.example.services;

import org.example.domain.Peliculas;

public interface IServicioPeliculas {

	public void listarPeliculas();
	public void agregarPelicula(Peliculas pelicula);
	public void buscarPelicula(Peliculas pelicula);

}
