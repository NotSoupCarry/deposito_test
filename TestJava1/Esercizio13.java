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
        System.out.println("\n*** BENVENUTO SU AMAZON 3 ***");
        while (true) {
            // Menu principale
            System.out.println("\n1. Registrati");
            System.out.println("2. Login");
            System.out.println("3. Esci");
            System.out.print("Scelta: ");
            int scelta = controlloInputInteri(scanner); // Chiede la scelta dell'utente

            switch (scelta) {
                case 1:
                    registrazione(scanner); // Registrazione dell'utente
                    break;
                case 2:
                    login(scanner); // Login dell'utente
                    break;
                case 3:
                    System.out.println("THE END, ciaooo...");
                    return; // Esce dal programma
                default:
                    System.out.println("Scelta non valida!"); // Gestisce la scelta non valida
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
        } while (valore < 0); // Continua a chiedere se il valore è negativo
        return valore; // Restituisce l'input valido
    }

    // Metodo per controllare che l'input stringa non sia vuoto
    public static String controlloInputStringhe(Scanner scanner) {
        String valore;
        do {
            valore = scanner.nextLine().trim(); // Rimuove gli spazi superflui
            if (valore.isEmpty()) {
                System.out.print("Input non valido. Inserisci un testo: ");
            }
        } while (valore.isEmpty()); // Continua a chiedere se la stringa è vuota
        return valore; // Restituisce l'input valido
    }

    // Metodo per gestire l'input "si" o "no"
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

    // Metodo per controllare l'input della password
    private static String controllaPassword(Scanner scanner) {
        String password;
        boolean passwordValida = false;

        do {
            System.out.print("(almeno 3 caratteri e un maiuscolo): ");
            password = scanner.nextLine();

            // Controlla la lunghezza della password
            if (password.length() < 3) {
                System.out.println("La password deve essere lunga almeno 3 caratteri.");
                continue; // Torna all'inizio del ciclo
            }

            // Controlla se c'è almeno un carattere maiuscolo
            boolean contieneMaiuscola = false;
            for (char c : password.toCharArray()) {
                if (Character.isUpperCase(c)) {
                    contieneMaiuscola = true;
                    break;
                }
            }

            if (!contieneMaiuscola) {
                System.out.println("La password deve contenere almeno un carattere maiuscolo.");
                continue; // Torna all'inizio del ciclo
            }

            // Se tutti i controlli sono superati, la password è valida
            passwordValida = true;

        } while (!passwordValida); // Ripete finché la password non è valida

        return password; // Restituisce la password valida
    }

    // Metodo per registrare un nuovo utente
    private static void registrazione(Scanner scanner) {
        System.out.print("Inserisci nome: ");
        String nome = controlloInputStringhe(scanner); // Controlla il nome dell'utente

        System.out.print("Inserisci password");
        String pass = controllaPassword(scanner); // Controlla la password dell'utente

        System.out.print("Scegli la tua domanda segreta: ");
        String domanda = controlloInputStringhe(scanner); // Controlla la domanda segreta

        System.out.print("Risposta alla domanda segreta: ");
        String risposta = controlloInputStringhe(scanner); // Controlla la risposta alla domanda segreta

        // Aggiungi i dati agli ArrayList
        nomi.add(nome);
        password.add(pass);
        domandeSegrete.add(domanda);
        risposteSegrete.add(risposta);

        System.out.println("Registrazione completata!"); // Conferma della registrazione
    }

    // Metodo per il login dell'utente
    private static void login(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = controlloInputStringhe(scanner); // Controlla il nome inserito

        System.out.print("Password");
        String pass = controllaPassword(scanner); // Controlla la password inserita

        int indice = -1;
        // Verifica se le credenziali sono corrette
        for (int i = 0; i < nomi.size(); i++) {
            if (nomi.get(i).equals(nome) && password.get(i).equals(pass)) {
                indice = i;
                break; // Esce dal ciclo se trova una corrispondenza
            }
        }

        if (indice == -1) {
            System.out.println("Credenziali non valide!"); // Se le credenziali non sono corrette
            return;
        }

        System.out.println("Login effettuato con successo!"); // Se il login è riuscito
        gestisciProfilo(scanner, indice); // Gestisce il profilo dell'utente
    }

    // Metodo per gestire le modifiche del profilo
    private static void gestisciProfilo(Scanner scanner, int indice) {
        boolean exitGestioneProfilo = false;
        while (!exitGestioneProfilo) {
            // Menu di gestione del profilo
            System.out.println("\n1. Modifica nome");
            System.out.println("2. Modifica password");
            System.out.println("3. Esci");
            System.out.print("Scelta: ");
            int scelta = controlloInputInteri(scanner); // Controlla la scelta dell'utente

            switch (scelta) {
                case 1:
                    modificaNome(scanner, indice); // Modifica del nome
                    break;
                case 2:
                    modificaPassword(scanner, indice); // Modifica della password
                    break;
                case 3:
                    exitGestioneProfilo = true; // Esce dalla gestione del profilo
                    return;
                default:
                    System.out.println("Scelta non valida!"); // Gestisce la scelta non valida
            }
        }
    }

    // Metodo per modificare il nome dell'utente
    private static void modificaNome(Scanner scanner, int indice) {
        System.out.print("Nuovo nome: ");
        nomi.set(indice, scanner.nextLine()); // Aggiorna il nome
        System.out.println("Nome modificato con successo!"); // Conferma della modifica
    }

    // Metodo per modificare la password dell'utente
    private static void modificaPassword(Scanner scanner, int indice) {
        System.out.println("Domanda segreta: " + domandeSegrete.get(indice)); // Mostra la domanda segreta
        System.out.print("Risposta: ");
        String risposta = controlloInputStringhe(scanner); // Controlla la risposta

        if (!risposta.equals(risposteSegrete.get(indice))) {
            System.out.println("Risposta errata!"); // Se la risposta non è corretta
            return;
        }

        System.out.print("Nuova password");
        password.set(indice, controllaPassword(scanner)); // Modifica la password

        // Chiede se l'utente vuole cambiare anche la domanda segreta
        System.out.print("Vuoi cambiare anche la domanda segreta? (si/no): ");
        String scelta = controlloSiNo(scanner);

        if (scelta.equals("si")) {
            System.out.print("Nuova domanda segreta: ");
            domandeSegrete.set(indice, controlloInputStringhe(scanner)); // Cambia la domanda segreta

            System.out.print("Nuova risposta segreta: ");
            risposteSegrete.set(indice, controlloInputStringhe(scanner)); // Cambia la risposta segreta
        }

        System.out.println("Password (e domanda segreta) modificate con successo!"); // Conferma della modifica
    }
}
