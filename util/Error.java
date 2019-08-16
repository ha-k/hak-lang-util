//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

/**
 * @version     Last modified on Fri Nov 16 00:47:39 2012 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */

/**
 * This is the class of objects representing errors.
 */

public class Error implements Locatable
{
  private String _prefix = "*** ";
  private String _label = "Error: ";
  private String _msg = "an error happened";
  private String _see = " - see ";
  private Locatable _extent;
  private boolean _isWarning = false;

  /**
   * Returns the string that starts this error's printed form (default = <tt>"*** "</tt>).
   */
  public final String getPrefix ()
    {
      return _prefix;
    }

  /**
   * Sets this error's prefix to the specified String, and returns this error.
   */
  public final Error setPrefix (String prefix)
    {
      _prefix = prefix;
      return this;
    }

  /**
   * Returns the string that labels this error's printed form (default = <tt>"Error: "</tt>).
   */
  public final String getLabel ()
    {
      return _label;
    }

  /**
   * Sets this error's label to the specified String, and returns this error.
   */
  public final Error setLabel (String label)
    {
      _label = label;
      return this;
    }

  /**
   * Returns the string of this error's message (default = <tt>"an error happened"</tt>).
   */
  public final String getMsg ()
    {
      return _msg;
    }

  /**
   * Sets this error's message to the specified String, and returns this error.
   */
  public final Error setMsg (String msg)
    {
      _msg = msg;
      return this;
    }

  /**
   * Returns the string indicating this error's extent (default = <tt>" - see "</tt>).
   */
  public final String getSee ()
    {
      return _see;
    }

  /**
   * Sets this error's the string indicating this error's extent the specified String,
   * and returns this error.
   */
  public final Error setSee (String see)
    {
      _see = see;
      return this;
    }

  /**
   * Returns this error's extent as a <a href="./Locatable.html"><tt>Locatable</tt></a>.
   */
  public final Locatable getExtent ()
    {
      return _extent;
    }

  /**
   * Sets this error's extent to the specified <a href="./Locatable.html">
   * <tt>Locatable</tt></a>, and returns this error.
   */
  public final Error setExtent (Locatable extent)
    {
      _extent = extent;
      return this;
    }

  /**
   * Returns true if this error in only a warning.
   */
  public final boolean isWarning ()
    {
      return _isWarning;
    }

  /**
   * Qualifies this error as only a warning, and returns this error.
   */
  public final Error makeWarning ()
    {
      _isWarning = true;
      return this;
    }  

  /**
   * Returns the start of this error's extent as a <a href="./Location.html">
   * <tt>Location</tt></a>.
   */
  public final Location getStart ()
    {
      return (_extent == null) ? null : _extent.getStart();
    }
    
  /**
   * Returns the end of this error's extent as a <a href="./Location.html">
   * <tt>Location</tt></a>.
   */
  public final Location getEnd ()
    {
      return (_extent == null) ? null : _extent.getEnd();
    }
    
  /**
   * Sets the start of this error's extent to the specified <a href="./Location.html">
   * <tt>Location</tt></a> and returns this.
   */
  public final Locatable setStart (Location location)
    {
      if (_extent == null) _extent = new Span();
      _extent.setStart(location);
      return this;
    }
    
  /**
   * Sets the end of this error's extent to the specified <a href="./Location.html">
   * <tt>Location</tt></a> and returns this.
   */
  public final Locatable setEnd (Location location)
    {
      if (_extent == null) _extent = new Span();
      _extent.setEnd(location);
      return this;
    }
    
  /**
   * Returns an explicit String describing the location of this error's extent.
   * The default is <tt>getExtent().locationString()</tt> if this error's extent
   * is non <tt>null</tt>, or the empty string othetwise. This method may be overridden.
   */
  public String locationString ()
    {
      return (_extent == null) ? "" : _extent.locationString();
    }

  /**
   * Returns a String composed of all the elements of this error in the form:
   * <tt>getPrefix()+getLabel()+getMsg()+getSee()+locationString()</tt>.
   * Thus, the default is <tt>"*** Error: an error happened - see "+locationString()</tt>.
   * This method may be overridden.
   */
  public String toString ()
    {
      String errMsg = _prefix + _label + _msg;
      boolean isLocated = !locationString().equals("");
      if (isLocated)
	errMsg += _see + locationString();
      return errMsg;
    }
}
