public class testabdu {
    public static void main(String[] args) throws UlovligUtskrift {
      Narkotisk narkotika = new Narkotisk("heroin", 1000, 2.2, 99);
      Vanedannende vann = new Vanedannende("murfin", 222, 12, 43);
      Vanlig vanlig = new Vanlig("paracet", 219, 5.5);
      Lege lege = new Lege("Aboud");
      Spesialist spesialist = new Spesialist("Abdullah", "2321");
      Pasient   Pasient = new Pasient("20203020", "Ola");
      MilResept milResept = new MilResept(narkotika,lege,Pasient, 0);
      P_resepter p_resepter = new P_resepter(vanlig, new Lege("Abul"), new Pasient("32323", "Ola"), 0);
      Blaaresepter blaaresepter = new Blaaresepter(vann, new Lege("Ole"), new Pasient("293293023", "Kari"), 21);
      Hvitresept hvitresept = new Hvitresept(narkotika, new Lege("Alaa"), new Pasient("2939293", "Victor"), 2);
      lege.skrivHvitresept(vanlig,  Pasient, 2);
      lege.skrivBlaaresepter(vanlig, Pasient, 55);
      lege.skrivMilResept(narkotika, Pasient, 55);
      lege.skrivPResept(vann, Pasient, 55);
      

      //NB!!!! Tror logisk feil for spesialist
      spesialist.skrivMilResept(narkotika,  Pasient, 323);
      System.out.println(lege.hentUtskrevneResepter());
      /* 
      System.out.println(milResept);
      System.out.println(p_resepter);
      System.out.println(blaaresepter);
      System.out.println(hvitresept);
       */
    }
  
     
  }