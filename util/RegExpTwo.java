//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

/**
  * This abstract class is a partial implementation of the abstract class
 * <a href="RegExp.html"><tt>RegExp</tt></a> whose instances are built
 * using a  dyadic operator; namely, the following concrete subclasses:
 *
 * <p>
 *
 * <ul>
 *
 * <li> <a href="RegExpChoice.html"><tt>RegExpChoice</tt></a>
 *
 * <p><li> <a href="RegExpConcat.html"><tt>RegExpConcat</tt></a>
 *
 * </ul>
 *
 * @version     Last modified on Mon Apr 04 16:05:25 2016 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */
public abstract class RegExpTwo extends RegExp
{
  /**
   * Constructs a <tt>RegExpTwo</tt> with the two specified
   * <tt>RegExp</tt>'s.
   */
  protected RegExpTwo (RegExp left, RegExp right)
    {
      _left = cleanup(left);
      _right = cleanup(right);
    }

  protected RegExp _left;
  protected RegExp _right;

  public final RegExp getLeft ()
    {
      return _left;
    }

  public final RegExp getRight ()
    {
      return _right;
    }

  public final RegExp normalLeft ()
    {
      return _left.normalForm();
    }

  public final RegExp normalRight ()
    {
      return _right.normalForm();
    }

  /**
   * <a name="matchingPair">This</a> returns true iff both arguments
   * are equal or unary <tt>RegExp</tt>'s dealing with the same
   * expression. In other words, a "matching pair" of <tt>RegExp</tt>'s
   * is any pair from the set: <b><tt>{X, X?, X+, X\*, X^n,
   * X_p^q}</tt></b>, for any <tt>RegExp</tt> <b><tt>X</tt></b>.
   */
  private static final boolean matchingPair (RegExp l, RegExp r)
    {
      if (l.equals(r))
        return true;

      if (l.isUnary())
        return r.isUnary() ? l.arg().equals(r.arg())
                           : l.arg().equals(r);

      if (r.isUnary())
        return l.equals(r.arg());

      return false;
    }

  protected int _matchingPairCode = -1;
  protected int _matchingArgsCode = -1;

  protected final int matchingPairCode ()
    {
      if (_matchingPairCode >= 0)
        return _matchingPairCode;

      return _matchingPairCode = matchingPairCode(_left,_right);
    }

  protected final int matchingArgsCode ()
    {
      if (_matchingArgsCode >= 0)
        return _matchingArgsCode;

      if (type() != _right.type())
        return -1;

      return _matchingArgsCode = matchingPairCode(_left,_right.left());
    }

  protected final String code  (int n)
    {
      switch (n)
        {
        case XX: return "XX";
        case XO: return "XO";
        case XP: return "XP";
        case XS: return "XS";
        case XN: return "XN";
        case XR: return "XR";

        case OX: return "OX";
        case OO: return "OO";
        case OP: return "OP";
        case OS: return "OS";
        case ON: return "ON";
        case OR: return "OR";

        case PX: return "PX";
        case PO: return "PO";
        case PP: return "PP";
        case PS: return "PS";
        case PN: return "PN";
        case PR: return "PR";

        case SX: return "SX";
        case SO: return "SO";
        case SP: return "SP";
        case SS: return "SS";
        case SN: return "SN";
        case SR: return "SR";

        case NX: return "NX";
        case NO: return "NO";
        case NP: return "NP";
        case NS: return "NS";
        case NN: return "NN";
        case NR: return "NR";

        case RX: return "RX";
        case RO: return "RO";
        case RP: return "RP";
        case RS: return "RS";
        case RN: return "RN";
        case RR: return "RR";
        }

      return "??";
    }
  
  /**
   * <b><a name="matchingPairCodes">Matching Pair Codes</a></b>
   *
   * <pre>
   * TYPE  MNEMONIC  MEANING
   * --------------------------------
   * X         X     any RegExp
   * X?        O     RegExpOption
   * X+        P     RegExpPlus
   * X\*        S     RegExpStar
   * X^n       N     RegExpPower
   * X_m^n     R     RegExpPowerRange
   * </pre>
   */

  public static final byte XX =  1;
  public static final byte XO =  2;
  public static final byte XP =  3;
  public static final byte XS =  4;
  public static final byte XN =  5;
  public static final byte XR =  6;

  public static final byte OX =  7;
  public static final byte OO =  8;
  public static final byte OP =  9;
  public static final byte OS = 10;
  public static final byte ON = 11;
  public static final byte OR = 12;

  public static final byte PX = 13;
  public static final byte PO = 14;
  public static final byte PP = 15;
  public static final byte PS = 16;
  public static final byte PN = 17;
  public static final byte PR = 18;

  public static final byte SX = 19;
  public static final byte SO = 20;
  public static final byte SP = 21;
  public static final byte SS = 22;
  public static final byte SN = 23;
  public static final byte SR = 24;

  public static final byte NX = 25;
  public static final byte NO = 26;
  public static final byte NP = 27;
  public static final byte NS = 28;
  public static final byte NN = 29;
  public static final byte NR = 30;

  public static final byte RX = 31;
  public static final byte RO = 32;
  public static final byte RP = 33;
  public static final byte RS = 34;
  public static final byte RN = 35;
  public static final byte RR = 36;

  /**
   * <a name="matchingPairCode">This</a> returns a <tt>byte</tt> which
   * is non-zero iff its arguments form a <a
   * href="#matchingPair"><tt>matchingPair</tt></a>, in which case it
   * returns a <tt>byte</tt> which is a code uniquely identifying the
   * entry at hand. Namely, for any <tt>RegExp</tt> <b><tt>X</tt></b>:

   * <center>
   * <table cellpadding="10" cellspacing="5">

   * <tr>
   * <td><b><tt>
   *
   * CODE
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * X
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * X?
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * X+
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * X\*
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * X^q
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * X_p^q
   *
   * </tt></b></td>
   * </tr>
   
   * <tr>
   * <td><b><tt>
   *
   * X
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * XX
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * XO
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * XP
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * XS
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * XN
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * XR
   *
   * </tt></b></td>
   * </tr>
   
   * <tr>
   * <td><b><tt>
   *
   * X?
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * OX
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * OO
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * OP
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * OS
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * ON
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * OR
   *
   * </tt></b></td>
   * </tr>
   
   * <tr> 
   * <td><b><tt>
   *
   * X+
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * PX
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * PO
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * PP
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * PS
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * PN
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * PR
   *
   * </tt></b></td>
   * </tr>
   
   * <tr> 
   * <td><b><tt>
   *
   * X\*
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * SX
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * SO
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * SP
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * SS
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * SN
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * SR
   *
   * </tt></b></td>
   * </tr>
   
   * <tr>
   * <td><b><tt>
   *
   * X^n
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * NX
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * NO
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * NP
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * NS
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * NN
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * NR
   *
   * </tt></b></td>
   * </tr>
   
   * <tr> 
   * <td><b><tt>
   *
   * X_m^n
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * RX
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * RO
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * RP
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * RS
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * RN
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * RR
   *
   * </tt></b></td>
   * </tr>
   
   * </table>
   * </center>

   * The diagonal entries <tt>OO</tt>, <tt>PP</tt>, <tt>SS</tt>,
   * <tt>NN</tt>, and <tt>RR</tt> are never returned as they are
   * systematically preempted earlier by the <tt>XX</tt> case. They are
   * given here for the sake of completeness and for the justification
   * of correctness of this specification.

   */
  private static final byte matchingPairCode (RegExp l, RegExp r)
    {
      if (l.equals(r))
        return XX;

      if (l.isUnary())
        {
          if (r.isUnary())
            {
              if (l.arg().equals(r.arg()))
                switch (l.type())
                  {
                  case OPTION_EXP:
                    switch (r.type())
                      {
                      case OPTION_EXP: return OO;      // this is never reached
                      case PLUS_EXP:   return OP;
                      case STAR_EXP:   return OS;
                      case POWER_EXP:  return ON;
                      case RANGE_EXP:  return OR;
                      }
                  case PLUS_EXP:
                    switch (r.type())
                      {
                      case OPTION_EXP: return PO;
                      case PLUS_EXP:   return PP;      // this is never reached
                      case STAR_EXP:   return PS;
                      case POWER_EXP:  return PN;
                      case RANGE_EXP:  return PR;
                      }
                  case STAR_EXP:
                    switch (r.type())
                      {
                      case OPTION_EXP: return SO;
                      case PLUS_EXP:   return SP;
                      case STAR_EXP:   return SS;      // this is never reached
                      case POWER_EXP:  return SN;
                      case RANGE_EXP:  return SR;
                      }
                  case POWER_EXP:
                    switch (r.type())
                      {
                      case OPTION_EXP: return NO;
                      case PLUS_EXP:   return NP;
                      case STAR_EXP:   return NS;
                      case POWER_EXP:  return NN;      // this is never reached
                      case RANGE_EXP:  return NR;
                      }
                  case RANGE_EXP:
                    switch (r.type())
                      {
                      case OPTION_EXP: return RO;
                      case PLUS_EXP:   return RP;
                      case STAR_EXP:   return RS;
                      case POWER_EXP:  return RN;
                      case RANGE_EXP:  return RR;      // this is never reached
                      }
                  }
            }

          if (l.arg().equals(r))
            switch (l.type())
              {
              case OPTION_EXP: return OX;
              case PLUS_EXP:   return PX;
              case STAR_EXP:   return SX;
              case POWER_EXP:  return NX;
              case RANGE_EXP:  return RX;
              }
        }

      if (r.isUnary() && l.equals(r.arg()))
        switch (r.type())
          {
          case OPTION_EXP: return XO;
          case PLUS_EXP:   return XP;
          case STAR_EXP:   return XS;
          case POWER_EXP:  return XN;
          case RANGE_EXP:  return XR;
          }

      return 0;
    }

  // All the commented code below is junk...

  // /**
  //  * This returns true iff both this <tt>RegExp</tt>'s normalized
  //  * components are equal, or unary <tt>RegExp</tt>'s dealing with
  //  * the same expression.
  //  */
  // protected final boolean matchingArgs ()
  //   {
  //     return matchingPair(left(),right());
  //   }

  // /**
  //  * This returns true iff this is in a right associative form and the
  //  * first two items of this <tt>RegExp</tt>'s normalized components
  //  * are equal, or unary <tt>RegExp</tt>'s dealing with the same
  //  * expression. That is, whenever this is of the form either <tt>
  //  * X.(Y.Z)</tt> or <tt> X|(Y|Z)</tt>, where
  //  * <tt>matchingPair(X,Y)</tt> is <tt>true</tt>.
  //  */
  // protected final boolean matchingFirstArgs ()
  //   {
  //     return right().type() == type()
  //         && matchingPair(left(),
  //                         right().left());
  //   }

  // /**
  //  * Whenever <tt>matchingArgs()</tt> is <tt>true</tt>, this returns a
  //  * new binary expression obtained by swapping the left and right
  //  * expressions.
  //  */
  // protected final RegExpTwo swapArgs ()
  //   {
  //     if (isConcat())
  //       return concat(right(),left());

  //     return choice(right(),left());
  //   }

  // /**
  //  * Whenever <tt>matchingFirstArgs()</tt> is <tt>true</tt>, this
  //  * returns a new binary expression obtained by swapping the first
  //  * two expressions.
  //  */
  // protected final RegExpTwo swapFirstArgs ()
  //   {
  //     if (isConcat())
  //       return concat(right().left(),
  //                     concat(left(),
  //                            right().right()));

  //     return choice(right().left(),
  //                   choice(left(),
  //                          right().right()));
      
  //   }

  /**
   * Returns <tt>true</tt> iff the specified object is a
   * <tt>RegExpTwo</tt> of same type and components as this one's.
   */
  public final boolean equals (Object other)
    {
      return (this == other)
          || (other instanceof RegExp
              && type() == ((RegExp)other).type()
              && _left.equals(((RegExpTwo)other).left())
              && _right.equals(((RegExpTwo)other).right()));
    }
}
