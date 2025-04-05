package Aula3_exercicios;

import java.util.Scanner;

public class Ex3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner entrada = new Scanner(System.in);
		System.out.println("ser o numero e nagativo");
		double salario, taxa, aumento,total;
		System.out.println("Digite o salario: ");
		salario = entrada.nextDouble();
		
		taxa = 15 ;
		
		if(salario < 1500) {
			System.out.println("Você receberar um aumento de 15%:");
			aumento = ( salario * taxa ) / 100 ;
			total = salario + aumento;
			System.out.printf("Seu novo salario: %.2f " , total );
		}else {
			System.out.println("Você receberar não receberar um aumento");
		}
	}

}
