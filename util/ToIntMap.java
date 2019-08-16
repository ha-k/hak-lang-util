//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

/**
 * @version     Last modified on Tue Jun 19 17:40:07 2012 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */

import java.util.Iterator;

/**
 * This is the mother of the classes of hash tables mapping to ints.
 */

abstract public class ToIntMap
{
  /**
   * The value returned when a key has no mapping.
   */
  public final static int NOT_FOUND_VALUE = Integer.MIN_VALUE;

  /**
   * The table storing the entries of this map.
   */
  protected Entry[] _table;

  /**
   * The number of entries in this map.
   */
  protected int _size;

  /**
   * The load factor of this map.
   */
  protected float _loadFactor;

  /**
   * The value of this field is <tt>_table.length x _loadFactor</tt>.
   * The map is automatically rehashed when its size exceeds this threshold.  
   */
  protected int _threshold;

  /**
   * Sets the threshold factor with the specified initial capacity and
   * load factor. If the load factor is greater than <tt>1</tt>, it is
   * reset to 1 (which means that the map is enlarged only when all
   * the table's indices are occupied - generally a bad idea). Returns
   * the maximum of <tt>1</tt> and the specified initial capacity.
   *
   * @param    initialCapacity   the initial capacity of the map
   * @param    loadFactor        the load factor of the map
   * @throws   IllegalArgumentException if either argument is negative.
   */
  protected final int _setThreshold (int initialCapacity, float loadFactor)
    {
      if (initialCapacity < 0)
        throw new IllegalArgumentException("illegal initial capacity: "+
                                           initialCapacity);

      if (loadFactor <= 0 || Float.isNaN(loadFactor))
        throw new IllegalArgumentException("illegal load factor: "+
                                           loadFactor);

      if (initialCapacity==0) initialCapacity = 1;

      _threshold = (int)(initialCapacity * (_loadFactor = Math.min(1,loadFactor)));

      return initialCapacity;
    }

  /**
   * Returns the number of key-value entries in this map.
   */
  public final int size ()
    {
      return _size;
    }

  /**
   * Returns <tt>true</tt> iff this map is empty.
   */
  public final boolean isEmpty ()
    {
      return _size == 0;
    }

  /**
   * Returns <tt>true</tt> if this map maps one or more keys to the
   * specified value.
   *
   * @param value the value whose presence in this map is to be tested.
   */
  public final boolean containsValue (int value)
    {
      for (int i=0; i<_table.length; i++)
        for (Entry entry = _table[i]; entry != null; entry = entry.next)
          if (value == entry.value)
            return true;

      return false;
    }

  /**
   * Returns <tt>true</tt> if this map contains the specified entry.
   *
   * @param candidate the entry whose presence in this map is to be tested.
   */
  abstract public boolean containsEntry (Entry candidate);

  /**
   * Rehashes the contents of this map into a new map with a larger capacity.
   * This method is called automatically when the number of keys in this map
   * exceeds the threshold indicated by the capacity times the load factor.
   */
  protected final void _rehash ()
    {
      int oldCapacity = _table.length;
      Entry[] oldTable = _table;

      _table = new Entry[2*oldCapacity + 1];
      _threshold = (int)(_table.length * _loadFactor);

      for (int i=0; i<oldCapacity; i++)
        for (Entry oldEntry = oldTable[i]; oldEntry != null;)
          {
            Entry entry = oldEntry;
            oldEntry = oldEntry.next;

            int index = (entry.hash() & 0x7FFFFFFF) % _table.length;
            entry.next = _table[index];
            _table[index] = entry;
          }
    }

  /**
   * Puts the mapping defined by the specified entry into this map.
   * Returns the old value if one was there, or the new value.
   *
   * @param entry the entry whose mapping is to be put into this map.
   */
  abstract public int put (Entry entry);

  /**
   * Includes all of the entries from the specified map to this one.
   * These entries replace any entries that this map had for any of the
   * keys currently in the specified map.
   *
   * @param map <tt>IntToIntMap</tt> whose entries will be stored in this map.
   */
  public final void include (ToIntMap map)
    {
      for (Iterator i = map.iterator(); i.hasNext();)
        put((Entry)i.next());
    }

  /**
   * Removes all entries from this map.
   */
  public final void clear ()
    {
      for (int i=0; i<_table.length; i++)
        _table[i] = null;
      _size = 0;
    }

  /**
   * Returns an iterator through the entries of this map. The object returned
   * by its <tt>next()</tt> method is a <tt>ToIntMap.Entry</tt>.
   */
  public final Iterator iterator ()
    {
      return new MapIterator(_table);
    }

  /**
   * Returns an iterator through the values of this map. The value returned
   * by its <tt>next()</tt> method is an int.
   */
  abstract public IntIterator values ();

  /**
   * Compares the specified object with this map for equality.  Returns
   * <tt>true</tt> if the given object is also a map and the two maps
   * have equal entries.
   *
   * @param object object to be compared for equality with this map.
   */
  public boolean equals (Object object)
    {
      if (object == this)
        return true;

      if (!(object instanceof ToIntMap))
        return false;

      return isEqualTo((ToIntMap)object);
    }

  /**
   * Compares the specified map object with this map for equality.
   *
   * @param map map to be compared for equality with this map.
   */
  public final boolean isEqualTo (ToIntMap map)
    {
      if (map == this)
        return true;

      if (map.size() != size())
        return false;

      for (Iterator i = iterator(); i.hasNext();)
        if (!map.containsEntry((Entry)i.next()))
          return false;

      return true;
    }

  /**
   * Returns the hash code value for this map. The hash code of a map is
   * defined to be the exclusive or of the hash codes of each entry.
   */
  public final int hashCode ()
    {
      int code = 0;
      for (Iterator i = iterator(); i.hasNext();)
        code ^= i.next().hashCode();
      return code;
    }

  /**
   * Returns a string form for this map.
   */
  public final String toString ()
    {
      StringBuilder buf = new StringBuilder("{");

      for (Iterator i = iterator(); i.hasNext();)
        buf.append(i.next() + (i.hasNext() ? ", " : ""));

      return buf.append("}").toString();
    }

  /**
   * The class defining the map entries.
   */
  abstract public static class Entry implements Comparable
    {
      protected int value;
      protected Entry next;

      abstract int hash ();

      public final int setValue (int v)
        {
          return value = v;
        }

      public final int value ()
        {
          return value;
        }

      public boolean lessThan (Comparable other)
        {
          if (!(other instanceof Entry))
            return false;

          return value < ((Entry)other).value();
        }
    }

  /**
   * Class defining an iterator through the map's entries.
   */
  private static class MapIterator implements Iterator
    {
      private Entry[] _table;
      private Entry _next = null;
      private int _index = -1;

      MapIterator (Entry[] table)
        {
          _table = table;
        }

      public final boolean hasNext ()
        {
          while (_next == null && ++_index < _table.length)
            _next = _table[_index];

          return _next != null;
        }

      public final Object next ()
        {
          Entry entry = _next;
          _next = _next.next;
          return entry;
        }

    public final void remove ()
      {
        throw new UnsupportedOperationException();
      }
  }

  /**
   * Class defining an iterator through the map's values.
   */
  protected static class ValueIterator implements IntIterator
    {
      private Iterator _entries;

      ValueIterator (Iterator entries)
        {
          _entries = entries;
        }

      public final boolean hasNext ()
        {
          return _entries.hasNext();
        }

      public final int next ()
        {
          return ((Entry)_entries.next()).value;
        }
  }
}
