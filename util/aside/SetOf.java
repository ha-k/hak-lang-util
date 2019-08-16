//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

import java.util.BitSet;
import java.util.AbstractList;
import java.util.Vector;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * This class is a generic data type for sets of objects. Instances of
 * this class denote subsets of a fixed reference set - an
 * <tt>AbstractList</tt> of <b>Object</b>s - called the <i>base
 * structure</i>. All sets operations are implemented efficiently using
 * an underlying bitset representation.  In effect, using this class
 * alleviates the need to deal directly with the represention and always
 * manipulate sets as implicit collections of objects.
 *
 * <p>
 *
 * This package also defines classes for iterating through
 * <tt>SetOf</tt> objects: an <tt>Enumeration</tt> class
 * (<tt>SetEnumeration</tt>), an <tt>IndexEnumeration</tt> class
 * (<tt>SetIndices</tt>), and an <tt>Iterator</tt> class
 * (<tt>SetIterator</tt>).
 *
 * <p>
 *
 * <b>NB:</b> There are some caveats to be aware of when using
 * <tt>SetOf</tt>:
 * <ul>
 * <li> this class will work correctly assuming that no element
 *      is ever removed from the base structure;
 * <li> for efficiency reasons, no check is made that the base
 *      structure is indeed a set (<i>i.e.</i>, contains no duplicates);
 * </ul>
 *
 * @see         SetEnumeration
 * @see         SetIndices
 * @see         SetIterator
 *
 * @version     Last modified on Sat May 11 06:26:54 2019 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */
public class SetOf
{
  /**
   * The base structure comprising the collection of all elements.
   */
  AbstractList base;
  
  /**
   * This set's underlying representation as a bit word.
   */
  BitSet carrier;

  /**
   * Constructs a new (empty) set with the specified array base,
   * which is built as a <tt>Vector</tt> if <tt>synchronize</tt>
   * is <tt>true</tt>, or as an <tt>ArrayList</tt> otherwise.
   * 
   * @param base        the reference set as an array
   * @param synchronize if <tt>true</tt>, the base structure is synchronized
   */
  public SetOf (Object[] base, boolean synchronize)
  {
    if (synchronize)
      this.base = new Vector(base.length);
    else
      this.base = new ArrayList(base.length);

    for (int i=0; i<base.length; i++)
      this.base.add(base[i]);
    carrier = new BitSet(base.length);
  }

  /**
   * Constructs a new (empty) set with the specified array base.
   * <b>NB:</b> the concrete base structure used is an <tt>ArrayList</tt>
   * - that is, it is unsynchronized. For a synchronized base, use
   * <tt>SetOf(Object[],true)</tt>.
   * 
   * @param base the reference set as an array
   */
  public SetOf (Object[] base)
  {
    this.base = new ArrayList(base.length);

    for (int i=0; i<base.length; i++)
      this.base.add(base[i]);
    carrier = new BitSet(base.length);
  }

  /**
   * Constructs a new (empty) set with the specified AbstractList base.
   * @param base the reference set as an AbstractList.
   */
  public SetOf (AbstractList base)
  {
    this.base = base;
    carrier = new BitSet(this.base.size());
  }

  /**
   * Constructs a new singleton set containing the single
   * specified index with the specified AbstractList base.
   * @param base the reference set as a AbstractList
   * @param index the index of the object
   */
  public SetOf (AbstractList base, int index)
  {
    new SetOf(base).add(index);
  }

  /**
   * Constructs a new singleton set containing the single
   * specified index with the specified array base.
   * @param base the reference set as an array
   * @param index the index of the object
   */
  public SetOf (Object[] base, int index)
  {
    new SetOf(base).add(index);
  }

  /**
   * Constructs a new singleton set containing the single
   * specified object with the specified AbstractList base.
   * @param base the reference set as a AbstractList
   * @param object the object
   */
  public SetOf (AbstractList base, Object object)
  {
    new SetOf(base).add(object);
  }

  /**
   * Constructs a new singleton set containing the single
   * specified object with the specified array base.
   * @param base the reference set as an array
   * @param object the object
   */
  public SetOf (Object[] base, Object object)
  {
    new SetOf(base).add(object);
  }

  /**
   * Constructs a copy of the  specified set.
   * @param set the set to copy
   */
  public SetOf (SetOf set)
  {
    base = set.base;
    carrier = (BitSet)set.carrier.clone();
  }

  /**
   * Constructs a new set with the specified base and underlying bitset
   * representation.
   * @param base the reference set as an array
   * @param carrier the bitset representation
   */
  public SetOf (Object[] base, BitSet carrier)
  {
    this.base = new ArrayList(base.length);
    for (int i=0; i<base.length; i++)
      this.base.add(base[i]);
    this.carrier = carrier;
  }

  /**
   * Constructs a new set with the specified base and underlying bitset
   * representation.
   * @param base the reference set as a AbstractList
   * @param carrier the bitset representation
   */
  public SetOf (AbstractList base, BitSet carrier)
  {
    this.base = base;
    this.carrier = carrier;
  }

  //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

  /**
   * This is an array that is set to contain the base AbstractList
   * indices of this set elements when iterating through the set (i.e.,
   * when a <tt>SetEnumeration</tt> or <tt>SetIndices</tt> is built on
   * this set). It is reset to null anytime the set is modified.
   */  
  int[] indices = null;

  /**
   * Counts and returns the number of elements currently in this set.
   */
  private final int count ()
  {
    return carrier.size();
  }

  /**
   * Builds the array of indices corresponding to this set.
   */
  final void buildIndices ()
  {
    if (!isEmpty())
      {
	indices = new int[count()];
	int index = 0;
	for (int i = carrier.nextSetBit(0); i >= 0; i = carrier.nextSetBit(i+1))	  
	  indices[index++] = i;
      }
  }

  /**
   * The underlying representation for the empty set.
   */
  private static final BitSet EMPTY_BITSET = new BitSet();
  /**
   * A private representation for the full set.
   * @see #top()
   */
  private static SetOf FULL_SET;

  //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

  /**
   * Returns the number of elements in this set.
   */
  public final int size ()
  {
    if (indices == null)
      return count();

    return indices.length;
  }

  /**
   * Returns a set representing the reference set; that is, a set
   * containing all the base structure's elements.
   */
  public final SetOf top ()
  {
    if (FULL_SET==null || FULL_SET.base != base)
      {
	FULL_SET = new SetOf(base);
	for (int i=0; i<base.size(); i++)	  
	  FULL_SET.carrier.set(i);
      }
    return FULL_SET;
  }

  /**
   * Returns true iff this set contains all the base structure's elements.
   */
  public final boolean isFull ()
  {
    return carrier.equals(top().carrier);
  }

  /**
   * Returns true iff this set is empty.
   */
  public final boolean isEmpty ()
  {
    return carrier.equals(EMPTY_BITSET);
  }

  /**
   * Returns the index of a specified object element of this set.
   * @param object the object
   * @return the desired index or -1 if the object is not there
   */ 
  public final int indexOf (Object object)
  {
    if (object instanceof Indexed)
      // WARNING: this works only if
      return ((Indexed)object).index; // index is the position in base 

    return base.indexOf(object);
  }

  /**
   * Adds the element of specified index into this set.
   * @param index the index
   * @return this set
   */
  public SetOf add (int index)
  {
    if (indices == null)
      {
	carrier.set(index);
	return this;
      }

    if (!carrier.get(index))
      {
	carrier.set(index);
	indices = null;
      }

    return this;
  }
  
  /**
   * Adds a specified object into this set.
   * @param object the object
   * @return this set
   */
  public SetOf add (Object object)
  {
    carrier.set(indexOf(object));     
    return this;
  }

  /**
   * Returns a new set obtained from the given set after adding
   * the given index.
   * @param s a set
   * @param index the index
   * @return a new set which is s with the object at index added
   */
  public final static SetOf add (SetOf s, int index)
  {
    SetOf newset = new SetOf(s);
    newset.carrier.set(index);
    return newset;
  }

  /**
   * Removes the element at specified index from this set.
   * @param index the index
   * @return this set
   */
  public SetOf remove (int index)
  {
    if (indices == null)
      {
	carrier.clear(index);
	return this;
      }

    if (carrier.get(index))
      {
	carrier.clear(index);
	indices = null;
      }

    return this;
  }

  /**
   * Removes the specified object from this set.
   * @param object the object
   * @return this set
   */
  public SetOf remove (Object object)
  {
    return remove(indexOf(object));
  }

  /**
   * Returns a new set obtained from the given set after removing
   * the element at the given index.
   * @param s a set
   * @param index the index
   * @return a new set which is s deprived of the object at index
   */
  public final static SetOf remove (SetOf s, int index)
  {
    SetOf newset = new SetOf(s);
    newset.carrier.clear(index);
    return newset;
  }

  /**
   * Returns the base index of the first element in this set.
   * @return the desired index or -1 if empty.
   */
  public final int firstIndex ()
  {
    if (!isEmpty())
      {
	if (indices != null)
	  return indices[0];

	for (int i=0; i<base.size(); i++)	  
	  if (carrier.get(i))
	    return i;
      }

    return -1;
  }

  /**
   * Returns the first element in this set.
   * @return the desired element or <b>null</b> if empty.
   */
  public final Object firstElement ()
  {
    int i = firstIndex();
    return (i<0) ? null : base.get(i);
  }

  /**
   * <i><b>NB:</b> The following set operations assume that all
   * operands refer to the same base structure.</i>
   */

  /**
   * Modifies this set to the union of this set and the specified set.
   * @param other a set
   * @return this set
   */
  public final SetOf union (SetOf other)
  {
    if (indices == null)
      {
	carrier.or(other.carrier);
	return this;
      }

    if (!other.isSubsetOf(this))
      {
	carrier.or(other.carrier);
	indices = null;
      }

    return this;
  }

  /**
   * Returns the union of two sets without modifying either one.
   * @param s1 a set
   * @param s2 a set
   * @return a new set which is the union of s1 and s2
   */
  public final static SetOf union (SetOf s1, SetOf s2)
  {
    SetOf newset = new SetOf(s1);
    newset.carrier.or(s2.carrier);
    return newset;
  }

  /**
   * Modifies this set to the intersection of this set and the specified set.
   * @param other a set
   * @return this set
   */
  public final SetOf intersection (SetOf other)
  {
    if (indices == null)
      {
	carrier.and(other.carrier);
	return this;
      }

    if (!this.isSubsetOf(other))
      {
	carrier.and(other.carrier);
	indices = null;
      }

    return this;
  }

  /**
   * Returns the intersection of two sets without modifying either one.
   * @param s1 a set
   * @param s2 a set
   * @return a new set which is the intersection of s1 and s2
   */
  public final static SetOf intersection (SetOf s1, SetOf s2)
  {
    SetOf newset = new SetOf(s1);
    newset.carrier.and(s2.carrier);
    return newset;
  }

  /**
   * Modifies this set to the difference of this set and the specified set.
   * @param other a set
   * @return this set
   */
  public final SetOf minus (SetOf other)
  {
    if (indices == null)
      {
	carrier.or(other.carrier);
	carrier.xor(other.carrier);
	return this;
      }

    if (!intersection(this,other).isEmpty())
      {
	carrier.or(other.carrier);
	carrier.xor(other.carrier);
	indices = null;
      }

    return this;
  }

  /**
   * Returns the difference of two sets without modifying either one.
   * @param s1 a set
   * @param s2 a set
   * @return a new set which is the difference of s1 and s2
   */
  public final static SetOf minus (SetOf s1, SetOf s2)
  {
    SetOf newset = new SetOf(s1);
    newset.carrier.or(s2.carrier);
    newset.carrier.xor(s2.carrier);
    return newset;
  }

  /**
   * Modifies this set to its complement (relative to the base).
   * @return this set
   */
  public final SetOf not ()
  {
    carrier.xor(top().carrier);
    indices = null;
    return this;
  }

  /**
   * Returns the complement (relative to the base) of the specified set
   * without modifying it.
   * @param s a set
   */
  public final static SetOf not (SetOf s)
  {
    SetOf newset = new SetOf(s);
    newset.carrier.xor(s.top().carrier);
    return newset;
  }

  /**
   * Returns true iff the specified index is that of an element of this set.
   * @param index the index
   */
  public final boolean contains (int index)
  {
    return carrier.get(index);
  }

  /**
   * Returns true iff the specified object is an element of this set.
   * @param object the object
   */
  public final boolean contains (Object object)
  {
    return carrier.get(indexOf(object));
  }

  /**
   * Returns true iff this set is included in or equal to the
   * specified set.
   * @param other a set
   */
  public final boolean isSubsetOf (SetOf other)
  {
    BitSet intersection = (BitSet)carrier.clone();
    intersection.and(other.carrier);
    return carrier.equals(intersection);
  }

  /**
   * Returns true iff this set is strictly included in the
   * specified set.
   * @param other a set
   */
  public final boolean isStrictSubsetOf (SetOf other)
  {
    return isSubsetOf(other) && size() != other.size();
  }

  /**
   * Returns true iff this set is equal to the specified set.
   * @param other a set
   */
  public final boolean isEqualTo (SetOf other)
  {
    return carrier.equals(other.carrier);
  }

  /**
   * Returns true iff the specified object is a set equal to this.
   * @param other an object
   */
  public boolean equals (Object other)
  {
    if (other instanceof SetOf)
      return this.isEqualTo(((SetOf)other));
    return false;
  }

  /**
   * Returns a hashcode for this set.
   */
  public int hashCode ()
  {
    return carrier.hashCode();
  }

  /**
   * Returns a string representation of this set as a comma-separated
   * list of elements between curly braces.
   */
  public String toString ()
  {
    String s = "";
    for (Iterator i=iterator(); i.hasNext();)      
      {
	if (s.length() > 0)
	  s += ", ";
	s += i.next().toString();
      }
    return "{" + s + "}";
  }

  /**
   * Returns a string representation of this set as a bit string of 0's and 1's.
   */
  public final String toBitString ()
  {
    return toBitString('0','1');
  }

  /**
   * Returns a string representation of this set as a bit string of two
   * specified characters standing for "absent" and "present" respectively.
   * @param out a character
   * @param in a character   
   */
  public final String toBitString (char out, char in)
  {
    StringBuilder s = new StringBuilder();
    for (int i=0; i<base.size(); i++)      
      s.append(carrier.get(i) ? in : out);
    return s.toString();
  }

  /**
   * Returns an <b>Enumeration</b> over the object elements of this set.
   */
  public final Enumeration elements ()
  {
    return new SetEnumeration(this);
  }

  /**
   * Returns an <b>Iterator</b> over the object elements of this set.<br>
   */
  public final Iterator iterator ()
  {
    return new SetIterator(this);
  }

  /**
   * Returns an enumeration-like object over the base structure's indices for
   * the elements of this set.
   */
  public final IndexEnumeration indices ()
  {
    return new SetIndices(this);
  }

}
