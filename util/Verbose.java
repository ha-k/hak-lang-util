//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

/**
 * This is a repository of constants to denote various levels
 * or verbosity.
 *
 * @version	Last modified on Tue Jun 19 17:40:10 2012 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright	&copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */
public interface Verbose
{
  public static int SILENT = 0;		public static int QUIET = 0;
  public static int TERSE = 1;		public static int NORMAL = 1;
  public static int VERBOSE = 2;
  public static int DETAILED = 3;
  public static int BABBLE = 4;
}
