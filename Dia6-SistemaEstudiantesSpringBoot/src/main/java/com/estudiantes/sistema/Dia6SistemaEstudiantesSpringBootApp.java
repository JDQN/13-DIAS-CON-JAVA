package com.estudiantes.sistema;

import com.estudiantes.sistema.model.Estudiante;
import com.estudiantes.sistema.services.EstudianteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Dia6SistemaEstudiantesSpringBootApp implements CommandLineRunner {

	@Autowired
	private EstudianteServicio estudianteServicio;


	private static final Logger logger = LoggerFactory.getLogger(Dia6SistemaEstudiantesSpringBootApp.class);


	String nl = System.lineSeparator();


	public static void main(String[] args) {
		logger.info("Iniciando la aplicacion...");
		// Levantar la fabrica de Spring
		SpringApplication.run(Dia6SistemaEstudiantesSpringBootApp.class, args);
		logger.info("Aplicacion finalizada!");
	}


	@Override
	public void run(String... args) throws Exception {
		logger.info("Ejecutando el metodo run de la interfaz CommandLineRunner");

		var salir = false;
		var consola = new Scanner(System.in);

		while (!salir) {
			mostrarMenu();
			salir = ejecutarOpciones(consola);
			logger.info(nl);
		}
	}

	private void mostrarMenu(){
		logger.info(nl);
		logger.info("""
				*** Sistema de Estudiantes ***
				1. Listar estudiantes
				2. Buscar Estudiante
				3. Agregar Estudiante
				4. Modificar Estudiante
				5. Eliminar Estudiante
				6. Salir
				Elige una opcion:
				""");
	}

	private boolean ejecutarOpciones(Scanner consola){

		var opcion = Integer.parseInt(consola.nextLine());
		var salir = false;

		switch (opcion) {
			case 1 ->{//Listar estudiantes
				logger.info(nl + "Listando estudiantes..." + nl);
				List<Estudiante> estudiantes = estudianteServicio.listarEstudiante();
				estudiantes.forEach((estudiante) -> logger.info(estudiante.toString() + nl));
			}
			case 2 -> { //Buscar estudiante por Id
				logger.info(nl + "Ingrese el id del estudiante a buscar: ");

				var idEstudiante = Integer.parseInt(consola.nextLine());
				Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);
				var idEncontrado = estudiante.getIdEstudiante();

				if(idEncontrado != null){
					logger.info("Estudiante encontrado: " + estudiante + nl);
				}else{
					logger.info("Estudiante no encontrado con id: " + estudiante + nl);
				}
			}
			case 3 -> { //Agregar nuevo estudiante
				logger.info("Agreagr estudiante.. ");

				logger.info("Nombre del estudiante: ");
				var nombre = consola.nextLine();
				logger.info("Apellido del estudiante: ");
				var apellido = consola.nextLine();
				logger.info("Telefono del estudiante: ");
				var telefono = consola.nextLine();
				logger.info("Email del estudiante: ");
				var email = consola.nextLine();

				//Aqui creamos el objeto estudiante sin el ID
				var estudiante = new Estudiante();
				estudiante.setNombre(nombre);
				estudiante.setApellido(apellido);
				estudiante.setTelefono(telefono);
				estudiante.setEmail(email);
				estudianteServicio.guardarEstudiante(estudiante);
				logger.info("Estudiante agregado: " + estudiante + nl);
			}
			case 4 -> {//Modificae estudiante
				logger.info("Modificar estudiante.. ");
				logger.info("Ingrese el id del estudiante a modificar: ");
				var idEstudiante = Integer.parseInt(consola.nextLine());

				//Buscamos el estudiante a modificar
				Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);
				if(estudiante != null){
					logger.info("Nombre del estudiante: ");
					var nombre = consola.nextLine();
					logger.info("Apellido del estudiante: ");
					var apellido = consola.nextLine();
					logger.info("Telefono del estudiante: ");
					var telefono = consola.nextLine();
					logger.info("Email del estudiante: ");
					var email = consola.nextLine();

					//Aqui creamos el objeto estudiante con el ID
					estudiante.setNombre(nombre);
					estudiante.setApellido(apellido);
					estudiante.setTelefono(telefono);
					estudiante.setEmail(email);
					estudianteServicio.guardarEstudiante(estudiante);
					logger.info("Estudiante modificado correctamente: " + estudiante + nl);
				}else{
					logger.info("Estudiante no encontrado con id: " + estudiante + nl);
				}
			}
			case 5 -> {//Eliminar estudiante
				logger.info("Eliminar estudiante: " + nl);
				logger.info("Ingrese el id del estudiante a eliminar: ");
				var idEstudiante = Integer.parseInt(consola.nextLine());

				//Buscamos el estudiante a eliminar
				Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);
				if(estudiante != null){
					estudianteServicio.eliminarEstudiante(estudiante);
					logger.info("Estudiante eliminado correctamente: " + estudiante + nl);
				}else{
					logger.info("Estudiante no encontrado con id: " + idEstudiante + nl);
				}
			}
			case 6 -> {//Salir
				logger.info("Saliendo del sistema..." + nl + nl);
				salir = true;
			}
			default -> System.out.println("Opcion no valida" + opcion + nl);
		}

		return salir;
	}

}
