package ec.edu.puce.abstractas;

public class Main {

	public static void main(String[] args) {
		
		// Rectángulos
		System.out.println("|| RECTÁNGULOS ||");
		Rectangulo r1 = new Rectangulo("Rectángulo 1");
		r1.setBase(7);
		r1.setAltura(15);
		System.out.println(r1.toString());

		Rectangulo r2 = new Rectangulo("Retángulo 2");
		r2.setBase(80);
		r2.setAltura(10);
		System.out.println(r2.toString());

		if (r1.mayorQue(r2))
			System.out.println("El mayor es: " + r1.toString());
		else
			System.out.println("El mayor es: " + r2.toString());
		System.out.println("");
		
		// Triángulo
		System.out.println("|| TRIÁNGULOS ||");
		Triangulo t1 = new Triangulo("Triángulo 1");
		t1.setBase(8);
		t1.setAltura(14);
		System.out.println(t1.toString());
		
		Triangulo t2 = new Triangulo("Triángulo 2");
		t2.setBase(20);
		t2.setAltura(15);
		System.out.println(t2.toString());
		
		if (t1.mayorQue(t2))
			System.out.println("El mayor es: " + t1.toString());
		else
			System.out.println("El mayor es: " + t2.toString());
		System.out.println("");
		
		// Círculo
		System.out.println("|| CÍRCULOS ||");
		Circulo c1 = new Circulo("Círculo 1");
		c1.setRadio(12);
		System.out.println(c1.toString());
				
		Circulo c2 = new Circulo("Círculo 2");
		c2.setRadio(6);
		System.out.println(c2.toString());
				
		if (c1.mayorQue(c2))
			System.out.println("El mayor es: " + c1.toString());
		else
			System.out.println("El mayor es: " + c2.toString());
		
	}
	
}
