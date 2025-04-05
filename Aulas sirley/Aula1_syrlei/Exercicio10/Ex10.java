package Exercicio10;
import java.util.Scanner;

public class Ex10 {

    public static void main (String[] args){
        System.out.println("Calculo da Media Ponderada de Duas Provas Respectivamente");
        Scanner entrada = new Scanner(System.in);
        double MedPond1,MedPond2, p1 , p2, peso1, peso2 ;
        System.out.println("Digite a nota da p1: ");
        p1 = entrada.nextDouble();
        System.out.println("Digite o peso da p1: ");
        peso1 = entrada.nextDouble();
        System.out.println("Digite a nota da p2: ");
        p2 = entrada.nextDouble();
        System.out.println("Digite o peso da p2: ");
        peso2 = entrada.nextDouble();

        MedPond1= (p1 * peso1) / ( peso1 + peso2) ;
        System.out.println("Media Ponderada da P1: " + MedPond1);
        MedPond2= (p2 * peso2) / (peso1 + peso2) ;
        System.out.printf("Media Ponderada da p2: %.2f ", MedPond2);
    }
}
