package lista_1;

import java.util.Scanner;

public class Ex11_VolumeEsfera {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Calculadora Aritimetica");
		Scanner entrada = new Scanner(System.in);
		double  pi, raio , Volume	;	
		
		System.out.println("Digite Um valor para o raio: ");
		raio = entrada.nextDouble();
		
		pi = 3.14;
		System.out.println("Valor de Pi: " + pi);
		
		Volume = (4 * ( pi * (raio * raio * raio) ) ) / 3 ;
		
		System.out.printf("Valor do Volume da esfera: %.2f " ,  Volume);
	}

}
