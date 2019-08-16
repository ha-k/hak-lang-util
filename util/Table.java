//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

/**
 * @version     Last modified on Tue Jun 19 17:39:53 2012 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy;-2003 <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */

public class Table
  {
    private ArrayList keys;
    private ArrayList values;

    public Table ()
      {
	keys = new ArrayList();
	values = new ArrayList();
      }

    public Table (int cap)
      {
	keys = new ArrayList(cap);
	values = new ArrayList(cap);
      }

    public void put (Object key, Object value)
      {
	keys.add(key);
	values.add(value);
      }

    public Object get (Object key)
      {
	int index = keys.indexOf(key);
	return index == -1 ? null : values.get(index);
      }

    public ArrayList values ()
      {
	return values;
      }

    public boolean isEmpty ()
      {
	return keys.isEmpty();
      }
}
