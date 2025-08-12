package ex2;

public class CalSalaraio {

	public static void main(String[] args) {
		 
		funcDados f = new funcDados();
		f.nome="Carlos";
		f.Cargo = "Gerenete";
		f.codigoFunc=121;
		f.salarioBruto = 3500;
		System.out.print(f.mostrarDado());
		System.out.print("Salario L: "+ f.calcularInss(20));

	}

}
