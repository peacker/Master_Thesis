//package master_thesis;


import java.util.Scanner;
import java.math.BigInteger;
////mandando in esecuzione la classe, viene chiesto di inserire il numero da fattorizzare n, la base a e il limite l fino a cui eseguire la ricerca.Stampa il primo fattore trovato utilizzando il metodo di Pollar p-1.
//{}
public final class PollardPmeno1BI {
		//metodo che restituisce 1 se il numero dato in input è primo
		public static int isPrimeBI (BigInteger p){
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
        }

        /*non utilizzato
        //metodo che calcola il mcm di tutti i numeri fino al limite inserito in input
        public static BigInteger mcmfinoa(BigInteger L){
                BigInteger mcm = new BigInteger("1");
                BigInteger i = new BigInteger("2");
                //BigInteger m = new BigInteger("1");
                while (i.compareTo(L) != 1){
                	//moltiplico mcm per 1 se MCD(mcm,i)=i
                	//               per p se i = p^e cioe' se MCD(mcm,i)=p^(e-1)
                	mcm = mcm.multiply(i.divide(i.gcd(mcm)));
                	i = i.add(BigInteger.ONE);
                }
                return mcm;
        }*/

        //crea la lista 2 3 2 5 7 2 3 11 13 2 17 19 ... fino al numero int l (compreso se è una potenza di primi) e
        //ritorna il riferimento a questa lista
        public static BigInteger[] creaListaPrimiEPotenzeMinoriDi(int l){
        	//Scanner input = new Scanner(System.in);

        	BigInteger[] lista = new BigInteger[l];//la lista contenente i numeri primi e le potenze di primi minori di l sarà lunga meno di l in realtà
        	int i = 2; //primo numero da inserire nella lista
        	int prov;
        	int indiceLista = 0;
        	while (i<=l){
        		prov = isPowerOfPrime(i);
        		if (isPrime(prov)==1){
        			 lista[indiceLista] = new BigInteger(String.valueOf(prov));
        			 indiceLista++;
        		}
        		i++;
        	}
        	return lista;
        }

        //metodo che ritorna il primo fattore trovato di num usando il metodo p-1 di pollard
        //se non è stato trovato alcun fattore allora ritorna 1 o N
        public static BigInteger calcolaPrimoFattorePollardPmeno1(BigInteger num,BigInteger base, int lim,BigInteger[] listaPotenzePrimi){
        	BigInteger[] b = new BigInteger[listaPotenzePrimi.length];//+1 ??
        	int i = 0;
        	b[i] = base;
        	while (i<listaPotenzePrimi.length && listaPotenzePrimi[i]!=null){
        		if (num.gcd(b[i].subtract(BigInteger.ONE)).compareTo(BigInteger.ONE)==0){ // se MCD(num,b[i]^listaPotenzePrimi[i] mod num) = 1
        			b[i+1]=b[i].modPow(listaPotenzePrimi[i],num);
        			i++;
        		}
        		else {
        			System.out.println("numero di passi = "+i);
        			System.out.println( "MCD( "+ num + " , " +b[i].subtract(BigInteger.ONE)+" ) = "+num.gcd(b[i].subtract(BigInteger.ONE)) );
        			return num.gcd(b[i].subtract(BigInteger.ONE));
        		}
        	}
        	System.out.println( "MCD( "+ num + " , " +b[i].subtract(BigInteger.ONE)+" ) = "+num.gcd(b[i].subtract(BigInteger.ONE)) );
        	return num.gcd(b[i].subtract(BigInteger.ONE));
        }

        public static void main(String[] args) {
        	Scanner input = new Scanner(System.in);

        	System.out.println("Inserisci il numero N da fattorizzare: ");
        	BigInteger n = new BigInteger(input.nextLine());
        	System.out.println("Inserisci la base a: ");
        	BigInteger a = new BigInteger(input.nextLine());
        	System.out.println("Inserisci un limite L (non puo' essere un BigInteger): ");
        	int l = input.nextInt();

        	int fallito = 1;//vale 1 se il metodo e' fallito, 0 se ha avuto successo
        	BigInteger[] lista = creaListaPrimiEPotenzeMinoriDi(l);
        	//calcola il primo fattore
        	while (fallito==1){
        		BigInteger primoFattore = calcolaPrimoFattorePollardPmeno1(n,a,l,lista);
        		System.out.print( "il primo fattore trovato di "+ n + " e' "+ primoFattore);
        		System.out.println();
        		/*if (isPrimeBI(primoFattore)==0) System.out.println(" che non è primo");
        		else {
        			System.out.println(" che è primo");
        			return;
        		}*/
        		//se n non viene scomposto il metodo fallisce e viene suggerito di riprovare con un nuovo a
        		if (primoFattore.compareTo(n)==0){
        			System.out.println("Il metodo ha fallito, riprova con un a diverso...");
        			System.out.println("Inserisci una nuova base a: ");
        			a = input.nextBigInteger();
        			//a = a.add(BigInteger.ONE);
        			//System.out.println("Sto provando con a =  "+a);
        		}
        		//se n non viene scomposto il metodo fallisce e viene suggerito di riprovare con un nuovo l
        		else if (primoFattore.compareTo(BigInteger.ONE)==0){
				    System.out.println("Il metodo ha fallito, riprova con un L diverso...");
				    System.out.println("Inserisci un nuovo limite L: ");
				    l = input.nextInt();
				    lista = creaListaPrimiEPotenzeMinoriDi(l);
				}
        		else fallito = 0;
        	}
  		}
}
