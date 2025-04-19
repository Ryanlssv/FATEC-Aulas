package Exercicios;
import java.util.Scanner;
public class Ex_2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n,i;
		String s;
		char c;
		 n = sc.nextInt() ;
		 s = String.valueOf(n);
		 
		 for( i = 0 ; i < s.length() ; i ++) {
			 c = s.charAt(i);
	           
			 if (c == '-') {
	                System.out.print("menos, ");
	                continue;
	            }
			 
			 switch(c){
			 	case '0': System.out.print("zero"); break;
			 	case '1': System.out.print("um"); break;
			 	case '2': System.out.print("dois"); break;
			 	case '3': System.out.print("três"); break;
			 	case '4': System.out.print("quatro"); break;
			 	case '5': System.out.print("cinco"); break;
			 	case '6': System.out.print("seis"); break;
			 	case '7': System.out.print("sete"); break;
			 	case '8': System.out.print("oito"); break;
			 	case '9': System.out.print("nove"); break;
			 }
	            if (i < s.length() - 1) {
	                System.out.print(", ");
	            }
			 
		 }
	}

}
