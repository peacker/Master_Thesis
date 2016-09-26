package net.emanuelebellini.master_thesis



import java.util.Scanner;
import java.math.BigInteger;

 public class CalcolaFattoriBI {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Inserisci il numero da fattorizzare: ");
        BigInteger n = new BigInteger(input.nextLine());
        System.out.print("I fattori primi di " + n + " sono: ");

        BigInteger i = new BigInteger("2");
        // per ogni possibile fattore i
        while (i.compareTo(n.divide(i))!=1)  {  //for(i=2;i<=n/i;i++)

            // se i e' un fattore di N, dividi N ripetutamente per i
            while (n.remainder(i).compareTo(BigInteger.ZERO) == 0) {
                System.out.print(i + " ");
                n = n.divide(i);
            }
            i=i.add(BigInteger.ONE);
        }
        // se il più grande fattore trovato appare solo una volta, n > 1
        if (n.compareTo(BigInteger.ONE)==1) System.out.println(n);
        else       System.out.println();

    }
}

