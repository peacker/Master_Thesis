import java.util.Scanner;
import java.math.BigInteger;

////risolve il problema del logaritmo discreto, dati g, h ed n trova l'esponente a tale che g^a=h mod n
//funziona SOLO SE n E'UN NUMERO PRIMO!!!!!!!!
//INPUT:g, h, n
//OUTPUT:a

//{}

public final class logaritmoDiscretoStatistiche {

		public static BigInteger ordine(BigInteger g,BigInteger p){
			BigInteger e = new BigInteger("1");
			while (g.pow(e.intValue()).mod(p).compareTo(BigInteger.ONE)!=0 ) {e=e.add(BigInteger.ONE);}
			return e;
        }

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


        public static void main(String[] args) {
        	Scanner input = new Scanner(System.in);

        	System.out.print("Inserisci la base --- g = ");
        	BigInteger g = new BigInteger(input.nextLine());
        	//variabile che contiene la base del logaritmo

        	System.out.print("Inserisci --- h =  ");
        	BigInteger h = new BigInteger(input.nextLine());
        	//variabile che contiene h

           	System.out.print("Inserisci un numero primo per definire il campo Fp* --- p = ");
           	BigInteger p = new BigInteger(input.nextLine());
           	//variabile che contiene la cardinalità dell'anello

           	System.out.print("Inserisci il numero di partizioni --- ns = ");
			BigInteger ns = new BigInteger(input.nextLine());
        	//variabile che contiene la cardinalità dell'anello

        	BigInteger[] u = new BigInteger[ns.intValue()];
        	BigInteger[] v = new BigInteger[ns.intValue()];

			BigInteger[] gg;

			//calcolo l'ordine di g in Zp
			BigInteger ord = p.subtract(BigInteger.ONE);
			ord = ordine(g,p);
			System.out.println("l'ordine di "+g+" in Z"+p+" vale: "+ord);

			BigInteger x = new BigInteger("0");
			x = g;
			BigInteger a = new BigInteger("1");
			BigInteger b = new BigInteger("0");

			BigInteger xx = new BigInteger("0");
			BigInteger A = new BigInteger("1");
			BigInteger B = new BigInteger("0");

        	int finito = 1;
        	int numCoppieInvertibili = 0;
        	//per ns = 3
			for (BigInteger k=new BigInteger("1");k.compareTo(ord)!=1;k=k.add(BigInteger.ONE)){
			 for (BigInteger kk=new BigInteger("1");kk.compareTo(ord)!=1;kk=kk.add(BigInteger.ONE)){
			  for (BigInteger kkk=new BigInteger("1");kkk.compareTo(ord)!=1;kkk=kkk.add(BigInteger.ONE)){
			   for (BigInteger kkkk=new BigInteger("1");kkkk.compareTo(ord)!=1;kkkk=kkkk.add(BigInteger.ONE)){
				System.out.println(k+" - "+kk+" - "+kkk+" - "+kkkk);
				finito = 1;

				while(finito==1){
					//inizializzo i valori u[i] e v[i] con tutte le possibili combinazioni da 1 a ord
					u[1]=k;
					v[1]=kk;
					u[2]=kkk;
					v[2]=kkkk;
					//inizializzo i valori g[i] = g^u[i] * h^v[i]
					gg = new BigInteger[ns.intValue()];
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
					}

					if (B.subtract(b).gcd(ord).compareTo(BigInteger.ONE)==0){numCoppieInvertibili++;}
					finito = 0;


				}//fine while

			   }//fine for 4
			  }//fine for 3
			 }//fine for 2
			}//fine for 1
			System.out.println("Il numero di coppie invertibili trovato e': "+numCoppieInvertibili);
			System.out.println("Su un totale di: "+ord.pow(4)+" coppie possibili");
			System.out.println("Percentuale di coppie invertibili su coppie possibili = "+numCoppieInvertibili/ord.pow(4).intValue()*100+" %");
			System.out.print("fine:");
			input.nextLine();
        }
}
