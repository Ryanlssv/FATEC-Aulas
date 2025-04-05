package lista_1;

import java.util.Scanner;

public class Ex1_aumento_salario {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Calculadora Novo Salario");
		Scanner entrada = new Scanner(System.in);
		double salario,taxa,salario2, total;
		
		System.out.println("Digite o salario atual: ");
		salario = entrada.nextDouble();
		
		taxa = 25;
		System.out.println("Taxa de Aumento salarial: " + taxa);
		salario2 = ( salario * taxa ) / 100 ;
		total = salario + salario2;
		System.out.println("Novo Salario: " + total);
	}

}
