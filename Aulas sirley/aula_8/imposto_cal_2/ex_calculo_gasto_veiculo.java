package imposto_cal_2;

import java.text.DecimalFormat;
import java.util.Scanner;

public class ex_calculo_gasto_veiculo {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite seu Nome: ");
		String Nome = sc.nextLine();
		System.out.println("Digite A Placa do Seu veiculo: ");
		String numCPF = sc.nextLine();
		double MediaConsumo = 12 ;
		double preco = 5.80;
		

        double totalKm = 0;
        double totalLitros = 0;
        double totalGasto = 0;
        double maiorGasto = 0;
        String mesMaiorGasto = "";
			
		double[] kmPorMes = {
				 1200.0, 950.0, 1100.0, // Jan, Fev, Mar
				 800.0, 1300.0, 1500.0, // Abr, Mai, Jun
				 2000.0, 1800.0, // Jul, Ago
				 750.0, 900.0, // Set, Out
				 1100.0, 1600.0 // Nov, Dez
	
		};
		
	    String[] nomesMeses = {
	            "Janeiro", "Fevereiro", "Março",
	            "Abril", "Maio", "Junho",
	            "Julho", "Agosto",
	            "Setembro", "Outubro",
	            "Novembro", "Dezembro"
	        };
	    
	    
	    System.out.print("------- DETALHE DE GASTOS MENSAIS --------");
	    
	    
	    for (int i = 0; i < 12; i++) {
            double gasto = calGastoMensal(nomesMeses[i], kmPorMes[i], MediaConsumo, preco);
            double litros = kmPorMes[i] / MediaConsumo;    
    		System.out.println( nomesMeses[i] + " : R$" + formatarNumero(gasto));
	    }
	
		
		
		double TotalKmRodado = 0;
		 for (double rodado : kmPorMes) {
			 TotalKmRodado += rodado;
		 }System.out.println(TotalKmRodado);
		 
		 
			 

	}

	public static double calGastoMensal(String string, double kmPorMes, double mediaConsumo, double preco) {
		double litros = kmPorMes / mediaConsumo;
		return litros * preco;
	}
	
    public static String formatarNumero(double valor) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(valor);
    }
	
}
