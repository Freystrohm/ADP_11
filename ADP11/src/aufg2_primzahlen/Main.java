package aufg2_primzahlen;

public class Main {

  public static void main(String[] args) {
	int k = 1000;
    SiebDesEratosthenes test = new SiebDesEratosthenes(k);
    test.sieben();
//    test.siebAusgeben();
//    System.out.println("\nk = " + k);
//    
//    
    boolean[] array = Primzahlsuche.langsameSuche(k);
//    int i =0;
//    for (boolean b : array) 
//    {
//		System.out.println(i + ":" + b);
//		i++;
//	}
//    
//    
//    
    array = Primzahlsuche.schnelleSuche(k);
    System.out.println(Primzahlsuche.istPrimzahl(k));
//    i =0;
//    for (boolean b : array) 
//    {
//		System.out.println(i + ":" + b);
//		i++;
//	}
    System.out.println("Aufwand langsam: " + Primzahlsuche.zaehlerLangsam);
    System.out.println("Aufwand Schnell: " + Primzahlsuche.zaehlerSchnell);
    System.out.println("Aufwand Sieb: " + test.getZaehler());
    System.out.println("Aufwand IstPrimzahl: " + Primzahlsuche.zaehlerIstPrimzahl);
  }
}
