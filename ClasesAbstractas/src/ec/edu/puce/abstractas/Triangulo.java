package ec.edu.puce.abstractas;

public class Triangulo extends FiguraGeometrica {
	private double base;
	private double altura;
	
	public Triangulo(String nombre) {
		super(nombre);
	}

	@Override
	public double calcularArea() {
		return (this.base * this.altura)/2;
	}

	public double getBase() {
		return base;
	}

	public void setBase(double base) {
		this.base = base;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}
}


