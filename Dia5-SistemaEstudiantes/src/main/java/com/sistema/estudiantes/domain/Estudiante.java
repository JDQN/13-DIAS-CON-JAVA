package com.sistema.estudiantes.domain;

import lombok.Data;

@Data
public
class Estudiante {

	private int    idEstudiante;
	private String nombre;
	private String apellido;
	private String telefono;
	private String email;


	public Estudiante() {
	}
	public Estudiante(int idEstudiante) {
		this.idEstudiante = idEstudiante;
	}
	public Estudiante(String nombre, String apellido, String telefono, String email) {
		this.nombre   = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.email    = email;
	}
	public Estudiante(int idEstudiante, String nombre, String apellido, String telefono, String email) {
		this.idEstudiante = idEstudiante;
		this.nombre       = nombre;
		this.apellido     = apellido;
		this.telefono     = telefono;
		this.email        = email;
	}


	@Override
	public String toString() {
		return "Estudiante{" +
				 "idEstudiante=" + idEstudiante +
				 ", nombre='" + nombre + '\'' +
				 ", apellido='" + apellido + '\'' +
				 ", telefono='" + telefono + '\'' +
				 ", email='" + email + '\'' +
				 '}';
	}
}
