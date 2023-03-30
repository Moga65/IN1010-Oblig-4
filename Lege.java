public class Lege implements Comparable<Lege> {
    String Navn;
    IndeksertListe<Resept> utskrevneResepter;
    public Lege(String Navn){
        this.Navn = Navn; 
        this.utskrevneResepter = new IndeksertListe<Resept>();
    }

    public String hentNavn(){
        return Navn;
    }

    public String toString() {
        return Navn;
    }

    public int compareTo(Lege neste_lege){
        return this.Navn.compareTo(neste_lege.Navn);
    }

    public IndeksertListe<Resept> hentUtskrevneResepter(){
        return utskrevneResepter;
    }
    
    public Hvitresept skrivHvitresept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
        Hvitresept nyHvitresept = new Hvitresept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(nyHvitresept);
        return nyHvitresept;
    }

    public MilResept skrivMilResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
        MilResept nyMilResept = new MilResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(nyMilResept);
        return nyMilResept;
    }

    public P_resepter skrivPResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
        P_resepter ny_P_resepter = new P_resepter(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(ny_P_resepter);
        return ny_P_resepter;
    }

    public Blaaresepter skrivBlaaresepter(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
        Blaaresepter ny_Blaaresepter = new Blaaresepter(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(ny_Blaaresepter);
        return ny_Blaaresepter;
    }
}
