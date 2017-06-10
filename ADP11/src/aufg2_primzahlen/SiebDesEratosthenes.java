package aufg2_primzahlen;

public class SiebDesEratosthenes {
  
  private boolean sieb[];
  private long zaehler;
  

  public SiebDesEratosthenes(int n) {
    zaehler = 0;
    sieb = new boolean[n+1];
    for(int i=0;i<sieb.length;i++){
      sieb[i] = true;
    }
  }
  
  public void sieben(){
    for(int i=2;i<Math.sqrt(sieb.length);i++){
      int j = 2;
      while(i*j<=sieb.length-1){
        zaehler++;
        sieb[i*j]=false;
        j++;
      }
    }
  }
  
  public void siebAusgeben(){
    for(int i=1;i<sieb.length;i++){
      if(sieb[i]){
        System.out.println(i);
      }
    }
  } 
  
  public long getZaehler() {
    return zaehler;
  }
  
}
