package com.cuentas.sistemas;

import jakarta.faces.view.ViewScoped;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@ViewScoped
@SpringBootApplication
public class SistemaCuentasApp{

	public static void main(String[] args) {

		SpringApplication.run(SistemaCuentasApp.class, args);
	}

}

