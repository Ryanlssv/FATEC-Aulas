package Exercicio4;

import java.util.Scanner;
public class Ex4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Calculo do Volume de uma lata de oleo");
		Scanner entrada = new Scanner(System.in);
		double volume , raio , altura, Pi  ;
		Pi = 3.14 ;
		System.out.println("Valor do PI: "+ Pi);
		System.out.println("Digite o valor do RAIO: ");
		raio = entrada.nextDouble();
		System.out.println("Digite o valor da Altura da Lata: ");
		altura = entrada.nextDouble();
		volume = (Pi * raio * raio * altura ) ;
		System.out.println("Volume da Lata: " + volume);
	}

}
