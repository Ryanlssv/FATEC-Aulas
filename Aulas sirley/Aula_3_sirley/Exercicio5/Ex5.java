package Exercicio5;

import java.util.Scanner;

public class Ex5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner entrada = new Scanner(System.in);
		int op;
		double x,y,result;
		System.out.println("Digite um numero de 1 a 4: ");
		String menu = "\n1 - soma \n2 - multiplicação \n3 - subtração \n4 - Divisão \n5 - exponencial \n6 - Raiz quadrada  ";
		System.out.println("Escolha uma opção" + menu );
		op = entrada.nextInt();
	
	switch (op) {
	
	case 0:
		
		break;
	
	case 1: System.out.println("Voce escolheu Soma: ");
	System.out.println("Digite o 1 numero: ");
	x = entrada.nextDouble();
	System.out.println("Digite o 2 numero: ");
	y = entrada.nextDouble();
	result = x + y;
	System.out.printf("Resultado : %.2f" , result );

		
		break;
	
	case 2: System.out.println(" Voce escolheu Multiplicação: ");
	System.out.println("Digite o 1 numero: ");
	x = entrada.nextDouble();
	System.out.println("Digite o 2 numero: ");
	y = entrada.nextDouble();
	result = x * y;
	System.out.printf("Resultado : %.2f", result );
		
		break;
		
	case 3: System.out.println("Voce escolheu Subtracãoo:");
	System.out.println("Digite o 1 numero: ");
	x = entrada.nextDouble();
	System.out.println("Digite o 2 numero: ");
	y = entrada.nextDouble();
	result = x - y;
	System.out.printf("Resultado :%.2f " , result );
		
		break;
	
	case 4: System.out.println("Voce escolheu Divisão: ");
	System.out.println("Digite o 1 numero: ");
	x = entrada.nextDouble();
	System.out.println("Digite o 2 numero: ");
	y = entrada.nextDouble();
	if( y == 0 ) {
		System.out.println("Não e possivel dividir por 0 ");
		break;
	}
	
	result = x / y;
	System.out.printf("Resultado : %.2f" , result );
		
		break;
		
	case 5:
		System.out.println("Voce escolheu exponenciação: ");
		System.out.println("Digite o 1 numero: ");
		x = entrada.nextDouble();
		System.out.println("Digite o expoente: ");
		y = entrada.nextDouble();
		
		result = Math.pow(x,y);
		System.out.printf("Resultado : %.2f" , result );
		break;
		
	case 6:
		System.out.println("Voce escolheu Raiz Quadrada: ");
		System.out.println("Digite o 1 numero: ");
		x = entrada.nextDouble();
		result = Math.sqrt(x);
		System.out.printf("Raiz quadrada de " + x  + " é: %.1f ", result );
		break;
	
	default: System.out.println(" Voce escolheu uma Opção invalida");
		
		break;
	}
	}

}
