//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

/**
 * This class is a concrete implementation of the abstract class <a
 * href="RegExpTwo.html"><tt>RegExpTwo</tt></a> to represent alternation.
 *
 * @version     Last modified on Mon Mar 26 10:45:54 2018 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */
public class RegExpChoice extends RegExpTwo
{
  /**
   * Constructs a <tt>RegExpChoice</tt> with the two specified
   * <tt>RegExp</tt>'s.
   */
  public RegExpChoice (RegExp left, RegExp right)
    {
      super(left,right);
    }

  public final int type ()
    {
      return CHOICE_EXP;
    }

  public final RegExp shallowCopy ()
    {
      return choice(_left,_right);
    }      

  public final RegExp deepCopy ()
    {
      return choice(_left.deepCopy(),_right.deepCopy());
    }      

  /**
   * <a name="normalize">This</a> computes the normal form of this
   * <tt>RegExpChoice</tt>, and sets its <tt>_normalForm</tt> field to
   * it. This normal form is computed according to the following rewrite
   * rules.
   *
   * <p>
   *
   * These rules are categorized into five subgroups, depending on the
   * nature of this <tt>RegExpChoice</tt>'s <tt>left()</tt> and
   * <tt>right()</tt> components, corresponding to the three following
   * cases:
   *
   * <ol>
   *
   * <li> <tt>left()</tt> or <tt>right()</tt> is <tt>EMPTY</tt>;
   *
   * <li> <tt>left()</tt> and <tt>right()</tt> forms are non-numeric
   * unary expressions forming a <i>matching pair</i>, or this is of the
   * form <tt>A|(B|C)</tt>, where <tt>A</tt> and <tt>B</tt> form such a
   * (restricted) <a href="RegExpTwo.html#matchingPair"> <i>matching
   * pair</i></a>;
   *
   * <li> power/range rules (taking care of choices of numeric unary
   * expressions);
   *
   * <li> commutation rules;
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
   * <li> a <a href="#powerrule"><i>power</i>-rule</a>,
   *
   * <li> a <a href="#swaprule"><i>swap</i>-rule</a>,
   *
   * <li> an <a href="#otherrule"><i>other</i>-rule</a>.
   *
   * </ol>
   *
   * respectively. [In what follows, the "<i>qualifier</i>-rule"
   * notation will be used systematically and exclusively to qualify
   * these groups.]


   *
   * <h3><a name="emptyrule"></a><i>Empty</i>-rules</h3>
   *
   * The rules for a choice involving <tt>EMPTY</tt> are:
   *
   * <ul>
   *
   * <li> <tt>
   *
   *      [CH:E_]
   *
   *      </tt>
   *      <tt>
   *
   *      ()|X
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X?
   *
   *      </tt>
   *
   * <li> <tt>
   *
   *      [CH:_E]
   *
   *      </tt>
   *      <tt>
   *
   *      X|()
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X?
   *
   *      </tt>
   *
   * </ul>

   * ********************************************************************

   *
   * <h3><a name="matchingpairrule"></a><i>Matching-pair</i>-rules</h3>
   *

   * The rules for choosing among any two regular expressions forming a
   * <a href="RegExpTwo.html#matchingPair">"matching pair"</a> are given
   * by the table below - but restricted to non-numeric unary
   * operators. 


   * <center>
   * <table summary="" cellpadding="10" cellspacing="5">

   * <tr>
   * <td><b><tt>
   *
   * _|_
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
   * X^n
   *
   * </tt></b></td>
   * <td><b><tt>
   *
   * X_m^n
   *
   * </tt></b></td>
   * </tr>
   
   * <tr>
   * <td><b><tt>
   *
   * X
   *
   * </tt></b></td>
   * <td><table summary=""><tr><td>
   * <b><tt>
   *
   * X
   *
   * </tt></b>
   * </td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CH:XX">[CH:XX]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td>
   * <b><tt>
   *
   * X?
   *
   * </tt></b>
   * </td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CH:XO">[CH:XO]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td>
   * <b><tt>
   *
   * X+
   *
   * </tt></b>
   * </td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CH:XP">[CH:XP]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td>
   * <b><tt>
   *
   * X\*
   *
   * </tt></b>
   * </td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CH:XS">[CH:XS]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td>
   *
   * <span style="color:red;font-size:smaller">(see below)</span>
   * 
   * </td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CH:XN">[CH:XN]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td>
   *
   * <span style="color:red;font-size:smaller">(see below)</span>
   * 
   * </td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CH:XR">[CH:XR]</a>
   *
   * </tt></td></tr></table></td>
   * </tr>
   
   * <tr>
   * <td><b><tt>
   *
   * X?
   *
   * </tt></b></td>
   * <td><table summary=""><tr><td>
   * <b><tt>
   *
   * X?
   *
   * </tt></b>
   * </td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CH:OX">[CH:OX]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td>
   * <b><tt>
   *
   * X?
   *
   * </tt></b>
   * </td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CH:OO">[CH:OO]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td>
   * <b><tt>
   *
   * X\*
   *
   * </tt></b>
   * </td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CH:OP">[CH:OP]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td>
   * <b><tt>
   *
   * X\*
   *
   * </tt></b>
   * </td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CH:OS">[CH:OS]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td>
   *
   * <span style="color:red;font-size:smaller">(see below)</span>
   * 
   * </td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CH:ON">[CH:ON]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td>
   *
   * <span style="color:red;font-size:smaller">(see below)</span>
   * 
   * </td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CH:OR">[CH:OR]</a>
   *
   * </tt></td></tr></table></td>
   * </tr>
   
   * <tr> 
   * <td><b><tt>
   *
   * X+
   *
   * </tt></b></td>
   * <td><table summary=""><tr><td>
   * <b><tt>
   *
   * X+
   *
   * </tt></b>
   * </td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CH:PX">[CH:PX]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td>
   * <b><tt>
   *
   * X\*
   *
   * </tt></b>
   * </td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CH:PO">[CH:PO]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td>
   * <b><tt>
   *
   * X+
   *
   * </tt></b>
   * </td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CH:PP">[CH:PP]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td>
   * <b><tt>
   *
   * X\*
   *
   * </tt></b>
   * </td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CH:PS">[CH:PS]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td>
   * <b><tt>
   *
   * X+
   *
   * </tt></b>
   * </td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CH:PN">[CH:PN]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td>
   * <b><tt>
   *
   * X+
   *
   * </tt></b>
   * </td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CH:PR">[CH:PR]</a>
   *
   * </tt></td></tr></table></td>
   * </tr>
   
   * <tr> 
   * <td><b><tt>
   *
   * X\*
   *
   * </tt></b></td>
   * <td><table summary=""><tr><td>
   * <b><tt>
   *
   * X\*
   *
   * </tt></b>
   * </td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CH:SX">[CH:SX]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td>
   * <b><tt>
   *
   * X\*
   *
   * </tt></b>
   * </td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CH:SO">[CH:SO]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td>
   * <b><tt>
   *
   * X\*
   *
   * </tt></b>
   * </td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CH:SP">[CH:SP]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td>
   * <b><tt>
   *
   * X\*
   *
   * </tt></b>
   * </td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CH:SS">[CH:SS]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td>
   * <b><tt>
   *
   * X\*
   *
   * </tt></b>
   * </td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CH:SN">[CH:SN]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td>
   * <b><tt>
   *
   * X\*
   *
   * </tt></b>
   * </td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CH:SR">[CH:SR]</a>
   *
   * </tt></td></tr></table></td>
   * </tr>
      
   * <tr> 
   * <td><b><tt>
   *
   * X^n
   *
   * </tt></b></td>
   * <td><table summary=""><tr><td>
   *
   * <span style="color:red;font-size:smaller">(see below)</span>
   * 
   * </td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CH:NX">[CH:NX]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td>
   *
   * <span style="color:red;font-size:smaller">(see below)</span>
   * 
   * </td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CH:NO">[CH:NO]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td>
   * <b><tt>
   *
   * X+
   *
   * </tt></b>
   * </td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CH:NP">[CH:NP]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td>
   * <b><tt>
   *
   * X\*
   *
   * </tt></b>
   * </td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CH:SS">[CH:NS]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td>
   *
   * <span style="color:red;font-size:smaller">(see below)</span>
   *
   * </td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CH:NN">[CH:NN]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td>
   *
   * <span style="color:red;font-size:smaller">(see below)</span>
   *
   * </td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CH:NR">[CH:NR]</a>
   *
   * </tt></td></tr></table></td>
   * </tr>

   * <tr> 
   * <td><b><tt>
   *
   * X_m^n
   *
   * </tt></b></td>
   * <td><table summary=""><tr><td>
   *
   * <span style="color:red;font-size:smaller">(see below)</span>
   * 
   * </td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CH:RX">[CH:RX]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td>
   *
   * <span style="color:red;font-size:smaller">(see below)</span>
   * 
   * </td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CH:RO">[CH:RO]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td>
   * <b><tt>
   *
   * X+
   *
   * </tt></b>
   * </td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CH:RP">[CH:RP]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td>
   * <b><tt>
   *
   * X\*
   *
   * </tt></b>
   * </td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CH:RS">[CH:RS]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td>
   *
   * <span style="color:red;font-size:smaller">(see below)</span>
   *
   * </td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CH:RN">[CH:RN]</a>
   *
   * </tt></td></tr></table></td>
   * <td><table summary=""><tr><td>
   *
   * <span style="color:red;font-size:smaller">(see below)</span>
   *
   * </td></tr><tr><td><tt style="font-size:smaller">
   *
   * <a href="#CH:RR">[CH:RR]</a>
   *
   * </tt></td></tr></table></td>
   * </tr>

   * </table>
   * </center>

   *
   * Each entry in this table is labeled <tt>[CH:<i>AB</i>]</tt> where
   * <tt><i>A</i></tt> and <tt><i>B</i></tt> are a pair of letters in
   * the set <b><tt>{X O P S N R}</tt></b>. Each letter is a mnemonic
   * for the case it stands for: <b><tt>X</tt></b> = "eXpression",
   * <b><tt>O</tt></b> = "Option", <b><tt>P</tt></b> = "Plus",
   * <b><tt>S</tt></b> = "Star", <b><tt>N</tt></b> = "Nth power",
   * <b><tt>R</tt></b> = "power Range".  To each entry in the above
   * table labeled <tt>[CH:<i>AB</i>]</tt>, there correspond <i>two</i>
   * rules, labeled <tt>[CH:<i>AB</i>1]</tt> and
   * <tt>[CH:<i>AB</i>2]</tt>: the first one covers the simple case and
   * the second one the nested case. They are listed again below for the
   * sake of clarity. Entries in the table that are marked "<span
   * style="color:red;font-size:smaller">(see below)</span>" yield
   * several results depending on the nature of the exponents in the
   * power or range and are treated in the "Power-rules" and
   * "Swap-rules" sections following this "Matching-pair-rule" section.

   * <p>
   *
   * These above table entries are given as rules below. Those marked
   * "<span style="color:red">(redundant)</span>" are in fact never used
   * as they are systematically preempted earlier by the the
   * <b><tt>X</tt></b>/<b><tt>X</tt></b> case. Indeed, it is never
   * possible for <a
   * href="RegExpTwo.html#matchingPairCode"><tt>matchingPairCode()</tt></a>
   * to return any of the values <tt>OO</tt>, <tt>PP</tt>, nor
   * <tt>SS</tt>. Therefore, the 6 corresponding rules <a
   * href="#CH:OO"><tt>[CH:OO1]</tt></a>, <a
   * href="#CH:OO"><tt>[CH:OO2]</tt></a>, <a
   * href="#CH:PP"><tt>[CH:PP1]</tt></a>, <a
   * href="#CH:PP"><tt>[CH:PP2]</tt></a>, <a
   * href="#CH:SS"><tt>[CH:SS1]</tt></a>, and <a
   * href="#CH:SS"><tt>[CH:SS2]</tt></a>, are redundant and could be
   * eliminated altogether. As a matter of fact, each one reduces to the
   * corresponding <a href="#CH:XX"><tt>[CH:XX]</tt></a> rule. This can
   * be trivially verified by looking at the rules (idempotence of
   * choice). The redundant rules are given here for the sake of
   * completeness and for the justification of correctness of this
   * specification.

   * ********************************************************************

   * <p>
   * <ul>

   * <li> First row:
   *
   * <p>
   * <ul>
   *
   * <li> <tt><a name="CH:XX">
   *
   *      [CH:XX1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X|X
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
   *      [CH:XX2]
   *
   *      </tt>
   *      <tt>
   *
   *      X|(X|Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X|Y
   *
   *      </tt>
   *
   * <p>
   *
   * <li> <tt><a name="CH:XO">
   *
   *      [CH:XO1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X|X?
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X?
   *
   *      </tt>
   *
   * <li> <tt>
   *
   *      [CH:XO2]
   *
   *      </tt>
   *      <tt>
   *
   *      X|(X?|Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X?|Y
   *
   *      </tt>
   *
   * <p>
   *
   * <li> <tt><a name="CH:XP">
   *
   *      [CH:XP1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X|X+
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X+
   *
   *      </tt>
   *
   * <li> <tt>
   *
   *      [CH:XP2]
   *
   *      </tt>
   *      <tt>
   *
   *      X|(X+|Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X+|Y
   *
   *      </tt>
   *
   * <p>
   *
   * <li> <tt><a name="CH:XS">
   *
   *      [CH:XS1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X|X\*
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X\*
   *
   *      </tt>
   *
   * <li> <tt>
   *
   *      [CH:XS2]
   *
   *      </tt>
   *      <tt>
   *
   *      X|(X\*|Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X\*|Y
   *
   *      </tt>

   * <p>
   *
   * <li> <a name="CH:XN"></a><table summary="" style="display:inline-table;vertical-align:top">
   *      <tr>
   *      <td><tt>
   *
   *      [CH:XN1]
   *
   *      </tt>
   *      <tt>
   *
   *      X|X^n
   *
   *      </tt>
   *      </td>
   *      <td>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;</td>
   *      <td>
   *      <tt>
   *
   *      X?
   *
   *      </tt>
   *      </td><td><span style="color:red">(if n = 0)</span></td></tr>
   *      <tr>
   *      <td></td>
   *      <td>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;</td>
   *      <td>
   *      <tt>
   *
   *      X
   *
   *      </tt>
   *      </td><td><span style="color:red">(if n = 1)</span></td></tr>
   *      <tr>
   *      <td></td>
   *      <td>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;</td>
   *      <td>
   *      <tt>
   *
   *      X_1^2 &nbsp;&nbsp;
   *
   *      </tt>
   *      </td><td><span style="color:red">(if n = 2)</span></td></tr>
   *      </table>
   *
   * <li> <table summary="" style="display:inline-table;vertical-align:top">
   *      <tr>
   *      <td><tt>
   *
   *      [CH:XN2]
   *
   *      </tt>
   *      <tt>
   *
   *      X|(X^n|Y)
   *
   *      </tt>
   *      </td>
   *      <td>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;</td>
   *      <td>
   *      <tt>
   *
   *      X?|Y
   *
   *      </tt>
   *      </td><td><span style="color:red">(if n = 0)</span></td></tr>
   *      <tr>
   *      <td></td>
   *      <td>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;</td>
   *      <td>
   *      <tt>
   *
   *      X|Y
   *
   *      </tt>
   *      </td><td><span style="color:red">(if n = 1)</span></td></tr>
   *      <tr>
   *      <td></td>
   *      <td>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;</td>
   *      <td>
   *      <tt>
   *
   *      X_1^2|Y &nbsp;&nbsp;
   *
   *      </tt>
   *      </td><td><span style="color:red">(if n = 2)</span></td></tr>
   *      </table>

   * </ul>
   *   
   * <p><li> Second row:
   *
   * <p>
   * <ul>
   
   * <li> <tt><a name="CH:OX">
   *
   *      [CH:OX1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X?|X
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X?
   *
   *      </tt>
   *
   * <li> <tt>
   *
   *      [CH:OX2]
   *
   *      </tt>
   *      <tt>
   *
   *      X?|(X|Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X?|Y
   *
   *      </tt>
   *

   ********************************************************************

   * <p>
   *
   * <li> <tt><a name="CH:OO">
   *
   *      [CH:OO1]
   *
   *      </a></tt>
   *      
   *      <tt>
   *
   *      X?|X?
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X?
   *
   *      </tt> <span style="color:red">(redundant)</span>
   *
   * <li> <tt>
   *
   *      [CH:OO2]
   *
   *      </tt>
   *      
   *      <tt>
   *
   *      X?|(X?|Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X?|Y
   *
   *      </tt> <span style="color:red">(redundant)</span>
   *
   * <p>
   *
   * <li> <tt><a name="CH:OP">
   *
   *      [CH:OP1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X?|X+
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X\*
   *
   *      </tt>
   *
   * <li> <tt>
   *
   *      [CH:OP2]
   *
   *      </tt>
   *      <tt>
   *
   *      X?|(X+|Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X\*|Y
   *
   *      </tt>
   *
   * <p>
   *
   * <li> <tt><a name="CH:OS">
   *
   *      [CH:OS1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X?|X\*
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X\*
   *
   *      </tt>
   *
   * <li> <tt>
   *
   *      [CH:OS2]
   *
   *      </tt>
   *      <tt>
   *
   *      X?|(X\*|Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X\*|Y
   *
   *      </tt>

   * </ul>
   *   
   * <p><li> Third row:
   *
   * <p>
   * <ul>
   
   * <li> <tt><a name="CH:PX">
   *
   *      [CH:PX1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X+|X
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X+
   *
   *      </tt>
   *
   * <li> <tt>
   *
   *      [CH:PX2]
   *
   *      </tt>
   *      <tt>
   *
   *      X+|(X|Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X+|Y
   *
   *      </tt>

   * <p>
   *
   * <li> <tt><a name="CH:PO">
   *
   *      [CH:PO1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X+|X?
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X\*
   *
   *      </tt>
   *
   * <li> <tt>
   *
   *      [CH:PO2]
   *
   *      </tt>
   *      <tt>
   *
   *      X+|(X?|Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X\*|Y
   *
   *      </tt>

   * <p>
   *
   * <li> <tt><a name="CH:PP">
   *
   *      [CH:PP1]
   *
   *      </a></tt>
   *      
   *      <tt>
   *
   *      X+|X+
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X+
   *
   *      </tt> <span style="color:red">(redundant)</span>
   *
   * <li> <tt>
   *
   *      [CH:PP2]
   *
   *      </tt>
   *      
   *      <tt>
   *
   *      X+|(X+|Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X+|Y
   *
   *      </tt> <span style="color:red">(redundant)</span>

   * <p>
   *
   * <li> <tt><a name="CH:PS">
   *
   *      [CH:PS1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X+|X\*
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X\*
   *
   *      </tt>
   *
   * <li> <tt>
   *
   *      [CH:PS2]
   *
   *      </tt>
   *      <tt>
   *
   *      X+|(X\*|Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X\*|Y
   *
   *      </tt>

   * <p>
   *
   * <li> <tt><a name="CH:PN">
   *
   *      [CH:PN1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X+|X^n
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X\*
   *
   *      </tt> &nbsp;<span style="color:red">(if n=0);</span>&nbsp;
   *      <tt>
   *
   *      X+
   *
   *      </tt> &nbsp;<span style="color:red">(otherwise)</span>
   *
   * <li> <tt>
   *
   *      [CH:PN2]
   *
   *      </tt>
   *      <tt>
   *
   *      X+|(X^n|Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X\*|Y
   *
   *      </tt> &nbsp;<span style="color:red">(if n=0);</span>&nbsp;
   *      <tt>
   *
   *      X+|Y
   *
   *      </tt> &nbsp;<span style="color:red">(otherwise)</span>

   * <p>
   *
   * <li> <tt><a name="CH:PR">
   *
   *      [CH:PR1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X+|X_m^n
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X\*
   *
   *      </tt> &nbsp;<span style="color:red">(if m=0);</span>&nbsp;
   *      <tt>
   *
   *      X+
   *
   *      </tt> &nbsp;<span style="color:red">(otherwise)</span>
   *
   * <li> <tt>
   *
   *      [CH:PR2]
   *
   *      </tt>
   *      <tt>
   *
   *      X+|(X_m^n|Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X\*|Y
   *
   *      </tt> &nbsp;<span style="color:red">(if m=0);</span>&nbsp;
   *      <tt>
   *
   *      X+|Y
   *
   *      </tt> &nbsp;<span style="color:red">(otherwise)</span>

   * </ul>
   *   
   * <p><li> Fourth row:
   *
   * <p>
   * <ul>

   * <li> <tt><a name="CH:SX">
   *
   *      [CH:SX1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X\*|X
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X\*
   *
   *      </tt>
   *
   * <li> <tt>
   *
   *      [CH:SX2]
   *
   *      </tt>
   *      <tt>
   *
   *      X\*|(X|Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X\*|Y
   *
   *      </tt>

   * <p>
   *
   * <li> <tt><a name="CH:SO">
   *
   *      [CH:SO1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X\*|X?
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X\*
   *
   *      </tt>
   *
   * <li> <tt>
   *
   *      [CH:SO2]
   *
   *      </tt>
   *      <tt>
   *
   *      X\*|(X?|Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X\*|Y
   *
   *      </tt>

   * <p>
   *
   * <li> <tt><a name="CH:SP">
   *
   *      [CH:SP1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X\*|X+
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X\*
   *
   *      </tt>
   *
   * <li> <tt>
   *
   *      [CH:SP2]
   *
   *      </tt>
   *      <tt>
   *
   *      X\*|(X+|Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X\*|Y
   *
   *      </tt>
   *
   * <p>
   *
   * <li> <tt><a name="CH:SS">
   *
   *      [CH:SS1]
   *
   *      </a></tt>
   *      
   *      <tt>
   *
   *      X\*|X\*
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X\*
   *
   *      </tt> <span style="color:red">(redundant)</span>
   *
   * <li> <tt>
   *
   *      [CH:SS2]
   *
   *      </tt>
   *      
   *      <tt>
   *
   *      X\*|(X\*|Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X\*|Y
   *
   *      </tt> <span style="color:red">(redundant)</span>

   * <p>
   *
   * <li> <tt><a name="CH:SN">
   *
   *      [CH:SN1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X\*|X^n
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X\*
   *
   *      </tt>
   *
   * <li> <tt>
   *
   *      [CH:SN2]
   *
   *      </tt>
   *      <tt>
   *
   *      X\*|(X^n|Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X\*|Y
   *
   *      </tt>

   * <p>
   *
   * <li> <tt><a name="CH:SR">
   *
   *      [CH:SR1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X\*|X_m^n
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X\*
   *
   *      </tt>
   *
   * <li> <tt>
   *
   *      [CH:SR2]
   *
   *      </tt>
   *      <tt>
   *
   *      X\*|(X_m^n|Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X\*|Y
   *
   *      </tt>

   * </ul>
   *   
   * <p><li> Fifth row:
   *
   * <p>
   * <ul>

   * <li> <tt><a name="CH:NP">
   *
   *      [CH:NP1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X^n|X+
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X\*
   *
   *      </tt> &nbsp;<span style="color:red">(if n=0);</span>&nbsp;
   *      <tt>
   *
   *      X+
   *
   *      </tt> &nbsp;<span style="color:red">(otherwise)</span>
   *
   * <li> <tt>
   *
   *      [CH:NP2]
   *
   *      </tt>
   *      <tt>
   *
   *      X^n|(X+|Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X\*|Y
   *
   *      </tt> &nbsp;<span style="color:red">(if n=0);</span>&nbsp;
   *      <tt>
   *
   *      X+|Y
   *
   *      </tt> &nbsp;<span style="color:red">(otherwise)</span>
   *
   * <p>
   *
   * <li> <tt><a name="CH:NS">
   *
   *      [CH:NS1]
   *
   *      </a></tt>
   *      
   *      <tt>
   *
   *      X^n|X\*
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X\*
   *
   *      </tt>
   *
   * <li> <tt>
   *
   *      [CH:NS2]
   *
   *      </tt>
   *      
   *      <tt>
   *
   *      X^n|(X\*|Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X\*|Y
   *
   *      </tt>

   * </ul>
   *   
   * <p><li> Sixth row:
   *
   * <p>
   * <ul>

   * <li> <tt><a name="CH:RP">
   *
   *      [CH:RP1]
   *
   *      </a></tt>
   *      <tt>
   *
   *      X_m^n|X+
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X\*
   *
   *      </tt> &nbsp;<span style="color:red">(if m=0);</span>&nbsp;
   *      <tt>
   *
   *      X+
   *
   *      </tt> &nbsp;<span style="color:red">(otherwise)</span>
   *
   * <li> <tt>
   *
   *      [CH:RP2]
   *
   *      </tt>
   *      <tt>
   *
   *      X_m^n|(X+|Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X\*|Y
   *
   *      </tt> &nbsp;<span style="color:red">(if m=0);</span>&nbsp;
   *      <tt>
   *
   *      X+|Y
   *
   *      </tt> &nbsp;<span style="color:red">(otherwise)</span>
   *
   * <p>
   *
   * <li> <tt><a name="CH:RS">
   *
   *      [CH:RS1]
   *
   *      </a></tt>
   *      
   *      <tt>
   *
   *      X_m^n|X\*
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X\*
   *
   *      </tt>
   *
   * <li> <tt>
   *
   *      [CH:RS2]
   *
   *      </tt>
   *      
   *      <tt>
   *
   *      X_m^n|(X\*|Y)
   *
   *      </tt>
   *      &nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   *      <tt>
   *
   *      X\*|Y
   *
   *      </tt>

   * </ul>

   * </ul>

   * ********************************************************************

   *
   * <h3><a name="powerrule"></a><i>Power</i>-rules</h3>
   *

   * The cases of <b><tt>N</tt></b> = "N-th
   * power" and <b><tt>R</tt></b> = "power
   * Range" missing in the table above need special attention and are
   * given here.

   * <ul>

   * <li> <tt>
   *
   * [CH:XN^2]
   *
   * </tt><tt>
   *
   * X | X^2
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X_1^2
   *
   * </tt>

   * <li> <tt>
   *
   * [CH:XR0n]
   *
   * </tt><tt>
   *
   * X | X_0^n
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X_0^n
   *
   * </tt>

   * <li> <tt>
   *
   * [CH:XR1n]
   *
   * </tt><tt>
   *
   * X | X_1^n
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X_1^n
   *
   * </tt>

   * <li> <tt>
   *
   * [CH:XR2n]
   *
   * </tt><tt>
   *
   * X | X_2^n
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X_1^n
   *
   * </tt>

   * <li> <tt>
   *
   * [CH:NN_1]
   *
   * </tt><tt>
   *
   * X^n | X^n+1
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X_n^n+1
   *
   * </tt>

   * <li> <tt>
   *
   * [CH:NR_1]
   *
   * </tt><tt>
   *
   * X^n | X_n+1^q
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X_n^q
   *
   * </tt>

   * <li> <tt>
   *
   * [CH:NR_2]
   *
   * </tt><tt>
   *
   * X^n | X_p^q
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X_p^q
   *
   * </tt>&nbsp;&nbsp;&nbsp;
   * (<i>if</i>&nbsp;&nbsp;<tt>p &le; n &le; q</tt>)

   * <li> <tt>
   *
   * [CH:RN_1]
   *
   * </tt><tt>
   *
   * X_m^n | X^n+1
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X_m^n+1
   *
   * </tt>

   * <li> <tt>
   *
   * [CH:RR12]
   *
   * </tt><tt>
   *
   * X_m^n | X_p^q
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X_<b>Min</b>(m,p)^<b>Max</b>(n,q)
   *
   * </tt>&nbsp;&nbsp;&nbsp;
   * (<i>if</i>&nbsp;&nbsp;<tt>n = &infin;</tt> or <tt>p &le; n+1</tt>)

   * </ul>

   *
   * <h3><a name="swaprule"></a><i>Swap</i>-rules</h3>
   *

   * Although choice is a commutative operation on regular expressions,
   * we put it systematically in some predefined order based on the nature
   * of the operands. This is what the swap-rules do.

   * <ul>

   * <li> <tt>
   *
   * [CH:ba%1]
   *
   * </tt><tt>
   *
   * b | a
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * a | b
   *
   * </tt>&nbsp;&nbsp;&nbsp;
   * (<i>if</i>&nbsp;&nbsp;<tt>a &lt;b</tt>)

   * <li> <tt>
   *
   * [CH:ba%2]
   *
   * </tt><tt>
   *
   * b | (a | X)
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * a | (b | X)
   *
   * </tt>&nbsp;&nbsp;&nbsp;
   * (<i>if</i>&nbsp;&nbsp;<tt>a &lt;b</tt>)

   * </ul>
   *
   * <ul>

   * <li> <tt>
   *
   * [CH:mn%1]
   *
   * </tt><tt>
   *
   * b^m | a^n
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * a^n | b^m
   *
   * </tt>&nbsp;&nbsp;&nbsp;
   * (<i>if</i>&nbsp;&nbsp;<tt>a &lt;b</tt>)

   * <li> <tt>
   *
   * [CH:mn%2]
   *
   * </tt><tt>
   *
   * b^m | (a^n | X)
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * a^n | (b^m | X)
   *
   * </tt>
   *      &nbsp;&nbsp;&nbsp;(<i>if</i>&nbsp;&nbsp;<tt>a &lt;b</tt>)

   * </ul>
   *
   * <ul>

   * <li> <tt>
   *
   * [CH:NX%1]
   *
   * </tt><tt>
   *
   * X^n | X
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X | X^n
   *
   * </tt>

   * <li> <tt>
   *
   * [CH:NX%2]
   *
   * </tt><tt>
   *
   * X^n | (X | Y)
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X | (X^n | Y)
   *
   * </tt>

   * </ul>
   *
   * <ul>

   * <li> <tt>
   *
   * [CH:NN%1]
   *
   * </tt><tt>
   *
   * X^n | X^m
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X^m | X^n
   *
   * </tt>&nbsp;&nbsp;&nbsp;
   * (<i>if</i>&nbsp;&nbsp;<tt>m &lt;n</tt>)

   * <li> <tt>
   *
   * [CH:NN%2]
   *
   * </tt><tt>
   *
   * X^n | (X^m | Y)
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X^m | (X^n | Y)
   *
   * </tt>&nbsp;&nbsp;&nbsp;
   * (<i>if</i>&nbsp;&nbsp;<tt>m &lt;n</tt>)

   * </ul>
   *
   * <ul>

   * <li> <tt>
   *
   * [CH:NR]
   *
   * </tt><tt>
   *
   * X^n | X_p^q
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X_p^q | X^n
   *
   * </tt>&nbsp;&nbsp;&nbsp;
   * (<i>if</i>&nbsp;&nbsp;<tt>p &le; q &le; n</tt>)

   * <li> <tt>
   *
   * [CH:RX]
   *
   * </tt><tt>
   *
   * X_m^n | X
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X | X_m^n
   *
   * </tt>

   * <li> <tt>
   *
   * [CH:RN]
   *
   * </tt><tt>
   *
   * X_p^q | X^m
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X^m | X_p^q
   *
   * </tt>&nbsp;&nbsp;&nbsp;
   * (<i>if</i>&nbsp;&nbsp;<tt>m &le; q</tt>)

   * <li> <tt>
   *
   * [CH:RR2]
   *
   * </tt><tt>
   *
   * X_p^q | (X_m^n | Y)
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X_m^n | (X_p^q | Y)
   *
   * </tt>&nbsp;&nbsp;&nbsp;
   * (<i>if</i>&nbsp;&nbsp;<tt>m &lt;p</tt>)

   * <li> <tt>
   *
   * [CH:ba%RR2]
   *
   * </tt><tt>
   *
   * b_m^n | (a_p^q | X)
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * a_p^q | (b_m^n | X)
   *
   * </tt>&nbsp;&nbsp;&nbsp;
   * (<i>if</i>&nbsp;&nbsp;<tt>a &lt;b</tt>)

   * <li> <tt>
   *
   * [CH:RR11]
   *
   * </tt><tt>
   *
   * X_p^q | X_m^n
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X_m^n | X_p^q
   *
   * </tt>&nbsp;&nbsp;&nbsp;
   * (<i>if</i>&nbsp;&nbsp;<tt>m &lt;p</tt>)

   * </ul>

   *
   * <h3><a name="otherrule"></a><i>Other</i>-rules</h3>
   *

   * <ul>

   * <li> <tt>
   *
   * [CH:O_]
   *
   * </tt><tt>
   *
   * X? | Y
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * (X | Y)?
   *
   * </tt>

   * <li> <tt>
   *
   * [CH:_O]
   *
   * </tt><tt>
   *
   * X | Y?
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * (X | Y)?
   *
   * </tt>

   * <li> <tt>
   *
   * [CH:AS]
   *
   * </tt><tt>
   *
   * (X | Y) | Z
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X | (Y | Z)
   *
   * </tt>

   * <li> <tt>
   *
   * [CH:XSX]
   *
   * </tt><tt>
   *
   * X.Y\* | X
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X.Y\*
   *
   * </tt>

   * <li> <tt>
   *
   * [CH:SXX]
   *
   * </tt><tt>
   *
   * Y\*.X | X
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * Y\*.X
   *
   * </tt>

   * <li> <tt>
   *
   * [CH:XPX]
   *
   * </tt><tt>
   *
   * X.Y+ | X
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X.Y\*
   *
   * </tt>

   * <li> <tt>
   *
   * [CH:PXX]
   *
   * </tt><tt>
   *
   * Y+.X | X
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * Y\*.X
   *
   * </tt>

   * <li> <tt>
   *
   * [CH:XXS]
   *
   * </tt><tt>
   *
   * X | X.Y\*
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X.Y\*
   *
   * </tt>

   * <li> <tt>
   *
   * [CH:XSX]
   *
   * </tt><tt>
   *
   * X | Y\*.X
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * Y\*.X
   *
   * </tt>

   * <li> <tt>
   *
   * [CH:XXP]
   *
   * </tt><tt>
   *
   * X | X.Y+
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * X.Y\*
   *
   * </tt>

   * <li> <tt>
   *
   * [CH:XPX]
   *
   * </tt><tt>
   *
   * X | Y+.X
   *
   * </tt>&nbsp;&nbsp;&rarr;&nbsp;&nbsp;
   * <tt>
   *
   * Y\*.X
   *
   * </tt>

   * </ul>

   ********************************************************************

   */
  public void normalize ()
    {
      String rule = null;
      RegExp left = left();
      RegExp right = right();

      out:
	{
	  // Empty-rules:

	  if (left.isEmpty())
	    {
	      rule = "[CH:E_] () | X --> X?";
	      _normalForm = option(right);
	      break out;
	    }

	  if (right.isEmpty())
	    {
	      rule = "[CH:_E] X | () --> X?";
	      _normalForm = option(left);
	      break out;
	    }

	  // Matching-pair-rules:

	  // 1st row:

	  if (right.equals(left))
	    {
	      rule = "[CH:XX1] X | X --> X";
	      _normalForm = left;
	      break out;
	    }

	  // 2nd row:

	  // 3rd row:

	  // 4th row:

	  // 5th row:

	  // 6th row:

	  // ...

	  // ---------------------------------------------------------- //

	  if (left.isOption())
	    {
	      rule = "[CH:O_] X? | Y --> (X | Y)?";
	      _normalForm = option(choice(left.arg(),
					  right));
	      break out;
	    }

	  if (right.isOption())
	    {
	      rule = "[CH:_O] X | Y? --> (X | Y)?";
	      _normalForm = option(choice(left,
					  right.arg()));
	      break out;
	    }

	  if (left.isChoice())
	    {
	      rule = "[CH:AS] (X | Y) | Z --> X | (Y | Z)";
	      _normalForm = choice(left.left(),
				   choice(left.right(),
					  right));
	      break out;
	    }
      
	  if (left.isSymbol())
	    {
	      if (right.isSymbol())
		{
		  if (right.isLexLess(left))
		    {
		      rule = "[CH:ba%1] b | a --> a | b\t(if a < b)";
		      _normalForm = choice(right,left);
		      break out;
		    }
		}

	      if (right.isChoice())
		{
		  RegExp snd = right.left();
		  if (snd.isSymbol() && snd.isLexLess(left))
		    {
		      rule = "[CH:ba%2] b | (a | X) --> a | (b | X)\t(if a < b)";
		      _normalForm = choice(snd,
					   choice(left,
						  right.right()));
		      break out;
		    }
		}
	    }

	  if (left.equals(right))
	    {
	      rule = "[CH:XX1] X | X --> X";
	      _normalForm = left;
	      break out;
	    }
      
	  if (right.isChoice()
	      && left.equals(right.left()))
	    {
	      rule = "[CH:XX2] X | (X | Y) --> X | Y";
	      _normalForm = right;
	      break out;
	    }

	  if (left.isConcat())
	    {
	      if (left.right().isStar()
		  && left.left().equals(right))
		{
		  rule = "[CH:XSX] X.Y* | X --> X.Y*";
		  _normalForm = left;
		  break out;
		}

	      if (left.left().isStar()
		  && left.right().equals(right))
		{
		  rule = "[CH:SXX] Y*.X | X --> Y*.X";
		  _normalForm = left;
		  break out;
		}

	      if (left.right().isPlus()
		  && left.left().equals(right))
		{
		  rule = "[CH:XPX] X.Y+ | X --> X.Y*";
		  _normalForm = concat(right,
				       star(left.right().arg()));
		  break out;
		}

	      if (left.left().isPlus()
		  && left.right().equals(right))
		{
		  rule = "[CH:PXX] Y+.X | X --> Y*.X";
		  _normalForm = concat(star(left.left().arg()),
				       right);
		  break out;
		}
	    }
      
	  if (right.isConcat())
	    {
	      if (right.right().isStar()
		  && right.left().equals(left))
		{
		  rule = "[CH:XXS] X | X.Y* --> X.Y*";
		  _normalForm = right;
		  break out;
		}

	      if (right.left().isStar()
		  && right.right().equals(left))
		{
		  rule = "[CH:XSX] X | Y*.X --> Y*.X";
		  _normalForm = right;
		  break out;
		}

	      if (right.right().isPlus()
		  && right.left().equals(left))
		{
		  rule = "[CH:XXP] X | X.Y+ --> X.Y*";
		  _normalForm = concat(left,
				       star(right.right().arg()));
		  break out;
		}

	      if (right.left().isPlus()
		  && right.right().equals(left))
		{
		  rule = "[CH:XPX] X | Y+.X --> Y*.X";
		  _normalForm = concat(star(right.left().arg()),
				       left);
		  break out;
		}
	    }

	  if (right.isPower()
	      && right.power() == 2
	      && left.equals(right.arg()))
	    {
	      rule = "[CH:XN^2] X | X^2 --> X_1^2";
	      _normalForm = range(left,1,2);
	      break out;
	    }

	  if (right.isRange()
	      && right.lower() >= 0
	      && right.lower() <= 2
	      && left.equals(right.arg()))
	    {
	      switch (right.lower())
		{
		case 0:
		  rule = "[CH:XR0n] X | X_0^n --> X_0^n";
		  _normalForm = right;
		  break out;
		case 1:
		  rule = "[CH:XR1n] X | X_1^n --> X_1^n";
		  _normalForm = right;
		  break out;
		case 2:
		  rule = "[CH:XR2n] X | X_2^n --> X_1^n";
		  _normalForm = range(left,
				      1,
				      right.upper());
		  break out;
		}
	    }	      
      
	  if (left.isPower())
	    {
	      if (left.arg().equals(right))
		{
		  rule = "[CH:NX%1] X^n | X --> X | X^n";
		  _normalForm = choice(right,left);
		  break out;
		}

	      if (right.isPower())
		{
		  if (left.arg().equals(right.arg()))
		    {
		      if (left.power()+1 == right.power())
			{
			  rule = "[CH:NN_1] X^n | X^n+1 --> X_n^n+1";
			  _normalForm = range(left.arg(),
					      left.power(),
					      right.power());
			  break out;
			}

		      if (right.power() < left.power())
			{
			  rule = "[CH:NN%1] X^n | X^m --> X^m | X^n\t(if m<n)";
			  _normalForm = choice(right,left);
			  break out;
			}
		    }

		  if (left.arg().isSymbol() && right.arg().isSymbol()
		      && right.arg().isLexLess(left.arg()))
		    {
		      rule = "[CH:mn%1] b^m | a^n --> a^n | b^m\t(if a<b)";
		      _normalForm = choice(right,left);
		      break out;
		    }
		}

	      if (right.isChoice() && right.left().isPower())
		{
		  if (left.arg().equals(right.left().arg())
		      && right.left().power() < left.power())
		    {
		      rule = "[CH:NN%2] X^n | (X^m | Y) --> X^m | (X^n | Y)\t(if m<n)";
		      _normalForm = choice(right.left(),
					   choice(left,
						  right.right()));
		      break out;
		    }

		  if (left.arg().isSymbol() && right.left().arg().isSymbol()
		      && right.left().arg().isLexLess(left.arg()))
		    {
		      rule = "[CH:mn%2] b^m | (a^n | X) --> a^n | (b^m | X)\t(if a<b)";
		      _normalForm = choice(right.left(),
					   choice(left,
						  right.right()));
		      break out;
		    }
		}

	      if (right.isRange()
		  && left.arg().equals(right.arg()))
		{
		  if (right.lower() <= left.power()
		      && left.power() <= right.upper())
		    {
		      rule = "[CH:NR_2] X^n | X_p^q --> X_p^q\t(if p<=n<=q)";
		      _normalForm = right;
		      break out;
		    }

		  if (left.power()+1 == right.lower())
		    {
		      rule = "[CH:NR_1] X^n | X_n+1^q --> X_n^q";
		      _normalForm = range(left.arg(),
					  left.power(),
					  right.upper());
		      break out;
		    }

		  if (right.lower() <= left.power())
		    {
		      rule = "[CH:NR] X^n | X_p^q --> X_p^q | X^n\t(if p <= n)";
		      _normalForm = choice(right,
					   left);
		      break out;
		    }
		}
	    }
	  
	  if (left.isRange())
	    {
	      if (left.arg().equals(right))
		{
		  rule = "[CH:RX] X_m^n | X --> X | X_m^n";
		  _normalForm = choice(right,left);
		  break out;
		}
	      
	      if (right.isPower())
		{
		  if (left.arg().equals(right.arg()))
		    {
		      if (left.upper()+1 == right.power())
			{
			  rule = "[CH:RN_1] X_m^n | X^n+1 --> X_m^n+1";
			  _normalForm = range(left.arg(),
					      left.lower(),
					      right.power());
			  break out;
			}

		      if (right.power() <= left.upper())
			{
			  rule = "[CH:RN] X_p^q | X^m --> X^m | X_p^q\t(if m<=q)";
			  _normalForm = choice(right,left);
			  break out;
			}
		    }
		}

	      if (right.isChoice()
		  && right.left().isRange())
		{
		  if (right.left().lower() < left.lower()
		      && left.arg().equals(right.left().arg()))
		    {
		      rule = "[CH:RR2] X_p^q | (X_m^n | Y) --> X_m^n | (X_p^q | Y)\t(if m<p)";
		      _normalForm = choice(right.left(),
					   choice(left,
						  right.right()));
		      break out;
		    }

		  if (left.arg().isSymbol() && right.left().arg().isSymbol()
		      && right.left().arg().isLexLess(left.arg()))
		    {
		      rule = "[CH:ba%RR2] b_m^n | (a_p^q | X) --> a_p^q | (b_m^n | X)\t(if a<b)";
		      _normalForm = choice(right.left(),
					   choice(left,
						  right.right()));
		      break out;
		    }
		}

	      if (right.isRange()
		  && left.arg().equals(right.arg()))
		{
		  if (right.lower() < left.lower())
		    {
		      rule = "[CH:RR11] X_p^q | X_m^n --> X_m^n | X_p^q\t(if m<p)";
		      _normalForm = choice(right,left);
		      break out;
		    }

		  if (left.upper() == OMEGA || right.lower() <= left.upper()+1)
		    {
		      rule = "[CH:RR12] X_m^n | X_p^q --> X_Min(m,p)^Max(n,q)\t(if n=~ or p<=n+1)";
		      _normalForm = range(left.arg(),
					  Math.min(left.lower(),
						   right.lower()),
					  Math.max(left.upper(),
						   right.upper()));
		      break out;
		    }
		}
	    }

	} // end of named block "out:"

      traceRule(rule);
      checkNormalForm();
    }
  
  /**
   * Returns a printable form for this <tt>RegExpChoice</tt>.
   */
  public final String toString ()
    {
      return _left+" | "+_right;
    }

  public final String toNormalString ()
    {
      if (!inNormalForm())
  	return _normalForm.toNormalString();

      return _left.toNormalString()+" | "+_right.toNormalString();
    }

  // // This prints with parentheses showing expicit nesting
  // // (for debugging purposes)
  // public final String toString ()
  //   {
  //     String left = _left.toString();
  //     String right = _right.toString();

  //     if (_left.isChoice())
  // 	left = "("+left+")";

  //     if (_right.isChoice())
  // 	right = "("+right+")";

  //     return left+" | "+right;
  //   }

  // public final String toNormalString ()
  //   {
  //     if (!inNormalForm())
  // 	return _normalForm.toNormalString();

  //     String left = _left.toNormalString();
  //     String right = _right.toNormalString();

  //     if (_left.isChoice())
  // 	left = "("+left+")";

  //     if (_right.isChoice())
  // 	right = "("+right+")";

  //     return left+" | "+right;
  //   }

}
