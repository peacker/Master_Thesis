package net.emanuelebellini.master_thesis


import java.util.Scanner;

import java.math.BigInteger;

//{}

public class CongruenzaQuadraticaBI {


    public static void main(String[] args) {



        Scanner input = new Scanner(System.in);

        System.out.println("Calcoliamo le soluzioni di u^2 = b modulo m: ");        
        System.out.print("Inserisci m: ");
        BigInteger m = new BigInteger(input.nextLine());
        System.out.print("Inserisci b < m: ");
        BigInteger b = new BigInteger(input.nextLine());
		BigInteger i;
		for(i = BigInteger.ZERO;i.compareTo(m) == -1;i=i.add(BigInteger.ONE)){
			if(i.pow(2).remainder(m).compareTo(b)==0) System.out.println(i);
		}
    }

}