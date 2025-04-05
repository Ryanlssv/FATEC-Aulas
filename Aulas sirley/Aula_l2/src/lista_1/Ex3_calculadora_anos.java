package lista_1;
import java.util.Scanner;
public class Ex3_calculadora_anos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Calculadora de Anos,dias,meses,semanas vividas: ");
		Scanner entrada = new Scanner(System.in);
		int anoN, ano, idade , dias , diasV, semanasv,semanas, meses, mesesv ;
		System.out.println("Digite o ano de nacimento: ");
		anoN = entrada.nextInt();
		
		ano = 2025;
		dias = 365;
		semanas =48;
		meses = 12;
		
		idade = ano - anoN ;
		System.out.println("Você tem: " + idade + " Anos");
		diasV = idade * dias ;
		System.out.println("Você tem: " + diasV + " Dias Vividos");
		semanasv = idade * semanas ;
		System.out.println("Você tem: " + semanasv + " semanas Vividas");
		mesesv = idade * meses ;
		System.out.println("Você tem: " + mesesv + " meses Vividos");
	}

}
