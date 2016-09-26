package net.emanuelebellini.master_thesis


import java.util.Scanner;
import java.math.BigInteger;

////mandando in esecuzione la classe, trova l' "esponente" m di x mod n, cioè m tale che x^m = 1 mod n
//INPUT:n
//OUTPUT:m
//x viene scelto casualmente tra gli elementi coprimi di n
//{}

public final class TrovaEsponente {
		//metodo che restituisce l'esponente m di un x scelto a caso tra i coprimi di n

        public static BigInteger scegliCoprimo(BigInteger n,BigInteger x){
		// metodo che restituisce il più piccolo coprimo di n > x
				x = x.add(BigInteger.ONE);
		        while (n.gcd(x).compareTo(BigInteger.ONE)!=0){
		        	x = x.add(BigInteger.ONE);
		        }
		        return x;
        }

		public static int trovaLogaritmoDiscreto(BigInteger g,BigInteger h,BigInteger n){
				// metodo che risolve il logaritmo discreto cioè trova e tale che g^e=h mod n
				// usa la BRUTE FORCE
					int e = 0;
					BigInteger temp = new BigInteger("1");
					BigInteger contaOp = new BigInteger("0");

					//mentre g^e mod n != h allora e = e+1
					while (temp.mod(n).compareTo(h) != 0 && (e<n.intValue())) {
						//System.out.println("temp = "+temp+" - g = "+g+" - h = "+h+" - e ="+e);
						temp = temp.multiply(g).mod(n);
						e=e+1;
						contaOp = contaOp.add(BigInteger.ONE);
					}
					System.out.println("#potenze modulari = "+contaOp+" --> calcolate in trovaLogaritmoDiscreto BRUTE FORCE");
					return e;
        }
        public static int trovaEsp(BigInteger x,BigInteger n){
				// metodo che restituisce m tale che x^m = 1 mod n
				int m = 2;
				BigInteger contaOp = new BigInteger("0");

	        	while (x.pow(m).mod(n).compareTo(BigInteger.ONE)!=0){ //mentre x^m mod n != 1 aumenta m di 1
		       		m = m+1;
		       		contaOp = contaOp.add(BigInteger.ONE);
		       	}
                         System.out.println("#potenze modulari = "+contaOp+" --> calcolate in trovaEsp BRUTE FORCE");
		       	return m;
		}
		public static int trovaEspConLogDiscr(BigInteger x,BigInteger n){
			// metodo che restituisce m tale che x^m = 1 mod n
			// se ritorna m=0 allora non è stato trovato nessun esponente
			int m = 0;
			int q = 1;
			BigInteger p = new BigInteger("2");
			BigInteger contaOp = new BigInteger("0");

			for (int i=0;i< (n.bitLength()+1) ;i++){//per i = 0, ..., log(n)+1 [che vale circa il numero di cifre di n in base 2]
				//se p è primo allora cerco di risolvere Log di x in base x^p
				//e controllo che il risultato q sia tale che x^pq sia congruo a x mod n
				//in questo caso restituisco m = pq-1
				if (p.isProbablePrime(100)) {
					q = trovaLogaritmoDiscreto(x.pow(p.intValue()),x,n);

					contaOp = contaOp.add(BigInteger.ONE);
					if (x.pow(q*p.intValue()).mod(n).compareTo(x)==0){
						System.out.println("#potenze modulari = "+contaOp+" --> calcolate in trovaEsp BY LOG DISCR");
						return (q*p.intValue()-1);
					}
				}
				p = p.add(BigInteger.ONE);
			}
			return m;
		}

        public static void main(String[] args) {
        	Scanner input = new Scanner(System.in);

        	System.out.println("Inserisci il numero n: ");
        	BigInteger n = new BigInteger(input.nextLine());
        	//variabile che contiene l'esponente da restituire

			System.out.println("Inserisci la base x: ");
        	BigInteger x = new BigInteger(input.nextLine());

        	int m = trovaEspConLogDiscr(x,n);

			System.out.println("l'esponente di "+x+" trovato con l'uso del logaritmo discreto e' m = "+m );
			System.out.println("--------------------------------------------------------");
			m = trovaEsp(x,n);

			System.out.println("l'esponente di "+x+" trovato con la forza bruta e' m = "+m );
  		}
}
