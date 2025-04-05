package Aula3_exercicios;
import java.util.Scanner;

public class Ex1 {

	public static void main(String[] args) {
		// TODO Auto-generated
		Scanner entrada = new Scanner(System.in);
		System.out.println("Calculo de maioridade");
		double idade,a,b;
		System.out.println("Digite sua idade: ");
		idade = entrada.nextInt();
		
		if(idade >= 18) {
			System.out.println( idade + " Anos, voce e maior de idade ");
		}else{
			System.out.println( idade + " Anos, voce e menor de idade ");
		}
	}

}
