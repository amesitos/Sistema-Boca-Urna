package ec.edu.puce.abstractas;

public class Circulo extends FiguraGeometrica {
	private double radio;
	private static final double PI = 3.1416;

	public Circulo(String nombre) {
		super(nombre);
	}
	
	@Override
	public double calcularArea() {
		return this.PI * (this.radio * this.radio);
	}

	public double getRadio() {
		return radio;
	}

	public void setRadio(double radio) {
		this.radio = radio;
	}

}
