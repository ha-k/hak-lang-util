//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

import java.util.Enumeration;
import java.util.NoSuchElementException;

/**
 * This provides an enumeration for <tt>SetOf</tt> objects.
 *
 * <p><b>NB<b>: once built with the constructor
 * <tt>SetEnumeration(s)</tt>, an enumeration will not be altered if a
 * set <t>s</t> is modified. In other words, it will keep enumerating
 * all and only the elements of the <i>original</i> set. This is why the
 * method <t>reset(SetOf)</t> is provided.
 *
 * @see         SetOf
 *
 * @version     Last modified on Sun Mar 25 09:05:52 2018 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */

public class SetEnumeration implements Enumeration
{
  private SetOf set;
  private int position = 0;
  private int[] indices;

  SetEnumeration (SetOf set)
    {
      setupEnumeration(set);
    }

  private void setupEnumeration (SetOf set)
    {
      if (set.indices == null) set.buildIndices();
      this.indices = set.indices;
      this.set = set;
    }

  public SetEnumeration reset (SetOf set)
    {
      setupEnumeration(set);
      return this;
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

