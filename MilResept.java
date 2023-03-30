

public class MilResept extends Hvitresept {
    public MilResept(Legemiddel legemiddel, Lege utskrivLege, Pasient pasient, int reit) { 
        super(legemiddel, utskrivLege, pasient, reit);
        this.reit = 3;
    }


    @Override
    public int prisAaBetale(){
        return 0;
    }



}



