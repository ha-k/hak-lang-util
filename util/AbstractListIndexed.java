//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

/**
 * This class is meant to be subclassed by the class of objects
 * identified by an index in an Arraylist collection.
 *
 * @version     Last modified on Fri Oct 19 17:37:27 2012 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */

import java.util.AbstractList;

public class AbstractListIndexed extends Indexed
{
  AbstractList set; /* reference set */

  public AbstractListIndexed (AbstractList set)
    {
      this.set = set;
    }

  public AbstractListIndexed (AbstractList set, int index)
    {
      this.set = set;
      this.index = index;
      set.add(this);
    }

  public void add ()
    {
      this.index = set.size();
      set.add(this);
    }

  public boolean equals (Object other)
    {
      if (this == other)
          return true;

      if (!(other instanceof AbstractListIndexed))
          return false;

      return this.set   == ((AbstractListIndexed)other).set
	  && this.index == ((AbstractListIndexed)other).index;
    }
}
