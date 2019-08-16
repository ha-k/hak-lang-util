//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

/**
 * Signals that a map is locked on attempting to modify it
 *
 * @see Map
 *
 * @version     Last modified on Tue Jun 19 17:37:54 2012 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */

public class LockedMapException extends RuntimeException
{
  /**
   * Constructs a new LockedMapException with no message.
   */
  public LockedMapException ()
    {
    }

  /**
   * Constructs a new LockedMapException with a message.
   */
  public LockedMapException (String msg)
    {
      super(msg);
    }
}
