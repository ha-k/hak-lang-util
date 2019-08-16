//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

/**
 * This class is meant to be subclassed by the class of objects
 * identified by an index in a collection.
 *
 * @see         ArrayIndexed
 * @see         AbstractListIndexed
 *
 * @version     Last modified on Fri Apr 17 15:54:52 2015 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */

public abstract class Indexed
{
  int index;     /* position in collection */

  /**
   * @return the index of this <tt>Indexed</tt> object.
   */
  final public int index ()
    {
      return index;
    }    

  /**
   * @return a string form of this <tt>Indexed</tt> object.
   */
  public String toString()
    {
      return String.valueOf(index);
    }

  /**
   * @return a hash code for this <tt>Indexed</tt> object.
   */
  public int hashCode()
    {
      return index;
    }
}
