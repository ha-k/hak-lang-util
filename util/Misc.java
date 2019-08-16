//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

/**
 * @version     Last modified on Sun Oct 14 03:22:23 2012 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */

/**
 * This class implements a few of useful methods.
 */

public class Misc
{
  /**
   * Returns <tt>true</tt> iff the two specified, possibly <tt>null</tt>, objects are equal.
   */
  public final static boolean equals (Object o1, Object o2)
    {
      return o1 == null ? o2 == null : o1.equals(o2);
    }

  /**
   * Returns the hashcode of the specified, possibly <tt>null</tt> object.
   */
  public final static int hashCode (Object object)
    {
      return object == null ? 0 : object.hashCode();
    }

  /**
   * Returns a hash code for the specified double.
   */
  public static final int hashCode (double x)
    {
      long v = Double.doubleToLongBits(x);
      return (int)(v ^ (v >>> 32));
    }        

  /**
   * Computes a hash code for the given string using a variant of the
   * algorithm due to P.J.Weinberger described in the Dragon Book. The
   * difference is that each character is weighed by a function of its
   * position and the string's length.  This has for effect to improve
   * scatterring of identifiers differring by a <i>suffix</i>, which is
   * a rather common situation in program texts (<i>e.g.</i><tt>i1, i2,
   * i3,</tt> ...). As always, this hash code is supposed to work best
   * when the size of the hash table (<i>i.e.</i> the number to '%' this
   * code with) is prime.
   */
  public static final int hash (String s)
    {
      int h = 0;
      int g = 0;

      int len = s.length();

      for (int i=0; i<len; i++)
        {
          h = (h << 4) + (len^i)*(int)s.charAt(i);
          g = h & 0xf0000000;

          if (g == 0)
            {
              h ^= g >>> 24;
              h ^= g;
            }
        }
      return Math.abs(h);
    }  

}
