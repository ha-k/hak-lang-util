//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

import java.util.Iterator;

/**
 * This provides an iterator for <tt>SetOf</tt> objects. <b>NB:</b> this
 * is an exact replica of the implementation of <tt>SetEnumeration</tt>
 * with the additional (non-supported) <tt>remove()</tt> method as
 * mandated by the <tt>java.util.Iterator</tt> interface.
 *
 * @see         SetOf
 *
 * @version     Last modified on Tue Jun 19 17:38:15 2012 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */

public class SetIterator implements Iterator
{
  // NB: This enumeration is not altered if the set is modified
  // after constructing the enumeration. In other words, it will
  // enumerate all and only the elements of the original set.

  private SetOf set;
  private int position = 0;
  private int[] indices;

  SetIterator (SetOf set)
    {
      if (set.indices == null) set.buildIndices();
      this.indices = set.indices;
      this.set = set;
    }

  public final boolean hasNext ()
    {
      return (indices != null) && (position < indices.length);
    }

  public final Object next ()
    {
      return (indices == null)
             ? null
             : set.base.get(indices[position++]);
    }

  /**
   * This method is not implemented - it just throws an
   * <tt>UnsupportedOperationException</tt>.
   */
  public final void remove ()
    {
      throw new UnsupportedOperationException();
    }
}





