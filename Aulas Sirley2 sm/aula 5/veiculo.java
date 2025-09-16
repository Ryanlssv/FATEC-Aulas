package ex;

public class veiculo {
    private String marca;
    private String modelo;
    private int portas;

    public veiculo() {}

    public veiculo(String modelo, String marca, int portas) {
        this.modelo = modelo;
        this.marca = marca;
        this.portas = portas;
   
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }


    public void tipoVeiculo()
    {
    System.out.println("O veículo é um carro ou moto");
    }

    
   public String toString() {
	   return("\n Marca: " + marca + " Modelo: " + modelo + " Porta: " + portas);
   };
    
    public void exibirDados() {
        System.out.println("Marca: " + marca + ", Modelo: " + modelo + ", Portas: " + portas);
    }

	public int getPortas() {
		return portas;
	}

	public void setPortas(int portas) {
		this.portas = portas;
	}
}
