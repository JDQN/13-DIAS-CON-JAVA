package com.tareas.sistema;

import com.tareas.sistema.presentacion.SistemasTareasFx;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TareasApplication {

	public static void main(String[] args) {
		//SpringApplication.run(SistemaTareasApp.class, args);
		Application.launch(SistemasTareasFx.class, args);
	}

}
