//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

import java.util.Iterator;

/**
 * This implements a stack of objects, with a finite capacity. If pushing a
 * new object runs over the capacity, room is made for it by discarding the
 * oldest object in the stack (returning it as the return value of the
 * overflowing <tt>push</tt>). This class is useful as a ``forgetful'' stack
 * which may memorize objects only up to a finite history.
 *
 * @version     Last modified on Tue Jun 19 17:36:35 2012 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */
public class FiniteStack implements ViewableStack
{
  protected Object[] container;         // slot container

  protected int capacity = 21;          // total number of assignable slots + 1 free cell
  protected int free     = 0;           // index of the free cell
  protected int latest   = free;        // most recently assigned slot
  protected int oldest   = free;        // least recently assigned slot

  /**
   * Constructs a <tt>FiniteStack</tt> object.
   */
  public FiniteStack ()
    {
      container = new Object[capacity];
    }

  /**
   * Constructs a <tt>FiniteStack</tt> object of specified capacity.
   */
  public FiniteStack (int capacity)
    {
      container = new Object[this.capacity=capacity+1]; // plus one for the free cell.
    }

  /**
   * Returns the index preceding the specified <tt>index</tt>, modulo
   * <tt>capacity</tt>.
   */
  protected final int prec (int index)
    {
      return (index+capacity-1)%capacity;
    }

  /**
   * Returns the index following the specified <tt>index</tt>, modulo
   * <tt>capacity</tt>.
   */
  protected final int succ (int index)
    {
      return (index+1)%capacity;
    }

  // NB: The stack is empty iff free==latest; otherwise, free==succ(latest).

  /**
   * Returns <tt>true</tt> iff this <tt>FiniteStack</tt> is empty.
   */
  public final boolean isEmpty ()
    {
      return (latest == free);  // NB: (oldest == free) as well.
    }

  /**
   * Returns the current total number of assignable slots.
   */
  public final int capacity ()
    {
      return capacity-1;        // minus one for the free cell.
    }

  /**
   * Returns the current number of assigned slots.
   */
  public final int size ()
    {
      if (isEmpty()) return 0;
      
      int size = latest-oldest+1;
      return size >= 0 ? size : capacity+size;
    }

  /**
   * Returns <tt>true</tt> iff this <tt>FiniteStack</tt> is full.
   */
  public final boolean isFull ()
    {
      return (free == prec(oldest));
    }

  /**
   * Erases all entries of this <tt>FiniteStack</tt>.
   */
  public final void flush ()
    {
      while (!isEmpty()) pop();
      oldest = latest = free = 0;
    }

  /**
   * Returns the latest object pushed onto this <tt>FiniteStack</tt> without
   * removing it, or <tt>null</tt> if it is empty.
   */
  public final Object peek ()
    {
      return latest();
    }

  /**
   * Returns the latest object pushed onto this <tt>FiniteStack</tt> without
   * removing it, or <tt>null</tt> if it is empty.
   */
  public final Object latest ()
    {
      return isEmpty() ? null : container[latest];
    }

  /**
   * Returns the oldest object of this <tt>FiniteStack</tt> without removing it,
   * or <tt>null</tt> if it is empty.
   */
  public final Object oldest ()
    {
      return isEmpty() ? null : container[oldest];
    }

  /**
   * Returns the index of the latest object in this stack, or <tt>-1</tt> if
   * the stack is empty.
   */
  public final int latestIndex ()
    {
      return isEmpty() ? -1 : latest;
    }

  /**
   * Returns the index of the oldest object in this stack, or <tt>-1</tt> if
   * the stack is empty.
   */
  public final int oldestIndex ()
    {
      return isEmpty() ? -1 : oldest;
    }

  /**
   * Returns true iff the specified index is between that of the oldest and
   * the latest, inclusively, <i>modulo</i> <tt>capacity</tt>.
   */
  protected final boolean isValidIndex (int index)
    {
      if (isEmpty() || index < 0 || index >= capacity)
	return false;

      if (oldest <= latest)
	return (oldest <= index && index <= latest);

      return !(free < index && index < oldest);
    }

  /**
   * Returns the object at the specified index where 0 is the index of
   * the oldest element, and <tt>size()-1</tt> that of the latest
   * element.
   */
  public final Object get (int index)
    {
      return container[(oldest+index)%capacity];
    }

  /**
   * Removes and returns the latest object pushed onto this <tt>FiniteStack</tt>;
   * returns <tt>null</tt> if it is empty.
   */
  public final Object pop ()
    {
      if (isEmpty())
	return null;

      Object value = container[latest];
      container[latest] = null;
      free = latest;
      if (latest != oldest)
	latest = prec(latest);

      return value;
    }  

  /**
   * Removes and returns the oldest object of this <tt>FiniteStack</tt>;
   * returns <tt>null</tt> if it is empty.
   */
  public final Object drop ()
    {
      if (isEmpty())
	return null;

      Object value = container[oldest];
      container[oldest] = null;
      if (oldest == latest)
        free = oldest;
      else
        oldest = succ(oldest);
      return value;
    }  

  /**
   * Pushes the specified object onto this <tt>FiniteStack</tt>. If the
   * capacity is exceeded, removes and returns the oldest object in the
   * stack. Otherwise, returns <tt>null</tt>.
   */
  public final Object push (Object object)
    {
      Object spill = null;

      if (isFull())
        {
          spill = container[oldest];
          oldest = succ(oldest);
        }

      container[latest = free] = object;
      free = succ(free);
      return spill;
    }

  /**
   * Sets the total number of assignable slots to the specified capacity.
   * If the current number of assigned slots is larger than the specified
   * capacity, slots older than the specified capacity are lost.
   */
  public final void setCapacity (int capacity)
    {
      Object[] container = new Object[capacity+1];      // plus one for the free cell.
      int size = Math.min(size(),capacity);
      int j = latest;
      for (int i = size-1; i >= 0; i--)
        {
          container[i] = this.container[j];
          j = prec(j);
        }
      this.container = container;
      this.capacity = capacity+1;
      oldest = 0;
      latest = size-1;
      free = size;
    }

  /**
   * Resizes this <tt>FiniteStack</tt>'s container to the current number of
   * assigned slots. If the stack is empty or full, then this does nothing.
   */
  public final void setToSize ()
    {
      if (isEmpty() || isFull()) return;
      setCapacity(size());
    }

  /**
   * Returns an iterator for this <tt>FiniteStack</tt>.
   * The stack elements are visited from newest to oldest.
   */
  public final Iterator iterator ()
    {
      return new FiniteStackIterator();
    }

  /**
   * Returns a printable string representation of this <tt>FiniteStack</tt>.
   * The stack elements are listed from newest to oldest.
   */
  public String toString ()
    {
      StringBuilder s = new StringBuilder("[");
      for (Iterator i=iterator(); i.hasNext();)
	{
	  s.append(i.next());
	  if (i.hasNext())
	    s.append(", ");
	}
      s.append("]");
      return s.toString();        
    }

  private class FiniteStackIterator implements Iterator
    {
      int nextIndex = latest;

      public boolean hasNext ()
	{
	  return !(nextIndex == free || container[nextIndex] == null);
	}

      public Object next ()
	{
	  Object item = container[nextIndex];
	  nextIndex = prec(nextIndex);
	  return item;
	}

      public final void remove ()
        {
          throw new UnsupportedOperationException();
        }
    }  
}
