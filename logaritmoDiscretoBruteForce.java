import java.util.Scanner;
import java.math.BigInteger;

////risolve il problema del logaritmo discreto, dati g, h ed n trova l'esponente a tale che g^a=h mod n
//INPUT:g, h, n
//OUTPUT:a

//{}

public final class logaritmoDiscretoBruteForce {

		public static int trovaLogaritmoDiscreto(BigInteger g,BigInteger h,BigInteger n){
			//metodo che risolve il logaritmo discreto cioè trova e tale che g^e=h mod n
			int e = 0;
			BigInteger temp = new BigInteger("1");

			//mentre g^e mod n != h allora e = e+1
			while (temp.mod(n).compareTo(h) != 0 && (e<n.intValue())) {
				System.out.println("temp = "+temp+" - g = "+g+" - h = "+h+" - e ="+e);
				temp = temp.multiply(g).mod(n);
				e=e+1;
			}
			return e;
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

        	int a = trovaLogaritmoDiscreto(g,h,n);

			if (g.pow(a).mod(n).compareTo(h)!=0) System.out.println("Il logaritmo cercato non esiste");
        	else System.out.println("Il logaritmo di "+h+" in base "+g+" mod "+n+" e': "+a );
        }
}
