package com.sistema.estudiantes.dao;

import com.mysql.cj.protocol.Resultset;
import com.sistema.estudiantes.conexion.Conexion;
import com.sistema.estudiantes.domain.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sistema.estudiantes.conexion.Conexion.getConnection;


//DAO - Data Access Object
public class EstudianteDAO {


	//Metodo listar estudiante
	public List<Estudiante> listarEstudiantes() {

		List<Estudiante>  estudiantes = new ArrayList<>();
		PreparedStatement ps;
		ResultSet         rs;
		Connection        conn        = getConnection();
		String            sql         = "SELECT * FROM estudiante ORDER BY idEstudiante";

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				var estudiante = new Estudiante();
				estudiante.setIdEstudiante(rs.getInt("idEstudiante"));
				estudiante.setNombre(rs.getString("nombre"));
				estudiante.setApellido(rs.getString("apellido"));
				estudiante.setTelefono(rs.getString("telefono"));
				estudiante.setEmail(rs.getString("email"));
				estudiantes.add(estudiante);
			}
		}
		catch (Exception e) {
			System.out.println("Ocurrio un error al seleccionar los datos: " + e.getMessage());
		}
		finally {
			try {
				conn.close();
			}
			catch (Exception e) {
				System.out.println("Ocurrio un error al cerrar la conexion: " + e.getMessage());
			}
		}
		return estudiantes;
	}


	//Metodo buscar estudiante por id
	public boolean buscarEstudiantePorId(Estudiante estudiante) {
		PreparedStatement ps;
		ResultSet         rs;
		Connection        conn = getConnection();
		String            sql  = "SELECT * FROM estudiante WHERE idEstudiante = ?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, estudiante.getIdEstudiante());
			rs = ps.executeQuery();

			if (rs.next()) {
				estudiante.setNombre(rs.getString("nombre"));
				estudiante.setApellido(rs.getString("apellido"));
				estudiante.setTelefono(rs.getString("telefono"));
				estudiante.setEmail(rs.getString("email"));
				return true;
			}
		}
		catch (Exception e) {
			System.out.println("Ocurrio un error al busacar el estudiante");
		}
		finally {
			try {
				conn.close();
			}
			catch (Exception e) {
				System.out.println("Ocurrio un error al cerrar la conexion: " + e.getMessage());
			}
		}
		return false;
	}


	//Metodo agregar estudiante
	public boolean agregarEstudiante(Estudiante estudiante) {
		PreparedStatement ps;
		Connection        conn = getConnection();
		String            sql  = "INSERT INTO estudiante(nombre, apellido, telefono, email) VALUES(?, ?, ?, ?)";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, estudiante.getNombre());
			ps.setString(2, estudiante.getApellido());
			ps.setString(3, estudiante.getTelefono());
			ps.setString(4, estudiante.getEmail());
			ps.execute();
			return true;
		}
		catch (Exception e) {
			System.out.println("Ocurrio un error al agregar al usuario" + e.getMessage());
		}
		finally {
			try {
				conn.close();
			}
			catch (Exception e) {
				System.out.println("Error al cerrar conexion: " + e.getMessage());
			}
		}
		return false;
	}


	//Metodo mpodificar estudiante
	public boolean modificarEstudiante(Estudiante estudiante) {
		PreparedStatement ps;
		Connection        conn = getConnection();
		String sql = "UPDATE estudiante SET nombre=?, apellido=?, telefono=?, email=? WHERE idEstudiante = ?";

		try{
			ps = conn.prepareStatement(sql);
			ps.setString(1, estudiante.getNombre());
			ps.setString(2, estudiante.getApellido());
			ps.setString(3, estudiante.getTelefono());
			ps.setString(4, estudiante.getEmail());
			ps.setInt(5, estudiante.getIdEstudiante());
			ps.execute();
			return true;
		}catch (Exception e){
			System.out.println("Error al modificar el estudiante: " + e.getMessage());
		}finally {
			try{
				conn.close();
			}catch (Exception e){
				System.out.println("Error al cerrar la conexion: " + e.getMessage());
			}
		}
		return false;
	}


	//Metodo para eliminae estudiante
	public boolean eliminarEstudiante(Estudiante estudiante){
		PreparedStatement ps;
		ResultSet rs;
		Connection conn = getConnection();
		String sql = "DELETE FROM estudiante WHERE id_estudiante = ?";

		try{
			ps = conn.prepareStatement(sql);
			ps.setInt(1, estudiante.getIdEstudiante());
			return true;
		}catch (Exception e){
			System.out.println("Error al eliminar el estudiante" + e.getMessage());
		}finally {
			try{
				conn.close();
			}catch (Exception e){
				System.out.println("Error al cerrar la conexion" + e.getMessage());
			}
		}
		return false;
	}



	public static void main(String[] args) {

		var estudianteDao = new EstudianteDAO();

		//Agregar Estudiante
		var nuevoEstudiante = new Estudiante("Carlos", "Lara", "321456987", "carlos@gmail.com");
		var agregado= estudianteDao.agregarEstudiante(nuevoEstudiante);
		if (agregado) {
			System.out.println("Estudiante agreagdo: " + nuevoEstudiante);
		} else {
			System.out.println("No se agrego el nuevo estudiante" + nuevoEstudiante);
		}


		//Modificar un estudiante ya existente
		var estudianteModificardo = new Estudiante(1, "Camila", "Perez", "789456123", "camila@gmail.com");
		var modificado = estudianteDao.modificarEstudiante(estudianteModificardo);
		if(modificado){
			System.out.println("El estudiante se modifico correctamente: " + estudianteModificardo);
		}else{
			System.out.println("No se pudo modificar el estudiante: " + estudianteModificardo);
		}


		//Elimiar estudiante
		var estudianteEliminar = new Estudiante(1);
		var eliminar = estudianteDao.eliminarEstudiante(estudianteEliminar);
		if(eliminar){
			System.out.println("Estudiante eliminado correctamente: " + estudianteEliminar);
		}else{
			System.out.println("Error al eliminar el estudiante: " + estudianteEliminar);
		}


		//Listar los estudiantes
		System.out.println("Listado de estudiantes");
		List<Estudiante> estudiantes = estudianteDao.listarEstudiantes();
		estudiantes.forEach(System.out::println);


		//Buscar estusiante por id
		var estudiante1 = new Estudiante(1);
		System.out.println("Estudiante astes de la busqueda: " + estudiante1);
		var encontrado = estudianteDao.buscarEstudiantePorId(estudiante1);
		if (encontrado) {
			System.out.println("Estudiante encontrado: " + estudiante1);
		} else {
			System.out.println("No se encontro estudiante: " + estudiante1.getIdEstudiante());
		}

	}

}

