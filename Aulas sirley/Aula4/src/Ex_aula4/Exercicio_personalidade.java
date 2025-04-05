package Ex_aula4;
import java.util.Scanner;

public class Exercicio_personalidade {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner entrada = new Scanner(System.in);
		
		int ano , dia , mes,personalidade ;
		System.out.println("Digite o ano de nacimento: ");
		ano = entrada.nextInt();
		System.out.println("Digite o mes de nacimento: ");
		mes = entrada.nextInt();
		System.out.println("Digite o dia de nacimento: ");
		dia = entrada.nextInt();
			
		personalidade = ano + dia + mes;
		
		switch (personalidade % 5) {
			case 0 :
				System.out.println(" personalidade Timido ");
				break;
				
			case 1 :
				System.out.println("personalidade Sonhador ");
				break;
				
			case 2 :
				System.out.println("personalidade Paquerador ");
				break;
				
			case 3 :
				System.out.println("personalidade Atraente ");
				break;
				
			case 4 :
				System.out.println("personalidade Irresistível ");
				break;
		}
		
	}

}
