package com.tareas.sistema.services;


import com.tareas.sistema.model.Tarea;
import com.tareas.sistema.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class tareaService implements ITareaService {

	@Autowired
	private  TareaRepository tareaRepository;

	@Override
	public
	List<Tarea> listarTareas() {
		return tareaRepository.findAll();
	}

	@Override
	public Tarea buscarTareaById(Integer idTarea) {
		Tarea tarea = tareaRepository.findById(idTarea).orElse(null);
		return tarea;
	}

	@Override
	public void guardarTarea(Tarea tarea) {
		tareaRepository.save(tarea);
	}

	@Override
	public void eliminarTarea(Tarea tarea) {
		tareaRepository.delete(tarea);
	}

}
