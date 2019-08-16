//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

/**
 * This class implements a stack of <tt>double</tt>s (as opposed to
 * <tt>Double</tt>s). It uses the same API as <tt>java.util.Stack</tt>
 * except that the primitive type <tt>double</tt> is used instead of <tt>Object</tt>.
 *
 * @version     Last modified on Fri Apr 17 15:49:54 2015 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */

public class DoubleStack extends DoubleArrayList
{
  // CONSTRUCTORS:

  /**
   * Constructs a new  empty DoubleStack.
   */
  public DoubleStack ()
    {
      super();
    }

  /**
   * Constructs an empty stack with the specified initial capacity and
   * capacity increment.
   *
   * @param     initialCapacity the initial capacity of the stack.
   * @param     capacityIncrement amount by which the capacity is increased on overflow.
   * @exception IllegalArgumentException if the specified initial capacity is negative.
   */
  public DoubleStack (int initialCapacity, int capacityIncrement)
    {
      super(initialCapacity, capacityIncrement);
    }

  /**
   * Constructs an empty stack with the specified initial capacity and
   * with its capacity increment equal to zero.
   *
   * @param     initialCapacity the initial capacity of the stack.
   * @exception IllegalArgumentException if the specified initial capacity is negative.
   */
  public DoubleStack (int initialCapacity)
    {
      super(initialCapacity);
    }

  // METHODS:

  /**
   * Pushes an item onto the top of this stack.
   *
   * @param	item the item to be pushed onto this stack.
   * @return    the item argument.
   */
   public final double push (double item)
    {
      add(item);
      return item;
    }

  /**
   * Removes the double at the top of this stack and returns it.
   *
   * @return    the double at the top of this stack.
   * @exception ArrayIndexOutOfBoundsException if this stack is empty.
   */
  public final double pop ()
    {
      return elementData[--elementCount];
    }

  /**
   * Looks at the object at the top of this stack without removing it
   * from the stack.
   *
   * @return    the object at the top of this stack.
   * @exception ArrayIndexOutOfBoundsException if this stack is empty.
   */
  public final double peek ()
    {
      return elementData[elementCount-1];
    }

  /**
   * Peeks at n positions from the top of the stack.
   *
   * @param n the offset from the top (0 is top, 1 is top-1,...)
   * @return the double that is at n positions below the top
   * @exception ArrayIndexOutOfBoundsException if index out of range.
   */
  public final double peek (int n)
    {
      return elementData[elementCount-n-1];
    }

  /**
   * Replaces the element at n positions from the top of the stack
   * with a new one; returns the old element.
   *
   * @param n the offset from the top (0 is top, 1 is top-1,...)
   * @param e the new element
   * @return the old element
   * @exception ArrayIndexOutOfBoundsException if index out of range.
   */
  public final double replace (int n, double e)
    {
      int pos = elementCount-n-1;

      double oldElement = elementData[pos];
      elementData[pos] = e;
      return oldElement;
    }

  /**
   * Tests if this stack is empty.
   *
   * @return    true if this stack contains no items; false otherwise.
   */
  public final boolean empty ()
    {
      return elementCount == 0;
    }

  /**
   * Returns the 1-based position wherethe specified double is on this
   * stack. If the double occurs as an item in this stack, this method
   * returns the distance from the top of the stack of the occurrence
   * nearest the top of the stack; the topmost item on the stack is
   * considered to be at distance 1.
   *
   * @param	d the desired double.
   * @return    the offset from top where the object is located; -1 if not found.
   */
  public final int search (double d)
    {
      for (int i=1; i<=elementCount; i++)
        if (d == elementData[elementCount-i]) return i;

      return -1;
    }

  /**
   * Returns a string representation of this Stack.
   * Note that a stack is written top-first!
   *
   * @return    a string representation of this collection.
   */
  public String toString ()
    {
      StringBuilder buf = new StringBuilder("[");

      for (int i=0; i<elementCount; i++)
        buf.append(elementData[elementCount-i-1]+(i==elementCount-1?"":","));

      return (buf.append("]")).toString();
    }
}

