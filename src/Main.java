import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Želiš li dodati polaznika? (Da/Ne)");
        String odgovor = sc.nextLine();

        if (odgovor.equalsIgnoreCase("Da")) {
            System.out.println("Unesi ime polaznika:");
            String ime = sc.nextLine();

            System.out.println("Unesi prezime polaznika:");
            String prezime = sc.nextLine();

            Povezivanje p = new Povezivanje();
            try {
                p.unesiPolaznika(1, ime, prezime);
                System.out.println("Polaznik uspješno dodan!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Nema unosa polaznika.");
        }
        System.out.println("Želiš li dodati novi program obrazovanja? (Da/Ne)");
        Povezivanje p = new Povezivanje();
        String odgovor2 = sc.nextLine();

        if (odgovor2.equalsIgnoreCase("Da")) {
            try {
                System.out.println("Unesi ID programa obrazovanja:");
                int programID = Integer.parseInt(sc.nextLine());

                System.out.println("Unesi naziv programa obrazovanja:");
                String naziv = sc.nextLine();

                System.out.println("Unesi broj CSVET bodova:");
                int csvet = Integer.parseInt(sc.nextLine());

                p.unesiProgram(programID, naziv, csvet);

                System.out.println("Program obrazovanja uspješno dodan!");
            } catch (SQLException e) {
                System.out.println("Greška prilikom unosa programa: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Neispravan unos broja, pokušaj ponovno.");
            }
        } else {
            System.out.println("Nema unosa novog programa obrazovanja.");
        }

        System.out.println("Želite li upisati polaznika na program obrazovanja? (Da/Ne)");
        String odgovor3 = sc.nextLine();

        if (odgovor3.equalsIgnoreCase("Da")) {
            try {
                System.out.println("Unesite ID upisa:");
                int idUpisa = Integer.parseInt(sc.nextLine());
                System.out.println("Unesite ID polaznika:");
                int idPolaznik = Integer.parseInt(sc.nextLine());

                System.out.println("Unesite ID programa obrazovanja:");
                int idProgram = Integer.parseInt(sc.nextLine());

                p.upisiPolaznikaNaProgram(idUpisa, idPolaznik, idProgram);

                System.out.println("Polaznik je uspješno upisan na program!");
            } catch (SQLException e) {
                System.out.println("Greška prilikom upisa polaznika na program: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Neispravan unos broja, pokušaj ponovno.");
            }
        } else {
            System.out.println("Nema upisa polaznika na program.");
        }
        System.out.println("Želite li prebaciti polaznika iz jednog programa obrazovanja u drugi? (Da/Ne)");
        String odgovor4 = sc.nextLine();

        if (odgovor4.equalsIgnoreCase("Da")) {
            try {
                System.out.println("Unesite ID polaznika:");
                int idPolaznik = Integer.parseInt(sc.nextLine());

                System.out.println("Unesite ID trenutnog programa obrazovanja:");
                int stariProgramID = Integer.parseInt(sc.nextLine());

                System.out.println("Unesite ID novog programa obrazovanja:");
                int noviProgramID = Integer.parseInt(sc.nextLine());

                p.prebaciPolaznika(idPolaznik, stariProgramID, noviProgramID);

                System.out.println("Polaznik je uspješno prebačen u novi program!");
            } catch (SQLException e) {
                System.out.println("Greška prilikom prebacivanja polaznika: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Neispravan unos broja, pokušaj ponovno.");
            }
        } else {
            System.out.println("Nema prebacivanja polaznika.");
        }
        System.out.println("Želite li ispisati polaznike za određeni program obrazovanja? (Da/Ne)");
        String odgovor5 = sc.nextLine();

        if (odgovor5.equalsIgnoreCase("Da")) {
            try {
                System.out.println("Unesite ID programa obrazovanja:");
                int programID = Integer.parseInt(sc.nextLine());
                System.out.println("\n=== Popis polaznika za program ID " + programID + " ===");
                p.ispisiPolaznikeZaProgram(programID);
            } catch (NumberFormatException e) {
                System.out.println("Neispravan unos broja, pokušajte ponovno.");
            } catch (SQLException e) {
                System.out.println("Greška pri dohvaćanju podataka: " + e.getMessage());
            }
        } else {
            System.out.println("Ispis polaznika otkazan.");
        }
    }
}
