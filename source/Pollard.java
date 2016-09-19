import java.io.*;
import java.lang.Math;
import java.math.BigInteger;
import java.util.Random;

//!!!!NB: per farlo funzionare scrivere nel prompt: java Pollard "numero da fattorizzare"

/**
 * @author <a href="mailto:shurel@etu.info.unicaen.fr">HUREL Samuel</a>
 * @author <a href="mailto:mnaveau@etu.info.unicaen.fr">NAVEAU Mikael</a>
 */

public final class Pollard {
        
        /**
         * Calcul un diviseur premier de l'entier n selon la methode de "Pollard".
         * @param n L'entier dont on cherche un diviseur premier.
         * @return int Un diviseur premier de n.
         */
        public static BigInteger pollard (BigInteger n){
                BigInteger u, v, g;
                Random r = new Random();
                BigInteger s =  new BigInteger(300, r).mod(n);
                
                u = s;
                v = s;
                g = BigInteger.ONE;
                
                //System.out.println("rand="+s);
                
                while ( g.compareTo(BigInteger.ONE) == 0 ){
                        u = ( (u.pow(2)).add(BigInteger.ONE) ).mod(n);
                        v = ( (v.pow(2)).add(BigInteger.ONE) ).mod(n);
                        v = ( (v.pow(2)).add(BigInteger.ONE) ).mod(n);
                        g = pgcd( ( u.subtract(v) ).abs(), n);
                        //System.out.println ("pgcd ("+u+"-"+v+","+n+")="+g);
                }
                
                return g;
        }
        
        /**
         * Calcule le plus grand diviseur commun des deux entiers m et n selon l'algorithme d'Euclide.
         * @return int Le plus grand diviseur commun de m et n.
         */
        public static BigInteger pgcd(BigInteger m, BigInteger n) {
                BigInteger r;
                while ( n.compareTo(BigInteger.ZERO) != 0 ) {
                        r = m.mod(n);
                        m = n;
                        n = r;
                }
    return m;
  }
        
        public static void main(String[] args) {
                if ( args.length != 1 ){
                        System.out.println("Pollard : usage : java Pollard <int>");
                        System.exit(1);
                }
                
                BigInteger input, output;
                input = new BigInteger(args[0]);
                
                System.out.print( args[0] + " = " );
                while ( input.compareTo(BigInteger.ONE) != 0 ){
                        output = pollard(input);
                        System.out.print( output );
                        input = input.divide(output);
                        if ( input.compareTo(BigInteger.ONE) != 0 )
                                System.out.print( "*" );
                        else
                                System.out.println("");
                }
                
  }
}
