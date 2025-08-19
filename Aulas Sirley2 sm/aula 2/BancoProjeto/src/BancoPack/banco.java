package BancoPack;

public class banco {
	 public int numero;
	 public String titular;
	 public double saldo;
	 
	 
	 public banco(int numero,String titular,double saldo){
			this.numero = numero;
			this.titular = titular;
			this.saldo= saldo ;
		}
	 
	    public String Extrato(){
	        return("\n Titular: "+ this.titular + "\n Saldo R$ :"+this.saldo+"\n numero: "+this.numero);
	    } 
	    
	    public double trans(double valor){
	    	return(this.saldo - valor);
	    }
	    
	    public double dep(double valor){
	    	return(this.saldo + valor);
	    }
	 
}

