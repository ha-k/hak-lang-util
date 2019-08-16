//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

/**
 * This is a wrapper of the Java <tt>String</tt> class implementing
 * the <a href="./Comparable.html"><tt>Comparable</tt></a> interface
 * using lexicographic ordering.
 *
 * @version     Last modified on Tue Jun 19 17:37:42 2012 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */

public class LexComparableString implements Comparable
{
  private String _string;

  public LexComparableString (String s)
    {
      _string = s.intern();
    }

  public final String string ()
    {
      return _string;
    }

  public final boolean lessThan (Comparable other)
    {
      if (!(other instanceof LexComparableString))
        return false;

      return _string.compareTo(((LexComparableString)other).string()) < 0;
    }

  public final int hashCode ()
    {
      return _string.hashCode();
    }

  public final boolean isEqualTo (String string)
    {
      return _string == string.intern();
    }

  public final boolean equals (Object object)
    {
      if (this == object)
        return true;

      if (!(object instanceof LexComparableString))
        return false;

      return _string == ((LexComparableString)object).string();
    }

  public final String toString ()
    {
      return _string;
    }
}
