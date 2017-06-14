/**
 * @author Johannes Kruber 
 */

package hybrid;

import blockChiffre.BlockChiffre;
import rsa.Krypt;

public class HybridVerfahren {
	private final char ASCI = 32;
	Krypt rsa;
	BlockChiffre symmetric;
	public HybridVerfahren(Krypt k, BlockChiffre b) {
		rsa = k;
		symmetric = b;
	}

	public char[] rsaVerschluesseln(String s) {
		int[] array = rsa.verschluesseln(s);
		char[] charArray = new char[array.length * 4];
		for (int i = 0; i < charArray.length; i += 4) {
			charArray[i] = (char) (array[i / 4] / Math.pow(95, 3) + ASCI);
			charArray[i + 1] = (char) ((array[i / 4] % Math.pow(95, 3)) / Math.pow(95, 2) + ASCI);
			charArray[i + 2] = (char) ((array[i / 4] % Math.pow(95, 2)) / 95 + ASCI);
			charArray[i + 3] = (char) (array[i / 4] % 95 + ASCI);
		}
		return charArray;
	}

	public String rsaentschluesseln(char[] c) {
		int[] array = new int[c.length / 4];
		for (int i = 0; i < c.length; i += 4) {
			array[i / 4] = (int) ((c[i] - ASCI) * Math.pow(95, 3) + (c[i + 1] - ASCI) * Math.pow(95, 2)
					+ (c[i + 2] - ASCI) * 95 + (c[i + 3] - ASCI));
		}
		return rsa.entschluesseln(array);
	}

	public String encryptString(String clear, int e, int hauptmodul) {
		
		String crypted = symmetric.encryptString(clear);

		char charKryptArray[] = crypted.toCharArray();
		int intKryptArray[] = new int[crypted.length()];

		for (int i = 0; i < charKryptArray.length; i++) {
			intKryptArray[i] = charKryptArray[i] - 32;
		}

		int sessionKey_0 = rsa.encrypt(intKryptArray[0], e, hauptmodul);
		int sessionkey_1 = rsa.encrypt(intKryptArray[1], e, hauptmodul);

		intKryptArray[0] = sessionKey_0 / (95 * 95 * 95);
		intKryptArray[1] = (sessionKey_0 % (95 * 95 * 95)) / (95 * 95);
		intKryptArray[2] = (sessionKey_0 % (95 * 95)) / 95;
		intKryptArray[3] = sessionKey_0 % 95;

		intKryptArray[4] = sessionkey_1 / (95 * 95 * 95);
		intKryptArray[5] = (sessionkey_1 % (95 * 95 * 95)) / (95 * 95);
		intKryptArray[6] = (sessionkey_1 % (95 * 95)) / 95;
		intKryptArray[7] = sessionkey_1 % 95;

		char encryptedCharArray[] = new char[intKryptArray.length];

		for (int i = 0; i < intKryptArray.length; i++) {
			encryptedCharArray[i] = (char) (intKryptArray[i] + 32);
		}

		return String.valueOf(encryptedCharArray);

	}

	public String decryptString(String toDecrypt, int d, int hauptmodul) {
		char charKryptArray[] = toDecrypt.toCharArray();

		int[] intKryptArray = new int[charKryptArray.length];
		for (int i = 0; i < charKryptArray.length; i++) {
			intKryptArray[i] = charKryptArray[i] - 32;
		}

		int sessionKey_0 = intKryptArray[3];
		sessionKey_0 += (intKryptArray[2] * 95) % (95 * 95);
		sessionKey_0 += (intKryptArray[1] * 95 * 95) % (95 * 95 * 95);
		sessionKey_0 += (intKryptArray[0] * 95 * 95 * 95);

		int sessionKey_1 = intKryptArray[7];
		sessionKey_1 += (intKryptArray[6] * 95) % (95 * 95);
		sessionKey_1 += (intKryptArray[5] * 95 * 95) % (95 * 95 * 95);
		sessionKey_1 += (intKryptArray[4] * 95 * 95 * 95);

		int s0 = rsa.decrypt(sessionKey_0, d, hauptmodul);
		int s1 = rsa.decrypt(sessionKey_1, d, hauptmodul);

		intKryptArray[0] = s0;
		intKryptArray[1] = s1;

		char charKryptArrayReturn[] = new char[intKryptArray.length];

		for (int i = 0; i < intKryptArray.length; i++) {
			charKryptArrayReturn[i] = (char) (intKryptArray[i] + 32);
		}
		String clear = symmetric.decryptString(String.valueOf(charKryptArrayReturn));
		return clear;
	}
}
