//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

import java.util.HashMap;

/**
 * This class is a concrete implementation of the abstract class <a
 * href="RegExp.html"><tt>RegExp</tt></a>. It is meant to represent an
 * alphabet symbol. It is simply a wrapper for an object to be used as
 * an alphabet symbol in a RE. Alphabet symbols are uniquely
 * represented. So this also provides a factory to return a unique
 * symbol from a specified object (using <tt>equals</tt> on the
 * objects). Hence, it has no public constructor.
 *
 *
 * @version     Last modified on Mon Mar 26 10:20:59 2018 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */
public class RegExpSymbol extends RegExp
{
  /**
   * Returns the unique canonical object called <tt>RegExpSymbol.EMPTY</tt>
   * representing the empty symbol <tt>()</tt>.
   */
  public static final RegExpSymbol EMPTY = new RegExpSymbol("()");

  /**
   * Returns a shallow copy of this <tt>RegExpSymbol</tt>; since
   * <tt>RegExpSymbol</tt>s are unique, it is in fact itself.
   *
   * @return this <tt>RegExpSymbol</tt>
   */
  public final RegExp shallowCopy ()
    {
      return this;
    }      

  /**
   * Returns a deep copy of this <tt>RegExpSymbol</tt>; since
   * <tt>RegExpSymbol</tt>s are unique, it is in fact itself.
   *
   * @return this <tt>RegExpSymbol</tt>
   */
  public final RegExp deepCopy ()
    {
      return this;
    }      

  /**
   * Returns an <tt>int</tt> denoting the type of this
   * <tt>RegExpSymbol</tt>: <tt>RegExp.EMPTY_EXP</tt> if this is the
   * empty symbol; <tt>RegExp.SYMBOL_EXP</tt> otherwise.
   *
   * @return this <tt>RegExpSymbol</tt>'s type (an <tt>int</tt>)
   */
  public final int type ()
    {
      return isEmpty() ? EMPTY_EXP : SYMBOL_EXP;
    }

  /**
   * Returns the object wrapped by this <tt>RegExpSymbol</tt>.
   *
   * @return the wrapped <tt>Object</tt>
   */
  private Object _object = null;

  /**
   * Returns this <tt>RegExpSymbol</tt>'s object.
   */
  public final Object object ()
    {
      return _object;
    }

  /**
   * The name of this <tt>RegExpSymbol</tt>.
   */
  String _name;

  /**
   * Sets the name of this <tt>RegExpSymbol</tt> to the specified
   * string.
   */
  public final void setName (String name)
    {
      _name = name.intern();
    }

  /**
   * Returns this <tt>RegExpSymbol</tt>'s name as a unique id.
   */
  public final String name ()
    {
      return _name == null ? _name = _object.toString().intern()
	                   : _name;
    }

  /**
   * A private static store associating an object to its canonical
   * <tt>RegExpSymbol</tt> wrapper. It uses an object's
   * <tt>equals(Object)</tt> method for lookups.
   */
  private static HashMap<Object,Object> _symbols = new HashMap<Object,Object>();

  /**
   * A private constructor used only once to generate the canonical
   * empty symbol.
   */
  private RegExpSymbol ()
    {
    }

  /**
   * A private constructor constructing a <tt>RegExpSymbol</tt>
   * wrapper for the specified object. It is used by the
   * <tt>get(Object)</tt> method, which is the only public way of
   * constructing <tt>RegExpSymbol</tt>'s.
   */
  private RegExpSymbol (Object object)
    {
      _object = object;
    }

  /**
   * This is the public method factory for creating
   * <tt>RegExpSymbol</tt> objects. It returns a canonical
   * <tt>RegExpSymbol</tt> wrapper for the specified object.  If the
   * argument is <tt>null</tt>, this returns the canonical
   * <tt>EMPTY</tt> <tt>RegExpSymbol</tt>. Otherwise, it will return
   * the same <tt>RegExpSymbol</tt> for <tt>equals</tt> objects.
   *
   * @param object an object
   * @return the canonical unique <tt>RegExpSymbol</tt> for the specified object
   */
  public static RegExpSymbol get (Object object)
    {
      if (object == null)
	return EMPTY;

      RegExpSymbol symbol = (RegExpSymbol)_symbols.get(object);

      if (symbol == null)
	_symbols.put(object,
		     symbol = new RegExpSymbol(object));

      return symbol;
    }

  /**
   * <a name="normalize">Computes</a> this <tt>RegExpSymbol</tt> as its
   * own normal form.
   */
  public final void normalize ()
    {
      _normalForm = this;
    }

  /**
   * Returns <tt>true</tt> iff the specified object is a
   * <tt>RegExpSymbol</tt> that has the same name as this.
   */
  public final boolean equals (Object other)
    {
      return (this == other)
	  || (other instanceof RegExpSymbol
	      && ((RegExpSymbol)other).name() == name());
    }

  /**
   * Returns a printable form for this <tt>RegExpSymbol</tt>.
   */
  public final String toString ()
    {
      return isEmpty() ? "()" : name();
    }

  public final String toNormalString ()
    {
      return toString();      
    }
}
