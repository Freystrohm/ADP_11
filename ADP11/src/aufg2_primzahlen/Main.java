package aufg2_primzahlen;

import java.util.Arrays;

import blockChiffre.BlockChiffre;
import hybrid.HybridVerfahren;
import rsa.Krypt;

public class Main {

	public static void main(String[] args) {
		// int k = 1000;
		// SiebDesEratosthenes test = new SiebDesEratosthenes(k);
		// test.sieben();
		//// test.siebAusgeben();
		//// System.out.println("\nk = " + k);
		////
		////
		// boolean[] array = Primzahlsuche.langsameSuche(k);
		//// int i =0;
		//// for (boolean b : array)
		//// {
		//// System.out.println(i + ":" + b);
		//// i++;
		//// }
		////
		////
		////
		// array = Primzahlsuche.schnelleSuche(k);
		// System.out.println(Primzahlsuche.istPrimzahl(k));
		//// i =0;
		//// for (boolean b : array)
		//// {
		//// System.out.println(i + ":" + b);
		//// i++;
		//// }
		// System.out.println("Aufwand langsam: " +
		// Primzahlsuche.zaehlerLangsam);
		// System.out.println("Aufwand Schnell: " +
		// Primzahlsuche.zaehlerSchnell);
		// System.out.println("Aufwand Sieb: " + test.getZaehler());
		// System.out.println("Aufwand IstPrimzahl: " +
		// Primzahlsuche.zaehlerIstPrimzahl);

		Krypt rsa = new Krypt();
		rsa.genererateKeys();
		BlockChiffre block = new BlockChiffre();
		HybridVerfahren hybrid = new HybridVerfahren(rsa, block);

		System.out.println("Blockchiffre:");
		String b = block.encryptString("BLOCKCHIFFRE");
		b = block.decryptString(b);
		System.out.println(b);
		
		System.out.println("rsa:");
		System.out.println(rsa.entschluesseln(rsa.verschluesseln("RSA")));
		
		
		System.out.println("Hybridverfahren:");
		String encrypted = hybrid.encryptString("HYBRIDVERFAHREN", rsa.getE(), rsa.getHauptmodul());
		encrypted = hybrid.decryptString(encrypted, rsa.getN(), rsa.getHauptmodul());
		System.out.println(encrypted);
	}
}
