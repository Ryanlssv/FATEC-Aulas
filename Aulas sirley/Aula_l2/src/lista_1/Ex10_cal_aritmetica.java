package lista_1;

import java.util.Scanner;

public class Ex10_cal_aritmetica {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Calculadora Aritimetica");
		Scanner entrada = new Scanner(System.in);
		double A, B , Sub, Mult, Som , Div	;	
		
		System.out.println("Digite Um valor para A: ");
		A = entrada.nextDouble();
		
		System.out.println("Digite Um valor para B: ");
		B = entrada.nextDouble();

		Sub = A - B ;
		System.out.println("Subrtração : " + Sub);
		
		Mult = A * B ;
		System.out.println("Multiplicação : " + Mult);
		
		Som = A + B ;
		System.out.println("Soma : " + Som);
		
		Div = A / B ;
		System.out.printf("Divisão %.2f : ", Div);
	}

}
