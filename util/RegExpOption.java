//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

/**
 * This class is a concrete implementation of the abstract class <a
 * href="RegExpOne.html"><tt>RegExpOne</tt></a> to represent an optional
 * regular expression (<i>i.e.</i>, zero or one).
 *
 * @version     Last modified on Thu Mar 31 15:55:59 2016 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */
public class RegExpOption extends RegExpOne
{
  /**
   * Constructs a <tt>RegExpOption</tt> with the specified
   * <tt>RegExp</tt>.
   */
  public RegExpOption (RegExp arg)
    {
      super(arg);
    }

  public final int type ()
    {
      return OPTION_EXP;
    }

  public final RegExp shallowCopy ()
    {
      return option(_arg);
    }      

  public final RegExp deepCopy ()
    {
      return option(_arg.deepCopy());
    }      

  /**
   * <a name="normalize">This</a> computes the normal form of this
   * <tt>RegExpOption</tt>, and sets its <tt>_normalForm</tt> field to
   * it. This normal form is computed according to the following rewrite
   * rules.
   *
   * <ul>
   *
   * <li> <tt>
   *
   * [OP:E]
   *
   * </tt><tt>
   *
   * ()?
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * ()
   *
   * </tt>
   * </li>
   *
   * <li> <tt>
   *
   * [OP:O]
   *
   * </tt><tt>
   *
   * X??
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X?
   *
   * </tt>
   *
   * </li>
   *
   * <li> <tt>
   *
   * [OP:P]
   *
   * </tt><tt>
   *
   * X+?
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X&lowast;
   *
   * </tt>
   *
   * </li>
   *
   * <li> <tt>
   *
   * [OP:S]
   *
   * </tt><tt>
   *
   * X&lowast;?
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X&lowast;
   *
   * </tt>
   *
   * </li>
   *
   * <li> <tt>
   *
   * [OP:R0]
   *
   * </tt><tt>
   *
   * X_0^n?
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X_0^n
   *
   * </tt>
   *
   * </li>
   *
   * <li> <tt>
   *
   * [OP:R1]
   *
   * </tt><tt>
   *
   * X_1^n?
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X_0^n
   *

   * </tt>
   *
   * </li>
   *
   * </ul>
   */
  public void normalize ()
  {
    String rule = null;
    RegExp arg = arg();

    switch (arg.type())
      {
      case EMPTY_EXP:
	rule = "[OP:E] ()? --> ()";
	_normalForm = RegExpSymbol.EMPTY;
	break;

      case OPTION_EXP:
	rule = "[OP:O] X?? --> X?";
	_normalForm = arg;
	break;

      case PLUS_EXP:
	rule = "[OP:P] X+? --> X*";
	_normalForm = star(arg.arg());
	break;

      case STAR_EXP:
	rule = "[OP:S] X*? --> X*";
	_normalForm = arg;
	break;

      case RANGE_EXP:
	switch (arg.lower())
	  {
	  case 0:
	    rule = "[OP:R0] X_0^n? --> X_0^n";
	    _normalForm = arg;
	    break;

	  case 1:
	    rule = "[OP:R1] X_1^n? --> X_0^n";
	    _normalForm = range(arg.arg(),0,arg.upper());
	    break;
	  }
      }

    traceRule(rule);
    checkNormalForm();
  }
  
  /**
   * Returns a printable form for this <tt>RegExpOption</tt>.
   */
  public final String toString ()
    {
      if (_arg.isBinary())
	return "("+_arg+")?";

      return _arg+"?";
    }

  public final String toNormalString ()
    {
      if (!inNormalForm())
	return _normalForm.toNormalString();

      if (_arg.isBinary())
	return "("+_arg.toNormalString()+")?";

      return _arg.toNormalString()+"?";
    }

}
