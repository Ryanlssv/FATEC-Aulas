package matrix;
import java.util.Scanner;
public class ex2 {

	public static void main(String[] args) {
 
		Scanner sc = new Scanner(System.in);
		System.out.println("Quantidade de Produtos: ");
		int numProduto = sc.nextInt();
		sc.nextLine();

		String [] NomesProdutos = new String[numProduto];
		double [] PrecoProdutos = new double [numProduto] ;
		double [] PrecoCusto  = new double[numProduto];
		int i;
		
		for(  i = 0; i<numProduto ; i++) {
			System.out.printf("\n Digite o nome do Produto %d:", i + 1);
			NomesProdutos[i] = sc.nextLine();	
		}
		
		for( int j = 0; j <numProduto ; j++) {
			System.out.printf("\n Digite o Preço do Produto %s:", j + 1, NomesProdutos[j]);
			PrecoProdutos[j] = sc.nextInt();
			
		}

	}

}
