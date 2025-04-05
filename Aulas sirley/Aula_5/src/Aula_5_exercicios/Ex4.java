package Aula_5_exercicios;
import java.util.Scanner;
public class Ex4 {
	
	// Do while numero aleatorio
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner entrada = new Scanner(System.in);
		double resposta = Math.random() * 11;
		int  i ,palpite, tentativa = 5;
		int n , max = 5;
		boolean acertou = false ;
		 //gera um número aleatório entre 0.0 e 0.999, 
		//multiplica por 5 teremos um número entre entre 0.0 e 4.999
		 int randomNumber = (int)resposta;
		 
		 do {
	            System.out.println("Digite um número entre 1 e 10.");
	            palpite = Integer.parseInt(entrada.nextLine());
	            if (palpite == randomNumber) {
	                System.out.println("Parabéns, você Acertou! :)");
	                acertou = true;
	            } else {
	                System.out.println(" ERROUUUU, tente novamente!");
	            }
		 }while(palpite != randomNumber );
	}

	
}
