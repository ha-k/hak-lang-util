//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

/**
 * This class is a concrete implementation of the abstract class <a
 * href="RegExpOne.html"><tt>RegExpOne</tt></a> to represent a "starred"
 * regular expression (<i>i.e.</i>, zero or more).
 *
 * @version     Last modified on Thu Mar 31 15:56:44 2016 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */
public class RegExpStar extends RegExpOne
{
  /**
   * Constructs a <tt>RegExpStar</tt> with the specified
   * <tt>RegExp</tt>.
   */
  public RegExpStar (RegExp arg)
    {
      super(arg);
    }

  public final RegExp shallowCopy ()
    {
      return star(_arg);
    }      

  public final RegExp deepCopy ()
    {
      return star(_arg.deepCopy());
    }      

  public final int type ()
    {
      return STAR_EXP;
    }

  /**
   * <a name="normalize">This</a> returns this <tt>RegExpStar</tt>'s
   * normal form according to the following rewrite rules:
   *
   * <ol>
   *
   * <li> <tt>
   *
   * [ST:E]
   *
   * </tt><tt>
   *
   * ()\*
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
   * [ST:O]
   *
   * </tt><tt>
   *
   * X?\*
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
   * [ST:P]
   *
   * </tt><tt>
   *
   * X+\*
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
   * [ST:S]
   *
   * </tt><tt>
   *
   * X\*\*
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X\*
   *
   * </tt>
   *
   * </ol>
   *
   * Note that there are no rules for <tt>X^n\*</tt> nor
   * <tt>X_n^m\*</tt> since such forms cannot be further normalized.
   */
  public void normalize ()
    {
      String rule = null;
      RegExp arg = arg();

      switch (arg.type())
	{
	case EMPTY_EXP:
	  rule = "[ST:E] ()* --> ()";
	  _normalForm = RegExpSymbol.EMPTY;
	  break;

	case OPTION_EXP:
	  rule = "[ST:O] X?* --> X*";
	  _normalForm = star(arg.arg());
	  break;

	case PLUS_EXP:
	  rule = "[ST:P] X+* --> X*";
	  _normalForm = star(arg.arg());
	  break;

	case STAR_EXP:
	  rule = "[ST:S] X** --> X*";
	  _normalForm = arg;
	  break;
	}

      traceRule(rule);
      checkNormalForm();
    }

  /**
   * Returns a printable form for this <tt>RegExpStar</tt>.
   */
  public final String toString ()
    {
      if (_arg.isBinary())
	return "("+_arg+")*";

      return _arg+"*";
    }

  public final String toNormalString ()
    {
      if (!inNormalForm())
	return _normalForm.toNormalString();

      if (_arg.isBinary())
	return "("+_arg.toNormalString()+")*";

      return _arg.toNormalString()+"*";
    }

}
