//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

/**
 * This abstract class is a partial implementation of the abstract class
 * <a href="RegExp.html"><tt>RegExp</tt></a> whose instances are built
 * using a monadic operator; namely, the following concrete subclasses:
 *
 * <p>
 *
 * <ul>
 *
 * <li> <a href="RegExpOption.html"><tt>RegExpOption</tt></a>
 *
 * <p><li> <a href="RegExpPlus.html"><tt>RegExpPlus</tt></a>
 *
 * <p><li> <a href="RegExpStar.html"><tt>RegExpStar</tt></a>
 *
 * <p><li> <a href="RegExpPower.html"><tt>RegExpPower</tt></a>
 *
 * <p><li> <a href="RegExpPowerRange.html"><tt>RegExpPowerRange</tt></a>
 *
 * </ul>
 *
 * @version     Last modified on Mon Apr 04 14:33:32 2016 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */
public abstract class RegExpOne extends RegExp
{
  /**
   * Constructs a <tt>RegExpOne</tt> with the specified
   * <tt>RegExp</tt>.
   */
  protected RegExpOne (RegExp arg)
    {
      _arg = cleanup(arg);
    }

  protected RegExp _arg;

  public final RegExp getArg ()
    {
      return _arg;
    }

  /**
   * Returns the normal form of the argument of this unary expression.
   */
  public final RegExp normalArg ()
    {
      return _arg.normalForm();
    }

  /**
   * Returns <tt>true</tt> iff the specified object is a
   * <tt>RegExpOne</tt> of same type and argument as this one's.
   */
  public boolean equals (Object other)
    {
      return (this == other)
	  || (other instanceof RegExp
	      && type() == ((RegExp)other).type()
	      && _arg.equals(((RegExpOne)other).arg()));
    }

}
