//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

/**
 * @version     Last modified on Fri Oct 19 17:29:04 2012 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */

import java.util.HashMap;
import java.util.Iterator;

/**
 * Extends HashMap with equals and hashCode methods to
 * allow "higher-order" maps (i.e., Maps of Maps).
 * <p>
 * <b>NB:</b> Of course, testing and storing a Map works only if it is
 * immutable (nothing more is put into or removed from it after
 * being initially filled). This is enforced by setting a lock
 * as soon as either equals or hashCode is used on a given Map.
 */

public class Map extends HashMap
{
  public Map ()
    {
      super();
    }
  
  public Map (int initialCapacity)
    {
      super(initialCapacity);
    }
  
  public Map (int initialCapacity, float loadFactor)
    {
      super(initialCapacity,loadFactor);
    }

  private boolean locked = false;

  private int hashcode = 0;

  public final void lock ()
    {
      locked = true;
    }

  public final boolean isLocked ()
    {
      return locked;
    }

  public int hashCode ()
    {
      if (!locked)
	{
	  locked = true;
	  int count = 0;
	  for (Iterator i = entrySet().iterator(); i.hasNext();)
	    { // The xor of the hash codes of the first 10 entries:
	      if (++count > 10)
		break;
	      hashcode ^= i.next().hashCode();
	    }
	}

      return hashcode;
    }

  public boolean equals (Object other)
    {
      locked = true;

      if (this == other)
        return true;

      if (!(other instanceof Map))
        return false;

      Map that = (Map)other;

      that.locked = true;

      if (this.size() != that.size())
        return false;

      for (Iterator i = entrySet().iterator(); i.hasNext();)
        { 
          java.util.Map.Entry entry = (java.util.Map.Entry)i.next();
          if (!entry.getValue().equals(that.get(entry.getKey())))
            return false;
        }

      return true;
    }

  public void clear ()
    {
      if (locked)
        throw new LockedMapException("Can't clear a locked Map");
      super.clear();
    }

  public Object put (Object k, Object o)
    {
      if (locked)
        throw new LockedMapException("Can't put into a locked Map");
      return super.put(k,o);
    }

  public Object remove (Object o)
    {
      if (locked)
        throw new LockedMapException("Can't remove from a locked Map");
      return super.remove(o);
    }
}

