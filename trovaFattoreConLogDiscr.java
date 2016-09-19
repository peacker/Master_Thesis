import java.util.Scanner;
import java.math.BigInteger;

////mandando in esecuzione la classe, trova un fattore di n con il metodo del Logaritmo Discreto
// n non deve essere pari e non deve essere una potenza di primi, queste condizioni vanno testate con altri programmi
//INPUT:n
//OUTPUT:p

//{}

public final class trovaFattoreConLogDiscr {

        public static BigInteger scegliCoprimo(BigInteger n,BigInteger x){
				// metodo che restituisce il più piccolo coprimo di n > x
						x = x.add(BigInteger.ONE);
				        while (n.gcd(x).compareTo(BigInteger.ONE)!=0){
				        	x = x.add(BigInteger.ONE);
				        }
				        return x;
        }

        public static int trovaEsp(BigInteger x,BigInteger n){
				// metodo che restituisce m tale che x^m = 1 mod n, con la BRUTE FORCE!
				int m = 1;

	        	while (x.pow(m).mod(n).compareTo(BigInteger.ONE)!=0){ //mentre x^m mod n != 1 aumenta m di 1
		       		//System.out.println("x = "+x+" - n = "+n+" - m = "+m);
		       		m = m+1;
		       	}
		       	return m;
		}

		public static BigInteger trovaFattore(BigInteger n, BigInteger x, int m){
			BigInteger p = new BigInteger("1");
			int k = 0;

			while (x.pow(m/(2^k)).mod(n).compareTo(BigInteger.ONE)==0 && x.pow(m/(2^(k+1))).mod(n).compareTo(BigInteger.ONE)==0 ){ //mentre x^m mod n != 1 aumenta m di 1
		    		k = k+1;
		    }
		    p = x.pow(m/(2^(k+1))).add(BigInteger.ONE).gcd(n);
			return p;
		}


        public static void main(String[] args) {
        	Scanner input = new Scanner(System.in);
			BigInteger n = new BigInteger("1");
			System.out.println("Inserisci il numero n (dispari e non potenza di primi) o 0 per uscire dal programma: ");
			n = new BigInteger(input.nextLine());
			while (n.compareTo(BigInteger.ZERO)!=0){


				//variabile che contiene l'esponente da restituire

				BigInteger p = new BigInteger("1");
				BigInteger x = new BigInteger("2");

				if (n.mod(x).compareTo(BigInteger.ZERO)==0) x = scegliCoprimo(n,x);
				System.out.println("il primo coprimo di "+n+" e': "+x );
				int m = trovaEsp(x,n);

				System.out.println("l'esponente di "+x+" trovato e' m = "+m );

				p = trovaFattore(n,x,m);

				System.out.println("il fattore di "+n+" trovato e' p = "+p );
				System.out.println("---------------------------------------------------------");
					while (p.compareTo(BigInteger.ONE) == 0){

					x = scegliCoprimo(n,x);

					System.out.println("il coprimo successivo di "+n+" e': "+x );

					m = trovaEsp(x,n);

					System.out.println("l'esponente di "+x+" trovato e' m = "+m );

					p = trovaFattore(n,x,m);

					System.out.println("il fattore di "+n+" trovato e' p = "+p );
					System.out.println("---------------------------------------------------------");
				}
				System.out.println("---------------------------------------------------------");
				System.out.println("Inserisci il numero n (dispari e non potenza di primi) o 0 per uscire dal programma: ");
				n = new BigInteger(input.nextLine());
			}
  		}
}
