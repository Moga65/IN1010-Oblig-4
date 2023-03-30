

public class Blaaresepter extends Resept {
    public Blaaresepter(Legemiddel legemiddel, Lege utskrivLege, Pasient pasient, int reit){
        super(legemiddel, utskrivLege, pasient, reit);
    }

    @Override
    public String farge(){
        return "Blaa resept";
    }
    @Override
    public int prisAaBetale(){
        double pris_med_rabatt = pris*0.25;
        int avrundet_pris = (int)Math.round(pris_med_rabatt);
        return avrundet_pris;
    }
    }




