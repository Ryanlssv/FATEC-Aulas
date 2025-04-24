package Exerciccios;
import java.util.Scanner;
public class Ex_2_funcao {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num;
		String resposta;
		num = sc.nextInt();
		System.out.println(num_extenso(num));

	}

	private static String num_extenso(int num) {
		String numString, resposta = "";
		int i;
		numString = Integer.toString(num);
		for( i = 0 ; i < numString.length() ; i ++) {
			char c = numString.charAt(i);
			switch(c){
		 	case '0': resposta+="-zero"; break;
		 	case '1': resposta+="-um"; break;
		 	case '2': resposta+="-dois"; break;
		 	case '3': resposta+="-tres"; break;
		 	case '4': resposta+="-quatro"; break;
		 	case '5': resposta+="-cinco"; break;
		 	case '6': resposta+="-seis"; break;
		 	case '7': resposta+="-sete"; break;
		 	case '8': resposta+="-oito"; break;
		 	case '9': resposta+="-nove"; break;
		 	default: resposta="";break;
		 }
		 	
		}
		return resposta;
	}

}