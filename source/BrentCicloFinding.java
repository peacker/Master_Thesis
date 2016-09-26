package net.emanuelebellini.master_thesis


import java.util.Scanner;
import java.math.BigInteger;
//{}
class BrentCicloFinding {
 public static BigInteger funzione(BigInteger x, BigInteger n,BigInteger a) 	{
 	BigInteger y;
 	// y = x^2 + a mod n
 	y = x.pow(2).add(a).remainder(n);
 	return y;
 }

 public static void main(String [] args){

	Scanner input = new Scanner(System.in);

	System.out.println("Inserisci un numero N: ");
	BigInteger numero = new BigInteger(input.nextLine());
	System.out.println("Inserisci un numero da cui partire minore di N: ");
	BigInteger inizio = new BigInteger(input.nextLine());
	System.out.println("Inserisci il parametro a: ");
	BigInteger a = new BigInteger(input.nextLine());
	BigInteger x = inizio;
	BigInteger y = inizio;
	BigInteger numConfronti = new BigInteger("0");
	BigInteger numValutazioni = new BigInteger("1");
	BigInteger ciclo = new BigInteger("0");
	BigInteger r = new BigInteger("1");
	int done = 1;
	BigInteger k = new BigInteger("0");
	//BigInteger i = new BigInteger("0");
	BigInteger j = new BigInteger("0");

	do{
		x = y;
		System.out.println();
		System.out.println("x = "+x);
		j = k;
		r = r.multiply(new BigInteger("2"));// r=r*2
		do{
			k = k.add(BigInteger.ONE);//.multiply(new BigInteger("3")).divide(new BigInteger("2"));
			y = funzione(y,numero,a);
			System.out.print("y = "+y+" - ");
			numValutazioni = numValutazioni.add(BigInteger.ONE);

			numConfronti = numConfronti.add(BigInteger.ONE);
			done = x.compareTo(y);

		}while( (done == -1 || done == 1) && k.compareTo(r.subtract(BigInteger.ONE)) == -1);
	}while(done == -1 || done == 1);
	ciclo = k.subtract(j).subtract(BigInteger.ONE);

	System.out.println();
	System.out.println("lunghezza ciclo =  "+ciclo.add(BigInteger.ONE));
	System.out.println("numero di f_valutazioni = "+numValutazioni);
	System.out.println("numero di confronti = "+numConfronti);
	return;


	}//chiude main
}//chiude classe