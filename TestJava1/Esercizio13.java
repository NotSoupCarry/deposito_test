import java.util.ArrayList;
import java.util.Scanner;

public class Esercizio13 {
    // ArrayList per memorizzare i dati degli utenti
    private static ArrayList<String> nomi = new ArrayList<>();
    private static ArrayList<String> password = new ArrayList<>();
    private static ArrayList<String> domandeSegrete = new ArrayList<>();
    private static ArrayList<String> risposteSegrete = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Registrati");
            System.out.println("2. Login");
            System.out.println("3. Esci");
            System.out.print("Scelta: ");
            int scelta = controlloInputInteri(scanner);

            switch (scelta) {
                case 1:
                    registrazione(scanner);
                    break;
                case 2:
                    login(scanner);
                    break;
                case 3:
                    System.out.println("Uscita...");
                    return;
                default:
                    System.out.println("Scelta non valida!");
            }
        }
    }

    // Metodo per controllare l'input intero
    public static int controlloInputInteri(Scanner scanner) {
        int valore;
        do {
            while (!scanner.hasNextInt()) {
                System.out.print("Devi inserire un numero intero. Riprova: ");
                scanner.next();
            }
            valore = scanner.nextInt();
            scanner.nextLine();
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

    private static String controlloSiNo(Scanner scanner) {
        String input;
        while (true) {
            input = scanner.nextLine().toLowerCase(); // Legge l'input e lo converte in minuscolo
            if (input.equals("si") || input.equals("no")) {
                break; // Esce dal ciclo se l'input è valido
            } else {
                System.out.print("Input non valido. Inserisci 'si' o 'no': ");
            }
        }
        return input; // Restituisce l'input corretto
    }

    private static void registrazione(Scanner scanner) {
        System.out.print("Inserisci nome: ");
        String nome = controlloInputStringhe(scanner);

        System.out.print("Inserisci password: ");
        String pass = controlloInputStringhe(scanner);

        System.out.print("Scegli la tua domanda segreta: ");
        String domanda = controlloInputStringhe(scanner);

        System.out.print("Risposta alla domanda segreta: ");
        String risposta = controlloInputStringhe(scanner);

        // Aggiungi i dati agli ArrayList
        nomi.add(nome);
        password.add(pass);
        domandeSegrete.add(domanda);
        risposteSegrete.add(risposta);

        System.out.println("Registrazione completata!");
    }

    private static void login(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = controlloInputStringhe(scanner);

        System.out.print("Password: ");
        String pass = controlloInputStringhe(scanner);

        int indice = -1;
        for (int i = 0; i < nomi.size(); i++) {
            if (nomi.get(i).equals(nome) && password.get(i).equals(pass)) {
                indice = i;
                break;
            }
        }

        if (indice == -1) {
            System.out.println("Credenziali non valide!");
            return;
        }

        System.out.println("Login effettuato con successo!");
        gestisciProfilo(scanner, indice);
    }

    private static void gestisciProfilo(Scanner scanner, int indice) {
        boolean exitGestioneProfilo = false;
        while (!exitGestioneProfilo) {
            System.out.println("\n1. Modifica nome");
            System.out.println("2. Modifica password");
            System.out.println("3. Esci");
            System.out.print("Scelta: ");
            int scelta = controlloInputInteri(scanner);

            switch (scelta) {
                case 1:
                    modificaNome(scanner, indice);
                    break;
                case 2:
                    modificaPassword(scanner, indice);
                    break;
                case 3:
                    exitGestioneProfilo = true;
                    return;
                default:
                    System.out.println("Scelta non valida!");
            }
        }
    }

    private static void modificaNome(Scanner scanner, int indice) {
        System.out.print("Nuovo nome: ");
        nomi.set(indice, scanner.nextLine());
        System.out.println("Nome modificato con successo!");
    }

    private static void modificaPassword(Scanner scanner, int indice) {
        System.out.println("Domanda segreta: " + domandeSegrete.get(indice));
        System.out.print("Risposta: ");
        String risposta = controlloInputStringhe(scanner);

        if (!risposta.equals(risposteSegrete.get(indice))) {
            System.out.println("Risposta errata!");
            return;
        }

        System.out.print("Nuova password: ");
        password.set(indice, controlloInputStringhe(scanner));

        System.out.print("Vuoi cambiare anche la domanda segreta? (si/no): ");
        String scelta = controlloSiNo(scanner);

        if (scelta.equals("si")) {
            System.out.print("Nuova domanda segreta: ");
            domandeSegrete.set(indice, controlloInputStringhe(scanner));

            System.out.print("Nuova risposta segreta: ");
            risposteSegrete.set(indice, controlloInputStringhe(scanner));
        }

        System.out.println("Password (e domanda segreta) modificate con successo!");
    }
}