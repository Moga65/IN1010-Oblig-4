public class P_resepter extends Hvitresept {

    public P_resepter(Legemiddel legemiddel, Lege utskrivLege, Pasient pasient, int reit){
        super(legemiddel, utskrivLege, pasient, reit);
    }
    @Override
    public int prisAaBetale(){
        int ny_pris;
        if((pris - 108) < 0){
            ny_pris = 0;
        }
        else{
            ny_pris = pris - 108;
        }
        return ny_pris;
        
}
}
