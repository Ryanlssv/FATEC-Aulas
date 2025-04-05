package Exercicio5;
import java.util.Scanner;
public class Ex5 {
    public static void main (String[] args){
        System.out.println("Calculo da soma armazenada em uma variavel");
        Scanner entrada = new Scanner(System.in);
        double soma, X , Y, Z;

        System.out.println("Digite Um valor para X: ");
        X = entrada.nextDouble();
        System.out.println("Digite Um valor para Y: ");
        Y = entrada.nextDouble();
        soma = X + Y;
        Z = soma;
        System.out.println("Soma dos valores de X e Y: " + Z);
    }
}
