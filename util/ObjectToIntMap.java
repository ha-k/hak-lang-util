//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

/**
 * @version     Last modified on Tue Jun 19 17:37:59 2012 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */

import java.util.Iterator;

/**
 * This is a class of hash tables mapping ints to ints.
 */

public class ObjectToIntMap extends ToIntMap
{
  /**
   * Constructs a new empty map with a default capacity of <tt>11</tt>.
   */
  public ObjectToIntMap ()
    {
      this(11);
    }

  /**
   * Constructs a new empty map with the specified initial capacity
   * and a default load factor equal to <tt>0.75</tt>.
   *
   * @param   initialCapacity   the initial capacity of the map
   * @throws  IllegalArgumentException if the initial capacity is negative.
   */
  public ObjectToIntMap (int initialCapacity)
    {
      this(initialCapacity,0.75f);
    }

  /**
   * Constructs a new empty map with the specified initial capacity and
   * load factor. If the load factor is greater than <tt>1</tt>, it is
   * reset to 1 (which means that the map is enlarged only when all
   * the table's indices are occupied - generally a bad idea).
   *
   * @param    initialCapacity   the initial capacity of the map
   * @param    loadFactor        the load factor of the map
   * @throws   IllegalArgumentException if either argument is negative.
   */
  public ObjectToIntMap (int initialCapacity, float loadFactor)
    {
      _table = new Entry[_setThreshold(initialCapacity,loadFactor)];
    }
    
  /**
   * Constructs a new map with the same entries as the given map.  The
   * map is created with a capacity of twice the number of entries in
   * the given map or 11 (whichever is greater), and a default load factor,
   * which is <tt>0.75</tt>.
   *
   * @param map the <tt>ObjectToIntMap</tt> whose entries are to be placed in this map.
  */
  public ObjectToIntMap (ObjectToIntMap map)
    {
      this(Math.max(2*map.size(),11));
      include(map);
    }

  /**
   * Returns the entry at the specified index in the table.
   */
  private final Entry _entry (int index)
    {
      return (Entry)_table[index];
    }

  /**
   * Returns the next entry of the specified entry;
   */
  private final Entry _next (Entry entry)
    {
      return (Entry)entry.next;
    }

  /**
   * Returns <tt>true</tt> if this map contains an entry for the specified key.
   *
   * @param key the key whose presence in this map is to be tested.
   */
  public final boolean containsKey (Object key)
    {
      int index = (Misc.hashCode(key) & 0x7FFFFFFF) % _table.length;

      for (Entry entry = _entry(index); entry != null; entry = _next(entry))
        if (Misc.equals(entry.key,key))
          return true;

      return false;
    }

  /**
   * Returns <tt>true</tt> if this map contains an entry with the specified key
   * and value.
   *
   * @param key the key 
   * @param value the value 
   */
  public final boolean containsEntry (Object key, int value)
    {
      return containsEntry(new Entry(key,value));
    }

  /**
   * Returns <tt>true</tt> if this map contains the specified entry.
   *
   * @param candidate the entry whose presence in this map is to be tested.
   */
  public final boolean containsEntry (ToIntMap.Entry candidate)
    {
      Entry mapping = (Entry)candidate;
      int index = (Misc.hashCode(mapping.key) & 0x7FFFFFFF) % _table.length;

      for (Entry entry = _entry(index); entry != null; entry = _next(entry))
        if (Misc.equals(entry.key,mapping.key))
          return (entry.value == mapping.value);

      return false;
    }

  /**
   * Returns the value to which this map maps the specified key.  Returns
   * <tt>NOT_FOUND_VALUE</tt> if the map contains no entry for this key.
   * The value <tt>NOT_FOUND_VALUE</tt> is defined as <tt>Integer.MIN_VALUE</tt>
   * (which is equal to <tt>-2147483648</tt>). Therefore, a return value
   * equal to <tt><tt>NOT_FOUND_VALUE</tt></tt> does not <i>necessarily</i>
   * indicate that the map contains no entry for the key; it's also possible
   * that the map explicitly maps the key to <tt>-2147483648</tt>. In this case,
   * the <tt>containsKey</tt> operation may be used to distinguish these two cases.
   *
   * @param key the key whose associated value is to be returned.
   */
  public final int get (Object key)
    {
      int index = (Misc.hashCode(key) & 0x7FFFFFFF) % _table.length;

      for (Entry entry = _entry(index); entry != null; entry = _next(entry))
        if (Misc.equals(entry.key,key))
          return entry.value;

      return NOT_FOUND_VALUE;
    }

  /**
   * Associates the current size of the map to the given object, and returns this value.
   */
  public final int add (Object object)
    {
      put(object,_size);
      return _size-1;
    }

  /**
   * Associates the specified value with the specified key in this map.
   * If the map previously contained an entry for this key, the old
   * value is replaced and returned; otherwise the new value is returned.
   * The map is automatically enlarged if its size exceeds its threshold.
   *
   * @param key key with which the specified value is to be associated.
   * @param value value to be associated with the specified key.
   */
  public final int put (Object key, int value)
    {
      int index = (Misc.hashCode(key) & 0x7FFFFFFF) % _table.length;

      for (Entry entry = _entry(index) ; entry != null ; entry = _next(entry))
        if (Misc.equals(entry.key,key))
          {
            int old = entry.value;
            entry.value = value;
            return old;
          }

      if (_size >= _threshold)
        {
          _rehash();
          index = (Misc.hashCode(key) & 0x7FFFFFFF) % _table.length;
        }

      _table[index] = new Entry(key,value,_entry(index));
      _size++;

      return value;
    }

  /**
   * Removes the entry for the given key from this map if present.
   * Returns the previous value associated with the specified key, or
   * <tt>NOT_FOUND_VALUE</tt> if there was no entry for the key.  A
   * returned <tt>NOT_FOUND_VALUE</tt> may also indicate that the map
   * previously associated <tt>NOT_FOUND_VALUE</tt> with the specified key.
   *
   * @param key key whose entry is to be removed from the map.
   */
  public final double remove (Object key)
    {
      int index = (Misc.hashCode(key) & 0x7FFFFFFF) % _table.length;

      Entry predecessor = null;
      
      for (Entry entry = _entry(index); entry != null; entry = _next(entry))
        {
          if (Misc.equals(entry.key,key))
            {
              if (predecessor != null)
                predecessor.next = entry.next;
              else
                _table[index] = entry.next;

              _size--;
              return entry.value;
            }
          predecessor = entry;
        }

      return NOT_FOUND_VALUE;
    }

  /**
   * Puts the mapping defined by the specified entry into this map.
   * Returns the old value if one was there, or the new value.
   *
   * @param entry the entry whose mapping is to be put into this map.
   */
  public final int put (ToIntMap.Entry entry)
    {
      Entry mapping = (Entry)entry;
      return put(mapping.key,mapping.value);
    }

  /**
   * Returns an iterator through the keys of this map. The value returned
   * by its <tt>next()</tt> method is an Object.
   */
  public final Iterator keys ()
    {
      return new KeyIterator(iterator());
    }

  /**
   * Returns an iterator through the values of this map. The value returned
   * by its <tt>next()</tt> method is an int.
   */
  public final IntIterator values ()
    {
      return new ValueIterator(iterator());
    }

  /**
   * Compares the specified object with this map for equality. Returns
   * <tt>true</tt> if the given object is also a map and the two maps
   * have equal entries.
   *
   * @param object object to be compared for equality with this map.
   */
  public final boolean equals (Object object)
    {
      if (object == this)
        return true;

      if (!(object instanceof ObjectToIntMap))
        return false;

      return isEqualTo((ObjectToIntMap)object);
    }

  /**
   * The class defining the map entries.
   */
  public static class Entry extends ToIntMap.Entry
    {
      Object key;

      Entry (Object key, int value)
        {
          this.key = key;
          this.value = value;
        }

      Entry (Object key, int value, Entry next)
        {
          this.key = key;
          this.value = value;
          this.next = next;
        }

      public final Object key ()
        {
          return key;
        }

      public final int hash ()
        {
          return Misc.hashCode(key);
        }

      public final boolean equals (Object object)
        {
          if (!(object instanceof Entry))
            return false;

          Entry entry = (Entry)object;

          return Misc.equals(key,entry.key) && value == entry.value;
        }

      public final int hashCode ()
        {
          return Misc.hashCode(key) ^ value;
        }

      public final String toString ()
        {
          return key + "=" + value;
        }
    }

  /**
   * The class defining the iterator through the map's keys.
   */
  private static class KeyIterator implements Iterator
    {
      private Iterator _entries;

      KeyIterator (Iterator entries)
        {
          _entries = entries;
        }

      public final boolean hasNext ()
        {
          return _entries.hasNext();
        }

      public final Object next ()
        {
          return ((Entry)_entries.next()).key;
        }

    public final void remove ()
      {
        throw new UnsupportedOperationException();
      }
  }


}
