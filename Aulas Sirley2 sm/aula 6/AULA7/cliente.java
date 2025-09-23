package AULA7;

public class cliente {
	private int codigo;
	private String nome;
	public static int totalClientes = 0;
	
	public cliente() {
		totalClientes++;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	};
	
	
}
