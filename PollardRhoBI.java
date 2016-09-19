import java.util.Scanner;
import java.math.BigInteger;
////mandando in esecuzione la classe, viene chiesto di inserire il numero da fattorizzare n, la base a e il limite l fino a cui eseguire la ricerca.Stampa il primo fattore trovato utilizzando il metodo Rho di Pollar
//{}
public final class PollardRhoBI {
		//metodo che restituisce 1 se il numero dato in input è primo
		/*public static int isPrimeBI (BigInteger p){
				if (p.compareTo(BigInteger.ONE)==0) return 0;
        		BigInteger i = new BigInteger("2");
        		while (i.compareTo(p.divide(i)) != 1){ //mentre i<n/i
        			if (p.remainder(i).compareTo(BigInteger.ZERO) == 0) return 0;//se p/i ha resto 0
        			i = i.add(BigInteger.ONE);
        		}
        		return 1;
        }

        public static int isPrime (int p){
        		int i = 2;
        		if (p==1) return 0;
        		while (i<=p/i){ //mentre i<n/i
        			if (p%i == 0) return 0;//se p/i ha resto 0
        			i++;
        		}
        		return 1;
        }
        //ritorna q se p = q^e è la potenza di un primo, altrimenti ritorna 4
        public static int isPowerOfPrime (int p){
        		int controllo = p;
        		for (int k=2;k<=p;k++){
        			while (p%k==0){
        				p = p/k;
        			}
        			if (p==1)return k;
        			else if (controllo > p) return 4;
        		}
        		return 4;
        }*/



        //restituisce il valore della funzione x^2 + a
        public static BigInteger Funzione(BigInteger x, BigInteger a,BigInteger n){
        	x = x.pow(2).add(a).remainder(n);

        	return x;
        }

        //metodo che ritorna il primo fattore trovato di num usando il metodo Rho di pollard
        //se non è stato trovato alcun fattore allora ritorna num
        public static BigInteger calcolaPrimoFattorePollardRho(BigInteger n,BigInteger a, BigInteger s){
        	BigInteger u ;
        	BigInteger v ;
        	BigInteger g = new BigInteger("1");
        	u = s;
        	v = s;

        	while (g.compareTo(BigInteger.ONE) == 0){
        		u = Funzione(u,a,n);
        		System.out.print("valori u = " + u);
        		v = Funzione(Funzione(v,a,n),a,n);
        		System.out.print(" | v = " + v);
        		g = n.gcd(u.subtract(v).abs());
        		System.out.println(" | g = " + g);
        	}
        	return g;
        }

        public static void main(String[] args) {
        	Scanner input = new Scanner(System.in);

        	System.out.println("Inserisci il numero N da fattorizzare: ");
        	BigInteger n = new BigInteger(input.nextLine());
        	System.out.println("Inserisci il parametro a: ");
        	BigInteger a = new BigInteger(input.nextLine());
        	System.out.println("Inserisci un valore s da cui cominciare la ricerca: ");
        	BigInteger s = new BigInteger(input.nextLine());

        	BigInteger fattoreTrovato = calcolaPrimoFattorePollardRho(n,a,s);
        	System.out.println( "il primo fattore trovato di "+ n + " e' "+ fattoreTrovato);
        	while (n.compareTo(fattoreTrovato)==0){
        		System.out.println("Inserisci un nuovo parametro a: ");
	        	a = new BigInteger(input.nextLine());
        		System.out.println("Inserisci un nuovo valore s da cui cominciare la ricerca: ");
        		s = new BigInteger(input.nextLine());
        		fattoreTrovato = calcolaPrimoFattorePollardRho(n,a,s);
        		System.out.println( "il primo fattore trovato di "+ n + " e' "+ fattoreTrovato);
        	}
  		}
}
