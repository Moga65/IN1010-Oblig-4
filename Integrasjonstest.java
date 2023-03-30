public class Integrasjonstest {
  public static void main(String[] args) {
    Narkotisk narkotika = new Narkotisk("heroin", 1000, 2.2, 99);
    Vanedannende vann = new Vanedannende("murfin", 222, 12, 43);
    Vanlig vanlig = new Vanlig("paracet", 219, 5.5);
    Lege lege = new Lege("Aboud");
    Pasient pasient = new Pasient("20203020", "Ola");
    MilResept milResept = new MilResept(narkotika,lege,pasient, 0);
    P_resepter p_resepter = new P_resepter(vanlig, new Lege("Abul"), new Pasient("32323", "Ola"), 0);
    Blaaresepter blaaresepter = new Blaaresepter(vann, new Lege("Ole"), new Pasient("293293023", "Kari"), 21);
    Hvitresept hvitresept = new Hvitresept(narkotika, new Lege("Alaa"), new Pasient("2939293", "Victor"), 2);
    
    System.out.println(milResept);
    System.out.println(p_resepter);
    System.out.println(blaaresepter);
    System.out.println(hvitresept);
  }

   
}
