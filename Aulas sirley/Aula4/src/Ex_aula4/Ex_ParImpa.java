package Ex_aula4;
import java.util.Scanner;
public class Ex_ParImpa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner entrada = new Scanner(System.in);
		int n;
		System.out.println("DIGITE UM NUMERO: ");
		n = entrada.nextInt();
		if(n % 2 == 0 ) {
			System.out.println("PAR");
		}else {
			System.out.println("IMPAR");
		}
	}

}
