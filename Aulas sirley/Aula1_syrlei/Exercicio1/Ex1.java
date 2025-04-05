package Exercicio1;

import java.util.Scanner;
public class Ex1 {  

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Calculo da subtração");
		Scanner entrada = new Scanner(System.in);
		double p1, p2, sub  ;
		System.out.println("Digite Um numero: ");
		p1 = entrada.nextDouble();
		System.out.println("Digite Um segundo numero: ");
		p2 = entrada.nextDouble();
		sub = (p1 - p2 ) ;
		System.out.println("Total da subtração: " + sub);   
	}

}
