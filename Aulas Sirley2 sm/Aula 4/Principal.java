package banco2DSM;
 
import javax.swing.JOptionPane;
 
 
 
public class Principal {

	public static void main(String[] args) {
		String menu= "\n1.Mostrar dados da Conta \n2.Depositar. \n3.Sacar. \n0.Sair. \n Digite a opção desejada: ";
		String entrada;
		int op=0;
		double valor;
		Cliente cliente1= new Cliente(1,"Anderson Soares","Rua José Bonifácio");
	
		
		Conta c= new Conta(1,cliente1,1000);
		Conta outra= new Conta(2, new Cliente(2,"Segundo cliente","endereço2"),500);
		do {
			entrada= JOptionPane.showInputDialog(menu);
			op=Integer.parseInt(entrada);
			switch(op) {
			case 1:
				JOptionPane.showMessageDialog(null,c.mostraDados());
			  break;
			case 2: 
				entrada= JOptionPane.showInputDialog("Digite o valor");
				valor=Double.parseDouble(entrada);
				c.deposita(valor);
				JOptionPane.showMessageDialog(null," Operação realizada com sucesso" );
			  break;
			case 3: 
				entrada= JOptionPane.showInputDialog("Digite o valor");
				valor=Double.parseDouble(entrada);
				if(c.saca(valor))
					JOptionPane.showMessageDialog(null," Operação realizada com sucesso" );
				else
					JOptionPane.showMessageDialog(null," Saldo Insuficiente" );
				break;
			case 0:
				JOptionPane.showMessageDialog(null, "Até Logo!");
				break;
			default: JOptionPane.showMessageDialog(null, "Opção inválida");
		            
			}
				
			
				
		}while(op!=0);
				

	}

}

 