package lista_1;

import java.util.Scanner;

public class Ex12_cal_suEpos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Calculadora Aritimetica");
		Scanner entrada = new Scanner(System.in);
		double  sucessor, A , antecessor	;	
		
		System.out.println("Digite Um valor para A: ");
		A = entrada.nextDouble();
		
		sucessor = A + 1;
		System.out.println("Sucessor: " + sucessor);
		
		antecessor = A - 1;
		System.out.printf("Antecessor: " + antecessor);
	}

}
