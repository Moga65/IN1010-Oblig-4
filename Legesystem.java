import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Legesystem {
    static IndeksertListe<Legemiddel> legemidler;
    static IndeksertListe<Resept> resepter;
    static IndeksertListe<Lege> leger;
    static IndeksertListe<Pasient> pasienter;

    public Legesystem(){
        legemidler = new IndeksertListe<>();
        resepter = new IndeksertListe<>();
        leger = new IndeksertListe<>();
        pasienter = new IndeksertListe<>();
    }

    public void les_fra_fil(String filnavn){
        File fil = new File(filnavn);

        try (FileReader filReader = new FileReader(fil);
             BufferedReader bufReader = new BufferedReader(filReader)) {

            String linje;
            while ((linje = bufReader.readLine()) != null) {
                if (linje.startsWith("#") || linje.trim().isEmpty()) {
                    continue;
                }

                String[] verdier = linje.split(",");
                

                if (linje.contains("Pasienter")) {
                    String navn = verdier[0].trim();
                    String fnr = verdier[1].trim();
                  
                } else if (linje.contains("Legemidler")) {
                    String navn = verdier[0].trim();
                    String type = verdier[1].trim();
                    double pris = Double.parseDouble(verdier[2].trim());
                    double virkestoff = Double.parseDouble(verdier[3].trim());
                } 

                else if (linje.contains("Leger")) {
                    String navn = verdier[0].trim();
                    int kontrollid = Integer.parseInt(verdier[1].trim());
                }

                else if (linje.contains("Resepter")) {
                    int legemiddelNummer = Integer.parseInt(verdier[0].trim());
                    String legeNavn = verdier[1].trim();
                    int pasientID = Integer.parseInt(verdier[2].trim());
                    String type = verdier[3].trim();
                }
            }

        } 
        catch (IOException e) {
            System.err.println("Feil ved lesing av fil: " + e.getMessage());
        }
    }

    public void hovedprogramm(){
        Scanner input = new Scanner(System.in);
        boolean neste = true;

        while (neste){
            System.out.println("Velg et tall: ");
            System.out.println("1. Skriv ut oversikt");
            System.out.println("2. Legg til elementer");
            System.out.println("3. Bruk en resept");
            System.out.println("4. Statistikk");
            System.out.println("5. Skriv data til fil");
            System.out.println("6. Avslutt systemet!");

            int svar = input.nextInt();
            input.nextLine();
            switch (svar) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    neste = false;
                    break;
                default:
                    System.out.println("Ugyldig valg, prøv igjen.");
        }}
    }

    public static void main(String[] args) {
        String filnavn = "legedata.txt";
        Legesystem legesystem = new Legesystem();
        legesystem.les_fra_fil(filnavn);
        legesystem.hovedprogramm();
    }
    }

