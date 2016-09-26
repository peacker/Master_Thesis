package net.emanuelebellini.master_thesis


import java.util.Scanner;
import java.math.BigInteger;

////mandando in esecuzione la classe, trova un fattore di n con il metodo del Logaritmo Discreto
// n non deve essere pari e non deve essere una potenza di primi, queste condizioni vanno testate con altri programmi
//versione3: non c'e' piu' la scelta del coprimo, ma viene scelto un elemento di Z/NZ casualmente e se non è coprimo con N,allora avrò trovato un fattore
//inoltre la ricerca dell'esponente avviene cercando il logaritmo discreto risolto con RHO!!
//INPUT:n
//OUTPUT:p

//{}

public final class trovaFattoreConLogDiscr4 {

        public static BigInteger fx(BigInteger x,BigInteger[] gg,BigInteger p,BigInteger ns,BigInteger ord){
		// metodo che restituisce x^2 se S(x) = 0, altrimenti restituisce x*gg[S(x)]
				if (S(x,ns).compareTo(BigInteger.ZERO)==0) return x.multiply(x).mod(p);
				else return x.multiply(gg[S(x,ns).intValue()]).mod(p);
			}

			public static BigInteger fa(BigInteger x, BigInteger a,BigInteger[] u,BigInteger p,BigInteger ns,BigInteger ord){
				// metodo che restituisce 2a se S(x) = 0, altrimenti restituisce a+u[S(x)]
				if (S(x,ns).compareTo(BigInteger.ZERO)==0) return a.add(a).mod(ord);//se nn ho l'ordine devo fare tutto mod(p.subtract(BigInteger.ONE))
				else return a.add(u[S(x,ns).intValue()]).mod(ord);//se nn ho l'ordine devo fare tutto mod(p.subtract(BigInteger.ONE))
			}

			//in realtà non e' necessario costruire la funzione fb,poichè e' uguale ad fa ma con i parametri cambiati,la metto per completezza
			public static BigInteger fb(BigInteger x, BigInteger b,BigInteger[] v,BigInteger p,BigInteger ns,BigInteger ord){
				// metodo che restituisce 2a se S(x) = 0, altrimenti restituisce a+u[S(x)]
				if (S(x,ns).compareTo(BigInteger.ZERO)==0) return b.add(b).mod(ord);//se nn ho l'ordine devo fare tutto mod(p.subtract(BigInteger.ONE))
				else return b.add(v[S(x,ns).intValue()]).mod(ord);//se nn ho l'ordine devo fare tutto mod(p.subtract(BigInteger.ONE))
			}

			public static BigInteger S(BigInteger x, BigInteger ns){
			// restituisce il valore di x modulo ns
				return x.mod(ns);
        }
        public static BigInteger ordine(BigInteger g,BigInteger p){
			//metodo che restituisce l'ordine dell'elemento g mod p
			BigInteger e = new BigInteger("1");
			while (g.pow(e.intValue()).mod(p).compareTo(BigInteger.ONE)!=0 ) {e=e.add(BigInteger.ONE);}
			return e;
        }

        public static BigInteger trovaLogaritmoDiscreto(BigInteger g,BigInteger h,BigInteger p){
		//restituisce (a-a')(b'-b)^-1 mod ord se MCD((b'-b),ord)=1,altrimenti restituisce 0

			//g contiene la base del logaritmo
			//h
			//p contiene la cardinalità dell'anello

			BigInteger ns = new BigInteger("4");
			//ns contiene il numero di partizioni del gruppo G

			//calcolo l'ordine di g in Zp
			BigInteger ord = p.subtract(BigInteger.ONE);
			ord = ordine(g,p);

			BigInteger[] u = new BigInteger[ns.intValue()];
			BigInteger[] v = new BigInteger[ns.intValue()];
			BigInteger x = new BigInteger("0");
			BigInteger xx = new BigInteger("0");
			BigInteger a = new BigInteger("0");
			BigInteger A = new BigInteger("0");
			BigInteger b,B;
			b = BigInteger.ONE;
			B = b.add(b);//inizializzo b e B in modo da poter entrare nel ciclo

			//while(b.mod(ord).compareTo(B.mod(ord))!=0 ){
				//inizializzo i valori u[i] e v[i] scelti casualmente a random tali che 0 <= u[i],v[i] < ordine di g
				//questi sono i parametri che rendono f una funzione pseudorandom
				BigInteger ran = new BigInteger("3");
				for (int i=1;i<ns.intValue();i++){
					u[i] = ran.pow(2).mod(ord);
					v[i] = ran;
					ran= ran.pow(2).mod(ord);
				}

				//inizializzo i valori g[i] = g^u[i] * h^v[i]
				BigInteger[] gg = new BigInteger[ns.intValue()];
				for (int i=1;i<ns.intValue();i++){
					gg[i] = g.pow(u[i].intValue()).multiply(h.pow(v[i].intValue())).mod(p);
				}

				x = g;
				a = new BigInteger("1");
				b = new BigInteger("0");

				xx = fx(x,gg,p,ns,ord);
				A = fa(x,a,u,p,ns,ord);
				B = fb(x,b,v,p,ns,ord);

				int i = 1;
				//////System.out.println(i+" --> "+x+" -- "+a+" -- "+b+" -- "+S(x,ns)+" <--> "+xx+" -- "+A+" -- "+B+" -- "+S(xx,ns));
				while (x.compareTo(xx)!=0){

					i++;

					a = fa(x,a,u,p,ns,ord);
					b = fb(x,b,v,p,ns,ord);
					x = fx(x,gg,p,ns,ord);

					A = fa(xx,A,u,p,ns,ord);
					B = fb(xx,B,v,p,ns,ord);
					xx = fx(xx,gg,p,ns,ord);
					//eseguo questo calcolo 2 volte per la successione x2i
					A = fa(xx,A,u,p,ns,ord);
					B = fb(xx,B,v,p,ns,ord);
					xx = fx(xx,gg,p,ns,ord);

					//////////System.out.println(i+" --> "+x+" -- "+a+" -- "+b+" -- "+S(x,ns)+" <--> "+xx+" -- "+A+" -- "+B+" -- "+S(xx,ns));
				}
				/////////////System.out.println("il minimo i tale che xi = x2i vale: "+i);

				//calcolo del logaritmo (a-A)(B-b)^(-1) mod ord //oppure mod p-1
				//////////System.out.println("il logaritmo cercato vale: "+a.subtract(A).multiply(B.subtract(b).modInverse(ord)).mod(ord));

			//}//chiude il while grande
			if (B.subtract(b).gcd(ord).compareTo(BigInteger.ONE)==0)
				return a.subtract(A).multiply(B.subtract(b).modInverse(ord)).mod(ord);
			else
				return BigInteger.ZERO;
		}
        public static BigInteger trovaEspConLogDiscr(BigInteger x,BigInteger n){
			// metodo che restituisce m tale che x^m = 1 mod n
			// se ritorna 0 allora non è stato trovato nessun esponente

			BigInteger q = new BigInteger("1");
			BigInteger p = new BigInteger("2");
			BigInteger contaOp = new BigInteger("0");

			for (int i=0;i< (n.bitLength()+1) ;i++){//per i = 0, ..., log(n)+1 [che vale circa il numero di cifre di n in base 2]
				//se p e' primo allora cerco di risolvere Log di x in base x^p
				//e controllo che il risultato q sia tale che x^pq sia congruo a x mod n
				//in questo caso restituisco pq-1
				if (p.isProbablePrime(100)) {
					q = trovaLogaritmoDiscreto(x.pow(p.intValue()),x,n);

					//se q vale 0 allora il logaritmo di x in base x^p non esiste, quindi passo al p successivo
					if (q.compareTo(BigInteger.ZERO)!=0){
						contaOp = contaOp.add(BigInteger.ONE);
						if (x.pow(q.multiply(p).intValue()).mod(n).compareTo(x)==0){
							/////////////System.out.println("#potenze modulari = "+contaOp+" --> calcolate in trovaEsp BY LOG DISCR");
							return (q.multiply(p).subtract(BigInteger.ONE));
						}
					}
				}
				p = p.add(BigInteger.ONE);
			}
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

			//mentre x^(m/2^(k+1)) mod n != +1 o -1 aumenta k di 1
			while (temp1.mod(n).compareTo(BigInteger.ONE)==0 && (temp2.mod(n).compareTo(BigInteger.ONE)==0 || temp2.mod(n).compareTo(menouno)==0)) {
		    		System.out.println("temp1 = "+temp1.mod(n));
		    		System.out.println("temp2 = "+temp2.mod(n));
		    		k = k.add(BigInteger.ONE);
		    		System.out.println("sono in trova fattore!!");
		    		temp1 = x.pow( /**/m.divide(due.pow(k.intValue())).intValue()/**/ );
					temp2 = x.pow( /**/m.divide(due.pow(k.add(BigInteger.ONE).intValue())).intValue()/**/ );

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
					m = trovaEspConLogDiscr(x,n);

					System.out.println("m = "+m);
					input.nextLine();
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
