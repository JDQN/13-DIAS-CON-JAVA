package com.estudiantes.sistema.services;

import com.estudiantes.sistema.model.Estudiante;
import com.estudiantes.sistema.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteServicio implements IEstudianteService{

	@Autowired
	private EstudianteRepository estudianteRepository;

	@Override
	public List<Estudiante> listarEstudiante() {
		List<Estudiante> estudiantes = estudianteRepository.findAll();
		return estudiantes;
	}

	@Override
	public Estudiante buscarEstudiantePorId(Integer idEstudiante) {
		Estudiante estudiante = estudianteRepository.findById(idEstudiante).orElse(null);
		return estudiante;
	}

	@Override
	public void guardarEstudiante(Estudiante estudiante) {
		estudianteRepository.save(estudiante);
	}

	@Override
	public void eliminarEstudiante(Estudiante estudiante) {
		estudianteRepository.delete(estudiante);
	}
}
