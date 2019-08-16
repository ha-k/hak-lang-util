//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

import java.util.Iterator;
import java.util.Collection;

/**
 * This class implements a vector of primitive <tt>int</tt> elements
 * to alleviate the need to use an ArrayList of <tt>Integer</tt> wrappers.
 * It implements all public members items of the <tt>java.util.ArrayList</tt>
 * API that make sense when dealing with primitive <tt>int</tt> elements
 * rather than <tt>Object</tt>s.
 * <p>
 * Like <tt>java.util.ArrayList</tt>, its underlying representation can grow
 * dynamically. As well, it is <i>not</i> synchronized.
 *
 * @version     Last modified on Fri Aug 03 05:52:12 2018 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */

public class IntArrayList
{
  // FIELDS:

  /**
   * The array buffer into which the components of the
   * <tt>IntArrayList</tt> are stored. The capacity of the
   * <tt>IntArrayList</tt> is the length of this array buffer, and is at
   * least large enough to contain all the <tt>IntArrayList</tt>'s
   * elements.
   */
  protected int[] elementData;

  /**
   * The number of valid components in this <tt>IntArrayList</tt>
   * object. Components <tt>elementData[0]</tt> through
   * <tt>elementData[elementCount-1]</tt> are the actual items.
   */
  protected int elementCount;

  /**
   * The amount by which the capacity of the <tt>IntArrayList</tt> is
   * automatically incremented when its size becomes greater than its
   * capacity. If the capacity increment is less than or equal to zero,
   * the capacity of the <tt>IntArrayList</tt> is multiplied by
   * <tt>incrementFactor</tt> each time it needs to grow. The default
   * value of <tt>incrementFactor</tt> is <tt>2.0</tt> (so growing
   * doubles the capacity). It may be reset with
   * <tt>setIncrementFactor(float)</tt> to any decimal value between
   * <tt>1.1</tt> and <tt>5.0</tt>.
   */
  protected int capacityIncrement;

  // CONSTRUCTORS:

  /**
   * Constructs an empty <tt>IntArrayList</tt> with the specified
   * initial capacity <tt>initialCapacity</tt> and capacity increment
   * <tt>capacityIncrement</tt>.
   *
   * @param     initialCapacity the initial capacity of the IntArrayList.
   * @param     capacityIncrement amount by which the capacity is increased on overflow.
   * @exception NegativeArraySizeException if the specified initial capacity is negative.
   */
  public IntArrayList (int initialCapacity, int capacityIncrement)
    {
      this(initialCapacity);
      this.capacityIncrement = capacityIncrement;
    }

  /**
   * Constructs an empty <tt>IntArrayList</tt> with the specified
   * initial capacity <tt>initialCapacity</tt> and with its capacity
   * increment equal to <tt>0</tt>.
   *
   * @param     initialCapacity the initial capacity of the IntArrayList.
   * @exception NegativeArraySizeException if the specified initial capacity is negative.
   */
  public IntArrayList (int initialCapacity)
    {
      elementData = new int[initialCapacity];
    }

  /**
   * Constructs an empty <tt>IntArrayList</tt> so that its internal data
   * array has size <tt>10</tt> and its standard capacity increment is
   * <tt>0</tt>.
   */
  public IntArrayList ()
    {
      this(10);
    }

  /**
   * Constructs an <tt>IntArrayList</tt> containing the values contained
   * in the specified array, in the same order.
   *
   * @param     a an array of ints.
   */
  public IntArrayList (int[] a)
    {
      elementData = new int[a.length];

      for (int i=0; i<a.length; i++)
        elementData[elementCount++] = a[i];
    }

  /**
   * Constructs a <tt>IntArrayList</tt> containing the
   * <tt>intValue()</tt> values of the <tt>java.lang.Number</tt> objects
   * in the specified collection, in the order they are returned by the
   * collection's iterator. If any object in the collection is not an
   * instance of <tt>java.lang.Number</tt>, a
   * <tt>ClassCastException</tt> exception is thrown.
   *
   * @param     c the collection whose elements are to be placed into this IntArrayList.
   * @exception ClassCastException if the collection contains a non-number.
   */
  public IntArrayList (Collection c)
    {
      elementData = new int[c.size()];
      for (Iterator i=c.iterator(); i.hasNext();)
        elementData[elementCount++] = ((Number)i.next()).intValue();
    }

  // METHODS:

  /**
   * Copies the components of this <tt>IntArrayList</tt> into the
   * specified array <tt>a</tt>. The item at index <tt>k</tt> in this
   * <tt>IntArrayList</tt> is copied into component <tt>k</tt> of
   * <tt>a</tt>. The array <tt>a</tt> must be big enough to hold all the
   * elements of this <tt>IntArrayList</tt>, else an
   * <tt>IndexOutOfBoundsException</tt> is thrown.
   *
   * @param a the array into which the components get copied.
   * @exception IndexOutOfBoundsException if the array <tt>a</tt> is too small.
   */
  public final void copyInto (int[] a)
    {
      for (int i=elementCount; i-->0;)
        a[i] = elementData[i];
    }

  /**
   * Trims the capacity of this <tt>IntArrayList</tt> to its current
   * size. If the capacity of this <tt>IntArrayList</tt> is larger than
   * its current size, this replaces its internal data array with a
   * smaller one of size equal to its number of elements. An application
   * can use this operation to minimize the storage of a
   * <tt>IntArrayList</tt>.
   */
  public final void trimToSize ()
    {
      if (elementCount == elementData.length) return;

      int[] newArray = newArray = new int[elementCount];

      for (int i=0; i<elementCount; i++)
        newArray[i] = elementData[i];

      elementData = newArray;
    }

  /**
   * Increases the capacity of this <tt>IntArrayList</tt>, if necessary,
   * to ensure that it can hold at least the number of components
   * specified by the minimum capacity argument.  <p> If the current
   * capacity of this <tt>IntArrayList</tt> is less than
   * <tt>minCapacity</tt>, then its capacity is increased by replacing
   * its internal data array with a larger one. The size of the new data
   * array will be the old size plus <tt>capacityIncrement</tt>, unless
   * the value of <tt>capacityIncrement</tt> is less than or equal to
   * <tt>0</tt>, in which case the new capacity will be the old capacity
   * multiplied by <tt>incrementFactor</tt>; and if this new size is
   * still smaller than <tt>minCapacity</tt>, then the new capacity will
   * be <tt>minCapacity</tt>.
   *
   * @param minCapacity the desired minimum capacity.
   */
  public final void ensureCapacity (int minCapacity)
    {
      if (elementData.length >= minCapacity)
	return;

      int newCapacity = Math.max(minCapacity,_increasedCapacity());
      // System.out.println("*** Allocating a new int array with capacity of "+newCapacity);
      int[] newArray = new int[newCapacity];
      
      for (int i=0; i<elementCount; i++)
	newArray[i] = elementData[i];
      
      elementData = newArray;
    }

  /**
   * Sets the size of this IntArrayList. If the new size is greater than the
   * current size, new zeroes are added to the end of the IntArrayList.
   *
   * @param     newSize the new size of this IntArrayList.
   */
  public final void setSize (int newSize)
    {
      ensureCapacity(elementCount = newSize);
    }

  /**
   * Returns the current capacity of this IntArrayList.
   *
   * @return    the current capacity the length of its internal data array.
   */
  public final int capacity ()
    {
      return elementData.length;
    }

  /**
   * Returns the number of components in this <tt>IntArrayList</tt>.
   *
   * @return the number of components in this <tt>IntArrayList</tt>.
   */
  public final int size ()
    {
      return elementCount;
    }

  /**
   * Tests if this <tt>IntArrayList</tt> has no components.
   *
   * @return    <tt>true</tt> if this <tt>IntArrayList</tt>'s size is zero; <tt>false</tt> otherwise.
   */
  public final boolean isEmpty ()
    {
      return elementCount == 0;
    }

  /**
   * Tests if the specified integer <tt>element</tt> is a component in this
   * <tt>IntArrayList</tt>.
   *
   * @param  element an <tt>int</tt>.
   * @return true if the specified <tt>int</tt> is in this <tt>IntArrayList</tt>; <tt>false</tt> otherwise.
   */
  public final boolean contains (int element)
    {
      for (int i=0; i<elementCount; i++)
        if (elementData[i] == element) return true;

      return false;
    }

  /**
   * Searches for the first occurrence of the given int; returns -1 if it
   * is not found.
   *
   * @param     element an int.
   * @return    the index of the first occurrence of the argument or -1.
   */
  public final int indexOf (int element)
    {
      for (int i=0; i<elementCount; i++)
        if (elementData[i] == element) return i;

      return -1;
    }

  /**
   * Searches for the first occurence of the given int, beginning
   * the search at index; returns -1 if it is not found.
   *
   * @param     element an integer.
   * @param     index the non-negative index to start searching from.
   * @exception IndexOutOfBoundsException if index is negative.
   */
  public final int indexOf (int element, int index)
    {
      for (int i=index; i<elementCount; i++)
        if (elementData[i] == element) return i;

      return -1;
    }

  /**
   * Returns the index of the last occurrence of the specified int in
   * this IntArrayList; returns -1 if it is not found.
   *
   * @param     element the desired component.
   * @return    the index of the last occurrence of the specified int, or -1.
   */
  public final int lastIndexOf (int element)
    {
      for (int i=elementCount; i-->0;)
        if (elementData[i] == element) return i;

      return -1;
    }

  /**
   * Searches backwards for the specified integer, starting from the
   * specified index, and returns its index, or -1 if not found.
   *
   * @param     element an integer.
   * @param     index the non-negative index to start searching from.
   * @exception IndexOutOfBoundsException if index is invalid.
   */
  public final int lastIndexOf (int element, int index)
    {
      for (int i=index; i-->0;)
        if (elementData[i] == element) return i;

      return -1;
    }

  /**
   * Returns the component at the specified index. This method is
   * identical in functionality to the get method.
   *
   * @param     index an index into this IntArrayList.
   * @exception ArrayIndexOutOfBoundsException if the index is invalid.
   */
  public final int elementAt (int index)
    {
      return get(index);
    }

  /**
   * Returns the first component (the item at index 0) of this IntArrayList.
   *
   * @return    the first component of this IntArrayList.
   * @exception ArrayIndexOutOfBoundsException if this IntArrayList is empty.
   */
  public final int firstElement ()
    {
      return elementData[0];
    }

  /**
   * Returns the last component of the IntArrayList.
   *
   * @return    the last component of the IntArrayList, i.e., the component at size() - 1.
   * @exception ArrayIndexOutOfBoundsException if this IntArrayList is empty.
   */
  public final int lastElement ()
    {
      return elementData[elementCount-1];
    }

  /**
   * Sets the component at the specified index of this IntArrayList to be the
   * specified integer. The previous component at that position is lost.
   * <p>
   * The index must be a value greater than or equal to 0 and less than
   * the current size of the IntArrayList.
   * <p>
   * This method is identical in functionality to the set method except
   * that the latter returns the old value that was stored at the specified
   * position and it has its argument in a different order.
   *
   * @param     element what the component is to be set to.
   * @param     index the specified index.
   * @exception ArrayIndexOutOfBoundsException if the index was invalid.
   */
  public final void setElementAt (int element, int index)
    {
      set(index,element);
    }

  /**
   * Deletes the component at the specified index. Each component in
   * this IntArrayList with an index greater or equal to the specified index
   * is shifted downward to have an index one smaller than the value it
   * had previously. The size of this IntArrayList is decreased by 1.
   * <p>
   * The index must be a value greater than or equal to 0 and less than
   * the current size of the IntArrayList.
   * <p>
   * This method is identical in functionality to the removeIndex method
   * except that the removeIndex method returns the old value that was
   * stored at the specified position.
   * <p>
   * <b>N.B.</b>Because <tt>remove(int)</tt> is ambiguous, it is not
   * supported for IntArrayLists; its functionality is provided by
   * <tt>removeIndex(int)</tt> and <tt>removeElement(int)</tt>.
   *
   * @param     index the index of the integer to remove.
   * @exception ArrayIndexOutOfBoundsException if the index was invalid.
   */
  public final void removeElementAt (int index)
    {
      removeIndex(index);
    }

  /**
   * Inserts the specified integer as a component in this IntArrayList at the
   * specified index. Each component in this IntArrayList with an index
   * greater or equal to the specified index is shifted upward to have
   * an index one greater than the value it had previously.
   * <p>
   * The index must be a value greater than or equal to 0 and less than
   * or equal to the current size of the IntArrayList. (If the index is equal
   * to the current size of the IntArrayList, the new element is appended to
   * the IntArrayList.)
   * <p>
   * This method is identical in functionality to the add(int, int)
   * method except that the latter method reverses the order of the
   * arguments, to match array usage  more closely.
   *
   * @param     element the element to insert.
   * @param     index where to insert the new component.
   * @exception ArrayIndexOutOfBoundsException if the index was invalid.
   */
  public final void insertElementAt (int element, int index)
    {
      add(index,element);
    }

  /**
   * Adds the specified component to the end of this IntArrayList, increasing
   * its size by one. The capacity of this IntArrayList is increased if its
   * size becomes greater than its capacity.
   * <p>
   * This method is identical in functionality to the add(int) method
   * except that the latter returns a boolean.
   *
   * @param     element the component to be added.
   */
  public final void addElement (int element)
    {
      add(element);
    }

  /**
   * Removes all components from this IntArrayList.
   * This method is identical in functionality to the clear method.
   *
   */
  public final void removeAllElements ()
    {
      elementCount = 0;
    }

  /**
   * Returns a clone of this IntArrayList. The copy will contain a reference
   * to a clone of the internal data array, not a reference to the
   * original internal data array of this IntArrayList object.
   *
   * @return    a clone of this IntArrayList.
   */
  public final Object clone ()
    {
      int[] newArray = new int[elementData.length];

      for (int i=0; i<elementData.length; i++)
        newArray[i] = elementData[i];

      return new IntArrayList(newArray,elementCount,capacityIncrement,incrementFactor);
    }

  /**
   * Returns an array containing all of the elements in this IntArrayList in
   * the correct order.
   */
  public final int[] toArray ()
    {
      int[] a = new int[elementCount];

      for (int i=0; i<elementCount; i++)
        a[i] = elementData[i];

      return a;
    }

  /**
   * Returns the element at the specified position in this IntArrayList.
   *
   * @exception ArrayIndexOutOfBoundsException if the index is invalid.
   */
  public final int get (int index)
    {
      return elementData[index];
    }

  /**
   * Replaces the element at the specified position in this IntArrayList with
   * the specified element.
   *
   * @param     index index of element to replace.
   * @param     element element to be stored at the specified position.
   * @return    the element previously at the specified position.
   * @exception ArrayIndexOutOfBoundsException if index is out of range.
   */
  public final int set (int index, int element)
    {
      int old = elementData[index];
      elementData[index] = element;
      return old;
    }

  /**
   * Replaces the element at the last position in this IntArrayList with
   * the specified element.
   *
   * @param     element element to be stored at the last position.
   * @exception ArrayIndexOutOfBoundsException if index is out of range.
   */
  public final void setLast (int element)
    {
      elementData[elementCount-1] = element;
    }

  /**
   * Appends the specified element to the end of this IntArrayList.
   *
   * @param     element element to be appended to this IntArrayList.
   * @return    true (following the <tt>java.util.ArrayList</tt> API).
   */
  public final boolean add (int element)
    {
      if (elementCount == elementData.length) _grow();
      elementData[elementCount++] = element;
      return true;
    }

  /**
   * Removes the first occurrence of the specified element. If this IntArrayList 
   * does not contain the element, it is left unchanged. 
   * <p>
   * <b>N.B.</b>Because <tt>remove(int)</tt> is ambiguous, it is not
   * supported for IntArrayLists; its functionality is provided by
   * <tt>removeIndex(int)</tt> and <tt>removeElement(int)</tt>.
   *
   * @param     element element to be removed from this IntArrayList, if present.
   * @return    true if this IntArrayList contained the specified element.
   */
  public final boolean removeElement (int element)
    {
      int i = 0;

      for (i=0; i<elementCount; i++)
        if (elementData[i] == element) break;

      if (i == elementCount) return false;

      for (;i<elementCount-1;i++)
        elementData[i] = elementData[i+1];

      elementCount--;

      return true;
    }

  /**
   * Inserts the specified integer <tt>element</tt> at the specified
   * <tt>index</tt> position in this <tt>IntArrayList</tt>. Shifts the
   * element currently at that position (if any) and any subsequent
   * elements to the right (adds one to their indices).
   *
   * @param     index index at which the specified element is to be inserted.
   * @param     element element to be inserted.
   * @exception ArrayIndexOutOfBoundsException if index is out of range.
   */
  public final void add (int index, int element)
    {
      // System.out.println("*** Need to insert element "+element+" at index "+index+" into "+this);
      elementCount++;
      // System.out.println("*** Ensuring capacity of "+elementCount);
      ensureCapacity(elementCount);
      // System.out.println("*** Current capacity is "+elementData.length);

      for (int i=elementCount-1; i>index; i--)
	// {
	//   System.out.println("*** Shifting element at position "+(i-1)+" ("+elementData[i-1]+") to position "+i);
	  elementData[i] = elementData[i-1];
        // }

      elementData[index] = element;
      // System.out.println("*** New set of indices is now "+this);
    }

  /**
   * Removes the element at the specified position and shifts any subsequent
   * elements to the left (subtracts one from their indices). Returns the
   * element that was removed.
   *
   * @param     index the index of the element to removed.
   * @exception ArrayIndexOutOfBoundsException if index is out of range.
   */
  public final int removeIndex (int index)
    {
      int old = elementData[index];
      
      for (int i=index; i<elementCount-1; i++)
        elementData[i] = elementData[i+1];

      elementCount--;

      return old;
    }

  /**
   * Removes all of the elements from this IntArrayList.
   */
  public final void clear ()
    {
      elementCount = 0;
    }

  /**
   * Returns true if this IntArrayList contains all of the elements in the
   * specified array.
   *
   * @param     a array containing the elements to be tested for membership.
   * @return    true if all of the elements of the specified array belong in this.
   */
  public final boolean containsAll (int[] a)
    {
      for (int i=0; i<a.length; i++)
        if (!contains(a[i])) return false;

      return true;
    }

  /**
   * Returns true if this IntArrayList contains all of the elements in the
   * specified IntArrayList.
   *
   * @param     v IntArrayList of elements to be tested for membership.
   * @return    true if all of the elements of the specified collection belong in this.
   */
  public final boolean containsAll (IntArrayList v)
    {
      for (int i=v.size(); i-->0;)
        if (!contains(v.get(i))) return false;

      return true;
    }

  /**
   * Appends all of the elements in the specified array to the end
   * of this IntArrayList, in the order of the array.
   *
   * @param     a array containing the elements to be inserted.
   * @exception ClassCastException if the collection contains a non-number.
   * @return    true (following the <tt>java.util.ArrayList</tt> API).
   */
  public final boolean addAll (int[] a)
    {
      ensureCapacity(elementCount+a.length);
      for (int i=0; i<a.length; i++) add(a[i]);
      return true;
    }

  /**
   * Appends all of the elements in the specified IntArrayList to the end
   * of this IntArrayList.
   *
   * @param     v the IntArrayList of elements to be added.
   * @return    true (following the <tt>java.util.ArrayList</tt> API).
   */
  public final boolean addAll (IntArrayList v)
    {
      int size = v.size();
      ensureCapacity(elementCount+size);
      for (int i=0; i<size; i++) add(v.get(i));
      return true;
    }

  /**
   * Removes from this IntArrayList all of its elements that are contained
   * in the specified array. Note that all occurrences of such elemeents
   * are removed - not just the first.
   *
   * @param     a array of elements to be removed.
   * @return    true if this IntArrayList changed as a result of the call.
   */
  public final boolean removeAll (int[] a)
    {
      IntArrayList indices = new IntArrayList();

      for (int i=0; i<a.length; i++)
        for (int j=0; j<elementCount; j++)
          if (a[i] == elementData[j]) indices.add(j);

      removeAllIndices(indices);
      return !indices.isEmpty();
    }

  /**
   * Removes from this IntArrayList all of its elements that are contained
   * in the specified IntArrayList. Note that all occurrences of such elemeents
   * are removed - not just the first.
   *
   * @param     v the IntArrayList of elements to be removed.
   * @return    true if this IntArrayList changed as a result of the call.
   */
  public final boolean removeAll (IntArrayList v)
    {
      IntArrayList indices = new IntArrayList();

      for (int i=v.size(); i-->0;)
        for (int j=elementCount; j-->0;)
          if (v.get(i) == elementData[j]) indices.add(j);

      removeAllIndices(indices);
      return !indices.isEmpty();
    }

  /**
   * Removes from this IntArrayList the first occurrence of the elements
   * that are contained in the specified array
   *
   * @param     a array of elements to be removed.
   * @return    true if this IntArrayList changed as a result of the call.
   */
  public final boolean removeAllFirst (int[] a)
    {
      IntArrayList indices = new IntArrayList();

      for (int i=0; i<a.length; i++)
        for (int j=0; j<elementCount; j++)
          if (a[i] == elementData[j])
            {
              indices.add(j);
              break;
            }

      removeAllIndices(indices);
      return !indices.isEmpty();          
    }

  /**
   * Removes from this IntArrayList the first occurrence of the elements
   * that are contained in the specified IntArrayList.
   *
   * @param     v the IntArrayList of elements to be removed.
   * @return    true if this IntArrayList changed as a result of the call.
   */
  public final boolean removeAllFirst (IntArrayList v)
    {
      IntArrayList indices = new IntArrayList();

      int size = v.size();

      for (int i=0; i<size; i++)
        for (int j=0; j<elementCount; j++)
          if (v.get(i) == elementData[j])
            {
              indices.add(j);
              break;
            }

      removeAllIndices(indices);
      return !indices.isEmpty();
    }

  /**
   * Retains only the elements in this IntArrayList whose elements are
   * contained in the specified IntArrayList. In other words, removes from
   * this IntArrayList all of its elements that are not contained in the
   * specified IntArrayList.
   *
   * @param     v IntArrayList of elements to be retained.
   * @return    true if this IntArrayList changed as a result of the call.
   */
  public final boolean retainAll (IntArrayList v)
    {
      IntArrayList indices = new IntArrayList();

      for (int i=0; i<elementCount; i++)
        if (!v.contains(elementData[i]))
          indices.add(i);

      removeAllIndices(indices);
      return !indices.isEmpty();
    }

  /**
   * Inserts all the elements in the specified array at the specified
   * index in this IntArrayList. Shifts the element currently at that
   * position (if any) and any subsequent elements to the right (adds
   * the length of the array to their indices).
   *
   * @param     index index at which the specified element is to be inserted.
   * @param     a array of elements to be inserted.
   * @exception ArrayIndexOutOfBoundsException if index is out of range.
   */
  public final void addAll (int index, int[] a)
    {
      if (elementCount+a.length < elementData.length)
        {
          for (int i=index; i<elementCount; i++)
            elementData[i+a.length] = elementData[i];

          for (int i=0; i<a.length; i++)
            elementData[index+i] = a[i];
          
          elementCount += a.length;
          return;
        }

      int[] newArray = new int[_increasedCapacity(elementCount+a.length)];

      for (int i=0; i<index; i++)
        newArray[i] = elementData[i];

      for (int i=0; i<a.length; i++)
        newArray[index+i] = a[i];

      for (int i=index+a.length; i<elementCount+a.length; i++)
        newArray[i] = elementData[i-a.length];

      elementData = newArray;
      elementCount += a.length;
    }

  /**
   * Inserts all of the elements in the specified IntArrayList into this
   * IntArrayList at the specified position. Shifts the element currently
   * at that position (if any) and any subsequent elements to the right
   * (increases their indices). The new elements will appear in this
   * IntArrayList in their order in the specified IntArrayList.
   *
   * @param     index index where to insert the first element
   * @param     v IntArrayList to be inserted into this IntArrayList.
   * @exception ArrayIndexOutOfBoundsException if index is invalid.
   */
  public final void addAll (int index, IntArrayList v)
    {
      int size = v.size();
      
      if (elementCount+size < elementData.length)
        {
          for (int i=index; i<elementCount; i++)
            elementData[i+size] = elementData[i];

          for (int i=0; i<size; i++)
            elementData[index+i] = v.get(i);
          
          elementCount += size;
          return;
        }

      int[] newArray = new int[_increasedCapacity(elementCount+size)];

      for (int i=0; i<index; i++)
        newArray[i] = elementData[i];

      for (int i=0; i<size; i++)
        newArray[index+i] = v.get(i);

      for (int i=index+size; i<elementCount+size; i++)
        newArray[i] = elementData[i-size];

      elementData = newArray;
      elementCount += size;
    }

  /**
   * Compares the specified int with this IntArrayList for equality.
   * Returns true if and only if the specified object is also an
   * IntArrayList, both have the same size, and all corresponding pairs of
   * elements are equal. In other words, two IntArrayLists are defined to
   * be equal if they contain the same elements in the same order.
   *
   * @param     o the object to be compared for equality with this IntArrayList.
   * @return    true if the specified object is equal to this IntArrayList
   */
  public final boolean equals (Object o)
    {
      if (o == this)
	return true;
      
      if (!(o instanceof IntArrayList))
	return false;

      IntArrayList other = (IntArrayList)o;

      if (other.size() != elementCount)
	return false;

      for (int i=0; i<elementCount; i++)
        if (other.get(i) != elementData[i])
	  return false;

      return true;
    }

  /**
   * Returns the hash code value for this IntArrayList.
   */
  public int hashCode ()
    {
      int code = elementCount;
      int shift = 1;

      for (int i=0; i<elementCount; i += Math.max(1,elementCount/4))
        {
          if (shift > 0)
            code <<= 1;
          else
            code >>= 1;

          code ^= elementData[i];
          shift = -shift;
        }

      return code;
    }

  /**
   * Returns a string representation of this IntArrayList
   *
   * @return    a string representation of this collection.
   */
  public String toString ()
    {
      StringBuilder buf = new StringBuilder("[");

      for (int i=0; i<elementCount; i++)
        buf.append(elementData[i]+(i==elementCount-1?"":","));

      return (buf.append("]")).toString();
    }

  /**
   * Removes from this IntArrayList all of the elements whose index is
   * between fromIndex, inclusive and toIndex, exclusive. Shifts any
   * succeeding elements to the left (reduces their index). This call
   * shortens the ArrayList by (toIndex - fromIndex) elements. (If
   * toIndex &le; fromIndex, this operation has no effect.)
   * <p>
   * <b>N.B.</b> Note that this method is public, whereas - strangely
   * enough, it is protected in the <tt>java.util.ArrayList</tt> API!
   *
   * @param     fromIndex index of first element to be removed.
   * @param     toIndex index after last element to be removed.
   */
  public void removeRange (int fromIndex, int toIndex)
    {
      if (toIndex <= fromIndex) return;

      toIndex = Math.min(toIndex, elementCount);

      int gap = toIndex - fromIndex;

      for (int i=fromIndex; i<toIndex; i++)
        elementData[i] = elementData[i+gap];

      elementCount -= gap;
    }

  // NEW API:

  protected float incrementFactor = 2.0f;

  /**
   * <p>
   * @param     incrementFactor a float that is between 1.1 and 5.0
   * @exception IllegalArgumentException if not between 1.1 and 5.0
   */
  public final void setIncrementFactor (float incrementFactor)
    {
      if (incrementFactor < 1.1 || incrementFactor > 5.0)
        throw new IllegalArgumentException("Increment factor "+incrementFactor
                                           +" must be in [1.1,5.0] range");
      this.incrementFactor = incrementFactor;
    }

  /**
   * Clears all elements of this IntArraylist at index equal to or
   * greater than the specified index. If this index value is out range,
   * this clears all the IntArraylist.
   *
   * @param	index the index above which clearing takes effect
   */
    public final void clear (int index)
    {
      elementCount = (index < 0 || index >= elementCount) ? 0 : index;
    }

  // PRIVATES:

  /**
   * A constructor used privately for cloning only...
   */
  private IntArrayList (int[] elementData, int elementCount,
                     int capacityIncrement, float incrementFactor)
    {
      this.elementData = elementData;
      this.elementCount = elementCount;
      this.capacityIncrement = capacityIncrement;
      this.incrementFactor = incrementFactor;
    }

  /**
   * Grows the size of the internal array.
   */
  private final void _grow ()
    {
      int[] newArray = new int[_increasedCapacity()];

      for (int i=0; i<elementCount; i++)
        newArray[i] = elementData[i];

      elementData = newArray;
    }

  /**
   * Computes a greater capacity for this IntArrayList.
   *
   * @return    the increased capacity
   */
  private final int _increasedCapacity ()
    {
      if (capacityIncrement <= 0)
        {
          int capacity = elementData.length == 0 ? 1 : elementData.length;
          return (int)Math.floor(capacity*incrementFactor+1);
        }

      return elementData.length+capacityIncrement;
    }

  /**
   * Computes a greater capacity for this IntArrayList that is at least
   * the specified capacity.
   *
   * @param     minCapacity the minimum necessary capacity.
   * @return    the increased capacity
   */
  private final int _increasedCapacity (int minCapacity)
    {
      if (capacityIncrement <= 0)
        {
          int capacity = elementData.length == 0 ? 1 : elementData.length;
          return Math.max(minCapacity,
                          (int)Math.floor(capacity*incrementFactor));
        }

      return Math.max(minCapacity,elementData.length+capacityIncrement);
    }

  /**
   * Removes from this IntArrayList all of its elements that are located at
   * the indices in the specified IntArrayList. This assumes that the indices
   * appear sorted in increasing order.
   *
   * @param     indices indices of elements to be removed.
   */
  private final void removeAllIndices (IntArrayList indices)
    {
      int size = indices.size();

      for (int i=1; i<=size; i++)
        {
          int lo = indices.get(i-1);
          int hi = (i < size) ? indices.get(i) : elementCount;
          for (int j=lo+1; j<hi; j++) elementData[j-i] = elementData[j];
        }

      elementCount -= size;
    }
}
