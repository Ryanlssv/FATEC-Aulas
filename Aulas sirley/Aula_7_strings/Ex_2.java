package Aula_7_Ex;
import java.util.Scanner;
import java.io.IOException;

public class Ex_2 {

	 public static void main(String[] args) throws IOException {
			Scanner ler = new Scanner(System.in);
			String Nome;
			char Sexo;
			
			System.out.println("Digite seu Nome: ");
			Nome = ler.nextLine();
			System.out.println("Digite seu Sexo: ");
			Sexo = (char)System.in.read();
			
			
			if ((Sexo == 'M') || (Sexo == 'm')) {
				System.out.println("Bem vindo senhor " + Nome );
			} else {
				System.out.println("Bem vindo senhora " + Nome);
			}
			
	}

}
