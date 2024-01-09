package com.tareas.sistema.services;

import com.tareas.sistema.model.Tarea;

import java.util.List;

public interface ITareaService {

	public List<Tarea> listarTareas();

	public Tarea buscarTareaById(Integer idTarea);

	//Aqui utilizamos el mismo metodo para guardar y actualizar una tarea
	//La diferencia es que si el idTarea es null, se crea una nueva tarea si no es igual a null se actualiza la tarea
	public void guardarTarea(Tarea tarea);

	public void eliminarTarea(Tarea tarea);

}
