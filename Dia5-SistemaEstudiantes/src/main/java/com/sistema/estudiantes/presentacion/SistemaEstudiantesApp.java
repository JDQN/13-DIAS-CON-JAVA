package com.sistema.estudiantes.presentacion;

import com.sistema.estudiantes.dao.EstudianteDAO;
import com.sistema.estudiantes.domain.Estudiante;

import java.util.EnumSet;
import java.util.List;
import java.util.Scanner;


public class SistemaEstudiantesApp {

	public static void main(String[] args) {

		var salir   = false;
		var consola = new Scanner(System.in);
		var estudianteDao = new EstudianteDAO();

		while (!salir) {
			try {
				mostrarMenu();
				salir = ejecutarOpciones(consola, estudianteDao);
			}
			catch (Exception e) {
				System.out.println("Ocurrio un error al ejecutar el programa: " + e.getMessage());
			}
			System.out.println("");
		}
	}


	private static void mostrarMenu(){
		System.out.print("""
            *** Sistema de Estudiantes ***
            1. Listar Estudiantes
            2. Buscar Estudiante
            3. Agregar Estudiante
            4. Modificar Estudiante
            5. Eliminar Estudiante
            6. Salir
            Elige una opcion: 
            """);
	}


	private static boolean ejecutarOpciones(Scanner consola, EstudianteDAO estudianteDAO) {
		var opcion = Integer.parseInt(consola.nextLine());
		var salir = false;

		switch (opcion) {
			case 1 -> {//Listar estudiantes
				System.out.println("Listar Estudiante...");
				List<Estudiante> estudiantes = estudianteDAO.listarEstudiantes();
				estudiantes.forEach(System.out::println);
			}
			case 2 -> {//Buscar estudiante por id
				System.out.println("Ingrese el id del estudiante a buscar");

				var idEstudiante = Integer.parseInt(consola.nextLine());
				var estudiante = new Estudiante(idEstudiante);
				var encontrado = estudianteDAO.buscarEstudiantePorId(estudiante);

				if(encontrado){
					System.out.println("Estudiante encontrado: " + estudiante);
				}else{
					System.out.println("Estudiante no encontrado: " + estudiante);
				}
			}

			case 3 -> {//Agregar nuevo estudiante
				System.out.println("Agregar Estudiante: ");

				System.out.print("Nombre: ");
				var nombre = consola.nextLine();
				System.out.print("Apellido: ");
				var apellido = consola.nextLine();
				System.out.print("Telefono: ");
				var telefono = consola.nextLine();
				System.out.print("Email: ");
				var email = consola.nextLine();

				var estudiante = new Estudiante(nombre, apellido, telefono, email);
				var agregado = estudianteDAO.agregarEstudiante(estudiante);

				if(agregado){
					System.out.println("Estudiante agregado correctamente");
				}else{
					System.out.println("No se pudo agregar el estudiante");
				}
			}

			case 4 -> {//Modificar estudiante
				System.out.println("Modificar Estudiante: ");
				System.out.println("Id Estudiante: ");
				var idEstudiante = Integer.parseInt(consola.nextLine());
				System.out.print("Nombre: ");
				var nombre = consola.nextLine();
				System.out.print("Apellido: ");
				var apellido = consola.nextLine();
				System.out.print("Telefono: ");
				var telefono = consola.nextLine();
				System.out.print("Email: ");
				var email = consola.nextLine();

				// Crear el objeto estudiante a modificar
				var estudiante = new Estudiante(idEstudiante, nombre, apellido, telefono, email);
				var modificado = estudianteDAO.modificarEstudiante(estudiante);
				if(modificado){
					System.out.println("Estudiante modificado correctamente: " + estudiante);
				}else {
					System.out.println("No se pudo modificar el estudiante: " + estudiante);
				}
			}
			case 5 -> { //Eliminar estudiante
				System.out.println("Eliminar estudinate: ");
				System.out.println("Id Estudiante: ");
				var idEstudiante = Integer.parseInt(consola.nextLine());
				var estudiante = new Estudiante(idEstudiante);
				var eliminado = estudianteDAO.eliminarEstudiante(estudiante);

				if(eliminado){
					System.out.println("Estudiante eliminado correctamente: " + estudiante);
				}else{
					System.out.println("No se pudo eliminar el estudiante: " + estudiante);
				}
			}
			case 6 -> {
				System.out.println("Saliendo del sistema...");
				salir = true;
			}
			default -> System.out.println("Opcion no valida");
		}
		return salir;
	}

}
