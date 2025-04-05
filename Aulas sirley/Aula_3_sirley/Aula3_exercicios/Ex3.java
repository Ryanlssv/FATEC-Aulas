package Aula3_exercicios;
import java.util.Scanner;
public class Ex3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner entrada = new Scanner(System.in);
		System.out.println("ser o numero e nagativo");
		double nota1, nota2, MediaAl;
		System.out.println("Digite a nota 1: ");
		nota1 = entrada.nextDouble();
		System.out.println("Digite a nota 2: ");
		nota2 = entrada.nextDouble();
		
		MediaAl = ( nota1 + nota2) / 2 ;
	
		
		if(MediaAl < 6) {
			System.out.println("Media final " + MediaAl + " REPROVADO ");
		}else {
			System.out.println("Media final " + MediaAl + " APROVADO ");
		}
	}

}
