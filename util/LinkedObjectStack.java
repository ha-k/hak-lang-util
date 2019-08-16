//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

/**
 * @version     Last modified on Tue Jun 19 17:37:49 2012 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */

import java.util.Iterator;

/**
 * This class implements a stack of objects using a Lisp-style list representation.
 */

public class LinkedObjectStack extends LinkedStack
{
  public final void push (Object object)
    {
      Cell cell = new Cell(object);
      cell.next = _top;
      _top = cell;
    }

  public final Object pop ()
    {
      if (_top == null)
        throw new EmptyStackException();

      Object object = ((Cell)_top).contents;
      _top = _top.next;

      return object;
    }

  public final Object peek ()
    {
      if (_top == null)
        throw new EmptyStackException();

      return ((Cell)_top).contents;
    }

  public final Object peek (int n)
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

  public final Iterator iterator ()
    {
      return new LinkedObjectStackIterator((Cell)_top);
    }

  public final String toString ()
    {
      StringBuilder buf = new StringBuilder("[");

      for (Iterator i=iterator(); i.hasNext();)
        {
          buf.append(i.next());
          if (i.hasNext()) buf.append(",");
        }

      return buf.append("]").toString();
    }

  public static class Cell extends LinkedStack.Cell
    {
      Object contents;

      Cell (Object object)
        {
          contents = object;
        }
    }

  private static class LinkedObjectStackIterator implements Iterator
    {
      private Cell _current;

      public LinkedObjectStackIterator (Cell cell)
        {
          _current = cell;
        }

      public final boolean hasNext ()
        {
          return _current != null;
        }

      public final Object next ()
        {
          Object object = _current.contents;
          _current = (Cell)_current.next;
          return object;
        }

      public final void remove ()
        {
          throw new UnsupportedOperationException();
        }
    }
}
