package banco2DSM;
import java.util.ArrayList;
import java.util.Scanner;

public class ArrayL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		
		ArrayList<Integer> n = new ArrayList<Integer>();
		 
		System.out.println("Digite 5 numeros");
		 
		for(int i = 0; i < 5; i++) {
			n.add(s.nextInt());
			
		}
		System.out.println("\n Lista de Numeros: "+ n);
			
	}

}
