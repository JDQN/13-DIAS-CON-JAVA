package org.example.presentacion;

import org.example.domain.Peliculas;
import org.example.services.IServicioPeliculas;
import org.example.services.ServicioPeliculasArchivos;
import org.example.services.ServicioPeliculasListar;

import java.util.Scanner;


public class CatalogoPeliculasApp {

	public static void main(String[] args) {

		var salir   = false;
		var consola = new Scanner(System.in);

		//Agregamos la implementacion del servicio
		//IServicioPeliculas servicioPeliculas = new ServicioPeliculasListar();

		IServicioPeliculas servicioPeliculas = new ServicioPeliculasArchivos();
		while (!salir) {
			try {
				mostrarMenu();
				salir = ejecutarOpciones(consola, servicioPeliculas);
			}
			catch (Exception e) {
				System.out.println("Error!");
			}
			System.out.println("\n");
		}//fin while
	}


	private static void mostrarMenu(){
		System.out.println("Catalogo de Peliculas - Menu");
		System.out.println("1. Agregar Pelicula");
		System.out.println("2. Listar Peliculas");
		System.out.println("3. Buscar Pelicula");
		System.out.println("4. Salir");
	}


	private static boolean ejecutarOpciones(Scanner consola, IServicioPeliculas servicioPeliculas){
		var opcion = Integer.parseInt(consola.nextLine());
		var salir = false;
		switch(opcion){
			case 1 -> {
				System.out.println("Ingrese el nombre de la pelicula");
				var nombrePelicula = consola.nextLine();
				servicioPeliculas.agregarPelicula(new Peliculas(nombrePelicula));
			}
			case 2 -> servicioPeliculas.listarPeliculas();
			case 3 -> {
				System.out.println("Ingrese el nombre de la pelicula a buscar");
				var nombrePelicula = consola.nextLine();
				servicioPeliculas.buscarPelicula(new Peliculas(nombrePelicula));
			}
			case 4-> {
				System.out.println("Gracias por usar el catalogo de peliculas");
				salir = true;
			}
			default -> System.out.println("Opcion no reconocida" + opcion);
		}
		return salir;
	}


}

