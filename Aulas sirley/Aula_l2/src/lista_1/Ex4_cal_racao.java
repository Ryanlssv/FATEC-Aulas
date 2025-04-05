package lista_1;

import java.util.Scanner;

public class Ex4_cal_racao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Calculadora Ração");
		Scanner entrada = new Scanner(System.in);
		double Kg, grama , Qdia , cal, total;
		
		System.out.println("Digite quantos KG de ração: ");
		Kg = entrada.nextDouble();
		grama = Kg * 1000 ;
		
		System.out.println("Digite quantidade de ração para cada gato: ");
		Qdia= entrada.nextDouble();
		
		cal = ( Qdia * 2 ) * 5 ;
		
		total = grama - cal ;
		
		System.out.println("O que sobrou de ração em 5 dias: ");
		System.out.println("KG: " + total / 1000 );
		System.out.println("Gramas: " + total );		
		
		
		
	}

}
