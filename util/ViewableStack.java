//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

import java.util.Iterator;

/**
 * @version     Last modified on Fri Mar 11 12:19:48 2016 by hak
 * @author      <a href="mailto:hak@acm.org"> A&iuml;t-Kaci</a>
 * @copyright &copy; 2002 <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */

public interface ViewableStack
{
  public int size ();
  public Object get (int i);
  public Object peek ();
  public boolean isEmpty ();
  public Iterator iterator ();
}
