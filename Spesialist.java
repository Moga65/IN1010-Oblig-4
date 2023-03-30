public class Spesialist extends Lege implements Godkjenningsfritak{

    String kontrollkode;
    public Spesialist(String Navn, String kontrollkode) {
        super(Navn);
        this.kontrollkode = kontrollkode;
    }

    @Override
    public String hentKontrollkode() {
        return kontrollkode;
    }
    
    @Override
    public String toString() {
        return super.toString() +  ". Kontrollkode: " + kontrollkode;
    }
}
