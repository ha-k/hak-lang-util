//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

/**
 * @version     Last modified on Tue Jun 19 17:37:51 2012 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */

/**
 * This class specifies an abstract stack represented as a Lisp-style list.
 * It is the mother of:
 * <ul>
 * <li> <a href="LinkedIntStack.html"><tt>LinkedIntStack</tt></a>
 * <li> <a href="LinkedDoubleStack.html"><tt>LinkedDoubleStack</tt></a>
 * <li> <a href="LinkedObjectStack.html"><tt>LinkedObjectStack</tt></a>
 * </ul>
 */

abstract public class LinkedStack
{
  protected Cell _top;

  public final void set (Cell cell)
    {
      _top = cell;
    }

  public final Cell top ()
    {
      return _top;
    }

  public final Cell top (int n)
    {
      if (n < 0)
        throw new StackPeekException("`negative index: "+n);

      Cell cell = _top;
      int depth = n;

      for (;;depth--,cell = cell.next)
        {
          if (cell == null)
            throw new EmptyStackException();

          if (depth == 0)
            return cell;
        }
    }

  public final boolean isEmpty ()
    {
      return _top == null;
    }

  public final void clear ()
    {
      _top = null;
    }

  protected abstract static class Cell
    {
      Cell next;      
    }
}
