package Ejemplo;

public class Coche {
	
	private int id;
	private String marca;
	private String modelo;
	private double cilindrada;
	
	public Coche(int id, String marca, String modelo, double cilindrada) {
		this.id = id;
		this.marca = marca;
		this.modelo = modelo;
		this.cilindrada = cilindrada;
	}

	@Override
	public String toString() {
		return "Coche id: " + id + "\nMarca: " + marca + "\nModelo: " + modelo + "\nCilindrada: " + cilindrada+ "\n";
	}

}
