package Exercicio2;

import java.util.Scanner;
public class Ex2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Multiplicação de números reais");
		Scanner entrada = new Scanner(System.in);
		double p1 , p2 , mul ;
		System.out.println("Digite Um numero: ");
		p1 = entrada.nextDouble();
		System.out.println("Digite Um segundo numero: ");
		p2 = entrada.nextDouble();
		mul = (p1 * p2 ) ;
		System.out.println("Total da Multiplicação: " + mul);
	}

}
