package org.example.mundopc;


import org.example.mundopc.model.Computadora;
import org.example.mundopc.model.Monitor;
import org.example.mundopc.model.Raton;
import org.example.mundopc.model.Teclado;
import org.example.mundopc.services.Orden;

public class VentaComputadorasApp {
	public static void main(String[] args) {

		//Creamos un objeto de tipo Raton
		Raton ratonLenovo = new Raton("USB", "HP");
		Teclado tecladoLenovo = new Teclado("Bluetooth", "Lenovo");
		Monitor monitorHP = new Monitor("HP", 13);
		Computadora computadoraHP = new Computadora("HP", monitorHP, tecladoLenovo, ratonLenovo);
		System.out.println(computadoraHP);

		//Aqui creamos un Objeto computadora
		Monitor monitorDell = new Monitor("Dell", 15);
		Raton ratonDell = new Raton("Bluetooth", "Dell");
		Teclado tecladoDell = new Teclado("USB", "Dell");
		Computadora computadoraDell = new Computadora("Dell", monitorDell, tecladoDell, ratonDell);
		System.out.println(computadoraDell);

		//Aqui creamos otro objeto computadora
		Monitor monitorAsus = new Monitor("Asus", 15);
		Raton ratonAsus = new Raton("Bluetooth", "Asus");
		Teclado tecladoAsus = new Teclado("USB", "Asus");
		Computadora computadoraAsus = new Computadora("Asus", monitorAsus, tecladoAsus, ratonAsus);
		System.out.println(computadoraAsus);


		//Aqui Creamos las ordenes
		Orden orden1 = new Orden();
		orden1.agregarComputadora(computadoraHP);
		orden1.agregarComputadora(computadoraDell);
		orden1.agregarComputadora(computadoraAsus);
		orden1.mostrarOrden();
	}
}