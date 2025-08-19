package comercio;

public class Produto {

	// atributos
    public int codigo;
    public String nome;
    public double valor;
    //construtor
    Produto(int c,String nome,double v){
    	this.codigo=c;
    	this.nome=nome;
    	this.valor=v;
    }
    //métodos
    public String mostrarDados(){
        return ("\n Produto: "+ this.nome+"\n Valor: R$ "+ this.valor);
    }
    public double calcularTotal(int quantidade){
        return this.valor*quantidade;
    }

	
	
}
