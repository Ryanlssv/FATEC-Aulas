package Exerciccios;
import java.util.Scanner;
public class Ex_6 {

	public static void main(String[] args) {
		 Scanner scanner = new Scanner(System.in);
			String s1 , s2 ,s3,s4;
			int corte;
		// perde ao usuario digite os pais
	        System.out.print("Digite S1: ");
	        s1 = scanner.nextLine().toUpperCase();
	        System.out.print("Digite S2: ");
	        s2 = scanner.nextLine().toUpperCase();
	   // verifica se sao do mesmo tamnho
	        if (s1.length() != s2.length()) {
	            System.out.println(" nnao sao do mesmo tamanho.");
	            return;
	        }
	        // efetua o corte 
	        corte = s1.length() / 2;
	        s3 = s1.substring(0, corte) + s2.substring(corte);
	        s4 = s2.substring(0, corte) + s1.substring(corte);  

	        if (Math.random() > 0.5) {
	            s3 = mutacao(s3, s1, s2); 
	        } else {
	            s4 = mutacao(s4, s1, s2);  
	        }
	       // imprime os filhos gerados
	        System.out.println("Filhos:");
	        System.out.println("S3 = " + s3);
	        System.out.println("S4= " + s4);
	    }
	// funcao que gera as mutações e e gera os caracteres para s3 s4
	    private static String mutacao(String s, String s1, String s2) {
	        int posicao;
	        posicao = (int) (Math.random() * s.length());  
	        String caracteresP = s1 + s2;
	        char novoCaractere = caracteresP.charAt((int) (Math.random() * caracteresP.length()));
	        return s.substring(0, posicao) + novoCaractere + s.substring(posicao + 1);
	    }

}

