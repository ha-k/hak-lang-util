//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

/**
 * This class is a concrete implementation of the abstract class <a
 * href="RegExpOne.html"><tt>RegExpOne</tt></a> to represent a
 * regular expression that is repeated a finite number of times.
 *
 * @version     Last modified on Thu Mar 31 15:56:22 2016 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */
public class RegExpPower extends RegExpOne
{
  private int _power = 1;

  public final int getPower ()
    {
      return _power;
    }
  
  public final RegExp shallowCopy ()
    {
      return power(_arg,_power);
    }      

  public final RegExp deepCopy ()
    {
      return power(_arg.deepCopy(),_power);
    }      

  public final int type ()
    {
      return POWER_EXP;
    }

  public final RegExpPower setPower (int power)
    {
      _power = power;
      return this;
    }
  
  /**
   * Constructs a <tt>RegExpPower</tt> with the specified
   * <tt>RegExp</tt> an <tt>int</tt>.
   */
  public RegExpPower (RegExp arg, int power)
    {
      super(arg);
      setPower(power);
    }

  /**
   * <a name="normalize">This</a> returns this <tt>RegExpPower</tt>'s
   * normal form according to the following rewrite rules:
   *
   * <ol>
   *
   * <li> <tt>
   *
   * [PW:E]
   *
   * </tt><tt>
   *
   * ()^n
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
   * [PW:0]
   *
   * </tt><tt>
   *
   * X^n
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * ()
   *
   * </tt>&nbsp;&nbsp;&nbsp;
   * (<i>if</i>&nbsp;&nbsp;<tt>n&le;0</tt>)
   *
   * <li> <tt>
   *
   * [PW:1]
   *
   * </tt><tt>
   *
   * X^1
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X
   *
   * </tt>
   *
   * <li> <tt>
   *
   * [PW:O]
   *
   * </tt><tt>
   *
   * X?^n
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X_0^n
   *
   * </tt>
   *
   * <li> <tt>
   *
   * [PW:P]
   *
   * </tt><tt>
   *
   * X+^n
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X_n~
   *
   * </tt>
   *
   * <li> <tt>
   *
   * [PW:S]
   *
   * </tt><tt>
   *
   * X\*^n
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
   * [PW:N]
   *
   * </tt><tt>
   *
   * X^m^n
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X^m&times;n
   *
   * </tt>
   *
   * <li> <tt>
   *
   * [PW:R]
   *
   * </tt><tt>
   *
   * X_m^n^p
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X_m&times;p^n&times;p
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
	    rule = "[PW:E] ()^n --> ()";
	    _normalForm = RegExpSymbol.EMPTY;
	    break out;
	  }

	if (_power <= 0)
	  {
	    rule = "[PW:0] X^0 --> ()";
	    _normalForm = RegExpSymbol.EMPTY;
	    break out;
	  }

	if (_power == 1)
	  {
	    rule = "[PW:1] X^1 --> X";
	    _normalForm = arg;
	    break out;
	  }

	switch (arg.type())
	  {
	  case OPTION_EXP:
	    rule = "[PW:O] X?^n --> X_0^n";
	    _normalForm = range(arg.arg(),0,_power);
	    break out;

	  case PLUS_EXP:
	    rule = "[PW:P] X+^n --> X_n~";
	    _normalForm = range(arg.arg(),_power);
	    break out;

	  case STAR_EXP:
	    rule = "[PW:S] X*^n --> X*";
	    _normalForm = arg;
	    break out;

	  case POWER_EXP:
	    rule = "[PW:N] X^m^n --> X^m*n";
	    _normalForm = power(arg.arg(),
				times(arg.power(),_power));
	    break out;

	  case RANGE_EXP:
	    rule = "[PW:R] X_m^n^p --> X_m*p^n*p";
	    _normalForm = range(arg.arg(),
				times(arg.lower(),_power),
				times(arg.upper(),_power));
	    break out;
	  }

      } // end of named block "out:"

      traceRule(rule);
      checkNormalForm();
    }

  /**
   * Returns <tt>true</tt> iff the specified object is a
   * <tt>RegExpPower</tt> that has the same argument and power as this
   * one.
   */
  public final boolean equals (Object other)
    {
      return super.equals(other)
	  && _power == ((RegExp)other).power();
    }

  /**
   * Returns a printable form for this <tt>RegExpPower</tt>.
   */
  public final String toString ()
    {
      if (_arg.isBinary())
	return "("+_arg+")^"+_power;

      return _arg+"^"+_power;
    }

  public final String toNormalString ()
    {
      if (!inNormalForm())
	return _normalForm.toNormalString();

      if (_arg.isBinary())
	return "("+_arg.toNormalString()+")^"+_power;

      return _arg.toNormalString()+"^"+_power;
    }

}
