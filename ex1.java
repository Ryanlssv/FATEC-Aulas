package ex1;

public class ex1 {
// atributo
	
	public String nome;
	public double valor;
	public int codigo;
	
// metodos
	public String mostrarDados(){
		return ("\n produto: "+ this.nome + " Codigo: " + this.codigo + " R$: " + this.valor);
	}
	public double calcularTotal(int q) {
		return q * this.valor;
		
	}

}
