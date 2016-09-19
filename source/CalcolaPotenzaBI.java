	import java.util.Scanner;
	
	import java.math.BigInteger;
	
	
	//{}
	public class CalcolaPotenzaBI {
		
	
	    public static void main(String[] args) {
		
	
	
	        Scanner input = new Scanner(System.in);
	
	        System.out.print("Inserisci la base: ");        
	        System.out.println();
	        BigInteger m = new BigInteger(input.nextLine());
	        System.out.print("L'esponente: ");
	        System.out.println();
	        int n = input.nextInt();
	
	        BigInteger ris = m.pow(n);
	        System.out.print(m + " ^ " + n + " = " + ris);
	        System.out.println();
	    }
	
	}
	
