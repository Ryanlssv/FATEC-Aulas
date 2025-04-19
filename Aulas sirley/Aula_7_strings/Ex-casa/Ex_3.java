package v;
import java.util.Scanner;
public class Ex_3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
			String nome , sobrenome,Vof2, Vof,m1,m2 ;
		System.out.println("Digite Seu nome: ");
			nome = sc.nextLine();
			System.out.println("Digite Seu sobrenome: ");
			sobrenome = sc.nextLine();
			char l1 = nome.charAt(0) ;
			char l2 = sobrenome.charAt(0) ;
			 Vof = String.valueOf(l1); 
			 m1 = Vof.toUpperCase();
			 Vof2 = String.valueOf(l2); 
			 m2 = Vof2.toUpperCase();
			 System.out.println(m1 + m2 );
			 
			
		
	}

}
