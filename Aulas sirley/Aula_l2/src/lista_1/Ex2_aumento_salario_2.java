package lista_1;

import java.util.Scanner;

public class Ex2_aumento_salario_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Calculadora Novo Salario");
		Scanner entrada = new Scanner(System.in);
		double salario,taxa,salario2, total;
		
		System.out.println("Digite o salario atual: ");
		salario = entrada.nextDouble();
		
		System.out.println("Digite o valor do percentual: ");
		taxa = entrada.nextDouble();
		
		salario2 = ( salario * taxa ) / 100 ;
		total = salario + salario2;
		System.out.printf("Novo Salario: %.2f " , total);
	}

}
