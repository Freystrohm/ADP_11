/**
 * @author Johannes Kruber 
 */

package rsa;

import aufg2_primzahlen.Primzahlsuche;

public class Krypt {
	public int e, n, hauptmodul, nebenmodul;
	public int q, p;
	private String testString = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. \n"
			+ "Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem."
			+ "\nNulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu.";

	public void setHauptmodul(int N)
	{
		hauptmodul = N;
	}
	public void setE(int e)
	{
		this.e= e;
	}
	
	public void setN(int n)
	{
		this.n= n;
	}
	
	public void genererateKeys() {
		p = getPrim();
		q = getPrim();
		hauptmodul = p * q;
		nebenmodul = (p - 1) * (q - 1);
		e = (int) (Math.random() * nebenmodul);
		if (e <= 1) {
			e = 2;
		}
		while (ExtEuklid.extEuklid(e, nebenmodul) != 1) {
			e++;
		}
		n = ExtEuklid.d;
		if (n < 0) {
			genererateKeys();
		} else if (!entschluesseln(verschluesseln(testString)).equals(testString)) {
			genererateKeys();
		}
	}

	private int getPrim() {
		int p = 0;
		while (p < 100) {
			p = (int) (Math.random() * 500);
		}
		while (!Primzahlsuche.istPrimzahl(p)) {
			p++;
			if (p > 500) {
				p = 100;
			}

		}
		return p;
	}

	public int[] verschluesseln(String s) {
		char[] array = new char[s.length()];
		int[] intarray = new int[s.length()];
		s.getChars(0, s.length(), array, 0);
		String eBin = Integer.toBinaryString(e);

		for (int i = 0; i < array.length; i++) {
			int a = array[i];
			long res = 1;

			for (int j = 0; j < eBin.length(); j++) {
				res = res * res;
				if (eBin.charAt(j) == '1') {
					res *= a;
				}
				res = res % hauptmodul;
			}
			intarray[i] = (char) res;
		}

		return intarray;
	}

	public String entschluesseln(int[] s) {
		String nBin = Integer.toBinaryString(n);
		char[] array = new char[s.length];

		for (int i = 0; i < s.length; i++) {
			int a = s[i];
			long res = 1;

			for (int j = 0; j < nBin.length(); j++) {
				res = res * res;
				if (nBin.charAt(j) == '1') {
					res *= a;
				}
				res = res % hauptmodul;
			}
			array[i] = (char) res;
		}

		return new String(array);
	}

	public int decryptAndEncrypt(int wert, int exp, int modul) {
		int erg = 1;
		for (int i = 0; i < exp; i++) {
			erg = (wert * erg) % modul;
		}
		return erg;
	}

	public int decrypt(int wert, int exp, int modul) {
		return decryptAndEncrypt(wert, exp, modul);
	}

	public int encrypt(int wert, int exp, int modul) {
		return decryptAndEncrypt(wert, exp, modul);
	}

	public int getHauptmodul() {
		return hauptmodul;
	}

	public int getQ() {
		return q;
	}

	public int getP() {
		return p;
	}

	public int getE() {
		return e;
	}

	public int getN() {
		return n;
	}

	public int getNebenmodul() {
		return nebenmodul;
	}

}
