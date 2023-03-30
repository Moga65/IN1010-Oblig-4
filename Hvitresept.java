public class Hvitresept extends Resept {
    
    public Hvitresept(Legemiddel legemiddel, Lege utskrivLege, Pasient pasient, int reit){
        super(legemiddel, utskrivLege, pasient, reit);
    }
    
    @Override
    public String farge(){
        return "Hvit resept";
    }
    @Override
    public int prisAaBetale() {
        
        return pris;
    }

    
}

