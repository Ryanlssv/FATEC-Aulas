package Aula3_exercicios;

import java.util.Scanner;

public class Ex2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner entrada = new Scanner(System.in);
		System.out.println("ser o numero e nagativo");
		double numero;
		System.out.println("Digite um numero: ");
		numero = entrada.nextInt();
		
		if(numero < 0) {
			System.out.println( numero + " É negativo ");
		}

	}

}
