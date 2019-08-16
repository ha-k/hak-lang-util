//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

/**
 * Signals an empty queue exception
 * @see	Queue
 *
 * @version	Last modified on Tue Jun 19 17:36:30 2012 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright	&copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */

public class EmptyQueueException extends RuntimeException
{
  /**
   * Constructs a new EmptyQueueException
   */
  public EmptyQueueException ()
    {
      super();
    }

  /**
   * Constructs a new EmptyQueueException with given message.
   */
  public EmptyQueueException (String message)
    {
      super(message);
    }
}
