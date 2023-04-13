public class Pasient {
    private int id;
    private String foedselsnummer;
    private String navn;
    static  int teller = 0;
    public IndeksertListe<Resept> resepter;

    public Pasient(String navn, String foedselsnummer){
        this.navn = navn;
        this.foedselsnummer = foedselsnummer;
        this.id = teller++;
        resepter = new IndeksertListe<Resept>();
    }
    //Skriver hjelpemetoder 
    public String hentFoedselsnummer(){
        return foedselsnummer;
    }

    public String hentNavn(){
        return navn;
    }

    public int hentPasientID(){
        return id;
    }

    public void leggTilResept(Resept resept){
        resepter.leggTil(resept);
    }

    public Lenkeliste<Resept> hentResept(){
        return resepter;
    }

    @Override
    public String toString() {
        return "Pasient: " + navn + ", ID: " + id + ", Fødselsnummer: " + foedselsnummer;
    }
}
