package com.tareas.sistema.controllers;

import com.tareas.sistema.model.Tarea;
import com.tareas.sistema.services.ITareaService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class IndexController implements Initializable {

	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private ITareaService tareaService;
	@FXML
	private TableView<Tarea> tareaTabla;
	@FXML
	private TableColumn<Tarea, Integer> idTareaColumna;
	@FXML
	private TableColumn<Tarea, String> nombreTareaColumna;
	@FXML
	private TableColumn<Tarea, String> responsableColumna;
	@FXML
	private TableColumn<Tarea, String> estatusColumna;
	private final ObservableList<Tarea> tareasList = FXCollections.observableArrayList();
	@FXML
	private TextField nombreTareaTexto;
	@FXML
	private TextField responsableTexto;
	@FXML
	private TextField estatusTexto;
	private Integer idTareaInterno;



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tareaTabla.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		configurarColumnas();
		listarTareas();
	}

	private void configurarColumnas(){
		idTareaColumna.setCellValueFactory(new PropertyValueFactory<>("idTarea"));
		nombreTareaColumna.setCellValueFactory(new PropertyValueFactory<>("nombreTarea"));
		estatusColumna.setCellValueFactory(new PropertyValueFactory<>("estatus"));
		responsableColumna.setCellValueFactory(new PropertyValueFactory<>("responsable"));
	}


	private void listarTareas(){
		logger.info("Ejecutando lista de tareas... ");
		tareasList.clear();
		tareasList.addAll(tareaService.listarTareas());
		tareaTabla.setItems(tareasList);
	}


	public void agregarTarea(){
		if(nombreTareaTexto.getText().isEmpty()){
			mostrarMensaje("Error validacion", "Debe proporcionar una tarea");
			nombreTareaTexto.requestFocus();
			return;
		}else {
			var tarea = new Tarea();
			recolectarDatosFormulario(tarea);
			tarea.setIdTarea(null);
			tareaService.guardarTarea(tarea);
			mostrarMensaje("Informacion", "Tarea agregada con exito");
			limpiarFormulario();
			listarTareas();
		}
	}


	public void cargarTareaFormulario(){
		var tarea = tareaTabla.getSelectionModel().getSelectedItem();
		if(tarea != null){
			idTareaInterno = tarea.getIdTarea();
			nombreTareaTexto.setText(tarea.getNombreTarea());
			responsableTexto.setText(tarea.getResponsable());
			estatusTexto.setText(tarea.getEstatus());
		}else{
			mostrarMensaje("Error", "Debe seleccionar una tarea");
		}
	}


	public void modificarTarea(){
		if(idTareaInterno == null){
			mostrarMensaje("Error", "Debe seleccionar una tarea");
			return;
		}
		if(nombreTareaTexto.getText().isEmpty()){
			mostrarMensaje("Error validacion", "Debe proporcionar una tarea");
			nombreTareaTexto.requestFocus();
			return;
		}
		var tarea = new Tarea();
		recolectarDatosFormulario(tarea);
		tareaService.guardarTarea(tarea);
		mostrarMensaje("Informacion", "Tarea modificada con exito");
		limpiarFormulario();
		listarTareas();
	}


	public void eliminarTarea(){
		var tarea = tareaTabla.getSelectionModel().getSelectedItem();
		if(tarea != null){
			logger.info("Registro a eliminar: " + tarea.toString());
			tareaService.eliminarTarea(tarea);
			mostrarMensaje("Informacion", "Tarea eliminada:" + tarea.getIdTarea());
			limpiarFormulario();
			listarTareas();
		}
		else{
			mostrarMensaje("Error", "No se ha seleccionado ninguna tarea");
		}
	}


	private void mostrarMensaje(String titulo, String mensaje){
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(titulo);
		alert.setHeaderText(null);
		alert.setContentText(mensaje);
		alert.showAndWait();
	}


	private void recolectarDatosFormulario(Tarea tarea){
		if(idTareaInterno != null) {
			tarea.setIdTarea(idTareaInterno);
			tarea.setNombreTarea(nombreTareaTexto.getText());
			tarea.setResponsable(responsableTexto.getText());
			tarea.setEstatus(estatusTexto.getText());
			tareaService.guardarTarea(tarea);
			mostrarMensaje("Tarea guardada", "La tarea ha sido guardada con exito");
			limpiarFormulario();
			listarTareas();
		}
	}


	public void limpiarFormulario(){
		idTareaInterno = null;
		nombreTareaTexto.clear();
		estatusTexto.clear();
		responsableTexto.clear();
	}



}
