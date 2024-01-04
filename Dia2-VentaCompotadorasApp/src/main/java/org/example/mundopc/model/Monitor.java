package org.example.mundopc.model;


public class Monitor {

	private final int ID_MONITOR;
	private String marca;
	private double tamano;
	private static int contadorMonitores;


	private Monitor() {
		this.ID_MONITOR = ++contadorMonitores;
	}

	public Monitor(String marca, double tamano) {
		this();
		this.marca             = marca;
		this.tamano            = tamano;
	}


	public int getID_MONITOR() {
		return ID_MONITOR;
	}

	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}

	public double getTamano() {
		return tamano;
	}
	public void setTamano(double tamano) {
		this.tamano = tamano;
	}

	public static int getContadorMonitores() {
		return contadorMonitores;
	}
	public static void setContadorMonitores(int contadorMonitores) {
		Monitor.contadorMonitores = contadorMonitores;
	}


	@Override
	public String toString() {
		return "Monitor{" +
				 "ID_MONITOR=" + ID_MONITOR +
				 ", marca='" + marca + '\'' +
				 ", tamano=" + tamano +
				 '}';
	}
}
