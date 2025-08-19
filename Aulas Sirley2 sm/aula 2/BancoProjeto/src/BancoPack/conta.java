package BancoPack;

import javax.swing.JOptionPane;

public class conta {

	public static void main(String[] args) {
		String menu= "\n1.Mostrar Extrato da conta \n2.Fazer Transferencia. \n3.Realisar Deposito. \n0.Sair. \n Digite a opção desejada: ";
		String entrada;
		int op=0;
		String t ="Digite o valor que deseja Transferir";
		String d ="Digite o valor que deseja Depositar";
		int v = 0;
		String sut ="Transferencia feita com sucesso";
		String sud ="Deposito feito com sucesso";
		String sal ="Saldo Atual: ";
		banco b = new banco(123,"Karlos",2000);
		
		do {
			entrada= JOptionPane.showInputDialog(menu);
			op=Integer.parseInt(entrada);
			switch(op) {
			case 1: JOptionPane.showMessageDialog(null, b.Extrato());
			break;
			case 2: 
				entrada= JOptionPane.showInputDialog(t);
				v=Integer.parseInt(entrada);
				if(v > b.saldo){
					JOptionPane.showMessageDialog(null, "Saldo Insuficiente");
					break;
				}
				JOptionPane.showMessageDialog(null, sal + b.trans(v));
				JOptionPane.showMessageDialog(null, sut);
				
		
			break;	
			case 3:
				entrada= JOptionPane.showInputDialog(d);
				v=Integer.parseInt(entrada);
				JOptionPane.showMessageDialog(null, sal + b.dep(v));
				JOptionPane.showMessageDialog(null, sud);
			break;	
			case 0: 
				 JOptionPane.showMessageDialog(null,"Adeus");
			break;	 
			default: JOptionPane.showMessageDialog(null, "Número errado", "Windoh", JOptionPane.ERROR_MESSAGE);
			}
			
		}while(op!=0);
	}

}
