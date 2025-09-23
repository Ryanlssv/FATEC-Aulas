package Cabstrata;

public class testF {

	public static void main(String[] args) {
		Programador p = new Programador(3000);
		p.reajustar();
		System.out.println("Salario: " + p.salario);
		
		desing d = new desing(2000);
		d.reajustar();
		System.out.println("Salario: " + d.salario);
		
	}

}
