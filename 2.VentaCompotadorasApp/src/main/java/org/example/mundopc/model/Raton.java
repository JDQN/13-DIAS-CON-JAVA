package org.example.mundopc.model;

public class Raton extends DispositivoEntrada {

	private final int ID_RATON;
	private static int contadorRatones;

	public Raton(String tipoDeEntrada, String marca) {
		super(tipoDeEntrada, marca);
		ID_RATON = ++contadorRatones;
	}

	@Override
	public String toString() {
		return "Raton{" +
				 "ID_RATON=" + ID_RATON +
				 '}' + super.toString();
	}

}
