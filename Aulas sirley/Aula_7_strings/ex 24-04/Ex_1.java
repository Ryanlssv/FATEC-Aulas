package Exerciccios;

import java.util.Scanner;

public class Ex_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner ler = new Scanner(System.in);
		String texto;
			int Tamanho , V, D;
		String M;	
		System.out.println("Digite uma Frase: ");
	
		texto = ler.nextLine();
		System.out.println("--- EXERCICIO A ----- \n");
		Tamanho = texto.length();
		System.out.println("Sua frase tem " + Tamanho + " Caracteres \n");
		
		System.out.println("--- EXERCICIO B ----- \n");
		M = texto.toUpperCase();
		System.out.println("Texto em Maiusculo: " + M + "\n");
		
		
		System.out.println("--- EXERCICIO C ----- \n");	
        
		 V = 0;
        for (int i = 0; i < texto.length(); i++) {
            char ch = texto.charAt(i);
            if (ch == 'a' || ch == 'A' || ch == 'e' || ch == 'E' || ch == 'i' || ch == 'I' || ch == 'o' || ch == 'O' || ch == 'u' || ch == 'U') {
            	V++;
            }
        } System.out.println("Numero de Vogais: " + V + "\n");
        
		System.out.println("--- EXERCICIO D ----- \n");	
        
		    D = 0;
	        for (int i = 0; i < texto.length(); i++) {
	            char ch = texto.charAt(i);
	            if (ch == '0' || ch == '1' || ch == '2' || ch == '3' || ch == '4' || ch == '5' || ch == '6' || ch == '7' || ch == '8' || ch == '9') {
	                D++;
	            }
	        }System.out.println("Número de dígitos: " + D);
		
	}

}
