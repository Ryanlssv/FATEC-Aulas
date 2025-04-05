package Exercicio8;
import java.util.Scanner;

public class Ex8 {
    public static void main (String[] args){
        System.out.println("Calculo de Madia de Consumo Kml");
        Scanner entrada = new Scanner(System.in);
        double volumeL, Km , Consumo;
        System.out.println("Digite a Distancia Percorida em KM: ");
        Km = entrada.nextDouble();
        System.out.println("Digite o Volume de Conbustivel consumido em L: ");
        volumeL = entrada.nextDouble();
        
        Consumo = Km / volumeL;
        
        System.out.printf("O consumo médio do automóvel é: %.2f km/l%n ", Consumo);
    };
}
