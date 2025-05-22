package imposto_cal_2;
import java.util.Scanner;
public class calculadora {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Calculadora de Imposto");
		System.out.println("Digite seu Nome: ");
		String Nome = sc.nextLine();
		System.out.println("Digite seu CPF: ");
		String numCPF = sc.nextLine();
		System.out.println("Digite sua renda mensal bruta nos ultimos 12 meses: ");
		System.out.println("janeiro: ");
		double j = sc.nextDouble() * 0.8 ;
		System.out.println("fevereiro: ");
		double f = sc.nextDouble() * 0.8 ;
		System.out.println("março: ");
		double m = sc.nextDouble() * 0.8 ;
		System.out.println("abril: ");
		double a = sc.nextDouble() * 0.8 ;
		System.out.println("maio: ");
		double ma = sc.nextDouble() * 0.8 ;
		System.out.println("junho: ");
		double jun = sc.nextDouble() * 0.8 ;
		System.out.println("julho: ");
		double jul = sc.nextDouble() * 0.8 ;
		System.out.println("agosto: ");
		double ag = sc.nextDouble() * 0.8 ;
		System.out.println("outubro: ");
		double o = sc.nextDouble() * 0.8 ;
		System.out.println("setembro: ");
		double s = sc.nextDouble() * 0.8 ;
		System.out.println("novembro: ");
		double n = sc.nextDouble() * 0.8 ;
		System.out.println("desembro: ");
		double d = sc.nextDouble() * 0.8 ;
		double basedeCal = j + f + m +a+ma+jun+jul+ag+o+s+n+d;
		if (basedeCal >=27110.41  && basedeCal <=33919.80 ) {
		double deducao =  2033.28;
		double alicota = 0.075;
		double cal = basedeCal * alicota;
		double imposto = cal - deducao;
		System.out.println(Nome + " Você terá que pagar imposto");
		System.out.println("CPF: "+numCPF);
		System.out.println("alicota: "+alicota);
		System.out.printf("Base de calculo: %.1f ",basedeCal);
		System.out.printf("Imposto Restringindo na fonte: %.2f " , imposto );
		}
		else if (basedeCal >= 33919.81  && basedeCal <= 45012.60 ) {
		double deducao =  4577.27;
		double alicota = 0.15;
		double cal = basedeCal * alicota;
		double imposto = cal - deducao;
		System.out.println(Nome + " Você terá que pagar imposto");
		System.out.println("CPF: "+numCPF);
		System.out.println("alicota: "+alicota);
		System.out.printf("Base de calculo: %.1f ",basedeCal);
		System.out.printf("Imposto Restringindo na fonte: %.2f " , imposto );
		}
		else if (basedeCal >=  45012.61  && basedeCal <=  55976.16 ) {
		double deducao =  7953.21;
		double alicota = 0.225;
		double cal = basedeCal * alicota;
		double imposto = cal - deducao;
		System.out.println(Nome + " Você terá que pagar imposto");
		System.out.println("CPF: "+numCPF);
		System.out.println("alicota: "+alicota);
		System.out.printf("Base de calculo: %.1f ",basedeCal);
		System.out.printf("Imposto Restringindo na fonte: %.2f " , imposto );
		}
		else if (basedeCal > 55976.16 ) {
		double deducao =   107520.2;
		double alicota = 0.275;
		double cal = basedeCal * alicota;
		double imposto = cal - deducao;
		System.out.println(Nome + " Você terá que pagar imposto");
		System.out.println("CPF: "+numCPF);
		System.out.println("alicota: "+alicota);
		System.out.printf("Base de calculo: %.1f ",basedeCal);
		System.out.printf("Imposto Restringindo na fonte: %.2f\n " , imposto );
		}
		else {
		System.out.println(Nome+" Voce esta isento de imposto");
		System.out.println("CPF: "+numCPF);
		}

	}

}
