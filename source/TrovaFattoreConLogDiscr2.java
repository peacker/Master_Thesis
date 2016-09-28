//package master_thesis;


import java.util.Scanner;
import java.math.BigInteger;

////mandando in esecuzione la classe, trova un fattore di n con il metodo del Logaritmo Discreto
// n non deve essere pari e non deve essere una potenza di primi, queste condizioni vanno testate con altri programmi
//versione2: non c'e' piu' la scelta del coprimo, ma viene scelto un elemento di Z/NZ casualmente e se non è coprimo con N,allora avrò trovato un fattore
//INPUT:n
//OUTPUT:p

//{}

public final class TrovaFattoreConLogDiscr2 {

        public static BigInteger trovaEsp(BigInteger x,BigInteger n){
				// metodo che restituisce m tale che x^m = 1 mod n, con la BRUTE FORCE!
				BigInteger m = new BigInteger("1");

	        	while (x.pow(m.intValue()).mod(n).compareTo(BigInteger.ONE)!=0){ //mentre x^m mod n != 1 aumenta m di 1
		       		//System.out.println("x = "+x+" - n = "+n+" - m = "+m);
		       		m = m.add(BigInteger.ONE);
		       	}
		       	return m;
		}

		public static BigInteger trovaFattore(BigInteger n, BigInteger x, BigInteger m){
			BigInteger p = new BigInteger("1");
			BigInteger k = new BigInteger("0");
			BigInteger temp1 = new BigInteger("1");
			BigInteger temp2 = new BigInteger("1");
			BigInteger due = new BigInteger("2");
			BigInteger menouno = new BigInteger("-1");

			temp1 = x.pow( /**/m.divide(due.pow(k.intValue())).intValue()/**/ );
			temp2 = x.pow( /**/m.divide(due.pow(k.add(BigInteger.ONE).intValue())).intValue()/**/ );

			//mentre x^(m/2^(k+1)) mod n != +1 o -1 aumenta k di 1
			while (temp1.mod(n).compareTo(BigInteger.ONE)==0 && (temp2.mod(n).compareTo(BigInteger.ONE)==0 || temp2.mod(n).compareTo(menouno)==0)) {
		    		k = k.add(BigInteger.ONE);
		    }
		    p = temp2.add(BigInteger.ONE).gcd(n);
			return p;
		}


        public static void main(String[] args) {
        	Scanner input = new Scanner(System.in);

			System.out.println("Inserisci il numero n (dispari e non potenza di primi) o 0 per uscire dal programma: ");
			BigInteger n = new BigInteger(input.nextLine());

			while (n.compareTo(BigInteger.ZERO)!=0){

				//variabile che contiene l'esponente da restituire

				BigInteger x = new BigInteger("2");
				BigInteger m = new BigInteger("1");
				BigInteger p = new BigInteger("1");

				//mentre n mod x != 0 && p=1
				//questo ciclo non puo' rimanere in loop perche' prima o poi x raggiungera' un divisore di n
				while (n.mod(x).compareTo(BigInteger.ZERO)!=0 && (p.compareTo(BigInteger.ONE)==0 || p.compareTo(n)==0)){

					System.out.println("x = "+x);
					m = trovaEsp(x,n);
					System.out.println("m = "+m);
					p = trovaFattore(n,x,m);
					System.out.println("p = "+p);

					//se alla fine del ciclo non ho trovato un fattore allora cambio il valore di x con x+1 mod n
					x = x.add(BigInteger.ONE).mod(n);
				}
				System.out.println("Fuori dal ciclo while:");
				System.out.println("x = "+x);
				System.out.println("p = "+p);

				System.out.println("Inserisci il numero n (dispari e non potenza di primi) o 0 per uscire dal programma: ");
				n = new BigInteger(input.nextLine());

			}
  		}
}
