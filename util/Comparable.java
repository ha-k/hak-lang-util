//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

/**
 * This is an interface for objects that must be compared.
 *
 * @version     Last modified on Fri Apr 17 15:59:45 2015 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */

public interface Comparable
{
  /**
   * Important: this should be a <i><b>strict</b></i> inequality.
   * In other words, it must return <tt>false</tt> for equal objects.
   * If not, a sorting procedure like QuickSort may loop forever.
   *
   * @param other an other <tt>Comparable</tt> object
   * @return <tt>true</tt> iff this <tt>Comparable</tt> is a lesser element than the specified one
   */
  public boolean lessThan (Comparable other);
}
