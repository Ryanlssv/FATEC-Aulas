package imposto_cal_2;

public class ex_sala {

	public static void main(String[] args) {
		int[] vetor1 = {1,2,3,4,5};
		int[] vetor2 = {5,4,3,2,1};
		int[] vetor3 = new int[5];
		
        for (int i = 0; i < vetor1.length; i++) {
            vetor3[i] = vetor1[i] + vetor2[i];
        }
      
        System.out.println("Vetor 1: ");
        for (int i = 0; i < vetor1.length; i++) {
            System.out.println(vetor1[i]);
        }
        
        System.out.println("Vetor 2: ");
        for (int i = 0; i < vetor2.length; i++) {
            System.out.println(vetor2[i]);
        }
        
        
        System.out.println("Vetor Soma: ");
        for (int i = 0; i < vetor3.length; i++) {
            System.out.println(vetor3[i]);
        }
        
        
        System.out.println("----------VALORES IMPARES DO VETOR -------------- ");
        
        System.out.println("Valores ímpares do vetor 1: ");
        for (int i = 0; i < vetor1.length; i++) {
            if (vetor1[i] % 2 != 0) {
                System.out.println(vetor1[i]);
            }
        }
        
        
        System.out.println("----------ENCONTRA O MAIOR VALOR DO VETOR -------------- ");
        
        int maior = vetor1[0];
        for (int i = 1; i < vetor1.length; i++) {
            if (vetor1[i] > maior) {
                maior = vetor1[i];
            }
        }System.out.println("Maior elemento do vetor soma: " + maior);
		
		
	}

}
