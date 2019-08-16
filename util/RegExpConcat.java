//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

import java.util.Iterator;

/**
 * This class is a concrete implementation of the abstract class <a
 * href="RegExpTwo.html"><tt>RegExpTwo</tt></a> to represent
 * concatenation.
 *
 * @version     Last modified on Mon Mar 26 10:38:17 2018 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */
public class RegExpConcat extends RegExpTwo
{
  /**
   * Constructs a <tt>RegExpConcat</tt> with the two specified
   * <tt>RegExp</tt>'s.
   */
  public RegExpConcat (RegExp left, RegExp right)
    {
      super(left,right);
    }

  public final RegExp shallowCopy ()
    {
      return concat(_left,_right);
    }      

  public final RegExp deepCopy ()
    {
      return concat(_left.deepCopy(),_right.deepCopy());
    }      

  public final int type ()
    {
      return CONCAT_EXP;
    }

  /**
   * <a name="normalize">This</a> returns this <tt>RegExpConcat</tt>'s
   * normal form according to the following (conditional) rewrite rules.
   *
   * <p>
   *
   * These rules are categorized into three subgroups, depending on the
   * nature of this <tt>RegExpConcat</tt>'s <tt>left()</tt> and
   * <tt>right()</tt> components, corresponding to the three following
   * cases:
   *
   * <ol>
   *
   * <li> <tt>left()</tt> or <tt>right()</tt> is <tt>EMPTY</tt>;
   *
   * <li> <tt>left()</tt> and <tt>right()</tt> form a <i>matching
   *      pair</i>, or this is of the form <tt>A.(B.C)</tt>, where
   *      <tt>A</tt> and <tt>B</tt> form a <a
   *      href="RegExpTwo.html#matchingPair"> <i>matching pair</i></a>;
   *
   * <li> the rest.
   *
   * </ol>
   *
   * We simply refer to a rule in each group as (for lack of better
   * terms!):

   * <ol>
   *
   * <li> an <a href="#emptyrule"><i>empty</i>-rule</a>,
   *
   * <li> a <a href="#matchingpairrule"><i>matching-pair</i>-rule</a>,
   *
   * <li> an <a href="#otherrule"><i>other</i>-rule</a>,
   *
   * </ol>
   *
   * respectively. [In what follows, the "<i>qualifier</i>-rule"
   * notation will be used systematically and exclusively to qualify
   * these groups.]

   * ********************************************************************

   *
   * <h3><a name="emptyrule"></a><i>Empty</i>-rules</h3>
   *
   * The rules for concatenating <tt>EMPTY</tt> to anything, or
   * anything to <tt>EMPTY</tt>, are:
   *
   * <ul>
   * <li> <tt>
   *
   *      [CO:E_]
   *
   *      </tt>
   *      <tt>
   *
   *      ().X
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X
   *
   *      </tt>
   *
   * <li> <tt>
   *
   *      [CO:_E]
   *
   *      </tt>
   *      <tt>
   *
   *      X.()
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X
   *
   *      </tt>
   *
   * </ul>

   * ********************************************************************

   *
   * <h3><a name="matchingpairrule"></a><i>Matching-pair</i>-rules</h3>
   *

   * The rules for concatenating any two regular expressions forming a
   * <a href="RegExpTwo.html#matchingPair">"matching pair"</a> are given
   * by the table below.  Note that, although concatenation is not a
   * commutative operation on strings nor on regular expressions, its
   * restriction to such <a href="RegExpTwo.html#matchingPair">"matching
   * pairs"</a> of regular expressions is commutative. Thus, the table
   * is symmetric in its rows and columns (<i>i.e.</i>, it is invariant
   * when swapping any corresponding pair of row/column).
   *
   * <center>
   * <table summary="" cellpadding="10" cellspacing="5">

   * <tr>
   * <td><b><tt>
   *
   * _._
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
   * <td><table summary=""><tr><td><b><tt>
   *
   * X^2
   *
   * </tt></b></td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CO:XX">[CO:XX]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td><b><tt>
   *
   * X_1^2
   *
   * </tt></b></td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CO:XO">[CO:XO]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td><b><tt>
   *
   * X_2~
   *
   * </tt></b></td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CO:XP">[CO:XP]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td><b><tt>
   *
   * X+
   *
   * </tt></b></td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CO:XS">[CO:XS]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td><b><tt>
   *
   * X^q+1
   *
   * </tt></b></td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CO:XN">[CO:XN]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td><b><tt>
   *
   * X_p+1^q+1
   *
   * </tt></b></td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CO:XR">[CO:XR]</a>
   *
   * </tt></td></tr></table></td>
   * </tr>
   
   * <tr>
   * <td><b><tt>
   *
   * X?
   *
   * </tt></b></td>
   * <td><table summary=""><tr><td><b><tt>
   *
   * X_1^2
   *
   * </tt></b></td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CO:OX">[CO:OX]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td><b><tt>
   *
   * X_0^2
   *
   * </tt></b></td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CO:OO">[CO:OO]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td><b><tt>
   *
   * X+
   *
   * </tt></b></td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CO:OP">[CO:OP]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td><b><tt>
   *
   * X\*
   *
   * </tt></b></td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CO:OS">[CO:OS]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td><b><tt>
   *
   * X_q^q+1
   *
   * </tt></b></td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CO:ON">[CO:ON]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td><b><tt>
   *
   * X_p^q+1
   *
   * </tt></b></td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CO:OR">[CO:OR]</a>
   *
   * </tt></td></tr></table></td>
   * </tr>
   
   * <tr>
   * <td><b><tt>
   *
   * X+
   *
   * </tt></b></td>
   * <td><table summary=""><tr><td><b><tt>
   *
   * X_2~
   *
   * </tt></b></td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CO:PX">[CO:PX]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td><b><tt>
   *
   * X+
   *
   * </tt></b></td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CO:PO">[CO:PO]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td><b><tt>
   *
   *  X_2~
   *
   * </tt></b></td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CO:PP">[CO:PP]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td><b><tt>
   *
   * X+
   *
   * </tt></b></td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CO:PS">[CO:PS]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td><b><tt>
   *
   * X_q+1~
   *
   * </tt></b></td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CO:PN">[CO:PN]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td><b><tt>
   *
   * X_p+1~
   *
   * </tt></b></td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CO:PR">[CO:PR]</a>
   *
   * </tt></td></tr></table></td>
   * </tr>
   
   * <tr>
   * <td><b><tt>
   *
   * X\*
   *
   * </tt></b></td>
   * <td><table summary=""><tr><td><b><tt>
   *
   * X+
   *
   * </tt></b></td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CO:SX">[CO:SX]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td><b><tt>
   *
   * X\*
   *
   * </tt></b></td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CO:SO">[CO:SO]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td><b><tt>
   *
   * X+
   *
   * </tt></b></td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CO:SP">[CO:SP]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td><b><tt>
   *
   * X\*
   *
   * </tt></b></td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CO:SS">[CO:SS]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td><b><tt>
   *
   * X_q~
   *
   * </tt></b></td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CO:SN">[CO:SN]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td><b><tt>
   *
   * X_p~
   *
   * </tt></b></td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CO:SR">[CO:SR]</a>
   *
   * </tt></td></tr></table></td>
   * </tr>
   
   * <tr>
   * <td><b><tt>
   *
   * X^n
   *
   * </tt></b></td>
   * <td><table summary=""><tr><td><b><tt>
   *
   * X^n+1
   *
   * </tt></b></td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CO:NX">[CO:NX]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td><b><tt>
   *
   * X_n^n+1
   *
   * </tt></b></td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CO:NO">[CO:NO]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td><b><tt>
   *
   * X_n+1~
   *
   * </tt></b></td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CO:NP">[CO:NP]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td><b><tt>
   *
   * X_n~
   *
   * </tt></b></td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CO:NS">[CO:NS]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td><b><tt>
   *
   * X^n+q
   *
   * </tt></b></td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CO:NN">[CO:NN]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td><b><tt>
   *
   * X_n+p^n+q
   *
   * </tt></b></td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CO:NR">[CO:NR]</a>
   *
   * </tt></td></tr></table></td>
   * </tr>
   
   * <tr>
   * <td><b><tt>
   *
   * X_m^n
   *
   * </tt></b></td>
   * <td><table summary=""><tr><td><b><tt>
   *
   * X_m+1^n+1
   *
   * </tt></b></td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CO:RX">[CO:RX]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td><b><tt>
   *
   * X_m^n+1
   *
   * </tt></b></td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CO:RO">[CO:RO]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td><b><tt>
   *
   * X_m+1~
   *
   * </tt></b></td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CO:RP">[CO:RP]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td><b><tt>
   *
   * X_m~
   *
   * </tt></b></td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CO:RS">[CO:RS]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td><b><tt>
   *
   * X_m+q^n+q
   *
   * </tt></b></td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CO:RN">[CO:RN]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td><b><tt>
   *
   * X_m+p^n+q
   *
   * </tt></b></td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CO:RR">[CO:RR]</a>
   *
   * </tt></td></tr></table></td>
   * </tr>
   
   * </table>
   * </center>
   
   *
   * Each entry in this table is labeled <tt>[CO:<i>AB</i>]</tt> where <tt><i>A</i></tt> and <tt><i>B</i></tt> are a pair of letters
   * in the set <b><tt>{X O P S N
   * R}</tt></b>. Each letter is a mnemonic for the case it
   * stands for: <b><tt>X</tt></b> =
   * "eXpression", <b><tt>O</tt></b> =
   * "Option", <b><tt>P</tt></b> =
   * "Plus", <b><tt>S</tt></b> =
   * "Star", <b><tt>N</tt></b> = "N-th
   * power", <b><tt>R</tt></b> = "power
   * Range".  To each entry in the above table labeled <tt>[CO:<i>AB</i>]</tt>, there
   * correspond <i>two</i> rules, labeled <tt>[CO:<i>AB</i>1]</tt> and <tt>[CO:<i>AB</i>2]</tt>: the first one
   * covers the simple case and the second one the nested case. They are
   * listed again below for the sake of clarity.
   *
   * <p>
   *
   * The entries marked "<span style="color:red">(redundant)</span>" are
   * never used as they are systematically preempted earlier by the
   * <b><tt>X</tt></b>/<b><tt>X</tt></b> case. Indeed, it is never
   * possible for <a
   * href="RegExpTwo.html#matchingPairCode"><tt>matchingPairCode()</tt></a>
   * to return any of the values <tt>OO</tt>, <tt>PP</tt>, <tt>SS</tt>,
   * <tt>NN</tt>, nor <tt>RR</tt>. Therefore, the 10 corresponding rules
   * <a href="#CO:OO"><tt>[CO:OO1]</tt></a>, <a
   * href="#CO:OO"><tt>[CO:OO2]</tt></a>, <a
   * href="#CO:PP"><tt>[CO:PP1]</tt></a>, <a
   * href="#CO:PP"><tt>[CO:PP2]</tt></a>, <a
   * href="#CO:SS"><tt>[CO:SS1]</tt></a>, <a
   * href="#CO:SS"><tt>[CO:SS2]</tt></a>, <a
   * href="#CO:NN"><tt>[CO:NN1]</tt></a>, <a
   * href="#CO:NN"><tt>[CO:NN2]</tt></a>, <a
   * href="#CO:RR"><tt>[CO:RR1]</tt></a>, and <a
   * href="#CO:RR"><tt>[CO:RR2]</tt></a> are redundant and could be
   * eliminated altogether. As a matter of fact, each is confluent with
   * the effect of applying the <tt>CO:XX</tt> rule followed by the
   * appropriate <tt>PW:_</tt> rule. This can be trivially verified by
   * looking at the appropriate rules. The redundant rules are given
   * here for the sake of completeness and for the justification of
   * correctness of this specification.

   * ********************************************************************

   * <ul>

   * <li> <tt><a name="CO:XX">
   *
   *      [CO:XX1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X.X
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X^2
   *
   *      </tt>
   *
   * <li> <tt><a name="CO:XX2">
   *
   *      [CO:XX2]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X.(X.Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X^2.Y
   *
   *      </tt>
   *
   * <p>
   *
   * <li> <tt><a name="CO:XO">
   *
   *      [CO:XO1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X.X?
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_1^2
   *
   *      </tt>
   *
   * <li> <tt><a name="CO:XO2">
   *
   *      [CO:XO2]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X.(X?.Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_1^2.Y
   *
   *      </tt>
   *
   * <p>
   *
   * <li> <tt><a name="CO:XP">
   *
   *      [CO:XP1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X.X+
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_2~
   *
   *      </tt>
   *
   * <li> <tt><a name="CO:XP2">
   *
   *      [CO:XP2]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X.(X+.Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_2~.Y
   *
   *      </tt>
   *
   * <p>
   *
   * <li> <tt><a name="CO:XS">
   *
   *      [CO:XS1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X.X\*
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X+
   *
   *      </tt>
   *
   * <li> <tt><a name="CO:XS2">
   *
   *      [CO:XS2]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X.(X\*.Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X+.Y
   *
   *      </tt>
   *
   * <p>
   *
   * <li> <tt><a name="CO:XN">
   *
   *      [CO:XN1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X.X^q
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X^q+1
   *
   *      </tt>
   *
   * <li> <tt><a name="CO:XN2">
   *
   *      [CO:XN2]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X.(X^q.Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X^q+1.Y
   *
   *      </tt>
   *
   * <p>
   *
   * <li> <tt><a name="CO:XR">
   *
   *      [CO:XR1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X.X_p^q
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_p+1^q+1
   *
   *      </tt>

   * ********************************************************************

   * <li> <tt><a name="CO:XR2">
   *
   *      [CO:XR2]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X.(X_p^q.Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_p+1^q+1.Y
   *
   *      </tt>

   * ********************************************************************

   * <p>
   *
   * <li> <tt><a name="CO:OX">
   *
   *      [CO:OX1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X?.X
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_1^2
   *
   *      </tt>
   *
   * <li> <tt><a name="CO:OX2">
   *
   *      [CO:OX2]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X?.(X.Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_1^2.Y
   *
   *      </tt>
   *
   * <p>
   *
   * <li> <tt><a name="CO:OO">
   *
   *      [CO:OO1]
   *
   *      </a></tt>
   *      
   *      <tt>
   *
   *      X?.X?
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_0^2
   *
   *      </tt> <span style="color:red">(redundant)</span>
   *
   * <li> <tt><a name="CO:OO2">
   *
   *      [CO:OO2]
   *
   *      </a></tt>
   *      
   *      <tt>
   *
   *      X?.(X?.Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_0^2.Y
   *
   *      </tt> <span style="color:red">(redundant)</span>
   *
   * <p>
   *
   * <li> <tt><a name="CO:OP">
   *
   *      [CO:OP1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X?.X+
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X+
   *
   *      </tt>
   *
   * <li> <tt><a name="CO:OP2">
   *
   *      [CO:OP2]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X?.(X+.Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X+.Y
   *
   *      </tt>
   *
   * <p>
   *
   * <li> <tt><a name="CO:OS">
   *
   *      [CO:OS1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X?.X\*
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X*
   *
   *      </tt>
   *
   * <li> <tt><a name="CO:OS2">
   *
   *      [CO:OS2]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X?.(X\*.Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X*.Y
   *
   *      </tt>
   *
   * <p>
   *
   * <li> <tt><a name="CO:ON">
   *
   *      [CO:ON1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X?.X^q
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_q^q+1
   *
   *      </tt>
   *
   * <li> <tt><a name="CO:ON2">
   *
   *      [CO:ON2]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X?.(X^q.Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_q^q+1.Y
   *
   *      </tt>
   *
   * <p>
   *
   * <li> <tt><a name="CO:OR">
   *
   *      [CO:OR1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X?.X_p^q
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_p^q+1
   *
   *      </tt>

   * ********************************************************************

   * <li> <tt><a name="CO:OR2">
   *
   *      [CO:OR2]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X?.(X_p^q.Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_p^q+1.Y
   *
   *      </tt>

   * ********************************************************************

   * <p>
   *
   * <li> <tt><a name="CO:PX">
   *
   *      [CO:PX1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X+.X
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_2~
   *
   *      </tt>
   *
   * <li> <tt><a name="CO:PX2">
   *
   *      [CO:PX2]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X+.(X.Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_2~.Y
   *
   *      </tt>
   *
   * <p>
   *
   * <li> <tt><a name="CO:PO">
   *
   *      [CO:PO1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X+.X?
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X+
   *
   *      </tt>
   *
   * <li> <tt><a name="CO:PO2">
   *
   *      [CO:PO2]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X+.(X?.Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X+.Y
   *
   *      </tt>
   *
   * <p>
   *
   * <li> <tt><a name="CO:PP">
   *
   *      [CO:PP1]
   *
   *      </a></tt>
   *      
   *      <tt>
   *
   *      X+.X+
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_2~
   *
   *      </tt> <span style="color:red">(redundant)</span>
   *
   * <li> <tt><a name="CO:PP2">
   *
   *      [CO:PP2]
   *
   *      </a></tt>
   *      
   *      <tt>
   *
   *      X+.(X+.Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_2~.Y
   *
   *      </tt> <span style="color:red">(redundant)</span>
   *
   * <p>
   *
   * <li> <tt><a name="CO:PS">
   *
   *      [CO:PS1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X+.X\*
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X+
   *
   *      </tt>
   *
   * <li> <tt><a name="CO:PS2">
   *
   *      [CO:PS2]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X+.(X\*.Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X+.Y
   *
   *      </tt>
   *
   * <p>
   *
   * <li> <tt><a name="CO:PN">
   *
   *      [CO:PN1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X+.X^q
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_q+1~
   *
   *      </tt>
   *
   * <li> <tt><a name="CO:PN2">
   *
   *      [CO:PN2]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X+.(X^q.Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_q+1~.Y
   *
   *      </tt>
   *
   * <p>
   *
   * <li> <tt><a name="CO:PR">
   *
   *      [CO:PR1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X+.X_p^q
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_p+1~
   *
   *      </tt>

   * ********************************************************************

   * <li> <tt><a name="CO:PR2">
   *
   *      [CO:PR2]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X+.(X_p^q.Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_p+1~.Y
   *
   *      </tt>

   * ********************************************************************

   * <p>
   *
   * <li> <tt><a name="CO:SX">
   *
   *      [CO:SX1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X\*.X
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X+
   *
   *      </tt>
   *
   * <li> <tt><a name="CO:SX2">
   *
   *      [CO:SX2]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X\*.(X.Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X+.Y
   *
   *      </tt>
   *
   * <p>
   *
   * <li> <tt><a name="CO:SO">
   *
   *      [CO:SO1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X\*.X?
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X*
   *
   *      </tt>
   *
   * <li> <tt><a name="CO:SO2">
   *
   *      [CO:SO2]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X\*.(X?.Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X*.Y
   *
   *      </tt>
   *
   * <p>
   *
   * <li> <tt><a name="CO:SP">
   *
   *      [CO:SP1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X\*.X+
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X+
   *
   *      </tt>
   *
   * <li> <tt><a name="CO:SP2">
   *
   *      [CO:SP2]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X\*.(X+.Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X+.Y
   *
   *      </tt>
   *
   * <p>
   *
   * <li> <tt><a name="CO:SS">
   *
   *      [CO:SS1]
   *
   *      </a></tt>
   *      
   *      <tt>
   *
   *      X\*.X\*
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X\*
   *
   *      </tt> <span style="color:red">(redundant)</span>
   *
   * <li> <tt><a name="CO:SS2">
   *
   *      [CO:SS2]
   *
   *      </a></tt>
   *      
   *      <tt>
   *
   *      X\*.(X\*.Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X\*.Y
   *
   *      </tt> <span style="color:red">(redundant)</span>
   *
   * <p>
   *
   * <li> <tt><a name="CO:SN">
   *
   *      [CO:SN1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X\*.X^q
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_q~
   *
   *      </tt>
   *
   * <li> <tt><a name="CO:SN2">
   *
   *      [CO:SN2]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X\*.(X^q.Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_q~.Y
   *
   *      </tt>
   *
   * <p>
   *
   * <li> <tt><a name="CO:SR">
   *
   *      [CO:SR1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X\*.X_p^q
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_p~
   *
   *      </tt>

   * ********************************************************************

   * <li> <tt><a name="CO:SR2">
   *
   *      [CO:SR2]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X\*.(X_p^q.Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_p~.Y
   *
   *      </tt>

   * ********************************************************************

   * <p>
   *
   * <li> <tt><a name="CO:NX">
   *
   *      [CO:NX1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X^n.X
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X^n+1
   *
   *      </tt>
   *
   * <li> <tt><a name="CO:NX2">
   *
   *      [CO:NX2]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X^n.(X.Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X^n+1.Y
   *
   *      </tt>
   *
   * <p>
   *
   * <li> <tt><a name="CO:NO">
   *
   *      [CO:NO1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X^n.X?
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_n^n+1
   *
   *      </tt>
   *
   * <li> <tt><a name="CO:NO2">
   *
   *      [CO:NO2]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X^n.(X?.Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_n^n+1.Y
   *
   *      </tt>
   *
   * <p>
   *
   * <li> <tt><a name="CO:NP">
   *
   *      [CO:NP1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X^n.X+
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_n+1~
   *
   *      </tt>
   *
   * <li> <tt><a name="CO:NP2">
   *
   *      [CO:NP2]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X^n.(X+.Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_n+1~.Y
   *
   *      </tt>
   *
   * <p>
   *
   * <li> <tt><a name="CO:NS">
   *
   *      [CO:NS1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X^n.X\*
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_n~
   *
   *      </tt>
   *
   * <li> <tt><a name="CO:NS2">
   *
   *      [CO:NS2]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X^n.(X\*.Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_n~.Y
   *
   *      </tt>
   *
   * <p>
   *
   * <li> <tt><a name="CO:NN">
   *
   *      [CO:NN1]
   *
   *      </a></tt>
   *      
   *      <tt>
   *
   *      X^n.X^q
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X^n+q
   *
   *      </tt> <span style="color:red">(redundant)</span>
   *
   * <li> <tt><a name="CO:NN2">
   *
   *      [CO:NN2]
   *
   *      </a></tt>
   *      
   *      <tt>
   *
   *      X^n.(X^q.Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X^n+q.Y
   *
   *      </tt> <span style="color:red">(redundant)</span>
   *
   * <p>
   *
   * <li> <tt><a name="CO:NR">
   *
   *      [CO:NR1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X^n.X_p^q
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_n+p^n+q
   *
   *      </tt>

   * ********************************************************************

   * <li> <tt><a name="CO:NR2">
   *
   *      [CO:NR2]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X^n.(X_p^q.Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_n+p^n+q.Y
   *
   *      </tt>

   * ********************************************************************

   * <p>
   *
   * <li> <tt><a name="CO:RX">
   *
   *      [CO:RX1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X_m^n.X
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_m+1^n+1
   *
   *      </tt>
   *
   * <li> <tt><a name="CO:RX2">
   *
   *      [CO:RX2]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X_m^n.(X.Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_m+1^n+1.Y
   *
   *      </tt>
   *
   * <p>
   *
   * <li> <tt><a name="CO:RO">
   *
   *      [CO:RO1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X_m^n.X?
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_m^n+1
   *
   *      </tt>
   *
   * <li> <tt><a name="CO:RO2">
   *
   *      [CO:RO2]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X_m^n.(X?.Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_m^n+1.Y
   *
   *      </tt>
   *
   * <p>
   *
   * <li> <tt><a name="CO:RP">
   *
   *      [CO:RP1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X_m^n.X+
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_m+1~
   *
   *      </tt>
   *
   * <li> <tt><a name="CO:RP2">
   *
   *      [CO:RP2]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X_m^n.(X+.Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_m+1~.Y
   *
   *      </tt>
   *
   * <p>
   *
   * <li> <tt><a name="CO:RS">
   *
   *      [CO:RS1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X_m^n.X\*
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_m~
   *
   *      </tt>
   *
   * <li> <tt><a name="CO:RS2">
   *
   *      [CO:RS2]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X_m^n.(X\*.Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_m~.Y
   *
   *      </tt>
   *
   * <p>
   *
   * <li> <tt><a name="CO:RN">
   *
   *      [CO:RN1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X_m^n.X^q
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_m+q^n+q
   *
   *      </tt>
   *
   * <li> <tt><a name="CO:RN2">
   *
   *      [CO:RN2]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X_m^n.(X^q.Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_m+q^n+q.Y
   *
   *      </tt>
   *
   * <p>
   *
   * <li> <tt><a name="CO:RR">
   *
   *      [CO:RR1]
   *
   *      </a></tt>
   *      
   *      <tt>
   *
   *      X_m^n.X_p^q
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_m+p^n+q
   *
   *      </tt> <span style="color:red">(redundant)</span>
   *
   * <li> <tt><a name="CO:RR2">
   *
   *      [CO:RR2]
   *
   *      </a></tt>
   *      
   *      <tt>
   *
   *      X_m^n.(X_p^q.Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X_m+p^n+q.Y
   *
   *      </tt> <span style="color:red">(redundant)</span>
   
   * </ul>

   * ********************************************************************

   * ********************************************************************

   *
   * <h3><a name="otherrule"></a><i>Other</i>-rules</h3>
   *

   * These rules are essentially putting all nested concatenations into
   * right-associative form and expanding left and right distributivity.

   * <ul>
   *
   * <li> <tt>
   *
   *      [CO:_A]
   *
   *      </tt>
   *      <tt>
   *
   *      (X.Y).Z
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X.(Y.Z)
   *
   *      </tt>
   *
   * <li> <tt>
   *
   *      [CO:D_]
   *
   *      </tt>
   *      <tt>
   *
   *      (X | Y).Z
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X.Z | Y.Z
   *
   *      </tt>
   *
   * <li> <tt>
   *
   *      [CO:_D]
   *
   *      </tt>
   *      <tt>
   *
   *      X.(Y | Z)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X.Y | X.Z
   *
   *      </tt>
   *
   * </ul>

   */
  public void normalize ()
    {
      String rule = null;
      RegExp left = left();
      RegExp right = right();

      out:
      {
	if (left.isEmpty())
	  {
	    rule = "[CO:E_] ().X --> X";
	    _normalForm = right;
	    break out;
	  }

	if (right.isEmpty())
	  {
	    rule = "[CO:_E] X.() --> X";
	    _normalForm = left;
	    break out;
	  }

	if (left.isConcat())
	  {
	    rule = "[CO:_A] (X.Y).Z --> X.(Y.Z)";
	    _normalForm = concat(left.left(),
				 concat(left.right(),
					right));
	    break out;
	  }

	if (left.isChoice())
	  {
	    rule = "[CO:D_] (X | Y).Z --> X.Z | Y.Z";
	    _normalForm = choice(concat(left.left(),
					right),
				 concat(left.right(),
					right));
	    break out;
	  }

	if (right.isChoice())
	  {
	    rule = "[CO:_D] X.(Y | Z) --> X.Y | X.Z";
	    _normalForm = choice(concat(left,
					right.left()),
				 concat(left,
					right.right()));
	    break out;
	  }

	/* **************************************** */

	RegExp X,Y;
	int m,n,p,q;

	// 	  int code = matchingPairCode();
	// 	  System.err.println("code for "+this+" = "+code(code));

	switch (matchingPairCode())
	  {
	  case XX:
	    rule = "[CO:XX1] X.X --> X^2";
	    X = left;
	    _normalForm = power(X,2);
	    break out;
	  case XO:
	    rule = "[CO:XO1] X.X? --> X_1^2";
	    X = left;
	    _normalForm = range(X,1,2);
	    break out;
	  case XP:
	    rule = "[CO:XP1] X.X+ --> X_2~";
	    X = left;
	    _normalForm = range(X,2);
	    break out;
	  case XS:
	    rule = "[CO:XS1] X.X* --> X+";
	    X = left;
	    _normalForm = plus(X);
	    break out;
	  case XN:
	    rule = "[CO:XN1] X.X^q --> X^q+1";
	    X = left;
	    q = right.power();
	    _normalForm = power(X,plus(q,1));
	    break out;
	  case XR:
	    rule = "[CO:XR1] X.X_p^q --> X_p+1^q+1";
	    X = left;
	    p = right.lower();
	    q = right.upper();
	    _normalForm = range(left,plus(p,1),plus(q,1));
	    break out;
	  case OX:
	    rule = "[CO:OX1] X?.X --> X_1^2";
	    X = right;
	    _normalForm = range(X,1,2);
	    break out;
	  case OO:	// this is never reached
	    rule = "[CO:OO1] X?.X? --> X_0^2";
	    X = left.arg();
	    _normalForm = range(X,0,2);
	    break out;
	  case OP:
	    rule = "[CO:OP1] X?.X+ --> X+";
	    _normalForm = right;
	    break out;
	  case OS:
	    rule = "[CO:OS1] X?.X* --> X*";
	    _normalForm = right;
	    break out;
	  case ON:
	    rule = "[CO:ON1] X?.X^q --> X_q^q+1";
	    X = left.arg();
	    q = right.power();
	    _normalForm = range(X,q,plus(q,1));
	    break out;
	  case OR:
	    rule = "[CO:OR1] X?.X_p^q --> X_p^q+1";
	    X = left.arg();
	    p = right.lower();
	    q = right.upper();
	    _normalForm = range(X,p,plus(q,1));
	    break out;
	  case PX:
	    rule = "[CO:PX1] X+.X --> X_2~";
	    X = right;
	    _normalForm = range(X,2);
	    break out;
	  case PO:
	    rule = "[CO:PO1] X+.X? --> X+";
	    _normalForm = left;
	    break out;
	  case PP:	// this is never reached
	    rule = "[CO:PP1] X+.X+ --> X_2~";
	    X = left.arg();
	    _normalForm = range(X,2);
	    break out;
	  case PS:
	    rule = "[CO:PS1] X+.X* --> X+";
	    _normalForm = left;
	    break out;
	  case PN:
	    rule = "[CO:PN1] X+.X^q --> X_q+1~";
	    X = left.arg();
	    q = right.power();
	    _normalForm = range(X,plus(q,1));
	    break out;
	  case PR:
	    rule = "[CO:PR1] X+.X_p^q --> X_p+1~";
	    X = left.arg();
	    p = right.lower();
	    q = right.upper();
	    _normalForm = range(X,plus(p,1));
	    break out;
	  case SX:
	    rule = "[CO:SX1] X*.X --> X+";
	    X = right;
	    _normalForm = plus(X);
	    break out;
	  case SO:
	    rule = "[CO:SO1] X*.X? --> X*";
	    _normalForm = left;
	    break out;
	  case SP:
	    rule = "[CO:SP] X*.X+ --> X+";
	    _normalForm = right;
	    break out;
	  case SS:	// this is never reached
	    rule = "[CO:SS1] X*.X* --> X*";
	    _normalForm = left;
	    break out;
	  case SN:
	    rule = "[CO:SN] X*.X^q --> X_q~";
	    X = left.arg();
	    q = right.power();
	    _normalForm = range(X,q);
	    break out;
	  case SR:
	    rule = "[CO:SR1] X*.X_p^q --> X_p~";
	    X = left.arg();
	    p = right.lower();
	    _normalForm = range(X,p);
	    break out;
	  case NX:
	    rule = "[CO:NX1] X^n.X --> X^n+1";
	    X = right;
	    n = left.power();
	    _normalForm = power(X,plus(n,1));
	    break out;
	  case NO:
	    rule = "[CO:NO1] X^n.X? --> X_n^n+1";
	    X = left.arg();
	    n = left.power();
	    _normalForm = range(X,n,plus(n,1));
	    break out;
	  case NP:
	    rule = "[CO:NP1] X^n.X+ --> X_n+1~";
	    X = left.arg();
	    n = left.power();
	    _normalForm = range(X,plus(n,1));
	    break out;
	  case NS:
	    rule = "[CO:NS1] X^n.X* --> X_n~";
	    X = left.arg();
	    n = left.power();
	    _normalForm = range(X,n);
	    break out;
	  case NN:	// this is never reached
	    rule = "[CO:NN1] X^n.X^q --> X^n+q";
	    X = left.arg();
	    n = left.power();
	    q = right.power();
	    _normalForm = power(X,plus(n,q));
	    break out;
	  case NR:
	    rule = "[CO:NR1] X^n.X_p^q --> X_n+p^n+q";
	    X = left.arg();
	    n = left.power();
	    p = right.lower();
	    q = right.upper();
	    _normalForm = range(X,plus(n,p),plus(n,q));
	    break out;
	  case RX:
	    rule = "[CO:RX1] X_m^n.X --> X_m+1^n+1";
	    X = right;
	    m = left.lower();
	    n = left.upper();
	    _normalForm = range(X,plus(m,1),plus(n,1));
	    break out;
	  case RO:
	    rule = "[CO:RO1] X_m^n.X? --> X_m^n+1";
	    X = left.arg();
	    m = left.lower();
	    n = left.upper();
	    _normalForm = range(X,m,plus(n,1));
	    break out;
	  case RP:
	    rule = "[CO:RP1] X_m^n.X+ --> X_m+1~";
	    X = left.arg();
	    m = left.lower();
	    n = left.upper();
	    _normalForm = range(X,plus(m,1));
	    break out;
	  case RS:
	    rule = "[CO:RS1] X_m^n.X* --> X_m~";
	    X = left.arg();
	    m = left.lower();
	    n = left.upper();
	    _normalForm = range(X,m);
	    break out;
	  case RN:
	    rule = "[CO:RN1] X_m^n.X^q --> X_m+q^n+q";
	    X = left.arg();
	    m = left.lower();
	    n = left.upper();
	    q = right.power();
	    _normalForm = range(X,plus(m,q),plus(n,q));
	    break out;
	  case RR:	// this is never reached
	    rule = "[CO:RR1] X_m^n.X_p^q --> X_m+p^n+q";
	    X = left.arg();
	    m = left.lower();
	    n = left.upper();
	    p = right.lower();
	    q = right.upper();
	    _normalForm = range(X,plus(m,p),plus(n,q));
	    break out;
	  }

	switch (matchingArgsCode())
	  {
	  case XX:
	    rule = "[CO:XX2] X.(X.Y) --> X^2.Y";
	    X = left;
	    Y = right.right();
	    _normalForm = concat(power(X,2),Y);
	    break out;
	  case XO:
	    rule = "[CO:XO2] X.(X?.Y) --> X_1^2.Y";
	    X = left;
	    Y = right.right();
	    _normalForm = concat(range(X,1,2),Y);
	    break out;
	  case XP:
	    rule = "[CO:XP2] X.(X+.Y) --> X_2~.Y";
	    X = left;
	    Y = right.right();
	    _normalForm = concat(range(X,2),Y);
	    break out;
	  case XS:
	    rule = "[CO:XS2] X.(X*.Y) --> X+.Y";
	    X = left;
	    Y = right.right();
	    _normalForm = concat(plus(X),Y);
	    break out;
	  case XN:
	    rule = "[CO:XN2] X.(X^q.Y) --> X^q+1.Y";
	    X = left;
	    q = right.left().power();
	    Y = right.right();
	    _normalForm = concat(power(X,plus(q,1)),Y);
	    break out;
	  case XR:
	    rule = "[CO:XR2] X.(X_p^q.Y) --> X_p+1^q+1.Y";
	    X = left;
	    p = right.left().lower();
	    q = right.left().upper();
	    Y = right.right();
	    _normalForm = concat(range(left,plus(p,1),plus(q,1)),Y);
	    break out;
	  case OX:
	    rule = "[CO:OX2] X?.(X.Y) --> X_1^2.Y";
	    X = right.left();
	    Y = right.right();
	    _normalForm = concat(range(X,1,2),Y);
	    break out;
	  case OO:	// this is never reached
	    rule = "[CO:OO2] X?.(X?.Y) --> X_0^2.Y";
	    X = left.arg();
	    Y = right.right();
	    _normalForm = concat(range(X,0,2),Y);
	    break out;
	  case OP:
	    rule = "[CO:OP2] X?.(X+.Y) --> X+.Y";
	    _normalForm = right;
	    break out;
	  case OS:
	    rule = "[CO:OS2] X?.(X*.Y) --> X*.Y";
	    _normalForm = right;
	    break out;
	  case ON:
	    rule = "[CO:ON2] X?.(X^q.Y) --> X_q^q+1.Y";
	    X = left.arg();
	    q = right.left().power();
	    Y = right.right();
	    _normalForm = concat(range(X,q,plus(q,1)),Y);
	    break out;
	  case OR:
	    rule = "[CO:OR2] X?.(X_p^q.Y) --> X_p^q+1.Y";
	    X = left.arg();
	    p = right.left().lower();
	    q = right.left().upper();
	    Y = right.right();
	    _normalForm = concat(range(X,p,plus(q,1)),Y);
	    break out;
	  case PX:
	    rule = "[CO:PX2] X+.(X.Y) --> X_2~.Y";
	    X = right.left();
	    Y = right.right();
	    _normalForm = concat(range(X,2),Y);
	    break out;
	  case PO:
	    rule = "[CO:PO2] X+.(X?.Y) --> X+.Y";
	    Y = right.right();
	    _normalForm = concat(left,Y);
	    break out;
	  case PP:	// this is never reached
	    rule = "[CO:PP2] X+.(X+.Y) --> X_2~.Y";
	    X = left.arg();
	    Y = right.right();
	    _normalForm = concat(range(X,2),Y);
	    break out;
	  case PS:
	    rule = "[CO:PS2] X+.(X*.Y) --> X+.Y";
	    X = left.arg();
	    Y = right.right();
	    _normalForm = concat(plus(X),Y);
	    break out;
	  case PN:
	    rule = "[CO:PN2] X+.(X^q.Y) --> X_q+1~.Y";
	    X = left.arg();
	    q = right.left().power();
	    Y = right.right();
	    _normalForm = concat(range(X,plus(q,1)),Y);
	    break out;
	  case PR:
	    rule = "[CO:PR2] X+.(X_p^q.Y) --> X_p+1~.Y";
	    X = left.arg();
	    p = right.left().lower();
	    q = right.left().upper();
	    Y = right.right();
	    _normalForm = concat(range(X,plus(p,1)),Y);
	    break out;
	  case SX:
	    rule = "[CO:SX2] X*.(X.Y) --> X+.Y";
	    X = right.left();
	    Y = right.right();
	    _normalForm = concat(plus(X),Y);
	    break out;
	  case SO:
	    rule = "[CO:SO2] X*.(X?.Y) --> X*.Y";
	    Y = right.right();
	    _normalForm = concat(left,Y);
	    break out;
	  case SP:
	    rule = "[CO:SP2] X*.(X+.Y) --> X+.Y";
	    _normalForm = right;
	    break out;
	  case SS:	// this is never reached
	    rule = "[CO:SS2] X*.(X*.Y) --> X*.Y";
	    _normalForm = right;
	    break out;
	  case SN:
	    rule = "[CO:SN2] X*.(X^q.Y) --> X_q~.Y";
	    X = left.arg();
	    q = right.left().power();
	    Y = right.right();
	    _normalForm = concat(range(X,q),Y);
	    break out;
	  case SR:
	    rule = "[CO:SR2] X*.(X_p^q.Y) --> X_p~.Y";
	    X = left.arg();
	    p = right.left().lower();
	    Y = right.right();
	    _normalForm = concat(range(X,p),Y);
	    break out;
	  case NX:
	    rule = "[CO:NX2] X^n.(X.Y) --> X^n+1.Y";
	    X = right.left();
	    n = left.power();
	    Y = right.right();
	    _normalForm = concat(power(X,plus(n,1)),Y);
	    break out;
	  case NO:
	    rule = "[CO:NO2] X^n.(X?.Y) --> X_n^n+1.Y";
	    X = left.arg();
	    n = left.power();
	    Y = right.right();
	    _normalForm = concat(range(X,n,plus(n,1)),Y);
	    break out;
	  case NP:
	    rule = "[CO:NP2] X^n.(X+.Y) --> X_n+1~.Y";
	    X = left.arg();
	    n = left.power();
	    Y = right.right();
	    _normalForm = concat(range(X,plus(n,1)),Y);
	    break out;
	  case NS:
	    rule = "[CO:NS2] X^n.(X*.Y) --> X_n~.Y";
	    X = left.arg();
	    n = left.power();
	    Y = right.right();
	    _normalForm = concat(range(X,n),Y);
	    break out;
	  case NN:	// this is never reached
	    rule = "[CO:NN2] X^n.(X^q.Y) --> X^n+q.Y";
	    X = left.arg();
	    n = left.power();
	    q = right.left().power();
	    Y = right.right();
	    _normalForm = concat(power(X,plus(n,q)),Y);
	    break out;
	  case NR:
	    rule = "[CO:NR2] X^n.(X_p^q.Y) --> X_n+p^n+q.Y";
	    X = left.arg();
	    n = left.power();
	    p = right.left().lower();
	    q = right.left().upper();
	    Y = right.right();
	    _normalForm = concat(range(X,plus(n,p),plus(n,q)),Y);
	    break out;
	  case RX:
	    rule = "[CO:RX2] X_m^n.(X.Y) --> X_m+1^n+1.Y";
	    X = right.left();
	    m = left.lower();
	    n = left.upper();
	    Y = right.right();
	    _normalForm = concat(range(X,plus(m,1),plus(n,1)),Y);
	    break out;
	  case RO:
	    rule = "[CO:RO2] X_m^n.(X?.Y) --> X_m^n+1.Y";
	    X = left.arg();
	    m = left.lower();
	    n = left.upper();
	    Y = right.right();
	    _normalForm = concat(range(X,m,plus(n,1)),Y);
	    break out;
	  case RP:
	    rule = "[CO:RP2] X_m^n.(X+.Y) --> X_m+1~.Y";
	    X = left.arg();
	    m = left.lower();
	    n = left.upper();
	    Y = right.right();
	    _normalForm = concat(range(X,plus(m,1)),Y);
	    break out;
	  case RS:
	    rule = "[CO:RS2] X_m^n.(X*.Y) --> X_m~.Y";
	    X = left.arg();
	    m = left.lower();
	    n = left.upper();
	    Y = right.right();
	    _normalForm = concat(range(X,m),Y);
	    break out;
	  case RN:
	    rule = "[CO:RN2] X_m^n.(X^q.Y) --> X_m+q^n+q.Y";
	    X = left.arg();
	    m = left.lower();
	    n = left.upper();
	    q = right.left().power();
	    Y = right.right();
	    _normalForm = concat(range(X,plus(m,q),plus(n,q)),Y);
	    break out;
	  case RR:	// this is never reached
	    rule = "[CO:RR2] X_m^n.(X_p^q.Y) --> X_m+p^n+q.Y";
	    X = left.arg();
	    m = left.lower();
	    n = left.upper();
	    p = right.left().lower();
	    q = right.left().upper();
	    Y = right.right();
	    _normalForm = concat(range(X,plus(m,p),plus(n,q)),Y);
	    break out;
	  }

      } // end of named block "out:"

      traceRule(rule);
      checkNormalForm();
    }

  private RegExp concat (ArrayList list)
  {
    RegExp e = RegExpSymbol.EMPTY;
    for (Iterator i=list.iterator(); i.hasNext();)
      e = concat((RegExp)i.next(),e);

    return e;
  }

  private final boolean reOccursIn (RegExp exp, ArrayList path)
  {
    if (exp.type() != CONCAT_EXP)
      return false;

    path.add(left());
    RegExpConcat seq = (RegExpConcat)exp;
    if (this == seq.right())
      return true;

    return reOccursIn(seq.right(),path);
  }

  /**
   * Returns a printable form for this <tt>RegExpConcat</tt>.
   */
  public final String toString ()
  {
    String left = _left.toString();
    String right = _right.toString();

    //       if (_left.isChoice())
    if (_left.isBinary())
      left = "("+left+")";

    if (_right.isChoice())
      right = "("+right+")";

    return left+"."+right;
  }

  public final String toNormalString ()
  {
      if (!inNormalForm())
	return _normalForm.toNormalString();

    String left = _left.toNormalString();
    String right = _right.toNormalString();

    //       if (_left.isChoice())
    if (_left.isBinary())
      left = "("+left+")";

    if (_right.isChoice())
      right = "("+right+")";

    return left+"."+right;
  }

}
