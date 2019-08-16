//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

/**
 * @version     Last modified on Tue Jun 19 17:37:44 2012 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */

/**
 * This class implements a stack of ints using a Lisp-style list representation.
 */

public class LinkedIntStack extends LinkedStack
{
  public final void push (int n)
    {
      Cell cell = new Cell(n);
      cell.next = _top;
      _top = cell;
    }

  public final int pop ()
    {
      if (_top == null)
        throw new EmptyStackException();

      int n = ((Cell)_top).contents;
      _top = _top.next;

      return n;
    }

  public final int peek ()
    {
      if (_top == null)
        throw new EmptyStackException();

      return ((Cell)_top).contents;
    }

  public final int peek (int n)
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

  public final IntIterator iterator ()
    {
      return new LinkedIntStackIterator((Cell)_top);
    }

  public final String toString ()
    {
      StringBuilder buf = new StringBuilder("[");

      for (IntIterator i=iterator(); i.hasNext();)
        {
          buf.append(i.next());
          if (i.hasNext()) buf.append(",");
        }

      return buf.append("]").toString();
    }

  public static class Cell extends LinkedStack.Cell
    {
      int contents;

      Cell (int n)
        {
          contents = n;
        }
    }

  private static class LinkedIntStackIterator implements IntIterator
    {
      private Cell _current;

      public LinkedIntStackIterator (Cell cell)
        {
          _current = cell;
        }

      public final boolean hasNext ()
        {
          return _current != null;
        }

      public final int next ()
        {
          int n = _current.contents;
          _current = (Cell)_current.next;
          return n;
        }
    }
}
