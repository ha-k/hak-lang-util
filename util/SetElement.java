// FILE. . . . . d:/hak/hlt/src/hlt/language/util/SetElement.java
// EDIT BY . . . Hassan Ait-Kaci
// ON MACHINE. . Hp-Zbook
// STARTED ON. . Fri Mar 30 04:00:25 2018

// Last modified on Fri May 11 10:00:02 2018 by hak

package hlt.language.util;

/**
 * This is a class wrapper for objects that are elements of a set.
 * It is meant to cache the index of an indexed object in a context
 * where this object has this index (a non-negative <tt>int</tt> if
 * it does, and <tt>-1</tt> if not.) Note that a <tt>SetElement</tt>
 * can have an index but no object. This essentially means that the
 * context associates no object to this index.
 *
 * THIS CLASS IS NOT USED FOR NOW - See notes:
 *
 * Note to myself: I need to rewrite (and test!) all of SetOf using this
 * instead of raw Object as element. (This would allow fuzzy sets of int
 * or double.)
 *
 * Note later ... just make it an instance of Object with the desired
 * additional fields ... no need to wrap itself!
 */

public class SetElement
{
  protected int index = -1;

  protected Object object = null;

  public SetElement (int index)
  {
    this.index = index;
  }
  
  public SetElement (int index, Object object)
  {
    this.index = index;
    this.object = object;
  }
  
  final public int index ()
  {
    return this.index;
  }

  final public Object object ()
  {
    return this.object;
  }

  final public SetElement setIndex (int index)
  {
    this.index = index;
    return this;
  }

  final public SetElement setObject (Object object)
  {
    this.object = object;
    return this;
  }

  public boolean equals (Object other)
  {
    if ((other instanceof SetElement) && (((Indexed)other).index == index))
      return true;
      
    return false;
  }

  public String toString ()
  {
    return object+"["+index+"]";
  }
}
