package com.ejemplo.springboot.uno;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListadoPersonasAPP {
	public static void main(String[] args) {

		Scanner       consola       = new Scanner(System.in);
		//Definimos la lista fuera del ciclo while
		List<Persona> personasLista = new ArrayList<>();

		//Aqui creamos el menu
		var salir = false;
		while(!salir){
			mostrarMenu();
			try {
				salir = ejecutarOperacion(consola, personasLista);
			}catch (Exception e){
				System.out.println("Ocurrio un error: " + e.getMessage());
			}
			System.out.println("");
		}
	}

	private static void mostrarMenu(){
		//Mostramios el menu
		System.out.println("1. Agregar persona ");
		System.out.println("2. Listar persona ");
		System.out.println("3. Salir ");
		System.out.print("Escribe una de las opciones: ");
	}

	private static boolean ejecutarOperacion(Scanner consola, List<Persona> personas){

		var opcion = Integer.parseInt(consola.nextLine());
		var salir = false;

		//Revisamos la opcion proporcionada
		switch (opcion){
			case 1 -> {//Agregamos persona a la lista
				System.out.println("Proporciona el nombre: ");
				var nombre = consola.nextLine();

				System.out.println("Proporciona el telefono: ");
				var telefono = consola.nextLine();

				System.out.println("Proporciona el email: ");
				var email = consola.nextLine();

				//Creamos el objeto de tipo persona
				var persona = new Persona(nombre, telefono, email);

				//Aqui agregamos el objeto a la lista d epersonas
				personas.add(persona);
				System.out.println("La lista tiene: " + personas.size() + " elementos ");
			}
			case 2 -> {//Aqui listamos las personas
				System.out.println("Listado de personas: ");
				//Mejora usando lambda y metodo de referencia
				//personas.forEach((persona) -> System.out.println(persona)); //Metodo lambda esta es la forma 1
				personas.forEach(System.out::println);//Metodo de referencia esta es la forma 2
			}
			case 3 -> { //Salimos del ciclo
				System.out.println("Hasta pronto... ");
				salir	= true;
			}
			default -> System.out.println("Opcion no reconocida");
		}
		return salir;
	}

}