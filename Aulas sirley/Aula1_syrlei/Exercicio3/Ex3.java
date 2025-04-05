package Exercicio3;

import java.util.Scanner;
public class Ex3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Total a ser pago por uma compra");
		Scanner entrada = new Scanner(System.in);
		double produto , total;
		int quantidade;
		produto = 30 ;
		System.out.println("Valor do Produto: "+ produto);
		System.out.println("Digite a quantidade a ser comprada: ");
		quantidade = entrada.nextInt();
		total = (produto * quantidade ) ;
		System.out.println("Total da compra: " + total);
	}

}
