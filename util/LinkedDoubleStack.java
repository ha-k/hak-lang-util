//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

/**
 * @version     Last modified on Tue Jun 19 17:37:43 2012 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */

/**
 * This class implements a stack of ints using a Lisp-style list representation.
 */

public class LinkedDoubleStack extends LinkedStack
{
  public final void push (double x)
    {
      Cell cell = new Cell(x);
      cell.next = _top;
      _top = cell;
    }

  public final double pop ()
    {
      if (_top == null)
        throw new EmptyStackException();

      double x = ((Cell)_top).contents;
      _top = _top.next;

      return x;
    }

  public final double peek ()
    {
      if (_top == null)
        throw new EmptyStackException();

      return ((Cell)_top).contents;
    }

  public final double peek (int n)
    {
      if (n < 0)
        throw new StackPeekException("`negative index: "+n);

      LinkedStack.Cell cell = _top;
      int depth = n;

      for (;;depth--,cell = cell.next)
        {
          if (cell == null)
            throw new EmptyStackException();

          if (depth == 0)
            return ((Cell)cell).contents;
        }
    }

  public final DoubleIterator iterator ()
    {
      return new LinkedDoubleStackIterator((Cell)_top);
    }

  public final String toString ()
    {
      StringBuilder buf = new StringBuilder("[");

      for (DoubleIterator i=iterator(); i.hasNext();)
        {
          buf.append(i.next());
          if (i.hasNext()) buf.append(",");
        }

      return buf.append("]").toString();
    }

  public static class Cell extends LinkedStack.Cell
    {
      double contents;

      Cell (double x)
        {
          contents = x;
        }
    }

  private static class LinkedDoubleStackIterator implements DoubleIterator
    {
      private Cell _current;

      public LinkedDoubleStackIterator (Cell cell)
        {
          _current = cell;
        }

      public final boolean hasNext ()
        {
          return _current != null;
        }

      public final double next ()
        {
          double x = _current.contents;
          _current = (Cell)_current.next;
          return x;
        }
    }
}
