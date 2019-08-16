//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

/**
 * This class is a concrete implementation of the abstract class <a
 * href="RegExpOne.html"><tt>RegExpOne</tt></a> to represent a
 * regular expression that is a finite choice of powers ranging
 * from <i>m</i> to <i>n</i>.
 *
 * @version     Last modified on Thu Mar 31 17:20:02 2016 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */
public class RegExpPowerRange extends RegExpOne
{
  private int _lower = 0;

  private int _upper = OMEGA;

  public final int getLower ()
    {
      return _lower;
    }
  
  public final int getUpper ()
    {
      return _upper;
    }
  
  public final RegExp shallowCopy ()
    {
      return range(_arg,_lower,_upper);
    }      

  public final RegExp deepCopy ()
    {
      return range(_arg.deepCopy(),_lower,_upper);
    }      

  public final int type ()
    {
      return RANGE_EXP;
    }

  public final RegExpPowerRange setLower (int lower)
    {
      _lower = lower;
      return this;
    }
  
  public final RegExpPowerRange setUpper (int upper)
    {
      _upper = upper;
      return this;
    }
  
  public final RegExpPowerRange setRange (int lower, int upper)
    {
      _lower = lower;
      _upper = upper;
      return this;
    }
  
  /**
   * Constructs a <tt>RegExpPowerRange</tt> with the specified
   * <tt>RegExp</tt>, lower bound <tt>0</tt> and upper bound
   * <tt>OMEGA</tt>.
   */
  public RegExpPowerRange (RegExp arg)
    {
      super(arg);
    }

  /**
   * Constructs a <tt>RegExpPowerRange</tt> with the specified
   * <tt>RegExp</tt> and lower bound, and upper bound <tt>OMEGA</tt>.
   */
  public RegExpPowerRange (RegExp arg, int lower)
    {
      super(arg);
      setLower(lower);
    }

  /**
   * Constructs a <tt>RegExpPowerRange</tt> with the specified
   * <tt>RegExp</tt>, and lower and upper bounds.
   */
  public RegExpPowerRange (RegExp arg, int lower, int upper)
    {
      super(arg);
      setRange(lower,upper);
    }

  /**
   * <a name="normalize">This</a> returns this
   * <tt>RegExpPowerRange</tt>'s normal form according to the following
   * rewrite rules:
   *
   * <ol>
   *
   * <li> <tt>
   *
   * [PR:E]
   *
   * </tt><tt>
   *
   * ()_m^n
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * ()
   *
   * </tt>
   *
   * <li> <tt>
   *
   * [PR:00]
   *
   * </tt><tt>
   *
   * X_0^0
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * ()
   *
   * </tt>
   *
   * <li> <tt>
   *
   * [PR:01]
   *
   * </tt><tt>
   *
   * X_0^1
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X?
   *
   * </tt>
   *
   * <li> <tt>
   *
   * [PR:O]
   *
   * </tt><tt>
   *
   * X_0~
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X\*
   *
   * </tt>
   *
   * <li> <tt>
   *
   * [PR:1_]
   *
   * </tt><tt>
   *
   * X_1~
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X+
   *
   * </tt>
   *
   * <li> <tt>
   *
   * [PR:__]
   *
   * </tt><tt>
   *
   * X_m^n
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * ()
   *
   * </tt>&nbsp;&nbsp;&nbsp;
   * (<i>if</i>&nbsp;&nbsp;<tt>n &lt; m</tt>)
   *
   * <li> <tt>
   *
   * [PR:-n]
   *
   * </tt><tt>
   *
   * X_m^n
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X_0^n
   *
   * </tt>&nbsp;&nbsp;&nbsp;
   * (<i>if</i>&nbsp;&nbsp;<tt>m &lt; 0</tt>)
   *
   * <li> <tt>
   *
   * [PR:m-]
   *
   * </tt><tt>
   *
   * X_m^n
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * ()
   *
   * </tt>&nbsp;&nbsp;&nbsp;
   * (<i>if</i>&nbsp;&nbsp;<tt>n &lt; 0</tt>)
   *
   * <li> <tt>
   *
   * [PR:mm]
   *
   * </tt><tt>
   *
   * X_m^m
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X^m
   *
   * </tt>
   *
   * <li> <tt>
   *
   * [PR:O]
   *
   * </tt><tt>
   *
   * X?_m^n
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * () | X_m^n
   *
   * </tt>
   *
   * <li> <tt>
   *
   * [PR:P]
   *
   * </tt><tt>
   *
   * X+_m^n
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X_m~
   *
   * </tt>
   *
   * <li> <tt>
   *
   * [PR:S]
   *
   * </tt><tt>
   *
   * X\*_m^n
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X\*
   *
   * </tt>
   *
   * </ol>
   */
  public void normalize ()
    {
      String rule = null;
      RegExp arg = arg();

      out:
      {
	if (arg.isEmpty())
	  {
	    rule = "[PR:E] ()_m^n --> ()";
	    _normalForm = RegExpSymbol.EMPTY;
	    break out;
	  }

	if (_lower == 0)
	  {
	    if (_upper <= 0)
	      {
		rule = "[PR:00] X_0^0 --> ()";
		_normalForm = RegExpSymbol.EMPTY;
		break out;
	      }

	    if (_upper == 1)
	      {
		rule = "[PR:01] X_0^1 --> X?";
		_normalForm = option(arg);
		break out;
	      }

	    if (_upper == OMEGA)
	      {
		rule = "[PR:0_] X_0~ --> X*";
		_normalForm = star(arg);
		break out;
	      }
	  }

	if (_lower == 1 && _upper == OMEGA)
	  {
	    rule = "[PR:1_] X_1~ --> X+";
	    _normalForm = plus(arg);
	    break out;
	  }

	if (_lower > _upper)
	  {
	    rule = "[PR:__] X_m^n --> ()\t(if n<m)";
	    _normalForm = RegExpSymbol.EMPTY;
	    break out;
	  }

	if (_lower < 0)
	  {
	    rule = "[PR:-n] X_m^n --> X_0^n\t(if m<0)";
	    _normalForm = range(arg,0,_upper);
	    break out;
	  }

	if (_upper < 0)
	  {
	    rule = "[PR:m-] X_m^n --> ()\t(if n<0)";
	    _normalForm = RegExpSymbol.EMPTY;
	    break out;
	  }

	if (_lower == _upper)
	  {
	    rule = "[PR:mm] X_m^m --> X^m";
	    _normalForm = power(arg,_lower);
	    break out;
	  }

	switch (arg.type())
	  {
	  case OPTION_EXP:
	    rule = "[PR:O] X?_m^n --> X_0^n";
	    _normalForm = range(arg.arg(),0,_upper);
	    break out;

	  case PLUS_EXP:
	    rule = "[PR:P] X+_m^n --> X_m~";
	    _normalForm = range(arg.arg(),_lower);
	    break out;

	  case STAR_EXP:
	    rule = "[PR:S] X*_m^n --> X*";
	    _normalForm = arg;
	    break out;
	  }

      } // end of labeled block "out:"

      traceRule(rule);
      checkNormalForm();
    }

  /**
   * Returns <tt>true</tt> iff the specified object is a
   * <tt>RegExpPowerRange</tt> that has the same argument, lower, and
   * upper bounds as this one.
   */
  public final boolean equals (Object other)
  {
    return super.equals(other)
      && _lower == ((RegExp)other).lower()
      && _upper == ((RegExp)other).upper();
  }

  /**
   * Returns a printable form for this <tt>RegExpPowerRange</tt>.
   */
  public final String toString ()
  {
    String upper = _upper==OMEGA ? "~" : "^"+_upper;

    if (_arg.isBinary())
      return "("+_arg+")_"+_lower+upper;

      return _arg+"_"+_lower+upper;
    }

  public final String toNormalString ()
  {
    if (!inNormalForm())
      return _normalForm.toNormalString();

    String upper = _upper==OMEGA ? "~" : "^"+_upper;

    if (_arg.isBinary())
      return "("+_arg.toNormalString()+")_"+_lower+upper;

      return _arg.toNormalString()+"_"+_lower+upper;
    }
}
