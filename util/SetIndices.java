//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

/**
 * This provides an enumeration through the indices of <tt>SetOf</tt> objects.
 *
 * @see		SetOf
 *
 * @version	Last modified on Tue Jun 19 17:38:14 2012 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright	&copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */

public class SetIndices implements IndexEnumeration
{
  // NB: This enumeration is not altered if the set is modified
  // after constructing the enumeration. In other words, it wil
  // enumerate all and only the elements of the original set.

  private SetOf set;
  private int position = 0;
  private int[] indices;

  SetIndices (SetOf set)
    {
      if (set.indices == null) set.buildIndices();

      this.indices = set.indices;
      this.set = set;
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
