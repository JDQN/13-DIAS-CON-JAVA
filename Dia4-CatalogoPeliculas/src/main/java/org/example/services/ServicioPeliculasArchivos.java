package org.example.services;

import org.example.domain.Peliculas;

import java.io.*;
import java.util.Scanner;

public class ServicioPeliculasArchivos implements IServicioPeliculas{

	private final String NOMBRE_ARCHIVO = "peliculas.txt";

	public ServicioPeliculasArchivos(){
		var archivo = new File(NOMBRE_ARCHIVO);
		try{
			//Si el archivo ya existe no se vuelve a creae
			if(archivo.exists()){
				System.out.println("El archivo ya existe");
			}else{
				//Si el archivo no existe se crea vacio
				var salida = new PrintWriter(new FileWriter(archivo));
				salida.close();
				System.out.println("Se a creado el archivo con exito");
			}
		}catch (Exception e){
			System.out.println("Ocurrio un error al abrir el archivo" + e.getMessage());
		}
	}


	@Override
	public void listarPeliculas() {
		//Aqui volvemos a abrir el archivo
		var archivo = new File(NOMBRE_ARCHIVO);

		try{
			//Aqui abrimos el archivo para lectura
			var entrada = new BufferedReader(new FileReader(archivo));
			//Aqui ya leemos linea a linea el archivo
			String linea;
			linea = entrada.readLine();
			//Aqui leemos todas las lineas disponibles
			while(linea != null){
				var pelicula = new Peliculas(linea);
				System.out.println(pelicula);
				//Antes d eterminar el ciclo volvemos a leer la sigiente linea
				linea = entrada.readLine();
			}
			//Aqui cerramos el archivo
			entrada.close();
		}catch (Exception e){
			System.out.println("Ocurrio un error al leer el archivo" + e.getMessage());
		}

	}


	@Override
	public void agregarPelicula(Peliculas pelicula) {
		boolean anexar = false;
		var archivo = new File(NOMBRE_ARCHIVO);
		try{
			//Aqui revisamos si el archivo existe
			anexar = archivo.exists();
			var salida = new PrintWriter(new FileWriter(archivo, anexar));
			//Aqui agregamos la pelicula (toString)
			salida.println(pelicula);
			salida.close();
			System.out.println("Se agrego al archivo: " + pelicula);
		}catch (Exception e){
			System.out.println("Ocurrio un error al agregar la prelicula " + e.getMessage());
		}
	}


	@Override
	public void buscarPelicula(Peliculas pelicula) {
		var archivo = new File(NOMBRE_ARCHIVO);
		try{
			//Abrimos el archivo para lectura linea a linea
			var entrada = new BufferedReader(new FileReader(archivo));
			String lineaTexto;
			lineaTexto = entrada.readLine();
			var indice = 1;
			var encontrada = false;
			var peliculaBuscar = pelicula.getNombre();
			while(lineaTexto != null){
				//Buscamos sin importar mayusculas o minusculas
				if(peliculaBuscar != null && peliculaBuscar.equalsIgnoreCase(lineaTexto)){
					encontrada = true;
					break;
				}
				//Leemos la sigiente liena antes d ela sigiente iteracion
				lineaTexto = entrada.readLine();
				indice++;
			}
			//Aqui imprimimos los resultados de la busqueda
			if(encontrada){
				System.out.println("Pelicula " + lineaTexto + " encontrada en la linea " + indice);
			}else{
				System.out.println("No se encontro la pelicula: " + pelicula.getNombre());
			}
			entrada.close();
		}catch (Exception e){
			System.out.println("Ocurrio un error al buscar en el archivo: " + e.getMessage());
		}
	}
}
