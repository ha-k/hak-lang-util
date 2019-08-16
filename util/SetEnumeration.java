//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

import java.util.Enumeration;
import java.util.NoSuchElementException;

/**
 * This provides an enumeration for <tt>SetOf</tt> objects.
 *
 * @see         SetOf
 *
 * @version     Last modified on Tue Jun 19 17:38:13 2012 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */

public class SetEnumeration implements Enumeration
{
  // NB: This enumeration is not altered if the set is modified
  // after constructing the enumeration. In other words, it will
  // enumerate all and only the elements of the original set.

  private SetOf set;
  private int position = 0;
  private int[] indices;

  SetEnumeration (SetOf set)
    {
      if (set.indices == null) set.buildIndices();
      this.indices = set.indices;
      this.set = set;
    }

  public final boolean hasMoreElements ()
    {
      return (indices != null) && (position < indices.length);
    }

  public final Object nextElement ()
    {
      return (indices == null)
             ? null
             : set.base.get(indices[position++]);
    }
}

