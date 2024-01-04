package org.example.mundopc.model;

public class Teclado extends DispositivoEntrada {

	private final int ID_TECLADO;
	private static int contadorTeclados;

	public Teclado(String tipoDeEntrada, String marca) {
		super(tipoDeEntrada, marca);
		ID_TECLADO = ++contadorTeclados;
	}

	@Override
	public String toString() {
		return "Teclado{" +
				 "ID_TECLADO=" + ID_TECLADO +
				 '}' + super.toString();
	}
}
