package com.libros.sistema.view;

import com.libros.sistema.model.Libro;
import com.libros.sistema.services.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Component
public
class LibroForm extends JFrame {

	LibroServicio libroServicio;
	private JPanel            panel;
	private JTable            tablaLibros;
	private JTextField        idTexto;
	private JTextField        libroTexto;
	private JTextField        autoTexto;
	private JTextField        precioTexto;
	private JLabel            Precio;
	private JTextField        existenciasTexto;
	private JButton           agregarButton;
	private JButton           modificarButton;
	private JButton           eliminarButton;
	private DefaultTableModel tablaModeloLibros;


	@Autowired
	public LibroForm(LibroServicio libroServicio) {
		this.libroServicio = libroServicio;
		iniciarForma();
		agregarButton.addActionListener(e -> agregarLibro());
		tablaLibros.addMouseListener(new MouseAdapter() {
			@Override
			public
			void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				cargarLibroSeleccionado();
			}
		});

		modificarButton.addActionListener(e -> modificarLibro());
		eliminarButton.addActionListener(e -> eliminarLibro());
	}


	private void iniciarForma() {
		setContentPane(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(900, 700);
		Toolkit   toolkit         = Toolkit.getDefaultToolkit();
		Dimension tamanioPantalla = toolkit.getScreenSize();
		int       x               = (tamanioPantalla.width - getWidth() / 2);
		int       y               = (tamanioPantalla.height = getHeight() / 2);
		setLocation(x, y);
	}


	private void agregarLibro() {
		if (libroTexto.getText().equalsIgnoreCase("")) {
			mostrarMensaje("El nombre del libro es requerido");
			libroTexto.requestFocusInWindow();
			return;
		}
		var nombreLibro = libroTexto.getText();
		var autor       = autoTexto.getText();
		var precio      = Double.parseDouble(precioTexto.getText());
		var existencias = Integer.parseInt(existenciasTexto.getText());

		//Creamos el objeto libro de la forma 1
		//var libro = new Libro();
		//libro.setNombreLibro(nombreLibro);
		//libro.setAutor(autor);
		//libro.setPrecio(precio);
		//libro.setExistencias(existencias);

		//Creamos el objeto libro de la forma 2 usando el constructor
		var libro = new Libro(null, nombreLibro, autor, precio, existencias);
		this.libroServicio.guardarLibro(libro);
		mostrarMensaje("Se agrego el libro correctamente");
		limpiarFormulario();
	}


	private void cargarLibroSeleccionado() {
		//Los indices de las columnas inician en cero
		var renglon = tablaLibros.getSelectedRow();
		if (renglon != -1) { //Si el renglon es diferente de -1 no se selecciona ningun registro
			String idLibro = tablaLibros.getModel().getValueAt(renglon, 0).toString();
			idTexto.setText(idLibro);

			String nombreLibro = tablaLibros.getModel().getValueAt(renglon, 1).toString();
			libroTexto.setText(nombreLibro);

			String autor = tablaLibros.getModel().getValueAt(renglon, 2).toString();
			autoTexto.setText(autor);

			String precio = tablaLibros.getModel().getValueAt(renglon, 3).toString();
			precioTexto.setText(precio);

			String existencias = tablaLibros.getModel().getValueAt(renglon, 4).toString();
			existenciasTexto.setText(existencias);
		}


	}


	private void modificarLibro() {
		if (this.idTexto.getText().equals("")) {
			mostrarMensaje("Debe seleccionar un registro...");
			return;
		} else {
			//Aqui verificamos que el nombre del libro no sea null
			if (libroTexto.getText().equals("")) {
				mostrarMensaje("Proporcionar el nombre del libro...");
				libroTexto.requestFocusInWindow();
				return;
			}
			//Aqui llenamos el objeto del libro a actualizar
			int idLibro     = Integer.parseInt(idTexto.getText());
			var nombreLibro = libroTexto.getText();
			var autor       = autoTexto.getText();
			var precio      = Double.parseDouble(precioTexto.getText());
			var existencias = Integer.parseInt(existenciasTexto.getText());
			var libro       = new Libro(idLibro, nombreLibro, autor, precio, existencias);
			this.libroServicio.guardarLibro(libro);
			mostrarMensaje("Se actualizo el libro correctamente");
			limpiarFormulario();
			listarLibros();
		}
	}


	private void eliminarLibro() {
		var renglon = tablaLibros.getSelectedRow();
		if (renglon != -1) {
			String idLibro = tablaLibros.getModel().getValueAt(renglon, 0).toString();
			var libro = new Libro();
			libro.setIdLibro(Integer.parseInt(idLibro));
			this.libroServicio.eliminarLibro(libro);
			mostrarMensaje("Libro " + idLibro + " eliminado correctamente");
			limpiarFormulario();
			listarLibros();
		}else{
			mostrarMensaje("No se ha seleccionado ningun registro...");
		}
	}


	private void limpiarFormulario() {
		libroTexto.setText("");
		autoTexto.setText("");
		precioTexto.setText("");
		existenciasTexto.setText("");
		listarLibros();
	}


	private void listarLibros() {

		//Aqui limpiamos la tabla
		tablaModeloLibros.setRowCount(0);

		//Aqui obtenemos todos los libros
		var libros = libroServicio.listarLibros();
		libros.forEach((libro) -> {

			Object[] renglonLibro = {
				libro.getIdLibro(),
				libro.getNombreLibro(),
				libro.getAutor(),
				libro.getPrecio(),
				libro.getExistencias()
			};
			this.tablaModeloLibros.addRow(renglonLibro);
		});
	}


	private void createUIComponents() {

		//Aqui creamoe l elemento id texto oculto
		idTexto = new JTextField();
		idTexto.setVisible(false);

		this.tablaModeloLibros = new DefaultTableModel(0, 5){
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};

		String[] cabeceros = {"Id", "Nombre", "Autor", "Precio", "Existencias"};
		tablaModeloLibros.setColumnIdentifiers(cabeceros);

		//Aqui instanciamos el Objeto JTable
		this.tablaLibros = new JTable(tablaModeloLibros);
		//Aqui evitamos que se seleccionen varios libros
		tablaLibros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		listarLibros();

	}


	private void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}

}

