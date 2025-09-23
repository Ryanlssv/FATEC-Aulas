package Cabstrata;

public class desing extends funcionario  {

	public desing(double s){
		this.salario=s;
		}
	
	@Override
	public void reajustar() {
		salario *= 1.20;	
	}
	
}


