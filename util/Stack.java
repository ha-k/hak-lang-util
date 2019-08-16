//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

import java.util.Iterator;

/**
 * @version     Last modified on Fri Mar 11 12:18:23 2016 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */

/**
 * This class implements an unsynchronized <tt>Stack</tt>. It has the
 * exact same API as that of <tt>java.util.Stack</tt> and one may use
 * this in its stead when one does not want to pay the price of mutex
 * synchronization and unneeded range check overhead for every stack
 * operation. In addition, it adds two new constructors that allow to
 * specify the stack's initial capacity.
 */

public class Stack extends ArrayList implements ViewableStack
{
  /**
   * Constructs an empty Stack.
   */
  public Stack ()
    {
      super();
    }

  /**
   * Constructs an empty stack with the specified initial capacity and
   * with its capacity increment equal to zero.
   *
   * <p>
   * @param     initialCapacity the initial capacity of the stack.
   * @exception IllegalArgumentException if the specified initial capacity is negative.
   */
  public Stack (int initialCapacity)
    {
      super(initialCapacity);
    }

  /**
   * Pushes an item onto the top of this stack. This has exactly the
   * same effect as: <tt>add(item)</tt>.
   *
   * @param   item the item to be pushed onto this stack.
   * @return  the <tt>item</tt> argument.
   */
  public final Object push (Object item)
    {
      add(item);
      return item;
    }

  /**
   * Removes the object at the top of this stack and returns that
   * object as the value of this function.
   *
   * @return     The object at the top of this stack.
   * @exception  ArrayIndexOutOfBoundsException if this stack is empty.
   */
  public final Object pop ()
    {
      return _elementData[--_size];
    }

  /**
   * Looks at the object at the top of this stack without removing it
   * from the stack.
   *
   * @return     the object at the top of this stack.
   * @exception  ArrayIndexOutOfBoundsException  if this stack is empty.
   */
  public final Object peek ()
    {
      return _elementData[_size-1];
    }

  /**
   * Peeks at n positions from the top of the stack.
   *
   * @param n the offset from the top (0 -&gt; top, 1 -&gt; top-1,...)
   * @exception ArrayIndexOutOfBoundsException if index out of range.
   */
  public final Object peek (int n)
    {
      return _elementData[_size-n-1];
    }

  /**
   * Replaces the object at n positions from the top of the stack
   * with a new one; returns the old object.
   *
   * @param n the offset from the top (0 -&gt; top, 1 -&gt; top-1,...)
   * @param newObject the new object
   * @return the old object at that position
   * @exception ArrayIndexOutOfBoundsException if index out of range.
   */
  public final Object replace (int n, Object newObject)
    {
      int pos = _size-n-1;

      Object oldObject = _elementData[pos];
      _elementData[pos] = newObject;
      return oldObject;
    }

  /**
   * Tests if this stack is empty.
   *
   * @return  <tt>true</tt> iff this contains no items; <tt>false</tt> otherwise.
   */
  public final boolean empty ()
    {
      return _size == 0;
    }

  /**
   * Returns the 1-based position where an object is on this stack.
   * If the object <tt>o</tt> occurs as an item in this stack, this
   * method returns the distance from the top of the stack of the
   * occurrence nearest the top of the stack; the topmost item on the
   * stack is considered to be at distance <tt>1</tt>. The <tt>equals</tt>
   * method is used to compare <tt>o</tt> to the
   * items in this stack.
   *
   * @param   o   the desired object.
   * @return  the 1-based position of the object from the top, or -1 if not found.
   */
  public final int search (Object o)
    {
      for (int i=1; i<=_size; i++)
        if (o.equals(_elementData[_size-i])) return i;
      return -1;
    }

  /**
   * Returns an iterator for this <tt>Stack</tt>.
   * The stack elements are visited from newest to oldest.
   */
  public Iterator iterator ()
    {
      return new StackIterator();
    }

  private class StackIterator implements Iterator
    {
      private int _nextIndex;

      StackIterator ()
        {
	  _nextIndex = _size-1;
        }

      public final boolean hasNext ()
        {
          return _nextIndex >= 0;
        }

      public final Object next ()
        {
          return _elementData[_nextIndex--];
        }
      
      public final void remove ()
        {
          throw new UnsupportedOperationException();
        }      
    }

}
