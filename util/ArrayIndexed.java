//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

/**
 * This class is meant to be subclassed by the class of objects
 * identified by an index in an array collection.
 *
 * @version	Last modified on Tue Jun 19 17:36:05 2012 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright	&copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */

public class ArrayIndexed extends Indexed
{
  public Object[] set;	/* reference set */

  public ArrayIndexed (Object[] set)
    {
      this.set = set;
    }

  public ArrayIndexed (Object[] set, int index)
    {
      this.set = set;
      set[this.index = index] = this;
    }
  
  public boolean equals (Object other)
    {
      if (this == other)
          return true;

      if (!(other instanceof ArrayIndexed))
          return false;

      return this.set   == ((ArrayIndexed)other).set
	  && this.index == ((ArrayIndexed)other).index;
    }
}
