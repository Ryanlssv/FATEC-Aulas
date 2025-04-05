package Ex_aula4;
import java.util.Scanner;
public class Ex5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner entrada = new Scanner(System.in);
		int n1,n2,n3,n4,numeroInvertido;
		System.out.println("Digite um numero de 3 digitos: ");
		n1 = entrada.nextInt();
		n2 = n1 / 100;
		System.out.println( "UNIDADE: " + n2);
		n3 = ((n1/10) % 10) ;
		System.out.println( "DEZENA: " + n3);
		n4 = (n1%10) ;
		System.out.println( "CENTENA: " + n4);
		numeroInvertido =  ( n4 * 100) + ( n3 * 10 )  +  n2 ;
		System.out.println( "Numero Invertido: " + numeroInvertido);
		
		
		
	}

}
