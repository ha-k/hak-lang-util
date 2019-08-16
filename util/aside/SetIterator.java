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
 * <p><b>NB<b>: once built wih the constructor <tt>SetIterator(s)</tt>,
 * a set iterator will not be altered if a set <t>s</t> is modified. In
 * other words, it will keep iterating through the elements of the
 * <i>original</i> set. This is why the method <t>reset(SetOf)</t> is
 * provided.
 *
 * @see         SetOf
 *
 * @version     Last modified on Sun Mar 25 09:06:30 2018 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */

public class SetIterator implements Iterator
{
  private SetOf set;
  private int position = 0;
  private int[] indices;

  SetIterator (SetOf set)
    {
      setupIterator(set);
    }

  private void setupIterator (SetOf set)
    {
      if (set.indices == null) set.buildIndices();
      this.indices = set.indices;
      this.set = set;
    }

  public SetIterator reset (SetOf set)
    {
      setupIterator(set);
      return this;
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





