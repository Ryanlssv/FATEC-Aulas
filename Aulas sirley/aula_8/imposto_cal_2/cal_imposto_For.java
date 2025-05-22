package imposto_cal_2;
import java.util.Scanner;
public class cal_imposto_For {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Calculadora de Imposto");
		System.out.println("Digite seu Nome: ");
		String Nome = sc.nextLine();
		System.out.println("Digite seu CPF: ");
		String numCPF = sc.nextLine();
		System.out.println("Digite sua renda mensal bruta nos ultimos 12 meses: ");

		for( int i = 0 ; i < 12 ; i++ ) {
			System.out.println("Digite sua Renda Do mes :");
			double j = sc.nextDouble() * 0.8 ;
			System.out.println(i);
		}
		


	}

}
