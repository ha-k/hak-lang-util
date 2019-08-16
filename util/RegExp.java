//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

import hlt.language.tools.Misc;

/**
 * This abstract class defines a representation for a regular expression
 * (RE) on an alphabet of <tt>Object</tt>'s (as opposed to just
 * <tt>char</tt>'s as done in most RE representation packages meant for
 * character word pattern-matching such as <tt>java.util.regex</tt>).
 *
 * <p>
 *
 * The purpose of this class and its subclasses is to allow the symbolic
 * manipulation of RE's on any alphabet, providing methods for reading,
 * writing, equality, and normalization to canonical form. RE matching
 * is not the intended use, although it should be simple to have that as
 * well by encoding each <tt>Object</tt> in the alphabet with a
 * <tt>char</tt> and delegate the work to <tt>java.util.regex</tt>.
 *
 * <p>
 *
 * This is an abstract class. It has the following subclasses:
 *
 * <p>
 *
 * <ul>
 *
 * <li><p> <a href="RegExpSymbol.html"><tt>RegExpSymbol</tt></a> (concrete);
 * </p></li> 
 *
 * <li><p> <a href="RegExpOne.html"><tt>RegExpOne</tt></a> 
 *     (abstract), which has the following subclasses:</p>
 *
 *     <ul>
 *
 *     <li><p> <a href="RegExpOption.html"><tt>RegExpOption</tt></a> (concrete) </p></li>
 *
 *     <li><p> <a href="RegExpPlus.html"><tt>RegExpPlus</tt></a> (concrete) </p></li>
 *
 *     <li><p> <a href="RegExpStar.html"><tt>RegExpStar</tt></a> (concrete) </p></li>
 *
 *     <li><p> <a href="RegExpPower.html"><tt>RegExpPower</tt></a> (concrete) </p></li>
 *
 *     <li><p> <a href="RegExpPowerRange.html"><tt>RegExpPowerRange</tt></a> (concrete) </p></li>
 *
 *     </ul> 
 * </li>
 *
 * <li><p> <a href="RegExpTwo.html"><tt>RegExpTwo</tt></a> (abstract),
 *     which has the following subclasses:</p>
 *
 *     <ul>
 *
 *     <li><p> <a href="RegExpConcat.html"><tt>RegExpConcat</tt></a> (concrete) </p></li>
 *
 *     <li><p> <a href="RegExpChoice.html"><tt>RegExpChoice</tt></a> (concrete) </p></li>
 *
 *     </ul>
 * </li>
 *
 * </ul>
 *
 * @version     Last modified on Mon Mar 26 10:05:16 2018 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */
public abstract class RegExp
{
  /**
   * Returns the canonical <tt>RegExpSymbol.EMPTY</tt> symbol.
   */
  public static final RegExpSymbol empty ()
  {
    return RegExpSymbol.EMPTY;
  }

  /**
   * This is a protected field inherited by all subclasses of
   * <tt>RegExp</tt>. It is initialized to <tt>null</tt> and meant to
   * hold the normal form of this <tt>RegExp</tt> which is computed by
   * the <tt>normalize()</tt> method the first time the
   * <tt>normalForm()</tt> method is invoked on this. The result is then
   * cached in this field so that any further calls to
   * <tt>normalForm()</tt> will simply return the expression computed
   * and saved once and for all in this field by the
   * <tt>normalize()</tt> method.
   */
  protected RegExp _normalForm = null;

  /**
   * This returns this <tt>RegExp</tt>'s normal form, which is computed
   * by the <tt>normalize()</tt> method the first time this
   * <tt>normalForm()</tt> method is invoked on a <tt>RegExp</tt>.  The
   * result is then cached so that any further calls to
   * <tt>normalForm()</tt> will simply return the expression computed
   * and saved once and for all for this <tt>RegExp</tt> by the
   * <tt>normalize()</tt> method.
   *
   * @return this regular expression's normal form
   */
  public final RegExp normalForm ()
  {
    if (_normalForm == null)
      normalize();

    return _normalForm;
  }    

  /**
   * This checks whether this expression's normal form has been reached,
   * and if not proceeds to normalize it further.
   */
  protected final void checkNormalForm ()
  {
    if (_normalForm == null) // no rule applied: this was already in normal form
      _normalForm = this;
    else
      _normalForm = _normalForm.normalForm();
  }

  /**
   * Returns <tt>true</tt> if this expression is in normal form.
   * <b>N.B.</b> This will compute its normal form as a side effet!
   */
  public final boolean inNormalForm ()
  {
    return this == normalForm();
  }

  /**
   * This is the method that computes this <tt>RegExp</tt>'s normal
   * form. It is implemented by each concrete subclass of
   * <tt>RegExp</tt>; namely,:
   *
   * <ul>
   *
   * <li> <a href="RegExpSymbol.html#normalize"><tt>RegExpSymbol.normalize()</tt></a> </li>
   *
   * <li> <a href="RegExpOption.html#normalize"><tt>RegExpOption.normalize()</tt></a> </li>
   *
   * <li> <a href="RegExpPlus.html#normalize"><tt>RegExpPlus.normalize()</tt></a> </li>
   *
   * <li> <a href="RegExpStar.html#normalize"><tt>RegExpStar.normalize()</tt></a> </li>
   *
   * <li> <a href="RegExpPower.html#normalize"><tt>RegExpPower.normalize()</tt></a> </li>
   *
   * <li> <a href="RegExpPowerRange.html#normalize"><tt>RegExpPowerRange.normalize()</tt></a> </li>
   *
   * <li> <a href="RegExpConcat.html#normalize"><tt>RegExpConcat.normalize()</tt></a> </li>
   *
   * <li> <a href="RegExpChoice.html#normalize"><tt>RegExpChoice.normalize()</tt></a> </li>
   *
   * </ul>
   *
   */
  abstract public void normalize ();

  /**
   * Returns <tt>true</tt> iff the specified <tt>RegExp</tt>'s normal
   * form is equal to this <tt>RegExp</tt>'s normal form.
   */
  public final boolean normEquals (RegExp other)
    {
      return normalForm().equals(other.normalForm());      
    }

  /**
   * This returns a shallow copy of this <tt>RegExp</tt>. 
   */
  abstract public RegExp shallowCopy ();

  /**
   * This returns a deep copy of this <tt>RegExp</tt>. 
   */
  abstract public RegExp deepCopy ();

  /**
   * This simply returns its argument, unless <tt>null</tt> - in which
   * case it returns <tt>RegExpSymbol.EMPTY</tt>.
   */
  public final RegExp cleanup (RegExp e)
    {
      return e == null  ? RegExpSymbol.EMPTY : e;
    }

  /**
   * Constants identifying each specific type of <tt>RegExp</tt>.
   */

  public final static int EMPTY_EXP  = 1<<0;
  public final static int SYMBOL_EXP = 1<<1;
  public final static int OPTION_EXP = 1<<2;
  public final static int PLUS_EXP   = 1<<3;
  public final static int STAR_EXP   = 1<<4;
  public final static int POWER_EXP  = 1<<5;
  public final static int RANGE_EXP  = 1<<6;
  public final static int CHOICE_EXP = 1<<7;
  public final static int CONCAT_EXP = 1<<8;

  public final static int UNARY_EXP  = OPTION_EXP
                                     | PLUS_EXP
                                     | STAR_EXP
                                     | POWER_EXP
                                     | RANGE_EXP
                                     ;

  public final static int BINARY_EXP = CONCAT_EXP
                                     | CHOICE_EXP
                                     ;

  /**
   * This returns the specific type identifying this <tt>RegExp</tt>.
   */
  abstract public int type ();

  /**
   * This returns a string identifying this <tt>RegExp</tt>'s specific
   * type.
   */
  final public String tag ()
    {
      switch (type())
	{
	case EMPTY_EXP:
	  return "Empty";
	case SYMBOL_EXP:
	  return "Symbol";
	case CHOICE_EXP:
	  return "Choice";
	case CONCAT_EXP:
	  return "Concat";
	case OPTION_EXP:
	  return "Option";
	case PLUS_EXP:
	  return "Plus";
	case POWER_EXP:
	  return "Power";
	case RANGE_EXP:
	  return "PowerRange";
	case STAR_EXP:
	  return "Star";
	}
      return "Unknown";
    }

  //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//

  /**
   * Each of the following methods tests whether this is a specific
   * <tt>RegExp</tt> type or form (<i>i.e.</i>, unary or binary).
   */

  public final boolean isEmpty ()
    {
      return this == RegExpSymbol.EMPTY;
    }

  public final boolean isSymbol ()
    {
      return type() == SYMBOL_EXP;
    }
  
  public final boolean isChoice ()
    {
      return type() == CHOICE_EXP;
    }
  
  public final boolean isConcat ()
    {
      return type() == CONCAT_EXP;
    }
  
  public final boolean isOption ()
    {
      return type() == OPTION_EXP;
    }
  
  public final boolean isPlus ()
    {
      return type() == PLUS_EXP;
    }
  
  public final boolean isPower ()
    {
      return type() == POWER_EXP;
    }
  
  public final boolean isRange ()
    {
      return type() == RANGE_EXP;
    }
  
  public final boolean isStar ()
    {
      return type() == STAR_EXP;
    }

  public final boolean isUnary ()
    {
      return (type() & UNARY_EXP) == type();
    }

  public final boolean isBinary ()
    {
      return (type() & BINARY_EXP) == type();
    }

  //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//

  /**
   * Each of the following methods is a convenient shorthand for its
   * specific corresponding <ttRegExp</tt> constructor. There is none
   * for <a href="RegExpSymbol.html"><tt>RegExpSymbol</tt></a> because
   * it has no public constructor: it relies on a factory.
   *
   * <p>
   *
   * These really are just shorthands to avoid cluttering the code of
   * the <tt>normalize()</tt> methods with a proliferation of lengthy
   * <tt>new</tt> constuctor expressions as required by Java.
   *
   * <p>
   *
   * <span style="color:tan;font-size:smaller"><tt> <b>N.B.</b>: This
   * adds (a useless) level of indirection. Ideally, these should be
   * macros rather than methods!  </tt></span>
   */

  protected final RegExpOption option (RegExp arg)
    {
      return new RegExpOption(arg);
    }

  protected final RegExpPlus plus (RegExp arg)
    {
      return new RegExpPlus(arg);
    }

  protected final RegExpStar star (RegExp arg)
    {
      return new RegExpStar(arg);
    }

  protected final RegExpPower power (RegExp arg, int power)
    {
      return new RegExpPower(arg,power);
    }

  protected final RegExpPowerRange range (RegExp arg, int lower, int upper)
    {
      return new RegExpPowerRange(arg,lower,upper);
    }

  protected final RegExpPowerRange range (RegExp arg, int lower)
    {
      return new RegExpPowerRange(arg,lower);
    }

  protected final RegExpConcat concat (RegExp left, RegExp right)
    {
      return new RegExpConcat(left,right);
    }

  protected final RegExpChoice choice (RegExp left, RegExp right)
    {
      return new RegExpChoice(left,right);
    }

  //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//

  /**
   * Each of the following methods performs an appropriate type cast to
   * this <tt>RegExp</tt> before extracting a component from its normal
   * form. This compoment being defined only for the type used in the
   * cast, all these methods assume that <tt>this</tt> has been
   * ascertained to be of the appropriate type so that the cast is
   * garanteed to be correct.
   *
   * <p>
   *
   * These really are just shorthands to avoid cluttering the code of
   * the <tt>normalize()</tt> methods with a proliferation of type-cast
   * expressions as required by Java.
   *
   * <p>
   *
   * <span style="color:tan;font-size:smaller"><tt> <b>N.B.</b>: This
   * adds (a useless) level of indirection. Ideally, these should be
   * macros rather than methods!  </tt></span>
   */

  /**
   * Assuming that this <tt>RegExp</tt> is a <tt>RegExpOne</tt>,
   * returns its argument.
   */
  protected final RegExp arg ()
    {
      return ((RegExpOne)this).getArg();
    }

  /**
   * Assuming that this <tt>RegExp</tt> is a <tt>RegExpOne</tt>,
   * returns its normalized argument.
   */
  protected final RegExp narg ()
    {
      return ((RegExpOne)this).normalArg();
    }

  /**
   * Assuming that this <tt>RegExp</tt> is a <tt>RegExpTwo</tt>,
   * returns its left part.
   */
  protected final RegExp left ()
    {
      return ((RegExpTwo)this).getLeft();
    }

  /**
   * Assuming that this <tt>RegExp</tt> is a <tt>RegExpTwo</tt>,
   * returns its normalized left part.
   */
  protected final RegExp nleft ()
    {
      return ((RegExpTwo)this).normalLeft();
    }

  /**
   * Assuming that this <tt>RegExp</tt> is a <tt>RegExpTwo</tt>,
   * returns its right part.
   */
  protected final RegExp right ()
    {
      return ((RegExpTwo)this).getRight();
    }

  /**
   * Assuming that this <tt>RegExp</tt> is a <tt>RegExpTwo</tt>,
   * returns its normalized right part.
   */
  protected final RegExp nright ()
    {
      return ((RegExpTwo)this).normalRight();
    }

  /**
   * Assuming that this <tt>RegExp</tt> is a <tt>RegExpPower</tt>,
   * returns its exponent.
   */
  protected final int power ()
    {
      return ((RegExpPower)this).getPower();
    }

  /**
   * Assuming that this <tt>RegExp</tt> is a <tt>RegExpPowerRange</tt>,
   * returns its lower exponent.
   */
  protected final int lower ()
    {
      return ((RegExpPowerRange)this).getLower();
    }

  /**
   * Assuming that this <tt>RegExp</tt> is a <tt>RegExpPowerRange</tt>,
   * returns its upper exponent.
   */
  protected final int upper ()
    {
      return ((RegExpPowerRange)this).getUpper();
    }

  //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//

  /**
   * This is a constant representing the cardinality of the set of
   * natural numbers.
   */
  protected static int OMEGA = Integer.MAX_VALUE;

  /**
   * This returns the sum of the two specified integers whenever this
   * sum does not exceed <tt>OMEGA</tt>; otherwise, returns
   * <tt>OMEGA</tt>.
   */
  protected final int plus (int x, int y)
    {
      if (x <= OMEGA && y <= OMEGA-x)
	return x+y;

      return OMEGA;
    }

  /**
   * This returns the product of the two specified integers whenever
   * this product does not exceed <tt>OMEGA</tt>; otherwise, returns
   * <tt>OMEGA</tt>.
   */
  protected final int times (int x, int y)
    {
      if (x == 0 || y == 0)
	return 0;

      if (x <= OMEGA && y <= OMEGA/x)
	return x*y;

      return OMEGA;
    }

  /**
   * Returns the printable form for this <tt>RegExp</tt>'s normal form.
   */
  public abstract String toNormalString ();
    // {
    //   return normalForm().toString();
    // }

  public final boolean isLexLess (RegExp other)
    {
      return this.toString().compareTo(other.toString()) < 0;
    }

  /**
   * This is a simple tracing facility printing on <tt>System.err</tt>
   * how this <tt>RegExp</tt> gets transformed into its normal form
   * using the specified rule.
   */

  protected final void traceRule (String rule)
    {
      if (tracing() && rule != null)
	{
	  System.err.println(Misc.repeat(70,'-'));
	  System.err.println(tag()+" Rule: "+rule+"\n\n\t"
			     +this+" --> "+_normalForm+"\n");
	}
    }

  /**
   * A flag indicating whether tracing mode is on.
   */
  private static boolean _tracing = false;

  /**
   * Tests whether tracing is on.
   */
  public static final boolean tracing ()
    {
      return _tracing;
    }

  /**
   * Enables tracing mode on.
   */
  public static final void trace ()
    {
      _tracing = true;
    }

  /**
   * Disables tracing mode.
   */
  public static final void noTrace ()
    {
      _tracing = false;
    }

  /**
   * Toggles tracing mode.
   */
  public static final void toggleTrace ()
    {
      _tracing = !_tracing;
    }
}
