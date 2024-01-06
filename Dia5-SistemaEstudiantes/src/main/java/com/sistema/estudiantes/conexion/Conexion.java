package com.sistema.estudiantes.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	public static
	Connection getConnection() {

		Connection conexion  = null;
		var        url       = "jdbc:mysql://localhost:3306/user?useSSL=false&serverTimezone=UTC";
		var        usario    = "root";
		var        password  = "root";

		//Cargamos la clase del driver en mysql en memoria
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(url, usario, password);
		}
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Error al conectar con la base de datos" + e.getMessage());
		}
		return conexion;
	}


	public static void main(String[] args) {
		var conexion = Conexion.getConnection();
		if(conexion != null){
			System.out.println("Conexion exitosa" + conexion);
		}else{
			System.out.println("Conexion fallida");
		}
	}

}
