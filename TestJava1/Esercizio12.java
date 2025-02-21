import java.util.Scanner;

public class Esercizio12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Chiedi all'utente di inserire l'intervallo
        System.out.print("Inserisci il limite inferiore dell'intervallo: ");
        int inizio = controlloInputInteri(scanner);
        System.out.print("Inserisci il limite superiore dell'intervallo: ");
        int fine = controlloInputInteri(scanner);

        // Inizializza la somma
        int somma = 0;

        // Stampa i numeri pari e calcola la somma
        System.out.println("Numeri pari nell'intervallo tra " + inizio + " e " + fine + ":");
        for (int i = inizio; i <= fine; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
                somma += i;
            }
        }

        // Stampa la somma finale
        System.out.println("La somma dei numeri pari è: " + somma);

        scanner.close();
    }

    // Metodo per controllare l'input intero e assicurarsi che sia un numero non negativo
    public static int controlloInputInteri(Scanner scanner) {
        int valore;
        do {
            while (!scanner.hasNextInt()) {
                System.out.print("Devi inserire un numero intero. Riprova ");
                scanner.next();
            }
            valore = scanner.nextInt();
            if (valore < 0) {
                System.out.print("Il numero non può essere negativo. Riprova: ");
            }
        } while (valore < 0);
        return valore;
    }
}
