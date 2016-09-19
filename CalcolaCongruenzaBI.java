import java.util.Scanner;

import java.math.BigInteger;



public class CalcolaCongruenzaBI {


    public static void main(String[] args) {



        Scanner input = new Scanner(System.in);

        System.out.print("Calcoliamo M modulo N, cioè il resto di M diviso N");        
        System.out.println();
        System.out.print("Inserisci M: ");
        BigInteger m = new BigInteger(input.nextLine());
        System.out.print("Inserisci N: ");
        BigInteger n = new BigInteger(input.nextLine());

        BigInteger ris = m.mod(n);
        System.out.print(m + " modulo " + n + " = " + ris);
        System.out.println();
    }

}

