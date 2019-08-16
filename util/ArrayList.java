//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

import java.util.Iterator;
import java.util.Collection;
import java.util.AbstractList;

/**
 * This class reimplements <tt>java.util.ArrayList</tt> to avoid paying the
 * penalty of index range checks that are performed needlessly by that class
 * in spite of that being done in any case by the JVM on the underlying array.
 * As its original namesake, this class is <i>not</i> synchronized.
 *
 * @version     Last modified on Fri Aug 03 05:46:47 2018 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */
public class ArrayList extends AbstractList implements Collection
{
  // FIELDS:

  /**
   * The array buffer into which the components of the ArrayList are
   * stored. The capacity of the ArrayList is the length of this array
   * buffer, and is at least large enough to contain all the ArrayList's
   * elements.
   */
  protected Object[] _elementData;

  /**
   * The number of components in this ArrayList object, regardless of
   * <tt>null</tt> gaps. Components <tt>_elementData[0]</tt> through
   * <tt>_elementData[_size-1]</tt> are the actual items. Some of these
   * components may be <tt>null</tt> if <tt>add(null)</tt> or
   * <tt>set(i,value)</tt> with <tt>i</tt> greater than <tt>_size</tt>
   * were invoked.
   */
  protected int _size;

  /**
   * The amount by which the capacity of the ArrayList is automatically
   * incremented when its size becomes greater than its capacity. If the
   * capacity increment is less than or equal to zero, the capacity of
   * the ArrayList is multiplied by <tt>incrementFactor</tt> each time it
   * needs to grow. The default value of <tt>incrementFactor</tt> is
   * 2.0 (so growing Objects the capacity). It may be reset with
   * <tt>setIncrementFactor(float)</tt> to any decimal value between
   * 1.1 and 5.0.
   */
  protected int capacityIncrement;

  // CONSTRUCTORS:

  /**
   * Constructs an empty ArrayList with the specified initial capacity and
   * capacity increment.
   *
   * @param     initialCapacity the initial capacity of the ArrayList.
   * @param     capacityIncrement amount by which the capacity is increased on overflow.
   * @exception NegativeArraySizeException if the specified initial capacity is negative.
   */
  public ArrayList (int initialCapacity, int capacityIncrement)
    {
      this(initialCapacity);
      this.capacityIncrement = capacityIncrement;
    }

  /**
   * Constructs an empty ArrayList with the specified initial capacity and
   * with its capacity increment equal to zero.
   *
   * @param     initialCapacity the initial capacity of the ArrayList.
   * @exception NegativeArraySizeException if the specified initial capacity is negative.
   */
  public ArrayList (int initialCapacity)
    {
      _elementData = new Object[initialCapacity];
    }

  /**
   * Constructs an empty ArrayList so that its internal data array has size
   * 10 and its standard capacity increment is zero.
   */
  public ArrayList ()
    {
      this(10);
    }

  /**
   * Constructs an ArrayList containing the objects contained in the
   * specified array, in the same order.
   *
   * @param     a an array of Objects.
   */
  public ArrayList (Object[] a)
    {
      _elementData = new Object[a.length];

      for (int i=0; i<a.length; i++)
        _elementData[_size++] = a[i];
    }

  /**
   * Constructs an ArrayList containing the objects in the specified collection,
   * in the order they are returned by the collection's iterator.
   *
   * @param     c the collection whose elements are to be placed into this ArrayList.
   */
  public ArrayList (Collection c)
    {
      _elementData = new Object[c.size()];

      for (Iterator i=c.iterator(); i.hasNext();)
        _elementData[_size++] = i.next();
    }

  // METHODS:

  /**
   * Copies the components of this ArrayList into the specified array
   * <tt>a</tt>. The item at index <tt>k</tt> in this ArrayList is
   * copied into component <tt>k</tt> of <tt>a</tt>. The array must be
   * big enough to hold all the elements of this ArrayList, else an
   * <tt>IndexOutOfBoundsException</tt> is thrown.
   *
   * @param     a the array into which the components get copied.
   * @exception IndexOutOfBoundsException if the array is too small.
   */
  public final void copyInto (Object[] a)
    {
      for (int i=0; i<_size; i++)
        a[i] = _elementData[i];
    }

  /**
   * Trims the capacity of this ArrayList to be the ArrayList's current
   * size. If the capacity of this ArrayList is larger than its current
   * size, then the capacity is changed to equal the size by replacing
   * its internal data array, kept in the field _elementData, with a
   * smaller one. An application can use this operation to minimize the
   * storage of an ArrayList.
   */
  public final void trimToSize ()
    {
      if (_size == _elementData.length)
	return;

      Object[] newArray = newArray = new Object[_size];

      for (int i=0; i<_size; i++)
        newArray[i] = _elementData[i];

      _elementData = newArray;
    }

  /**
   * Increases the capacity of this ArrayList, if necessary, to ensure that
   * it can hold at least the number of components specified by the
   * minimum capacity argument.  <p> If the current capacity of this
   * ArrayList is less than minCapacity, then its capacity is increased by
   * replacing its internal data array, kept in the field _elementData,
   * with a larger one. The size of the new data array will be the old
   * size plus capacityIncrement, unless the value of capacityIncrement
   * is less than or equal to zero, in which case the new capacity will
   * be the old capacity multiplied by incrementFactor; but if this new
   * size is still smaller than minCapacity, then the new capacity will
   * be minCapacity.
   *
   * @param     minCapacity the desired minimum capacity.
   */
  public final void ensureCapacity (int minCapacity)
    {
      if (_elementData.length >= minCapacity)
	return;

      Object[] newArray = new Object[Math.max(minCapacity,_increasedCapacity())];

      for (int i=0; i<_elementData.length; i++)
	newArray[i] = _elementData[i];

      _elementData = newArray;
    }

  /**
   * Sets the size of this ArrayList. If the new size is greater than the
   * current size, new nulls are added to the end of the ArrayList. If
   * the new size is less than the current size, all components at index
   * newSize and greater are set to null.
   *
   * @param     newSize the new size of this ArrayList.
   * @exception ArrayIndexOutOfBoundsException if new size is negative.
   */
  public final void setSize (int newSize)
    {
      ensureCapacity(newSize);

      for (;_size>newSize;)
        _elementData[--_size] = null;
    }

  /**
   * Returns the current capacity of this ArrayList.
   *
   * @return    the current capacity the length of its internal data array.
   */
  public final int capacity ()
    {
      return _elementData.length;
    }

  /**
   * Returns the number of components in this ArrayList. Note that if
   * <tt>null</tt> exists (by adding <tt>null</tt>s or setting higher
   * positions to <tt>null</tt>), this will return the index just above
   * that of the highest non-<tt>null</tt> element.
   *
   * @return    the number of components in this ArrayList.
   */
  public final int size ()
    {
      return _size;
    }

  /**
   * @return <tt>true</tt> iff there are <tt>null</tt> elements between
   * <tt>0</tt> and <tt>size()-1</tt>.
   */
  public final boolean hasGaps ()
    {
      for (int i=_size; i-->0;)
	if (_elementData[i] == null)
	  return true;

      return false;
    }

  /**
   * Eliminates <tt>null</tt> gaps from this ArrayList, compressing it
   * down so that all elements between <tt>0</tt> and <tt>size()-1</tt>
   * are non-<tt>null</tt>.
   */
  public final void removeGaps ()
    {
      Object[] newArray = new Object[_elementData.length];

      int newSize = 0;
      
      for (int i=0; i<_size; i++)
	if (_elementData[i] != null)
	  newArray[newSize++] = _elementData[i];

      _elementData = newArray;
      _size = newSize;
    }

  /**
   * Tests if this ArrayList has no components.
   *
   * @return    true if this ArrayList's size is zero; false otherwise.
   */
  public final boolean isEmpty ()
    {
      return _size == 0;
    }

  /**
   * Tests if the specified <tt>Object</tt> is a component in this
   * <tt>ArrayList</tt>.
   *
   * @param element an <tt>Object</tt>.
   * @return <tt>true</tt> if the specified <tt>Object</tt> is in this <tt>ArrayList</tt>; <tt>false</tt> otherwise.
   */
  public final boolean contains (Object element)
    {
      for (int i=_size; i-->0;)
        if (_elementData[i].equals(element))
	  return true;

      return false;
    }

  /**
   * Searches for the first occurrence of the given Object; returns -1 if it
   * is not found.
   *
   * @param     element a Object.
   * @return    the index of the first occurrence of the argument or -1.
   */
  public final int indexOf (Object element)
    {
      for (int i=0; i<_size; i++)
        if (_elementData[i].equals(element))
	  return i;

      return -1;
    }

  /**
   * Searches for the first occurence of the given Object, beginning
   * the search at index; returns -1 if it is not found.
   *
   * @param     element a Object.
   * @param     index the non-negative index to start searching from.
   * @return   the 1st occurrence of the specified object after the specified index; or -1 if there is none
   * @exception IndexOutOfBoundsException if index is negative.
   */
  public final int indexOf (Object element, int index)
    {
      for (int i=index; i<_size; i++)
        if (_elementData[i].equals(element))
	  return i;

      return -1;
    }

  /**
   * Returns the index of the last occurrence of the specified Object in
   * this ArrayList; returns -1 if it is not found.
   *
   * @param     element the desired component.
   * @return    the index of the last occurrence of the specified Object, or -1.
   */
  public final int lastIndexOf (Object element)
    {
      for (int i=_size; i-->0;)
        if (_elementData[i].equals(element))
	  return i;

      return -1;
    }

  /**
   * Searches backwards for the specified Object, starting from the
   * specified index, and returns its index, or -1 if not found.
   *
   * @param     element a Object.
   * @param     index the non-negative index to start searching from.
   * @return    the last occurrence of the specified object before the specified index; or -1 if there is none
   * @exception IndexOutOfBoundsException if index is invalid.
   */
  public final int lastIndexOf (Object element, int index)
    {
      for (int i=index; i>=0; i--)
        if (_elementData[i].equals(element)) return i;

      return -1;
    }

  /**
   * Returns the component at the specified index. This method is
   * identical in functionality to the get method.
   *
   * @param     index an index into this ArrayList.
   * @return    the component at the specified index.
   * @exception ArrayIndexOutOfBoundsException if the index is invalid.
   */
  public final Object elementAt (int index)
    {
      return _elementData[index];
    }

  /**
   * Returns the first component (the item at index 0) of this ArrayList.
   *
   * @return    the first component of this ArrayList.
   * @exception ArrayIndexOutOfBoundsException if this ArrayList is empty.
   */
  public final Object firstElement ()
    {
      return _elementData[0];
    }

  /**
   * Returns the last component of the ArrayList.
   *
   * @return    the last component of the ArrayList, i.e., the component at size() - 1.
   * @exception ArrayIndexOutOfBoundsException if this ArrayList is empty.
   */
  public final Object lastElement ()
    {
      return _elementData[_size-1];
    }

  /**
   * Sets the component at the specified index of this ArrayList to be the
   * specified Object. The previous component at that position is lost.
   * <p>
   * The index must be a value greater than or equal to 0 and less than
   * the current size of the ArrayList.
   * <p>
   * This method is identical in functionality to the set method except
   * that the latter returns the old object that was stored at the specified
   * position and it has its argument in a different order.
   *
   * @param     element what the component is to be set to.
   * @param     index the specified index.
   * @exception ArrayIndexOutOfBoundsException if the index was invalid.
   */
  public final void setElementAt (Object element, int index)
    {
      set(index,element);
    }

  /**
   * Deletes the component at the specified index. Each component in
   * this ArrayList with an index greater or equal to the specified index
   * is shifted downward to have an index one smaller than the value it
   * had previously. The size of this ArrayList is decreased by 1.
   * <p>
   * The index must be a value greater than or equal to 0 and less than
   * the current size of the ArrayList.
   * <p>
   * This method is identical in functionality to the remove(int) method
   * except that the remove(int) method returns the old value that was
   * stored at the specified position.
   *
   * @param     index the index of the Object to remove.
   * @exception ArrayIndexOutOfBoundsException if the index was invalid.
   */
  public final void removeElementAt (int index)
    {
      remove(index);
    }

  /**
   * Inserts the specified Object as a component in this ArrayList at the
   * specified index. Each component in this ArrayList with an index
   * greater or equal to the specified index is shifted upward to have
   * an index one greater than the value it had previously.
   * <p>
   * The index must be a value greater than or equal to 0 and less than
   * or equal to the current size of the ArrayList. (If the index is equal
   * to the current size of the ArrayList, the new element is appended to
   * the ArrayList.)
   * <p>
   * This method is identical in functionality to the add(int, Object)
   * method except that the latter method reverses the order of the
   * arguments, to match array usage  more closely.
   *
   * @param     element the element to insert.
   * @param     index where to insert the new component.
   * @exception ArrayIndexOutOfBoundsException if the index was invalid.
   */
  public final void insertElementAt (Object element, int index)
    {
      add(index,element);
    }

  /**
   * Adds the specified component to the end of this ArrayList, increasing
   * its size by one. The capacity of this ArrayList is increased if its
   * size becomes greater than its capacity.
   * <p>
   * This method is identical in functionality to the add(Object) method
   * except that the latter returns a boolean.
   *
   * @param     element the component to be added.
   */
  public final void addElement (Object element)
    {
      add(element);
    }

  /**
   * Removes all components from this ArrayList.
   * This method is identical in functionality to the clear method.
   *
   */
  public final void removeAllElements ()
    {
      _size = 0;
    }

  /**
   * Returns a clone of this ArrayList. The copy will contain a reference
   * to a clone of the internal data array, not a reference to the
   * original internal data array of this ArrayList object.
   *
   * @return    a clone of this ArrayList.
   */
  public final Object clone ()
    {
      Object[] newArray = new Object[_elementData.length];

      for (int i=0; i<_elementData.length; i++)
        newArray[i] = _elementData[i];

      return new ArrayList(newArray,_size,capacityIncrement,incrementFactor);
    }

  /**
   * Returns an array containing all of the elements in this ArrayList in
   * the correct order.
   */
  public final Object[] toArray ()
    {
      Object[] a = new Object[_size];

      for (int i=_size; i-->0;)
        a[i] = _elementData[i];

      return a;
    }

  /**
   * @return an array containing all of the string forms of the elements
   * in this ArrayList in the correct order.
   */
  public final String[] toStringArray ()
    {
      String[] a = new String[_size];

      for (int i=_size; i-->0;)
        a[i] = _elementData[i].toString();

      return a;
    }

  /**
   * Returns the element at the specified position in this ArrayList.
   *
   * @exception ArrayIndexOutOfBoundsException if the index is invalid.
   */
  public final Object get (int index)
    {
      return _elementData[index];
    }

  /**
   * Replaces the element at the specified position in this ArrayList
   * with the specified element. If the position is out of range of the
   * current element array, an <tt>ArrayIndexOutOfBoundsException</tt>
   * is thrown. To avoid this, one should call <tt>ensureCapacity</tt>
   * before calling this method. Note that, if the specified index is
   * still in range but higher than the current size, this will update
   * the current size. This means that this may introduce gaps of
   * <tt>null</tt> elements.
   *
   * @param     index index of element to replace.
   * @param     element element to be stored at the specified position.
   * @return    the element previously at the specified position.
   * @exception ArrayIndexOutOfBoundsException if index is out of range.
   */
  public final Object set (int index, Object element)
    {
      Object old = _elementData[index];
      _elementData[index] = element;
      _size = Math.max(_size,index+1);
      return old;
    }

  /**
   * Replaces the element at the specified position in this ArrayList
   * with the specified element. This ensures that if the position is
   * out of range of the current element array, the capacity will be
   * automatically increased to accommodate. Note that, if the specified
   * index is still in range but higher than the current size, this will
   * update the current size. This means that this may introduce gaps of
   * <tt>null</tt> elements.
   *
   * @param     index index of element to replace.
   * @param     element element to be stored at the specified position.
   * @return    the element previously at the specified position.
   */
  public final Object secureSet (int index, Object element)
    {
      ensureCapacity(index+1);
      Object old = _elementData[index];
      _elementData[index] = element;
      _size = Math.max(_size,index+1);
      return old;
    }

  /**
   * Replaces the element at the last position in this ArrayList with
   * the specified element, returning the element that was there, or
   * <tt>null</tt> if that position was empty.
   *
   * @param     element element to be stored at the last position.
   * @return    the element previously at the last position.
   */
  public final Object setLast (Object element)
    {
      int last = _size-1;
      Object old = _elementData[last];
      _elementData[last] = element;
      return old;
    }

  /**
   * Appends the specified element to the end of this ArrayList, growing
   * the array if necessary. Note that, of the specified object is
   * <tt>null</tt>, the element count is increased nonetheless.
   *
   * @param     element object to be appended to this ArrayList.
   * @return    true (following the <tt>java.util.ArrayList</tt> API).
   */
  public final boolean add (Object element)
    {
      if (_size == _elementData.length)
	_grow();

      _elementData[_size++] = element;

      return true;
    }

  /**
   * Appends the specified element to the end of this ArrayList, growing
   * the array if necessary. Note that, if the specified object is
   * <tt>null</tt>, the element count is increased nonetheless. This is
   * identical to <tt>add(Object)</tt> but returns this ArrayList
   * instead of a boolean.  This is convenient to compose several adds
   * in a row; <i>e.g.</i>, <tt>alist .include("a") .include("b")
   * .include("c") .include("d")</tt>.
   *
   * @param     element object to be appended to this ArrayList.
   * @return    this
   */
  public final ArrayList include (Object element)
    {
      if (_size == _elementData.length)
	_grow();

      _elementData[_size++] = element;

      return this;
    }

  /**
   * Removes the first occurrence of the specified element. If this ArrayList
   * does not contain the element, it is left unchanged.
   * <p>
   * <b>N.B.</b>Because <tt>remove(Object)</tt> is ambiguous, it is not
   * supported for ArrayLists; its functionality is provided by
   * <tt>removeIndex(int)</tt> and <tt>removeElement(Object)</tt>.
   *
   * @param     element object to be removed from this ArrayList, if present.
   * @return    true if this ArrayList contained the specified element.
   */
  public final boolean remove (Object element)
    {
      int i = 0;

      for (i=0; i<_size; i++)
        if (_elementData[i].equals(element))
	  break;

      if (i == _size)
	return false;

      for (;i<_size-1;i++)
        _elementData[i] = _elementData[i+1];

      _size--;

      return true;
    }

  /**
   * Removes the first occurrence of the specified element. If this ArrayList
   * does not contain the element, it is left unchanged. Note that this method is
   * identical to remove(Object).
   *
   * @param     element element to be removed from this ArrayList, if present.
   * @return    true if this ArrayList contained the specified element.
   */
  public final boolean removeElement (Object element)
    {
      return remove(element);
    }

  /**
   * Inserts the specified element at the specified position in this
   * ArrayList. Shifts the element currently at that position (if any) and
   * any subsequent elements to the right (adds one to their indices).
   *
   * @param     index position at which the specified element is to be inserted.
   * @param     element object to be inserted.
   * @exception ArrayIndexOutOfBoundsException if index is out of range.
   */
  public final void add (int index, Object element)
    {
      ensureCapacity(_size++);

      for (int i=_size-1; i>index; i--)
        _elementData[i] = _elementData[i-1];

      _elementData[index] = element;
    }

  /**
   * Removes the element at the specified position and shifts any subsequent
   * elements to the left (subtracts one from their indices). Returns the
   * element that was removed.
   *
   * @param     index the index of the element to removed.
   * @return    the element that was at this index position.
   * @exception ArrayIndexOutOfBoundsException if index is out of range.
   */
  public final Object remove (int index)
    {
      Object old = _elementData[index];

      for (int i=index; i<_size-1; i++)
        _elementData[i] = _elementData[i+1];

      _size--;

      return old;
    }

  /**
   * Removes all of the elements from this ArrayList.
   */
  public final void clear ()
    {
      _size = 0;
    }

  /**
   * Returns true if this ArrayList contains all of the elements in the
   * specified array.
   *
   * @param     a array containing the elements to be tested for membership.
   * @return    true if all of the elements of the specified array belong in this.
   */
  public final boolean containsAll (Object[] a)
    {
      for (int i=a.length; i-->0;)
        if (!contains(a[i])) return false;

      return true;
    }

  /**
   * Returns true if this ArrayList contains all of the elements in the
   * specified ArrayList.
   *
   * @param     v ArrayList of elements to be tested for membership.
   * @return    true if all the elements of the specified collection belong to this.
   */
  public final boolean containsAll (ArrayList v)
    {
      for (int i=v.size(); i-->0;)
        if (!contains(v.get(i))) return false;

      return true;
    }

  /**
   * Appends all of the elements in the specified array to the end
   * of this ArrayList, in the order of the array.
   *
   * @param     a array containing the elements to be inserted.
   * @exception ClassCastException if the collection contains a non-number.
   * @return    true (following the <tt>java.util.ArrayList</tt> API).
   */
  public final boolean addAll (Object[] a)
    {
      ensureCapacity(_size+a.length);

      for (int i=0; i<a.length; i++)
	_elementData[_size++] = a[i];

      return true;
    }

  /**
   * Appends all of the elements in the specified ArrayList to the end
   * of this ArrayList.
   *
   * @param     v the ArrayList of elements to be added.
   * @return    true (following the <tt>java.util.ArrayList</tt> API).
   */
  public final boolean addAll (ArrayList v)
    {
      int size = v.size();

      ensureCapacity(_size+size);

      for (int i=0; i<size; i++)
	add(v.get(i));

      return true;
    }

  /**
   * Removes from this ArrayList all of its elements that are contained
   * in the specified array. Note that all occurrences of such elemeents
   * are removed - not just the first.
   *
   * @param     a array of elements to be removed.
   * @return    true if this ArrayList changed as a result of the call.
   */
  public final boolean removeAll (Object[] a)
    {
      IntArrayList indices = new IntArrayList();

      for (int i=0; i<a.length; i++)
        for (int j=0; j<_size; j++)
          if (a[i].equals(_elementData[j]))
	    indices.add(j);

      removeAllIndices(indices);

      return !indices.isEmpty();
    }

  /**
   * Removes from this ArrayList all of its elements that are contained
   * in the specified ArrayList. Note that all occurrences of such elemeents
   * are removed - not just the first.
   *
   * @param     v the ArrayList of elements to be removed.
   * @return    true if this ArrayList changed as a result of the call.
   */
  public final boolean removeAll (ArrayList v)
    {
      IntArrayList indices = new IntArrayList();

      for (int i=v.size(); i-->0;)
        for (int j=_size; j-->0;)
          if (v.get(i).equals(_elementData[j]))
	    indices.add(j);

      removeAllIndices(indices);

      return !indices.isEmpty();
    }

  /**
   * Removes from this ArrayList the first occurrence of the elements
   * that are contained in the specified array
   *
   * @param     a array of elements to be removed.
   * @return    true if this ArrayList changed as a result of the call.
   */
  public final boolean removeAllFirst (Object[] a)
    {
      IntArrayList indices = new IntArrayList();

      for (int i=0; i<a.length; i++)
        for (int j=0; j<_size; j++)
          if (a[i].equals(_elementData[j]))
            {
              indices.add(j);
              break;
            }

      removeAllIndices(indices);
      return !indices.isEmpty();
    }

  /**
   * Removes from this ArrayList the first occurrence of the elements
   * that are contained in the specified ArrayList.
   *
   * @param     v the ArrayList of elements to be removed.
   * @return    true if this ArrayList changed as a result of the call.
   */
  public final boolean removeAllFirst (ArrayList v)
    {
      IntArrayList indices = new IntArrayList();

      int size = v.size();

      for (int i=0; i<size; i++)
        for (int j=0; j<_size; j++)
          if (v.get(i).equals(_elementData[j]))
            {
              indices.add(j);
              break;
            }

      removeAllIndices(indices);

      return !indices.isEmpty();
    }

  /**
   * Retains only the elements in this ArrayList whose elements are
   * contained in the specified ArrayList. In other words, removes from
   * this ArrayList all of its elements that are not contained in the
   * specified ArrayList.
   *
   * @param     v the ArrayList of elements to be retained.
   * @return    true if this ArrayList changed as a result of the call.
   */
  public final boolean retainAll (ArrayList v)
    {
      IntArrayList indices = new IntArrayList();

      for (int i=0; i<_size; i++)
        if (!v.contains(_elementData[i]))
          indices.add(i);

      removeAllIndices(indices);

      return !indices.isEmpty();
    }

  /**
   * Inserts all the elements in the specified array at the specified
   * index in this ArrayList. Shifts the element currently at that
   * position (if any) and any subsequent elements to the right (adds
   * the length of the array to their indices).
   *
   * @param     index index at which the specified element is to be inserted.
   * @param     a arrays of element to be inserted.
   * @exception ArrayIndexOutOfBoundsException if index is out of range.
   */
  public final void addAll (int index, Object[] a)
    {
      if (_size+a.length < _elementData.length)
        {
          for (int i=index; i<_size; i++)
            _elementData[i+a.length] = _elementData[i];

          for (int i=0; i<a.length; i++)
            _elementData[index+i] = a[i];

          _size += a.length;

          return;
        }

      Object[] newArray = new Object[_increasedCapacity(_size+a.length)];

      for (int i=0; i<index; i++)
        newArray[i] = _elementData[i];

      for (int i=0; i<a.length; i++)
        newArray[index+i] = a[i];

      for (int i=index+a.length; i<_size+a.length; i++)
        newArray[i] = _elementData[i-a.length];

      _elementData = newArray;
      _size += a.length;
    }

  /**
   * Inserts all of the elements in the specified ArrayList into this
   * ArrayList at the specified position. Shifts the element currently
   * at that position (if any) and any subsequent elements to the right
   * (increases their indices). The new elements will appear in this
   * ArrayList in their order in the specified ArrayList.
   *
   * @param     index index where to insert the first element
   * @param     v ArrayList to be inserted into this ArrayList.
   * @exception ArrayIndexOutOfBoundsException if index is invalid.
   */
  public final void addAll (int index, ArrayList v)
    {
      int size = v.size();

      if (_size+size < _elementData.length)
        {
          for (int i=index; i<_size; i++)
            _elementData[i+size] = _elementData[i];

          for (int i=0; i<size; i++)
            _elementData[index+i] = v.get(i);

          _size += size;
          return;
        }

      Object[] newArray = new Object[_increasedCapacity(_size+size)];

      for (int i=0; i<index; i++)
        newArray[i] = _elementData[i];

      for (int i=0; i<size; i++)
        newArray[index+i] = v.get(i);

      for (int i=index+size; i<_size+size; i++)
        newArray[i] = _elementData[i-size];

      _elementData = newArray;
      _size += size;
    }

  /**
   * Compares the specified object with this ArrayList for equality.
   * Returns true if and only if the specified object is also an
   * ArrayList, both have the same size, and all corresponding pairs of
   * elements are equal. In other words, two ArrayLists are defined to
   * be equal if all their elements are equal in the same order.
   *
   * @param     o the object to be compared for equality with this ArrayList.
   * @return    true if the specified object is equal to this ArrayList
   */
  public final boolean equals (Object o)
    {
      if (o == this)
	return true;
      
      if (!(o instanceof ArrayList))
	return false;

      ArrayList other = (ArrayList)o;

      if (other.size() != _size)
	return false;

      for (int i=0; i<_size; i++)
        if (!other.get(i).equals(_elementData[i]))
	  return false;

      return true;
    }

  /**
   * Returns the hash code value for this ArrayList.
   */
  public int hashCode ()
    {
      long code = _size;
      int shift = 1;

      for (int i=0; i<_size; i += Math.max(1,_size/4))
        {
          if (shift > 0)
            code <<= 1;
          else
            code >>= 1;

          code ^= _elementData[i].hashCode();
          shift = -shift;
        }

      return (int)code;
    }

  /**
   * Returns a string representation of this ArrayList
   *
   * @return    a string representation of this collection.
   */
  public String toString ()
    {
      StringBuilder buf = new StringBuilder("[");

      for (Iterator i=iterator(); i.hasNext();)
	{
	  buf.append(i.next());
	  if (i.hasNext())
	    buf.append(", ");
	}

      return (buf.append("]")).toString();
    }

  /**
   * Removes from this ArrayList all of the elements whose index is
   * between fromIndex, inclusive and toIndex, exclusive. Shifts any
   * succeeding elements to the left (reduces their index). This call
   * shortens the ArrayList by (toIndex &ndash; fromIndex) elements. (If
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

      toIndex = Math.min(toIndex, _size);

      int gap = toIndex - fromIndex;

      for (int i=fromIndex; i<toIndex; i++)
        _elementData[i] = _elementData[i+gap];

      _size -= gap;
    }

  // NEW API:

  protected float incrementFactor = 2.0f;

  /**
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
   * Clears all elements of this arraylist at index equal to or greater
   * than the specified index. If this index value is out range, this
   * clears all the arraylist.
   *
   * @param	index the index above which clearing takes effect
   */
    public final void clear (int index)
    {
      _size = (index < 0 || index >= _size) ? 0 : index;
    }

  // PRIVATES:

  /**
   * A constructor used privately for cloning only...
   */
  private ArrayList (Object[] _elementData, int _size,
                     int capacityIncrement, float incrementFactor)
    {
      this._elementData = _elementData;
      this._size = _size;
      this.capacityIncrement = capacityIncrement;
      this.incrementFactor = incrementFactor;
    }

  /**
   * Grows the size of the internal array.
   */
  private final void _grow ()
    {
      Object[] newArray = new Object[_increasedCapacity()];

      for (int i=0; i<_size; i++)
        newArray[i] = _elementData[i];

      _elementData = newArray;
    }

  /**
   * Computes a greater capacity for this ArrayList.
   *
   * @return    the increased capacity
   */
  private final int _increasedCapacity ()
    {
      if (capacityIncrement <= 0)
        {
          int capacity = _elementData.length == 0 ? 1 : _elementData.length;
          return (int)Math.floor(capacity*incrementFactor+1);
        }

      return _elementData.length+capacityIncrement;
    }

  /**
   * Computes a greater capacity for this ArrayList that is at least
   * the specified capacity.
   *
   * @param     minCapacity the minimum necessary capacity.
   * @return    the increased capacity
   */
  private final int _increasedCapacity (int minCapacity)
    {
      if (capacityIncrement <= 0)
        {
          int capacity = _elementData.length == 0 ? 1 : _elementData.length;
          return Math.max(minCapacity,
                          (int)Math.floor(capacity*incrementFactor));
        }

      return Math.max(minCapacity,_elementData.length+capacityIncrement);
    }

  /**
   * Removes from this ArrayList all of its elements that are located at
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
          int hi = (i < size) ? indices.get(i) : _size;
          for (int j=lo+1; j<hi; j++) _elementData[j-i] = _elementData[j];
        }

      _size -= size;
    }

  public Iterator iterator ()
    {
      return new ArrayListIterator(_elementData,_size);
    }

  private static class ArrayListIterator implements Iterator
    {
      private Object[] _data;
      private int _count;
      private int _current;

      ArrayListIterator (Object[] data, int count)
        {
          _data = data;
          _count = count;
        }

      public final boolean hasNext ()
        {
          return _current < _count;
        }

      public final Object next ()
        {
          return _data[_current++];
        }
      
      public final void remove ()
        {
          throw new UnsupportedOperationException();
        }      
    }
}
