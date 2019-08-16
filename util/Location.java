//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

/**
 * @version     Last modified on Tue Jun 19 17:37:53 2012 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */

/**
 * A class denoting a character's location as (line,column) in a file buffer.
 */

public class Location
{
  private String _file = "";
  private int _line;
  private int _column;

  public Location ()
    {
    }

  public Location (int line, int column)
    {
      _line = line;
      _column = column;
    }

  public Location (String file, int line, int column)
    {
      setFile(file);
      _line = line;
      _column = column;
    }

  public final Location setFile (String file)
    {
      if (file != null) _file = file.intern();  
      return this;
    }

  public final String getFile ()
    {
      return _file;
    }

  public final Location setLine (int line)
    {
      _line = line;
      return this;
    }

  public final int getLine ()
    {
      return _line;
    }

  public final Location setColumn (int column)
    {
      _column = column;
      return this;
    }

  public final int getColumn ()
    {
      return _column;
    }

  /**
   * Returns <tt>true</tt> if this location precedes the specified one.
   * This makes sense only for if the locations are within the same file.
   */
  public final boolean precedes (Location other)
    {
      if (other == null)
        throw new RuntimeException("Can't compare a null location");

      return getLine() == other.getLine() && getColumn() < other.getColumn()
          || getLine() < other.getLine();
    }
  
  public final boolean equals (Object o)
    {
      if (!(o instanceof Location)) return false;

      return   _file == ((Location)o).getFile()
          &&   _line == ((Location)o).getLine()
          && _column == ((Location)o).getColumn();
    }

  public final String toString ()
    {
      return _file+"("+_line+","+_column+")";
    }
}

