/**
 * @author Johannes Kruber 
 */

package hybrid;

import rsa.Krypt;

public class HybridVerfahren
{
	private final char ASCI = 32;
	Krypt rsa;
	public HybridVerfahren(Krypt k)
	{
		rsa = k;
	}

	public char[] rsaVerschluesseln(String s)
	{
		int[] array = rsa.verschluesseln(s);
		char[] charArray = new char[array.length * 4];
		for (int i = 0; i < charArray.length; i += 4)
		{
			charArray[i] = (char) (array[i / 4] / Math.pow(95, 3) + ASCI);
			charArray[i + 1] = (char) ((array[i / 4] % Math.pow(95, 3))
					/ Math.pow(95, 2) + ASCI);
			charArray[i + 2] = (char) ((array[i / 4] % Math.pow(95, 2)) / 95
					+ ASCI);
			charArray[i + 3] = (char) (array[i / 4] % 95 + ASCI);
		}
		return charArray;
	}

	public String rsaentschluesseln(char[] c)
	{
		int[] array = new int[c.length / 4];
		for (int i = 0; i < c.length; i +=  4)
		{
			array[i/4] = (int) ((c[i] - ASCI) * Math.pow(95, 3)
					+ (c[i + 1] - ASCI) * Math.pow(95, 2)
					+ (c[i + 2] - ASCI) * 95 + (c[i + 3] - ASCI));
		}
		return rsa.entschluesseln(array);
	}
}
