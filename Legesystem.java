import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;


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
                    //System.out.println("Modus: " + modus);
                }

                if(modus == 1){
                // System.out.println(linje);
                String [] verdier = linje.split(",");
                Pasient pass = new Pasient(verdier[0], verdier[1]);
                //System.out.println(pass);
                pasienter.leggTil(pass);
                }
                
                if(modus == 2){
                    String [] verdier = linje.split(",");
                    if(verdier[1].equals("narkotisk")){
                        Narkotisk nark = new Narkotisk(verdier[0], Integer.parseInt(verdier[2]), Double.parseDouble(verdier[3]), Integer.parseInt(verdier[4]));
                        legemidler.leggTil(nark);
                        //System.out.println(nark);
                    }
                    else if(verdier[1].equals("vanedannende")){
                        Vanedannende vanedannende = new Vanedannende(verdier[0], Integer.parseInt(verdier[2]), Double.parseDouble(verdier[3]), Integer.parseInt(verdier[4]));
                        legemidler.leggTil(vanedannende);
                        //System.out.println(vanedannende);
                    }
                    else if(verdier[1].equals("vanlig")){
                        Vanlig vanlig = new Vanlig(verdier[0], Integer.parseInt(verdier[2]), Double.parseDouble(verdier[3]));
                        legemidler.leggTil(vanlig);
                        //System.out.println(vanlig);
                    }
                    else{
                        //System.out.println("Feil i legemiddel");
                    }
                }
                
                if(modus == 3){
                    //System.out.println(linje);
                    String [] verdier = linje.split(",");
                    if(verdier[1].equals("0")){
                        Lege lege = new Lege(verdier[0]);
                        leger.leggTil(lege);
                        //System.out.println(lege);
                    }
                    else if(!verdier[1].equals("spesialist")){
                        Spesialist spesialist = new Spesialist(verdier[0], (verdier[1]));
                        leger.leggTil(spesialist);
                        //System.out.println(spesialist);
                    }
                    else{
                        //System.out.println("Feil i lege"); 
                    }
                }

                if(modus == 4){
                    //Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])
                    //System.out.println(linje);
                    String [] verdier = linje.split(",");

                    Legemiddel legemiddel = legemidler.hent(Integer.parseInt(verdier[0]));
                    Lege legen = null;
                    Pasient pasienten = pasienter.hent(Integer.parseInt(verdier[2]));
                    int reit = 0;

                    try {
                        reit = Integer.parseInt(verdier[4]);
                    } catch (Exception e) {
                    }

                    for (Lege lege : leger) {
                        if(lege.hentNavn().equals(verdier[1])){
                            legen = lege;
                            break;
                        }
                    }


                    if(verdier[3].equals("hvit")){
                        Hvitresept hvit = new Hvitresept(legemiddel, legen, pasienten, reit);
                        pasienten.leggTilResept(hvit);
                        legen.utskrevneResepter.leggTil(hvit);
                        resepter.leggTil(hvit);
                        //System.out.println(hvit);
                    }
                    else if(verdier[3].equals("blaa")){

                        if(legemiddel instanceof Narkotisk){
                            if(legen instanceof Spesialist){
                                Blaaresepter blaa = new Blaaresepter(legemiddel, legen, pasienten, reit);
                                pasienten.leggTilResept(blaa);
                                legen.utskrevneResepter.leggTil(blaa);
                                //System.out.println(blaa);
                            }
                            else{
                                System.out.println("Vanlig lege kan ikke skrive ut narkotiske resepter");
                            }
                        }

                    }
                    else if(verdier[3].equals("militaer")){
                        MilResept militaer = new MilResept(legemiddel, legen, pasienten, 0);
                        pasienten.leggTilResept(militaer);
                        legen.utskrevneResepter.leggTil(militaer);
                        resepter.leggTil(militaer);
                        //System.out.println(militaer);
                    }
                    else if(verdier[3].equals("p")){
                        P_resepter p = new P_resepter(legemiddel, legen, pasienten, reit);
                        pasienten.leggTilResept(p);
                        legen.utskrevneResepter.leggTil(p);
                        resepter.leggTil(p);
                        //System.out.println(p);
                    }
                    else{
                        System.out.println("Feil i resept");
                    }
                }

            }

            ArrayList<Lege> legerArr = new ArrayList<Lege>();
            for (Lege lege : leger) {
                legerArr.add(lege);
            }
            Collections.sort(legerArr);

            leger = new IndeksertListe<Lege>();

            for (Lege lege : legerArr) {
                leger.leggTil(lege);
            }


        } 
        catch (IOException e) {
            System.err.println("Feil ved lesing av fil: " + e.getMessage());
        }
    }

    public void E0(){
        Scanner input = new Scanner(System.in);
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
                E1();
            break;
            case 2:
                E2();
                break;
            case 3:
                E3();
                break;
            case 4:
                E4();
                break;
            case 5:
                E5();
                break;
            case 6:
                E_kill();
            default:
                System.out.println("Ugyldig valg, prøv igjen.");
        }
    }

    public void E1() {
        System.out.println("Oversikt");

        System.out.println("\nLegemidler:");

        for (Legemiddel legemiddel : legemidler) {
            System.out.println(legemiddel);
        }

        System.out.println("\nLeger:");
        for (Lege lege : leger) {
            System.out.println(lege);
        }

        System.out.println("\nPasienter:");
        for (Pasient pasient : pasienter) {
            System.out.println(pasient);
        }

        System.out.println("\nResepter:");

        for (Resept resept : resepter) {
            System.out.println(resept);
        }
        
        System.out.println("\n\n1. Tilbake til hovedmeny");
        System.out.println("2. Avslutt systemet!");



        Scanner input = new Scanner(System.in);
        int svar = input.nextInt();
        input.nextLine();


        switch (svar){
            case 1:
                E0();
                break;
            case 2:
                E_kill();
                break;
            default:
                System.out.println("Ugyldig valg, prøv igjen.");
        }


    }

    public void E2() {


        System.out.println("1. Tilbake til hovedmeny");
        System.out.println("2. Avslutt systemet!\n\n\n");
        System.out.println("3. legg til legemiddel");
        System.out.println("4. legg til lege");
        System.out.println("5. legg til pasient");
        System.out.println("6. legg til resept");


        Scanner input = new Scanner(System.in);
        int svar = input.nextInt();
        input.nextLine();


        switch (svar){
            case 1:
                E0();
                break;
            case 2:
                E_kill();
                break;
            case 3:
                leggTilLegemiddel();
                break;
            case 4:
                leggTilLege();
                break;
            case 5:
                leggTilPasient();
                break;
            case 6:
                leggTilResept();
                break;
            default:
                System.out.println("Ugyldig valg, prøv igjen.");
        }

    }
    
    public void leggTilLegemiddel() {
        System.out.println("1. Tilbake til hovedmeny");
        System.out.println("Velg legemiddeltype");
        System.out.println("2. Vanlig");
        System.out.println("3. Narkotisk");
        System.out.println("4. Vanedannende");

        Scanner input = new Scanner(System.in);
        int svar = input.nextInt();
        input.nextLine();
        switch (svar){
            case 1:
                E0();
                break;
            case 2:
                Scanner input2 = new Scanner(System.in);
                System.out.println("Navn: ");
                String navn = input2.nextLine();
                System.out.println("Pris: ");
                int pris = input2.nextInt();
                System.out.println("Virkestoff: ");
                double virkestoff = input2.nextDouble();
                Vanlig vanlig = new Vanlig(navn, pris, virkestoff);
                legemidler.leggTil(vanlig);
                break;
            case 3:     
                Scanner input3 = new Scanner(System.in);
                System.out.println("Navn: ");
                String navn2 = input3.nextLine();
                System.out.println("Pris: ");
                int pris2 = input3.nextInt();
                System.out.println("Virkestoff: ");
                double virkestoff2 = input3.nextDouble();
                System.out.println("Styrke: ");
                int styrke = input3.nextInt();
                Narkotisk narkotisk = new Narkotisk(navn2, pris2, virkestoff2, styrke);
                legemidler.leggTil(narkotisk);
                break;
            
            case 4:
                Scanner input4 = new Scanner(System.in);
                System.out.println("Navn: ");
                String navn3 = input4.nextLine();
                System.out.println("Pris: ");
                int pris3 = input4.nextInt();
                System.out.println("Virkestoff: ");
                double virkestoff3 = input4.nextDouble();
                System.out.println("Styrke: ");
                int styrke2 = input4.nextInt();
                Vanedannende vanedannende = new Vanedannende(navn3, pris3, virkestoff3, styrke2);
                legemidler.leggTil(vanedannende);
                break;
            default:
                System.out.println("Ugyldig valg, prøv igjen.");
        }
    }

    public void leggTilLege() {   
        System.out.println("1. Tilbake til hovedmeny");
        System.out.println("Velg legetype");
        System.out.println("2. Lege");
        System.out.println("3. Spesialist");

        Scanner input = new Scanner(System.in);
        int svar = input.nextInt();
        input.nextLine();
        switch (svar) {
            case 1:
            E0();
            break;
        case 2:
            Scanner input2 = new Scanner(System.in);
            System.out.println("Navn: ");
            String navn = input2.nextLine();
            Lege lege = new Lege(navn);
            leger.leggTil(lege);
            break;
        case 3:
            Scanner input3 = new Scanner(System.in);
            System.out.println("Navn: ");
            String navn2 = input3.nextLine();
            System.out.println("KontrollID: ");
            String kontrollID = input3.nextLine();
            Spesialist spesialist = new Spesialist(navn2, kontrollID);
            leger.leggTil(spesialist);
            break;
            default:
            System.out.println("Ugyldig valg, prøv igjen.");
        }

    }
    
    public Pasient leggTilPasient() {
        System.out.println("1. Tilbake til hovedmeny");
        System.out.println("2. Legg til pasient");

        Scanner input = new Scanner(System.in);
        int svar = input.nextInt();
        input.nextLine();
        switch (svar) {
            case 1:
            E0();
                break;
            case 2:
                Scanner input2 = new Scanner(System.in);
                System.out.println("Navn: ");
                String navn = input2.nextLine();
                System.out.println("Fødselsnummer: ");
                String fødselsnummer = input2.nextLine();
                Pasient pasient = new Pasient(navn, fødselsnummer);
                pasienter.leggTil(pasient);
                return pasient;
            default:
                System.out.println("Ugyldig valg, prøv igjen.");
        }
        return null;
    }

    public void leggTilResept() {
        System.out.println("1. Tilbake til hovedmeny");
        System.out.println("\nVelg lege: \n");
        int Counter = 2;
        for (Lege lege : leger) {
            System.out.println(Counter + ". " +lege.hentNavn());
            Counter++;
        }
        

        Scanner input = new Scanner(System.in);
        int svar = input.nextInt();
        input.nextLine();
        Lege legen = null;



        switch (svar) {
            case 1:
            E0();
            break;
            default:
                legen = leger.hent(svar-2);
        }

        System.out.println("1. Tilbake til hovedmeny");
        System.out.println("\n Velg legemiddel: \n");
        Counter = 2;
        for (Legemiddel legemiddel : legemidler) {
            System.out.println(Counter + ". " +legemiddel.hentNavn());
            Counter++;
        }
        Scanner input2 = new Scanner(System.in);
        int svar2 = input2.nextInt();
        input2.nextLine();
        Legemiddel legemiddelet = null;
        switch (svar2) {
            case 1:
            E0();
            break;
            default:
                legemiddelet = legemidler.hent(svar2-2);
        }

        System.out.println("1. Tilbake til hovedmeny");
        System.out.println("\n Velg pasient: \n");
        Counter = 2;
        for (Pasient pasient : pasienter) {
            System.out.println(Counter + ". " +pasient.hentNavn());
            Counter++;
        }
        Scanner input3 = new Scanner(System.in);
        int svar3 = input3.nextInt();
        input3.nextLine();
        Pasient pasienten = null;
        switch (svar3) {
            case 1:
            E0();
            break;
            default:
                pasienten = pasienter.hent(svar3-2);
        }

        System.out.println("1. Tilbake til hovedmeny");
        System.out.println("\n Velg antall reit: \n");



        Scanner input4 = new Scanner(System.in);
        int reit = input4.nextInt();
        input4.nextLine(); 


        System.out.println("1. Tilbake til hovedmeny");
        System.out.println("\n Velg resepttype: \n");
        System.out.println("2. Blå resept");
        System.out.println("3. Militær resept");
        System.out.println("4. P-resept");

        Scanner input5 = new Scanner(System.in);
        int svar5 = input5.nextInt();
        input5.nextLine();

        switch (svar5) {
            case 1:
            E0();
            break;
            case 2:
                Blaaresepter blaaResept = null;

                try {
                    blaaResept = legen.skrivBlaaresepter(legemiddelet, pasienten, reit);
                } catch (Exception e) {

                }
                resepter.leggTil(blaaResept);
                break;

            case 3:
                MilResept militaerResept = null;

                try {
                    militaerResept = legen.skrivMilResept(legemiddelet, pasienten, reit);
                } catch (Exception e) {
                }
                resepter.leggTil(militaerResept);

                break;
            case 4:
             P_resepter pResept = null;

                try {
                    pResept = legen.skrivPResept(legemiddelet, pasienten, reit);
                } catch (Exception e) {
                }
                 resepter.leggTil(pResept);
                break;
            default:
                System.out.println("Ugyldig valg, prøv igjen.");
        }
    }

    public void E3() {
        System.out.println("1. Tilbake til hovedmeny");
        System.out.println("\n Velg resept å bruke: \n");

        int Counter = 2;
        for (Resept resept : resepter) {
            System.out.println(Counter + ". " + resept.hentLegemiddel().hentNavn());
            Counter++;
        }
        Scanner input = new Scanner(System.in);
        int svar = input.nextInt();
        input.nextLine();

        switch (svar) {
            case 1:
            E0();
            break;
            default:
                Resept resepten = resepter.hent(svar-2);
                resepten.bruk();
                System.out.println("Resepten for " + resepten.legemiddel.navn+ " er nå brukt " + resepten.hentReit() + " bruk er igjen.");
        }
    }

    public void E4() {
        System.out.println("1. Tilbake til hovedmeny");
        System.out.println("\n Statistikk: \n");
        System.out.println("2. Antall resepter på vanedannende legemidler skrevet ut av hver lege");
        System.out.println("3. Antall resepter på narkotiske legemidler skrevet ut av hver lege");
        System.out.println("4. Statistikk på mulig misbruk:\n");

        Scanner input = new Scanner(System.in);
        int svar = input.nextInt();
        input.nextLine();

        switch (svar) {
            case 1:
                E0();
            break;
            case 2:
                for (Lege lege : leger) {
                    int antall = 0;
                    for (Resept resept : resepter) {
                        if (resept.utskrivLege == lege && resept.legemiddel instanceof Vanedannende) {
                            antall++;
                        }
                    }
                    System.out.println(lege.hentNavn() + " har skrevet ut " + antall + " resepter på vanedannende legemidler.");
                }
                break;
            case 3:
                for (Lege lege : leger) {
                    int antall = 0;
                    for (Resept resept : resepter) {
                        if (resept.utskrivLege == lege && resept.legemiddel instanceof Narkotisk) {
                            antall++;
                        }
                    }
                    System.out.println(lege.hentNavn() + " har skrevet ut " + antall + " resepter på narkotiske legemidler.");
                }
                break;
            case 4:
                for (Lege lege : leger) {
                    int antall = 0;
                    for(Resept resept : lege.utskrevneResepter){
                        if(resept.legemiddel instanceof Narkotisk){
                            antall++;
                        }
                    }
                    if(antall > 0){
                        System.out.println("\n" + lege.hentNavn() + " har skrevet ut " + antall + " resepter på narkotiske legemidler.\n");
                    }
                }

                for(Pasient pasient: pasienter){
                    int antall2 = 0;
                    for(Resept resept : pasient.resepter){
                        if(resept.legemiddel instanceof Narkotisk){
                            antall2++;
                        }
                    }
                    if(antall2 > 0){
                        System.out.println(pasient.hentNavn() + " har " + antall2 + " resepter på narkotiske legemidler.\n");
                    }
                }
            default:
                break;
        }
    }

    public void E5() {
        System.out.println("\n Skriv til fil: \n");
        System.out.println("\nHva skal den nye filen hete? (Du trenger ikke aa inkluder .txt)");
        Scanner scan = new Scanner(System.in);
        String filnavn = scan.nextLine() + ".txt";
        String filInnhold = "";
        for (Pasient pasient : pasienter) {
            filInnhold += pasient.toString() + "\n";
        }
        for (Lege lege : leger) {
            filInnhold += lege.toString() + "\n";
        }
        for (Legemiddel legemiddel : legemidler) {
            filInnhold += legemiddel.toString() + "\n";
        }
        for (Resept resept : resepter) {
            filInnhold += resept.toString() + "\n";
        }

        
        try {
            FileWriter writer = new FileWriter(filnavn);
            writer.write(filInnhold);
            writer.close();
          } catch (IOException e) {
            System.out.println("Kunne ikke skrive til fil");
          }
        E0();
    }

    public void E_kill() {
        System.exit(6);
    }


    public void hovedprogramm(){


        while (true){
            E0();
        }
    }

    public static void main(String[] args) {
        String filnavn = "legedata.txt";
        Legesystem legesystem = new Legesystem();
        legesystem.les_fra_fil(filnavn);
        legesystem.hovedprogramm();
    }

    }

