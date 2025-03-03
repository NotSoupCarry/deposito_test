import java.util.ArrayList;
import java.util.Scanner;

public class EsercizioZ1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();

        // Aggiunta di 3 libri predefiniti
        biblioteca.aggiungiLibro(new Libro("Il Signore degli Anelli", "J.R.R. Tolkien", 2200));
        biblioteca.aggiungiLibro(new Libro("1984", "George Orwell", 120));
        biblioteca.aggiungiLibro(new Libro("Il cacciatore di aquiloni", "Non mi ricordo", 340));

        // Stampiamo i libri iniziali
        System.out.println("Libri in biblioteca:");
        biblioteca.stampaTuttiLibri();

        // Chiedo all'utente di aggiungere un libro
        System.out.println("\nAggiungi un nuovo libro alla biblioteca:");

        System.out.print("Inserisci il titolo: ");
        String titolo = controlloInputStringhe(scanner);

        System.out.print("Inserisci l'autore: ");
        String autore = controlloInputStringhe(scanner);

        System.out.print("Inserisci il numero di pagine: ");
        int nrPagine = controlloInputInteri(scanner);

        // aggiunta del libro inserito dall'utente
        biblioteca.aggiungiLibro(new Libro(titolo, autore, nrPagine));

        // Stampa di tutti i libri dopo l'aggiunta dell'utente
        System.out.println("\nLista aggiornata dei libri in biblioteca:");
        biblioteca.stampaTuttiLibri();

        scanner.nextLine();

        // cercare un libro per titolo
        System.out.println("\nCerca un libro per titolo:");
        System.out.print("Inserisci il titolo del libro che vuoi cercare: ");
        String titoloCercato = controlloInputStringhe(scanner);
        biblioteca.cercaPerTitolo(titoloCercato);

        scanner.close();
    }

    // #region METODI PER IL CONTROLLO DEGLI INPUT
    // Metodo per controllare l'input intero
    public static Integer controlloInputInteri(Scanner scanner) {
        Integer valore;
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

    // Metodo per controllare che l'input stringa non sia vuoto
    public static String controlloInputStringhe(Scanner scanner) {
        String valore;
        do {
            valore = scanner.nextLine().trim();
            if (valore.isEmpty()) {
                System.out.print("Input non valido. Inserisci un testo: ");
            }
        } while (valore.isEmpty());
        return valore;
    }
    //#endregion
}

// Classe Libro
class Libro {
    String nome;
    String autore;
    int nrPagine;

    // Costruttore
    public Libro(String nome, String autore, int nrPagine) {
        this.nome = nome;
        this.autore = autore;
        this.nrPagine = nrPagine;
    }

    // Metodo per stampare i dettagli del libro
    public void stampaDettagli() {
        System.out.println("\nTitolo: " + nome + ", Autore: " + autore + ", Pagine: " + nrPagine);
    }
}

// Classe Biblioteca
class Biblioteca {
    private ArrayList<Libro> libri; // Lista per memorizzare i libri

    // Costruttore
    public Biblioteca() {
        this.libri = new ArrayList<>();
    }

    // Metodo per aggiungere un libro alla biblioteca
    public void aggiungiLibro(Libro libro) {
        libri.add(libro);
    }

    // Metodo per stampare tutti i libri
    public void stampaTuttiLibri() {
        System.out.println("Lista dei libri in biblioteca:");
        for (Libro libro : this.libri) {
            libro.stampaDettagli();
        }
    }

    // Metodo per cercare un libro per titolo
    public void cercaPerTitolo(String titolo) {
        boolean trovato = false;
        for (Libro libro : libri) {
            if (libro.nome.equalsIgnoreCase(titolo)) {
                System.out.println("\nLibro trovato!");
                libro.stampaDettagli();
                trovato = true;
                break; // Esce dal ciclo appena trova il libro
            }
        }
        if (!trovato) {
            System.out.println("\nLibro non trovato.");
        }
    }
}
