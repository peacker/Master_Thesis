import java.util.Scanner;
import java.math.BigInteger;

////risolve il problema del logaritmo discreto, dati g, h ed n trova l'esponente a tale che g^a=h mod n
//INPUT:g, h, n
//OUTPUT:a

//{}

public final class logaritmoDiscreto {

		public static BigInteger[] walk(BigInteger[] triplastar,BigInteger n,BigInteger g,BigInteger h){
		// metodo che compie il "cammino originale",restituendo i valori del passo successivo di x,a,b

			//assegno il valore 3 alla variabile tre
			BigInteger tre = new BigInteger("3");
			BigInteger[] tripla = new BigInteger[3];

			for (int i=0;i<3;i++) tripla[i] = triplastar[i];

			if (tripla[0].mod(tre).compareTo(BigInteger.ZERO)==0 ){
				tripla[0] = tripla[0].multiply(tripla[0]).mod(n);
				tripla[1] = tripla[1].add(tripla[1]).mod(n.subtract(BigInteger.ONE));
				tripla[2] = tripla[2].add(tripla[2]).mod(n.subtract(BigInteger.ONE));
			}
			else if(tripla[0].mod(tre).compareTo(BigInteger.ONE)==0 ){
				tripla[0] = tripla[0].multiply(g).mod(n);
				tripla[1] = tripla[1].add(BigInteger.ONE).mod(n.subtract(BigInteger.ONE));
			}
			else{
				tripla[0] = tripla[0].multiply(h).mod(n);
				tripla[2] = tripla[2].add(BigInteger.ONE).mod(n.subtract(BigInteger.ONE));
			}

			return tripla;
        }


        public static BigInteger trovaLogaritmoDiscreto(BigInteger g,BigInteger h,BigInteger n){
		// metodo che risolve il logaritmo discreto

			BigInteger temp = new BigInteger("0");

			BigInteger[] triplaLenta = new BigInteger[3];
			BigInteger[] triplaVeloce = new BigInteger[3];
			//contiene il valore xi nella prima casella, ai nella seconda, bi nella terza

			// inizializzo i valori di x1=g,a1=1,b1=0
			triplaLenta[0]=g;			triplaLenta[1]=new BigInteger("1");			triplaLenta[2]=new BigInteger("0");

			// inizializzo i valori di x2,a2,b2=walk(x1,a1,b1)
			for(int i=0;i<3;i++){
				triplaVeloce[i] = walk(triplaLenta,n,g,h)[i];
			}

			for(int i=0;i<3;i++){System.out.print(triplaLenta[i]+" - ");}
			for(int i=0;i<3;i++){System.out.print(triplaVeloce[i]+" - ");}
			System.out.println();

			//eseguo i passi
			while(triplaLenta[0].compareTo(triplaVeloce[0]) != 0){
				triplaLenta = walk(triplaLenta,n,g,h);
				//triplaVeloce = walk(walk(triplaVeloce,n,g,h),n,g,h);
				triplaVeloce = walk(triplaVeloce,n,g,h);
				triplaVeloce = walk(triplaVeloce,n,g,h);
				for(int i=0;i<3;i++){System.out.print(triplaLenta[i]+" - ");}
				for(int i=0;i<3;i++){System.out.print(triplaVeloce[i]+" - ");}
				System.out.println();
			}

			if (triplaLenta[2].mod(n).compareTo(triplaVeloce[2].mod(n))==0) {
				System.out.println("bi e' congruo a bj,metodo fallito! ");
				return BigInteger.ZERO;
			}
			else{
				//restituisce (a2-a1)(b1-b2)^(-1) mod n
				temp = triplaLenta[2].subtract(triplaVeloce[2]);
				System.out.println("b1-b2 =  "+temp );
				temp = temp.modInverse(n);
				System.out.println("(b1-b2)^(-1) =  "+temp );
				temp = triplaVeloce[1].subtract(triplaLenta[1]).multiply(temp);
				System.out.println("(a2-a1)(b1-b2)^(-1) =  "+temp );
				temp = temp.mod(n);
				System.out.println("(a2-a1)(b1-b2)^(-1) mod n =  "+temp );
				return triplaVeloce[1].subtract(triplaLenta[1]).multiply(triplaLenta[2].subtract(triplaVeloce[2]).modInverse(n)).mod(n);
			}
        }


        public static void main(String[] args) {
        	Scanner input = new Scanner(System.in);

        	System.out.println("Inserisci la base g: ");
        	BigInteger g = new BigInteger(input.nextLine());
        	//variabile che contiene la base del logaritmo

        	System.out.println("Inserisci h: ");
        	BigInteger h = new BigInteger(input.nextLine());
        	//variabile che contiene h

           	System.out.println("Inserisci n: ");
			BigInteger n = new BigInteger(input.nextLine());
        	//variabile che contiene la cardinalità dell'anello

        	BigInteger a = trovaLogaritmoDiscreto(g,h,n);

        	System.out.println("Il logaritmo di "+h+" in base "+g+" mod "+n+" e': "+a );
        }
}
