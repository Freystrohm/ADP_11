/**
 * @author Johannes Kruber 
 */

package rsa;

import aufg2_primzahlen.Primzahlsuche;

public class Krypt
{
	private int e, n;
	public void genererateKeys()
	{
		int p = getPrim(), q = getPrim();
		int hauptmodul = p * q, nebenmodul = (p - 1) * (q - 1);
		e = (int) (Math.random() * nebenmodul);
		if (e <= 1)
		{
			e = 2;
		}
		while(ExtEuklid.extEuklid(e, nebenmodul) != 1)
		{
			e++;
		}
		n = ExtEuklid.d;
	}

	private int getPrim()
	{
		int p = 0;
		while (p < 100)
		{
			p = (int) (Math.random() * 500);
		}
		while (Primzahlsuche.istPrimzahl(p))
		{
			p++;
			if (p > 500)
			{
				p = 100;
			}

		}
		return p;
	}
}
