package Main;

import blockChiffre.BlockChiffre;
import hybrid.HybridVerfahren;
import rsa.Krypt;

public class Test {

	public static void main(String[] args) {
		
		Krypt rsa = new Krypt();
		rsa.genererateKeys();
		HybridVerfahren h = new HybridVerfahren(rsa, new BlockChiffre());
		
		//System.out.println(h.decryptString2("  cA \"f`csaj`ropdgc$fp\\tkxn", 2747, 54889));
		
		rsa.setE(46823);
		rsa.setN(2747);
		rsa.setHauptmodul(54889);
		System.out.println(h.decryptString("  cA \"f`csaj`ropdgc$fp\\tkxn"));
		
		//System.out.println(h.decryptString(h.encryptString("hallo du DA")));

	}

}
