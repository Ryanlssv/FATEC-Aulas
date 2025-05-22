package imposto_cal_2;
import java.util.Scanner;
public class Vetor_sala {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int[] listaInt = new int[5];
		String[] bandas = {"DragonForces", "PowerWolf", "Raimundos"};
		
		for(int i = 0 ; i < bandas.length ; i++) {
			System.out.println(bandas[i]);
		}
		
		for(int i = 0 ; i < listaInt.length; i ++) {
			System.out.println("Digite um numero :");
			listaInt[i] = scanner.nextInt();
		}
		
		for(int registro:listaInt) {
			System.out.print("\t" + registro);
		}
		
		
		double[] listaD = {2,3.5,7.5};
		double soma = 0, media;
		for (double registro:listaD) {
			soma+=registro;
		} media  = soma/listaD.length;
		System.out.printf("Soma = %.2f.\t Media = %.2f",soma,media);
		
		
	}

}


