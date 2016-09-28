//package master_thesis;


import java.util.Scanner;
import java.math.BigInteger;
//{}
class FloydCicloFinding {
 public static BigInteger funzione(BigInteger x, BigInteger n,BigInteger a) 	{
 	BigInteger y;
 	// y = x^2 + a mod n
 	y = x.pow(2).add(a).remainder(n);
 	return y;
 }
 /*
 public static BigInteger TrovaCicloFloyd(BigInteger x0, BigInteger n) 	{
 	BigInteger ciclo;
 	BigInteger x;
 	BigInteger y;
 	BigInteger j;

 	x = x0;
 	y = x0;
 	j = 0;

 	while (x.compareTo(y) == 0){
 		x = funzione(x);
 		y = funzione(funzione(y));
 		j = j.add(BigInteger.ONE);
 	}
 	return ciclo;
 }
 */
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
	BigInteger numValutazioni = new BigInteger("0");
	BigInteger ciclo = new BigInteger("0"); //variabile contenente la lunghezza del ciclo

	for(int i = 0; i<=100000; i++){
		System.out.print(" x"+i+" = "+x+" ; ");
		System.out.println(" y"+2*i+" = "+y+" ; ");
		//System.out.println(" MCD( "+numero+" ,  "+y+" - " +x+"  ) =  "+numero.gcd(y.subtract(x).abs()));

		x = funzione(x,numero,a);
		numValutazioni = numValutazioni.add(BigInteger.ONE);

 		y = funzione(funzione(y,numero,a),numero,a);
 		numValutazioni = numValutazioni.add(BigInteger.ONE);
 		numValutazioni = numValutazioni.add(BigInteger.ONE);

		numConfronti = numConfronti.add(BigInteger.ONE);
		if (x.compareTo(y)==0){ //se x=y inizio il calcolo della lunghezza del ciclo
			y = funzione(x,numero,a);//non incremento il numero di valutazioni

			ciclo = new BigInteger("0");
			while (x.compareTo(y)!=0){
				y = funzione(y,numero,a); //non incremento il numero di valutazioni

				ciclo = ciclo.add(BigInteger.ONE);
			}

			System.out.print(" x"+(i+1)+" = "+x+" ; ");
			System.out.println(" y"+(2*i+1)+" = "+y+" ; ");

			System.out.println("lunghezza ciclo =  "+ciclo.add(BigInteger.ONE));
			System.out.println("numero di f_valutazioni = "+numValutazioni);
			System.out.println("numero di confronti = "+numConfronti);
			return;
		}

	}
 }
}