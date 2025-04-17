package Aula_7_Ex;

public class Ex_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			String Ola , Nome, concate ,teste;
			int i;
			
			Ola = "Óla!" ;
			Nome ="Zé";
			concate = Ola +"\t" + Nome ;
			System.out.println(concate);
		
			System.out.printf("Caracteres numéricos:\n");
			 for (i=48; i<=57; i++) {
			 System.out.printf("%c ", i);
			 }
			 System.out.printf("\n\nCaracteres alfabéticos maiúsculos:\n");
			 for (i=65; i<=90; i++) {
			 System.out.printf("%d ", i);
			 }
			 System.out.printf("\n\nCaracteres alfabéticos minúsculos:\n");
			 for (i=97; i<=122; i++) {
			 System.out.printf("%d ", i);
			 }
			 System.out.printf("\n");

	}

}
