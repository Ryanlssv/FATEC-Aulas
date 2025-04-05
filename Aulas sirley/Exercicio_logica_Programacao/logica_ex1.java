package Exercicio_logica_Programacao;

public class logica_ex1 {
    public static void main (String[] args){
        double a, b, c ,d;
        a = 10;
        b = 20;
        System.out.println("Dados de A (1): " + a);
        System.out.println("Dados de B (1): " + b);
        c = a;
        d = b;
        b = c;
        a = d;
        System.out.println("Dados A (2): " + a);
        System.out.println("Dados B (2) : " + b);
    };
}
