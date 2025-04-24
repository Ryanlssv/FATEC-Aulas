package Exerciccios;
import java.util.Scanner;
public class Ex_funcao {

	public static void main(String[] args) {
		Scanner ler = new Scanner(System.in);
		String texto;
		int i, tamanho, qtd_vogais = 0, qtd_algarismos = 0;
		System.out.printf("Insira qualquer texto e lhe darei algumas informações sobre ele.\n");
			texto = ler.nextLine();
			tamanho = texto.length();	
			texto = texto.toUpperCase();
			qtd_vogais= contaVogais(texto);
			 qtd_algarismos = contaAlgarismo(texto);
			System.out.println("Quantidade de Vogais: " + qtd_vogais);
			System.out.println("Quantidade de Algarismos: " + qtd_algarismos);
		}

	private static int contaAlgarismo(String texto) {
		int i, tamanho, qtd_algarismos = 0;
		tamanho = texto.length();	
		for ( i = 0; i < texto.length(); i++) {
            char ch = texto.charAt(i);
            if (ch == '0' || ch == '1' || ch == '2' || ch == '3' || ch == '4' || ch == '5' || ch == '6' || ch == '7' || ch == '8' || ch == '9') {
            	qtd_algarismos++;
            }
		}
		return qtd_algarismos;
	}

	private static int contaVogais(String texto) {
		int i, tamanho, qtd_vogais = 0;
		tamanho = texto.length();	
		for (i=0; i< tamanho; i++) {
			if ((texto.charAt(i) == 'A') ||
			(texto.charAt(i) == 'E') ||
			(texto.charAt(i) == 'I') ||
			(texto.charAt(i) == 'O') ||
			(texto.charAt(i) == 'U')){
			qtd_vogais++;
			}
		} return qtd_vogais;
		
	} 
			
	}

