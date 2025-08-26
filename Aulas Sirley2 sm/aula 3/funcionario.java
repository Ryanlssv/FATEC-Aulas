package banco2DSM;

public class funcionario {
	public int codigo;
	public String nome;
	public String departamento;
	public double salario;
	public String Entrada;
	public String Rg;
	public Boolean ativo;
	
public funcionario(int codigo, String nome,String Rg,String departamento,double salario, String entrada, String Entrada ,Boolean ativo) {
	this.codigo = codigo;
	this.nome = nome;
	this.Rg = Rg;
	this.departamento = departamento;
	this.salario = salario;
	this.Entrada = Entrada;
	this.ativo = ativo;
	
};

public double receberAumento(int taxa) {
	return this.salario * (taxa /100);
};

public double calAnual() {
	return this.salario * 12;
};

public Boolean ativo(Boolean ativo) {
	return this.ativo == false;
			
}

}


