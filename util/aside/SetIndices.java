//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

/**
 * This provides an enumeration through the indices of <tt>SetOf</tt> objects.
 *
 * <p><b>NB<b>: once built with the constructor <tt>SetIndices(t)</tt>,
 * an index enumeration will not be altered if a set <t>s</t> is
 * modified. In other words, it will keep enumerating all and only the
 * elements of the <i>original</i> set. This is why the method
 * <t>reset(SetOf)</t> is provided.
 *
 * @see		SetOf
 *
 * @version	Last modified on Sun Mar 25 09:07:33 2018 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright	&copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */

public class SetIndices implements IndexEnumeration
{
  private SetOf set;
  private int position = 0;
  private int[] indices;

  SetIndices (SetOf set)
    {
      setupIndexEnumeration(set);
    }

  private void setupIndexEnumeration (SetOf set)
    {
      if (set.indices == null) set.buildIndices();
      this.indices = set.indices;
      this.set = set;
    }

  public SetIndices reset (SetOf set)
    {
      setupIndexEnumeration(set);
      return this;
    }

  public final boolean hasMoreIndices ()
    {
      return (indices != null) && (position < indices.length);
    }

  public final int nextIndex ()
    {
      return (indices == null)
	     ? -1
	     : indices[position++];
    }
}
