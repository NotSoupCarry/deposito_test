import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class EsercizioZ3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creiamo due squadre con input manuale
        System.out.print("Inserisci il nome della prima squadra: ");
        String nomeSquadra1 = controlloInputStringhe(scanner); 
        Squadra squadra1 = new Squadra(nomeSquadra1);

        squadra1.inserisciGiocatori(scanner);

        System.out.print("\nInserisci il nome della seconda squadra: ");
        String nomeSquadra2 = controlloInputStringhe(scanner); 
        Squadra squadra2 = new Squadra(nomeSquadra2);

        squadra2.inserisciGiocatori(scanner);

        // Stampo le squadre
        System.out.println("\nSquadre create:");
        squadra1.stampaSquadra();
        squadra2.stampaSquadra();

        // Creo una partita tra le due squadre
        Partita partita = new Partita(squadra1, squadra2);
        partita.giocaPartita();

        scanner.close();
    }

    // #region METODI DI CONTROLLO INPUT
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

    // Metodo per controllare che il ruolo inserito sia valido
    public static String controlloRuolo(Scanner scanner) {
        final String[] RUOLI_VALIDI = {"Portiere", "Difensore", "Centrocampista", "Attaccante"};
        String ruolo;
        boolean ruoloValido = false;

        do {
            System.out.print("Ruolo del giocatore (Portiere, Difensore, Centrocampista, Attaccante): ");
            ruolo = scanner.nextLine().trim();

            // Controlla se il ruolo è tra quelli validi
            for (String validRole : RUOLI_VALIDI) {
                if (ruolo.equalsIgnoreCase(validRole)) {
                    ruoloValido = true;
                    break;
                }
            }

            if (!ruoloValido) {
                System.out.println("Ruolo non valido! Scegli tra Portiere, Difensore, Centrocampista, Attaccante.");
            }

        } while (!ruoloValido);  

        return ruolo;
    }
    // #endregion
}

//#region Classi
// Classe Calciatore
class Calciatore {
    String nome;
    String ruolo;

    // Costruttore
    public Calciatore(String nome, String ruolo) {
        this.nome = nome;
        this.ruolo = ruolo;
    }

    // Metodo per stampare i dettagli del calciatore
    public void stampaDettagli() {
        System.out.println("" + nome + " - " + ruolo);
    }
}

// Classe Squadra
class Squadra {
    String nomeSquadra;
    ArrayList<Calciatore> calciatori;

    // Costruttore con input manuale
    public Squadra(String nomeSquadra) {
        this.nomeSquadra = nomeSquadra;
        this.calciatori = new ArrayList<>();
    }

    // Metodo per inserire manualmente i calciatori
    public void inserisciGiocatori(Scanner scanner) {
        final int NUMERO_GIOCATORI = 1;

        System.out.println("\nInserisci i" + NUMERO_GIOCATORI + "giocatori per la squadra " + nomeSquadra);
        for (int i = 1; i <= NUMERO_GIOCATORI; i++) {
            System.out.print("Nome del giocatore " + i + ": ");
            String nome = EsercizioZ3.controlloInputStringhe(scanner);

            System.out.print("Ruolo del giocatore " + i + " (Portiere, Difensore, Centrocampista, Attaccante): ");
            String ruolo = EsercizioZ3.controlloRuolo(scanner);

            calciatori.add(new Calciatore(nome, ruolo));
        }
    }

    // Metodo per stampare i giocatori della squadra
    public void stampaSquadra() {
        System.out.println("Squadra: " + nomeSquadra);
        for (Calciatore c : calciatori) {
            c.stampaDettagli();
        }
    }
    
}

// Classe Partita
class Partita {
    Squadra squadra1;
    Squadra squadra2;

    // Costruttore
    public Partita(Squadra squadra1, Squadra squadra2) {
        this.squadra1 = squadra1;
        this.squadra2 = squadra2;
    }

    Random random = new Random();

    // Metodo per giocare la partita e randomizzare il risultato
    public void giocaPartita() {
        int golSquadra1 = random.nextInt(6);
        int golSquadra2 = random.nextInt(6);

        System.out.println("\nRISULTATO DELLA PARTITA:");
        System.out.println(squadra1.nomeSquadra + " " + golSquadra1 + " - " + golSquadra2 + " " + squadra2.nomeSquadra);

        // Determinare il vincitore
        if (golSquadra1 > golSquadra2) {
            System.out.println(squadra1.nomeSquadra + " VINCE!");
        } else if (golSquadra1 < golSquadra2) {
            System.out.println(squadra2.nomeSquadra + " VINCE!");
        } else {
            System.out.println("La partita è finita in PAREGGIO!");
        }
    }

}
//#endregion
