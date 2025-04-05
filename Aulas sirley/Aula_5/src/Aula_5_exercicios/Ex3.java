package Aula_5_exercicios;
import java.util.Scanner;
public class Ex3 {
	
// while
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner entrada = new Scanner(System.in);
		int  chute = -1;;

		while(chute != 10 ) {
			System.out.println("\nDIGITE UM NUMERO: ");
			chute = entrada.nextInt();
			if ( chute == 10 ) {
				System.out.println("Voce acertou  ");
			}else {
				System.out.println("Tente novamente ");
			}
		}
		
	}

}
