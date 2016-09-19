import java.util.Scanner;
import java.math.BigInteger;

//trova l'ordine di un elemento g di Zn

//INPUT:g appartenente a Zn
//OUTPUT:minimo e tale che g^e = 1

//{}

public final class cercaOrdine {

        public static void main(String[] args) {
        	Scanner input = new Scanner(System.in);

        	System.out.print("Inserisci l'elemento di cui vuoi sapere l'ordine, g = ");
        	BigInteger g = new BigInteger(input.nextLine());

        	System.out.print("Inserisci la cardinalita' del gruppo, n =  ");
        	BigInteger n = new BigInteger(input.nextLine());

			BigInteger e = new BigInteger("1");

			while (g.pow(e.intValue()).mod(n).compareTo(BigInteger.ONE)!=0 ) {
				System.out.println("e = "+e);
				e=e.add(BigInteger.ONE);
			}

			System.out.println("ord("+g+") = "+e);
        }
}
