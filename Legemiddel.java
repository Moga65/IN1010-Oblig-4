public abstract class Legemiddel {
    public final String navn; 
    int pris;
    public final double virkestoff;
    public final int id;
    private static int neste_id = 0;

    Legemiddel(String navn, int pris, double virkestoff){
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
        this.id = neste_id;
        neste_id++;
    }


    private static int nyid(){
        return neste_id++;
    } 

    public int hentPris(){
        return pris;
    }

    public void settNyPris(int ny_pris){
        pris = ny_pris;
    }

    public int hentID() {
        return id;
    }

    public String hentNavn(){
        return navn;
    }

    public String toString() {
        return "Navn: " + navn + " Pris: " + pris + " Virkestoff: " + virkestoff;
    }
    
}
