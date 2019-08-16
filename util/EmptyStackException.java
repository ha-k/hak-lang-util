//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

/**
 * Signals an empty stack exception
 *
 * @version     Last modified on Tue Jun 19 17:36:32 2012 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */

public class EmptyStackException extends RuntimeException
{
  /**
   * Constructs a new EmptyStackException
   */
  public EmptyStackException ()
    {
      super();
    }

  /**
   * Constructs a new EmptyStackException with given message.
   */
  public EmptyStackException (String message)
    {
      super(message);
    }
}
