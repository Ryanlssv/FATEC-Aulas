package matrix;

public class ex1 {

	public static void main(String[] args) {
		

		 int m1[][] = {{0,1,2,6},{2,4,5,6},{9,7,5,6}};

	        System.out.println("Matriz A:");
	    imprimeMatriz(m1);
	    
		int m2[][] = {{2,1,0,6},{5,4,2,6},{5,7,9,6}};

        System.out.println("Matriz B:");
	    imprimeMatriz(m2);
	    

        System.out.println("Matriz Soma:");
	    int[][] soma = SomaM(m1, m2);
	    imprimeMatriz(soma);

	}

    public static void imprimeMatriz(int[][] matriz) {
        for (int [] linha: matriz) {
            for (int coluna : linha) {
                System.out.print(coluna + "\t");
            }
            System.out.println();
        }
    }
    
    public static int[][] SomaM(int[][] m1, int[][] m2) {
        int linhas = m1.length;
        int colunas = m1[0].length;
        int[][] resultado = new int[linhas][colunas];

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                resultado[i][j] = m1[i][j] + m2[i][j];
            }
        }

        return resultado;
    }
}
