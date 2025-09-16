package ex;

public class carro extends veiculo {
    private int portas;

    // Construtor padrão - chamada implícita ao construtor da superclasse
    public carro() {
        super(); // pode ser omitido, chamada implícita
    }

    // Construtor com parâmetros - chamada explícita para o construtor de Veiculo
    public carro(String modelo, String marca, int portas) {
        super(modelo, marca,portas); // chamada explícita ao construtor da superclasse
        this.portas = portas;
    }

    // Getter
    public int getPortas() {
        return portas;
    }

    // Setter
    public void setPortas(int portas) {
        this.portas = portas;
    }
    
    public void tipoVeiculo()
    {
    System.out.println("O veículo é um carro");
    }
    
    
    public String toString() {
 	   return("\n Marca: " + getMarca() + " Modelo: " + getModelo() + " Portas: " + getPortas());
    };

    // Método opcional para exibir os dados
    public void exibirDados() {
        System.out.println("Marca: " + getMarca());
        System.out.println("Modelo: " + getModelo());
        System.out.println("Portas: " + portas);
    }
}
