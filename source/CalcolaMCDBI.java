package net.emanuelebellini.master_thesis


import java.util.Scanner;

import java.math.BigInteger;



public class CalcolaMCDBI {


    public static void main(String[] args) {



        Scanner input = new Scanner(System.in);

        System.out.print("Inserisci il primo intero: ");        
        BigInteger m = new BigInteger(input.nextLine());
        System.out.println();
        System.out.print("Inserisci il secondo intero: ");
        BigInteger n = new BigInteger(input.nextLine());
		System.out.println();
		
        BigInteger ris = m.gcd(n);
        System.out.print("Il Massimo Comune Divisore tra ");
        System.out.println();
        System.out.println(m);
        System.out.println("ed");
        System.out.println(n);
        System.out.println("e':");
        System.out.println(ris);
    }

}

