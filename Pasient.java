public class Pasient {
    private int id;
    private String foedselsnummer;
    private String navn;
    static  int teller = 0;
    private Lenkeliste<Resept> resepter;

    public Pasient(String foedselsnummer, String navn){
        this.navn = navn;
        this.foedselsnummer = foedselsnummer;
        this.id = teller++;
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

    public String toString() {
        return  navn ;
}
}
