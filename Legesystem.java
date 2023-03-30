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
            int modus = 0;
            while ((linje = bufReader.readLine()) != null) {
                if(linje.startsWith("#")){
                    modus++;
                    linje = bufReader.readLine();
                    System.out.println("Modus: " + modus);
                }

                if(modus == 1){
                /// System.out.println(linje);
                String [] verdier = linje.split(",");
                Pasient pass = new Pasient(verdier[0], verdier[1]);
                System.out.println(pass);
                pasienter.leggTil(pass);
                }
                
                if(modus == 2){
                    String [] verdier = linje.split(",");
                    if(verdier[1].equals("narkotisk")){
                        Narkotisk nark = new Narkotisk(verdier[0], Integer.parseInt(verdier[2]), Double.parseDouble(verdier[3]), Integer.parseInt(verdier[4]));
                        legemidler.leggTil(nark);
                        System.out.println(nark);
                    }
                    else if(verdier[1].equals("vanedannende")){
                        Vanedannende vanedannende = new Vanedannende(verdier[0], Integer.parseInt(verdier[2]), Double.parseDouble(verdier[3]), Integer.parseInt(verdier[4]));
                        legemidler.leggTil(vanedannende);
                        System.out.println(vanedannende);
                    }
                    else if(verdier[1].equals("vanlig")){
                        Vanlig vanlig = new Vanlig(verdier[0], Integer.parseInt(verdier[2]), Double.parseDouble(verdier[3]));
                        legemidler.leggTil(vanlig);
                        System.out.println(vanlig);
                    }
                    else{
                        System.out.println("Feil i legemiddel");
                    }
                }
                
                if(modus == 3){
                    //System.out.println(linje);
                    String [] verdier = linje.split(",");
                    if(verdier[1].equals("0")){
                        Lege lege = new Lege(verdier[0]);
                        leger.leggTil(lege);
                        System.out.println(lege);
                    }
                    else if(!verdier[1].equals("spesialist")){
                        Spesialist spesialist = new Spesialist(verdier[0], (verdier[1]));
                        leger.leggTil(spesialist);
                        System.out.println(spesialist);
                    }
                    else{
                        System.out.println("Feil i lege"); 
                    }

                }
               
                if(modus == 4){
                    //Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])
                    System.out.println(linje);
                    String [] verdier = linje.split(",");
                    if(verdier[3].equals("hvit")){
                        Hvitresept hvit = new Hvitresept(legemidler.hent(Integer.parseInt(verdier[0])), leger.hent(verdier[1]), pasienter.hent(Integer.parseInt(verdier[2])), Integer.parseInt(verdier[4]));
                        resepter.leggTil(hvit);
                        System.out.println(hvit);
                    }
                    else if(verdier[3].equals("blaa")){
                        Blaaresepter blaa = new Blaaresepter(legemidler.hent(Integer.parseInt(verdier[0])), leger.hent(verdier[1]), pasienter.hent(Integer.parseInt(verdier[2])), Integer.parseInt(verdier[4]));
                        resepter.leggTil(blaa);
                        System.out.println(blaa);
                    }
                    else if(verdier[3].equals("militaer")){
                        MilResept militaer = new MilResept(legemidler.hent(Integer.parseInt(verdier[0])), leger.hent(verdier[1]), pasienter.hent(Integer.parseInt(verdier[2])), Integer.parseInt(verdier[4]));
                        resepter.leggTil(militaer);
                        System.out.println(militaer);
                    }
                    else if(verdier[3].equals("p")){
                        P_resepter p = new P_resepter(legemidler.hent(Integer.parseInt(verdier[0])), leger.hent(verdier[1]), pasienter.hent(Integer.parseInt(verdier[2])));
                        resepter.leggTil(p);
                        System.out.println(p);
                    }
                    else{
                        System.out.println("Feil i resept");
                    }

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
        //legesystem.hovedprogramm();
    }


    /*                if (linje.startsWith("#") || linje.trim().isEmpty()) {
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
                } */
    }

