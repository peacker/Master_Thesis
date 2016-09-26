package net.emanuelebellini.master_thesis


import java.util.Scanner;
import java.math.BigInteger;
////
//{}
//F8, ottavo numero di Fermat
//115792089237316195423570985008687907853269984665640564039457584007913129639937
public final class PollardBrentRhoBI {

        //restituisce il valore della funzione x^1024 + a
        public static BigInteger funzione(BigInteger x, BigInteger a,BigInteger n){
        	x = x.pow(1024).add(a).remainder(n);

        	return x;
        }

        //metodo che ritorna il primo fattore trovato di num usando il metodo Rho di pollard
        //se non è stato trovato alcun fattore allora ritorna num
        public static BigInteger calcolaPrimoFattorePollardBrentRho(BigInteger n,BigInteger a, BigInteger x0){
        	BigInteger y ;
        	BigInteger x ;
        	BigInteger r = new BigInteger("1");
        	BigInteger q = new BigInteger("1"); // accumula i fattori |x-y| mod n
        	BigInteger k = new BigInteger("0");
        	BigInteger m = new BigInteger("1");
        	BigInteger i; //contatore
        	BigInteger min;
        	BigInteger ys; // salva il primo valore di y????
        	BigInteger MCD; // contiente il MCD

        	y = x0;
        	do
        	{
        		x = y;
        		//for i = 1..r
        		for (i = BigInteger.ONE; i.compareTo(r) == -1 || i.compareTo(r) == 0; i=i.add(BigInteger.ONE))
        		{
        			y = funzione(y,a,n);
        			k = BigInteger.ZERO;
        		}
        		do
        		{
        			ys = y;
        			min = m.min(r.subtract(k));
        			//for i = 1..min(m,r-k)
        			for (i = BigInteger.ONE;i.compareTo(min) == -1 || i.compareTo(min) == 0; i=i.add(BigInteger.ONE))
        			{
        				y = funzione(y,a,n);
        				//q = q*|x-y| mod n

        				q = q.multiply(x.subtract(y).abs()).mod(n);
        				//System.out.print(" // q = | "+x+" - "+y+" | = "+q+" mod "+n);
        			}
        			//System.out.println();
        			MCD = q.gcd(n);
        			//System.out.println("MCD = " + MCD);
        			k = k.add(m);
        		}
        		while ((k.compareTo(r) == -1)&&(MCD.compareTo(BigInteger.ONE) == 0));
        		// r = r*2
        		r = r.add(r);
        	}
        	while (MCD.compareTo(BigInteger.ONE) == 0);
        	if (MCD.compareTo(n) == 0)
        	{
        		do
        		{
        			ys = funzione(ys,a,n);
        			//MCD = MCD(|x-ys|,n)
        			MCD = n.gcd(x.subtract(ys).abs());
        		}
        		while (MCD.compareTo(BigInteger.ONE)==0);
        	}
        	return MCD;
        }

        public static void main(String[] args) {
        	Scanner input = new Scanner(System.in);

        	System.out.println("Inserisci il numero N da fattorizzare: ");
        	BigInteger n = new BigInteger(input.nextLine());
        	System.out.println("Inserisci il parametro a: ");
        	BigInteger a = new BigInteger(input.nextLine());
        	System.out.println("Inserisci un valore s da cui cominciare la ricerca: ");
        	BigInteger s = new BigInteger(input.nextLine());

        	BigInteger fattoreTrovato = calcolaPrimoFattorePollardBrentRho(n,a,s);
        	System.out.println( "il primo fattore trovato di "+ n + " e' "+ fattoreTrovato);
        	while (n.compareTo(fattoreTrovato)==0){
        		System.out.println("Inserisci un nuovo parametro a: ");
	        	a = new BigInteger(input.nextLine());
        		System.out.println("Inserisci un nuovo valore s da cui cominciare la ricerca: ");
        		s = new BigInteger(input.nextLine());
        		fattoreTrovato = calcolaPrimoFattorePollardBrentRho(n,a,s);
        		System.out.println( "il primo fattore trovato di "+ n + " e' "+ fattoreTrovato);
        	}
  		}
}
