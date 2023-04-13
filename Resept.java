public abstract class Resept {

    Legemiddel legemiddel;
    Lege utskrivLege;
    int reit;
    int id;
    int pris;
    Pasient pasient;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        this.legemiddel = legemiddel;
        this.utskrivLege = utskrivendeLege;
        this.pasient = pasient;
        this.reit = reit;
        this.id = id++;
        pris = legemiddel.hentPris();

    }

    public Object hentpasient(){
        return pasient;
    }

    public Legemiddel hentLegemiddel(){
        return legemiddel;
    }

    public Object hentLege(){
        return this.utskrivLege;
    }

    public int hentID(){
        return id;
    }

    public int hentReit(){
        return reit;
    }

    public boolean bruk(){
        if (reit == 0){
            return false;
        }
        else{
            reit--;
            return true;
        }
    }

    public String toString() {
        return "Legemiddel: " + legemiddel.navn + " Lege: " + utskrivLege.Navn + " Pasient: " + pasient + " Reit: " + reit;
    }

    abstract public String farge(); 
    abstract public int prisAaBetale();
    
}
