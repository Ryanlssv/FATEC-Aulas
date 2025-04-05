package Aula3_exercicios;

import java.util.Scanner;

public class Ex4_case {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner entrada = new Scanner(System.in);
			int op;
			System.out.println("Digite um numero de 1 a 4: ");
		op = entrada.nextInt();
		
		switch (op) {
		
		case 0:
			
			break;
		
		case 1: System.out.println("Voce escolheu Opção 1");
			
			break;
		
		case 2: System.out.println(" Voce escolheu Opção 2");
			
			break;
			
		case 3: System.out.println("Voce escolheu Opção 3");
			
			break;
		
		case 4: System.out.println("Voce escolheu Opção 4");
			
			break;
		
		default: System.out.println(" Voce escolheu uma Opção invalida");
			
			break;
		}
	}

}
