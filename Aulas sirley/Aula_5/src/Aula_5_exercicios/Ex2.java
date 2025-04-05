package Aula_5_exercicios;
import java.util.Scanner;
public class Ex2 {
	
// loop For
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner entrada = new Scanner(System.in);
		int n , i , max = 5;

		for ( i = 0; i < max; i++) {
			System.out.println("\nDIGITE UM NUMERO: ");
			n = entrada.nextInt();
			if(n % 2 == 0 ) {
				System.out.println("PAR");
			}else {
				System.out.println("IMPAR");
			}
		}
	}

}
