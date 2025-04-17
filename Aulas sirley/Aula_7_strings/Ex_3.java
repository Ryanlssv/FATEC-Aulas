package Aula_7_Ex;

import java.util.Scanner;

public class Ex_3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner ler = new Scanner(System.in);
		String Nome;
			int Tamanho;
		System.out.println("Digite seu Nome: ");
		Nome = ler.nextLine();
		Tamanho = Nome.length();
		System.out.println(Tamanho);
		
	}

}
