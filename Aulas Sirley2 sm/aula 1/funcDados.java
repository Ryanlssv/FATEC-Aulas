package ex2;

public class funcDados {
// Atributos	
	public String nome;
	public String Cargo;
	public double salarioBruto;
	public int  codigoFunc;

// metodos
	public String mostrarDado(){
		return ("\n Funcionario: " + this.nome + " Codigo: " + this.codigoFunc + " Salario Bruto R$: " + this.salarioBruto);
	}
	
	
	public double calcularInss(double i) {
		double desconto,s;
		desconto = (i / 100) * this.salarioBruto;
		s = this.salarioBruto - desconto;
		return s;
		
	}
}
