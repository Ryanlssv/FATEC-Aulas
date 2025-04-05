package Exercicio6;
import java.util.Scanner;

public class Ex6 {
    public static void main (String[] args){
        System.out.println("Calculo da Media Ponderada das notas de um aluno");
        Scanner entrada = new Scanner(System.in);
        double MedPond, p1 , p2, peso1, peso2 ;
        System.out.println("Digite a nota da p1: ");
        p1 = entrada.nextDouble();
        peso1 = 4;
        System.out.println("Valor do Peso dessa Prova: " + peso1);
        System.out.println("Digite a nota da p2: ");
        p2 = entrada.nextDouble();
        peso2 = 6;
        System.out.println("Valor do Peso dessa Prova: " + peso2);

        MedPond = ( (p1 * peso1) + (p2 * peso2) ) / (peso1 + peso2) ;
        System.out.println("Media Ponderada das notas do Aluno: " + MedPond);
    };    
}
