package comercio;

import javax.swing.JOptionPane;
import comercio.Funcionario;
public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String menu= "\n1.Mostrar dados do produto \n2.Calcular total da compra. \n3.Mostrar dados do funcionário.\n4.Calcular salário líquido do funcionário.\n0.Sair. \n Digite a opção desejada: ";
		String entrada;
		int op=0;
		String quantidade = "Digite a Quantidade: ";
		int q = 0;
		String taxa = "Digite o valor da taxa: ";
		double t = 0;
		
		Produto p= new Produto(12,"Café",35);
		Funcionario f= new Funcionario(12,"Carlos","Gerente",12324);
		do {
			entrada= JOptionPane.showInputDialog(menu);
			op=Integer.parseInt(entrada);
			switch(op) {
			case 1: JOptionPane.showMessageDialog(null, p.mostrarDados());
			  break;
			case 2: 
				entrada= JOptionPane.showInputDialog(quantidade);
				q=Integer.parseInt(entrada);
				JOptionPane.showMessageDialog(null, p.calcularTotal(q));;
			  break;
			case 3:JOptionPane.showMessageDialog(null, f.mostrarDados());
			break;
			case 4: 
				entrada= JOptionPane.showInputDialog(taxa);
				t=Integer.parseInt(entrada);
				JOptionPane.showMessageDialog(null, f.calculaSalarioLiquido(t));;
			break;	
			default: JOptionPane.showMessageDialog(null, "Número errado", "Windoh", JOptionPane.ERROR_MESSAGE);
			
			}
				
		}while(op!=0);
				
	}

}
