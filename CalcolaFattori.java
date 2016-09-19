
import java.util.Scanner;


public class CalcolaFattori {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Inserisci il numero da fattorizzare: ");
        int n = input.nextInt();
        System.out.print("I fattori primi di " + n + " sono: ");

        // per ogni possibile fattore i
        for (int i = 2; i <= n / i; i++) {

            // se i e' un fattore di N, dividi N ripetutamente per i
            while (n % i == 0) {
                System.out.print(i + " ");
                n = n / i;
            }
        }

        // se il più grande fattore trovato appare solo una volta, n > 1
        if (n > 1) System.out.println(n);
        else       System.out.println();
    }
}
