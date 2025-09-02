package banco2DSM;
 
public class Cliente {
	private int codigo;
	private String nome;
	private String endereco;
	//construtor
	public Cliente(int codigo,String nome,String endereco) {
		this.codigo= codigo;
		this.nome= nome;
		this.endereco=endereco;
	}
	
	public Cliente(String nome) {
		this.nome= nome;
	}
	
	public Cliente() {
	}
	
	
	public String getNome() {
		return this.nome;
	}
	
	public String getEndereco() {
		return this.endereco;
	}
	
	public int getCodigo() {
		return this.codigo;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String toString() {
		return("\n NOME:" + this.nome + "\n ENDERECO:" + this.endereco );
	}
	
}