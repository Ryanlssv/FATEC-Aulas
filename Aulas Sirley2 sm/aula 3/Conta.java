package banco2DSM;
 
public class Conta {
	//atributos
	public int numero;
	public Cliente titular;
	public double saldo;
	//construtor
	public Conta(int n, Cliente titular,double saldo) {
		this.numero=n;
		this.titular=titular;
		this.saldo=saldo;
	}
	
	public boolean saca(double valor) {
		if (this.saldo >= valor) {
			this.saldo -= valor;
			return true;
		} else {
			return false;
		}
	}
	
	public void deposita(double valor) {
		this.saldo += valor;
	}
	
	
	public String mostraDados() {
		return ("\n Conta número: "+this.numero+ " Titular: "+this.titular+" Saldo R$ "+this.saldo );
	}
}
 
