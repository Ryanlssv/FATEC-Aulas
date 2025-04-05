package Exercicio9;
import java.util.Scanner;

public class Ex9 {
    public static void main (String[] args){
        System.out.println("Calculo do salario a Ser pago");
        Scanner entrada = new Scanner(System.in);
        double pagH,bruto, liquido, inss , horasT ;
        System.out.println("Digite o Total de Horas Trabalhadas: ");
        horasT = entrada.nextDouble();
        System.out.println("Digite o Total Pago por Horas Trabalhadas: ");
        pagH = entrada.nextDouble();

        bruto = pagH * horasT ; 
        System.out.println("Total Bruto: " + bruto);

        System.out.println("Valor Da Taxa do INSS : 12% ");
        inss = 12;
        liquido = bruto - ( ( bruto * inss ) / 100 )   ;
        System.out.print("Total liquido a ser pago: " + liquido);      
    }
}
