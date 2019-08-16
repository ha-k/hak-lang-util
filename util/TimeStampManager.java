//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

/**
 * @version     Last modified on Tue Jun 19 17:40:04 2012 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy;-2003 <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */

/**
 * This is a class to manage time-stamped objects.
 */
public class TimeStampManager
{
  /**
   * This field records temporal precedence of the creation of time stamps.
   */
  private long _TIME_STAMP = 0;

  /**
   * Resets the temporal origin of time stamping to 0 for all time-stamped
   * objects managed by this object.
   */
  final void reset ()
    {
      _TIME_STAMP = 0;
    }

  /**
   * Constructs a time stamp manager.
   */
  public TimeStampManager ()
    {
    }

  /**
   * Issue and returns an new time stamp.
   */
  public long newTimeStamp ()
    {
      return _TIME_STAMP++;
    }

  /**
   * Sets the time stamp of the specified time-stamped object
   * to a new time stamp issued by this time stamp manager.
   */
  public void setTimeStamp (TimeStamped object)
    {
      object.setTimeStamp(newTimeStamp());
    }

}



