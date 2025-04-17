package Aula_7_Ex;

import java.util.Scanner;

public class Ex_4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner ler = new Scanner(System.in);
		String Nome;
			int Tamanho;
		while(true) {	
		System.out.println("Digite seu Nome: ");
		Nome = ler.nextLine();
			if(Nome.equalsIgnoreCase("fim")) {
				System.out.println("Ate logo");
				break;
			}else {
				Tamanho = Nome.length();
				System.out.println(Tamanho);
			}
				
		}
		
	}

}
