import java.util.Scanner;
import java.math.BigInteger;

////mandando in esecuzione la classe, trova un fattore di n con il metodo del Logaritmo Discreto
// n non deve essere pari e non deve essere una potenza di primi, queste condizioni vanno testate con altri programmi
//versione3: non c'e' piu' la scelta del coprimo, ma viene scelto un elemento di Z/NZ casualmente e se non è coprimo con N,allora avrò trovato un fattore
//inoltre la ricerca dell'esponente avviene cercando il logaritmo discreto con BRUTE FORCE..
//INPUT:n
//OUTPUT:p

//{}

public final class trovaFattoreConLogDiscr3 {

        public static BigInteger trovaLogaritmoDiscreto(BigInteger g,BigInteger h,BigInteger n){
			// metodo che risolve il logaritmo discreto cioè trova e tale che g^e=h mod n
			// usa la BRUTE FORCE
			BigInteger e = new BigInteger("0");
			BigInteger temp = new BigInteger("1");
			BigInteger contaOp = new BigInteger("0");

			//mentre g^e mod n != h allora e = e+1
			while (temp.mod(n).compareTo(h) != 0 && (e.compareTo(n)<0) ) {
				//System.out.println("temp = "+temp+" - g = "+g+" - h = "+h+" - e ="+e);
				temp = temp.multiply(g).mod(n);
				e=e.add(BigInteger.ONE);
				contaOp = contaOp.add(BigInteger.ONE);
			}
			////////System.out.println("#potenze modulari = "+contaOp+" --> calcolate in trovaLogaritmoDiscreto BRUTE FORCE");
			return e;
        }

        public static BigInteger trovaEspConLogDiscr(BigInteger x,BigInteger n){
			// metodo che restituisce m tale che x^m = 1 mod n
			// se ritorna 0 allora non è stato trovato nessun esponente

			BigInteger q = new BigInteger("1");
			BigInteger p = new BigInteger("2");
			BigInteger contaOp = new BigInteger("0");

			System.out.println("Cerco l'esponente di "+x+" mod "+n);

			for (int i=0;i< (n.bitLength()+1) ;i++){//per i = 0, ..., log(n)+1 [che vale circa il numero di cifre di n in base 2]
				//se p è primo allora cerco di risolvere Log di x in base x^p
				//e controllo che il risultato q sia tale che x^pq sia congruo a x mod n
				//in questo caso restituisco pq-1
				System.out.print("p = "+p);
				if (p.isProbablePrime(100)) {
					System.out.println(" che e' primo");

					q = trovaLogaritmoDiscreto(x.pow(p.intValue()),x,n);

					//se x^p^q mod n = x mod n
					if (x.pow(p.intValue()).pow(q.intValue()).mod(n).compareTo(x.mod(n))==0 ){
						System.out.println("q = log di "+x+" in base "+x+"^"+p+" = "+q);
						System.out.println("x^pq mod n = "+x+"^"+p+"*"+q+" mod "+n+" = "+x.pow(q.multiply(p).intValue()).mod(n) );
						if (x.pow(q.multiply(p).intValue()).mod(n).compareTo(x)==0){
							/////////////System.out.println("#potenze modulari = "+contaOp+" --> calcolate in trovaEsp BY LOG DISCR");
							System.out.println("m = pq-1 = "+q.multiply(p).subtract(BigInteger.ONE));
							return (q.multiply(p).subtract(BigInteger.ONE));
						}
					}
					else
						System.out.println("q = log di "+x+" in base "+x+"^"+p+" = NON ESISTE");

					contaOp = contaOp.add(BigInteger.ONE);


				}
				else System.out.println(" che NON e' primo ");
				p = p.add(BigInteger.ONE);
			}
			System.out.println("Non e' stato trovato nessun esponente perciò restituisco m = 0");
			return BigInteger.ZERO;
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

			System.out.println("x = "+x);
			System.out.println("m = "+m);
			System.out.println("k = "+k);
			System.out.println("x^(m/2^k) mod n = "+temp1.mod(n) );
			System.out.println("x^(m/2^k+1) mod n = "+temp2.mod(n) );
			//mentre x^(m/2^(k+1)) mod n != +1 o -1 aumenta k di 1
			while (temp1.mod(n).compareTo(BigInteger.ONE)==0 && (temp2.mod(n).compareTo(BigInteger.ONE)==0 || temp2.mod(n).compareTo(menouno)==0)) {
		    		/////////////System.out.println("temp1 = "+temp1.mod(n));
		    		/////////////System.out.println("temp2 = "+temp2.mod(n));
		    		k = k.add(BigInteger.ONE);
		    		////////////////System.out.println("sono in trova fattore!!");
		    		temp1 = x.pow( /**/m.divide(due.pow(k.intValue())).intValue()/**/ );
					temp2 = x.pow( /**/m.divide(due.pow(k.add(BigInteger.ONE).intValue())).intValue()/**/ );
					System.out.println("k = "+k);
					System.out.println("x^(m/2^k) mod n = "+temp1.mod(n) );
					System.out.println("x^(m/2^k+1) mod n = "+temp2.mod(n) );

		    }
		    p = temp2.add(BigInteger.ONE).gcd(n);
		    System.out.println("p = MCD(x^(m/2^k+1) mod n + 1, n) = MCD("+temp2.add(BigInteger.ONE).mod(n)+", "+n+") = "+p);
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

					//////System.out.println("x = "+x);
					m = trovaEspConLogDiscr(x,n);
					System.out.println("m = "+m);

					p = trovaFattore(n,x,m);
					//////System.out.println("p = "+p);

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
