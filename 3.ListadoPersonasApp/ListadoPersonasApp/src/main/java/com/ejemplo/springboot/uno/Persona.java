package com.ejemplo.springboot.uno;

public
class Persona {


	private        int    id;
	private        String nombre;
	private        String telefono;
	private        String email;
	private static int    numeroPersonas = 0;


	public
	Persona() {
		this.id = ++Persona.numeroPersonas;
	}

	public
	Persona(String nombre, String telefono, String email) {
		this();
		this.nombre         = nombre;
		this.telefono       = telefono;
		this.email          = email;
	}


	public
	int getId() {
		return id;
	}

	public
	void setId(int id) {
		this.id = id;
	}

	public
	String getNombre() {
		return nombre;
	}

	public
	void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public
	String getTelefono() {
		return telefono;
	}

	public
	void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public
	String getEmail() {
		return email;
	}

	public
	void setEmail(String email) {
		this.email = email;
	}


	@Override
	public
	String toString() {
		return "Persona{" +
				 "id=" + id +
				 ", nombre='" + nombre + '\'' +
				 ", telefono='" + telefono + '\'' +
				 ", email='" + email + '\'' +
				 '}';
	}

	public static
	void main(String[] args) {
		Persona persona = new Persona();
		persona.setNombre("Juan");
		persona.setEmail("juan@perez.com");
		persona.setTelefono("1234567890");
		System.out.println(persona);
	}


}
