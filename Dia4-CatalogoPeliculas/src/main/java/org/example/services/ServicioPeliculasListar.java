package org.example.services;

import org.example.domain.Peliculas;

import java.util.ArrayList;
import java.util.List;

public
class ServicioPeliculasListar implements IServicioPeliculas{

	private final List<Peliculas> peliculas;

	public ServicioPeliculasListar() {
		this.peliculas = new ArrayList<>();
	}

	@Override
	public void listarPeliculas() {
		System.out.println("Listado de Peliculas...");
		peliculas.forEach(System.out::println);
	}

	@Override
	public void agregarPelicula(Peliculas pelicula) {
		peliculas.add(pelicula);
		System.out.println("Se agrego la pelicula: " + pelicula);
	}

	@Override
	public void buscarPelicula(Peliculas pelicula) {
		//Aqui regresamos el indice de la pelicula encontrada en la lista
		var indice = peliculas.indexOf(pelicula);
		if(indice == -1){
			System.out.println(pelicula + " no se encuentra en la lista");
			return;
		}else{
			System.out.println("La pelicula se encuentra en el indice: " + indice);
		}

	}

	public static void main(String[] args) {

		Peliculas pelicula1 = new Peliculas("Pelicula 1");
		Peliculas pelicula2 = new Peliculas("Pelicula 2");

		IServicioPeliculas servicioPeliculas = new ServicioPeliculasListar();
		servicioPeliculas.agregarPelicula(pelicula1);
		servicioPeliculas.agregarPelicula(pelicula2);

		servicioPeliculas.listarPeliculas();

		//servicioPeliculas.buscarPelicula(pelicula1);
		servicioPeliculas.buscarPelicula(new Peliculas("Pelicula 3"));


	}

}
