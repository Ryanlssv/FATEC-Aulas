package comercio;

public class Funcionario {
	public int codigoFunc;
    public String nome;
    public String cargo;
    public double salarioBruto;
    
    
    public Funcionario(int c,String nome,String C ,double s){
    	this.codigoFunc=c;
    	this.nome=nome;
    	this.cargo=C;
    	this.salarioBruto= s ;
    }
    
    
    public String mostrarDados(){
        return("\n Nome: "+ this.nome+"\n Cargo: "+ this.cargo +"\n Salario R$ "+this.salarioBruto+"\n Codigo "+this.codigoFunc);
    }
    public double calculaSalarioLiquido(double INSS){
        return (this.salarioBruto-(this.salarioBruto*INSS/100));
    }

}
