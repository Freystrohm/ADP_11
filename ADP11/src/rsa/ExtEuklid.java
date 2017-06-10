/**
 * @author Johannes Kruber 
 */

package rsa;

public class ExtEuklid
{
	public static int g = 0, d = 0, k = 0;

	public static int extEuklid(int a, int b)
	{
		if (b == 0)
		{
			g = a;
			d = 1;
			k = 0;
		}
		else
		{
			extEuklid(b, a % b);
			int x = d;
			d = k;
			k = x - a / b * k;
		}
		return g;
	}
}
