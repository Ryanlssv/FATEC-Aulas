package Cabstrata;

public class Programador extends funcionario {

	public Programador(double s){
		this.salario=s;
		} 
	
	@Override
	public void reajustar() {
		salario *= 1.20;	
	}
	
}
