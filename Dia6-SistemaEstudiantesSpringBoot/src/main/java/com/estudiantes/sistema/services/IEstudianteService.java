package com.estudiantes.sistema.services;

import com.estudiantes.sistema.model.Estudiante;

import java.util.List;

public interface IEstudianteService {

	public List<Estudiante> listarEstudiante();
	public Estudiante buscarEstudiantePorId(Integer idEstudiante);
	public void guardarEstudiante(Estudiante estudiante);
	public void eliminarEstudiante(Estudiante estudiante);


}
