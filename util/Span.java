//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

/**
 * @version     Last modified on Fri Nov 16 01:08:26 2012 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */

/**
 * A class denoting the area spanning between two (character's) locations.
 */

public class Span implements Locatable
{
  private Location _start;
  private Location _end;

  public Span ()
    {
    }

  public Span (Location start, Location end)
    {
      _start = start;
      _end = end;
    }

  public final boolean isKnown ()
    {
      return
	   _start != null && _start.getLine() != 0 && _start.getColumn() != 0
	&& _end   != null && _end.getLine()   != 0 && _end.getColumn()   != 0;
    }

  public final Location start ()
    {
      return _start == null ? _start = new Location() : _start;
    }

  public final Location end ()
    {
      return _end == null ? _end = new Location() : _end;
    }

  public final Location getStart ()
    {
      return _start;
    }

  public final Location getEnd ()
    {
      return _end;
    }

  public final Locatable setStart (Location location)
    {
      _start = location;
      return this;
    }

  public final Locatable setEnd (Location location)
    {
      _end = location;
      return this;
    }

  public final int getStartLine ()
    {
      return start().getLine();
    }

  public final Span setStartLine (int line)
    {
      start().setLine(line);
      return this;
    }

  public final int getEndLine ()
    {
      return end().getLine();
    }

  public final Span setEndLine (int line)
    {
      end().setLine(line);
      return this;
    }

  public final int getStartCol ()
    {
      return start().getColumn();
    }

  public final Span setStartCol (int col)
    {
      start().setColumn(col);
      return this;
    }

  public final int getEndCol ()
    {
      return end().getColumn();
    }

  public final Span setEndCol (int col)
    {
      end().setColumn(col);
      return this;
    }

  public final String getStartFile ()
    {
      return start().getFile();
    }

  public final Span setStartFile (String file)
    {
      start().setFile(file);
      return this;
    }

  public final String getEndFile ()
    {
      return end().getFile();
    }

  public final Span setEndFile (String file)
    {
      end().setFile(file);
      return this;
    }

  public final boolean equals (Object o)
    {
      if (!(o instanceof Span)) return false;

      return _start != null
          &&   _end != null
          && _start.equals(((Span)o).getStart())
          &&   _end.equals(((Span)o).getEnd());
    }

  public final String locationString ()
    {
      return toString();
    }

  public final String toString ()
    {
      String s = null;
      if (_start == null)
        if (_end == null)
          s = "";
        else
          s = _end.toString();
      else
        if (_end == null || _start.equals(_end))
          s = _start.toString();
        else
          if (_start.getFile() == _end.getFile())
            if (_start.getLine() == _end.getLine())
              s = _start.getFile() + "(" + _start.getLine() + ","
                + _start.getColumn() + ".." +_end.getColumn() + ")";
            else
              s = _start + ".." + "(" + _end.getLine() + ","
                + _end.getColumn() + ")";
          else
            s = _start + ".." + _end;

      return s;
    }
}
