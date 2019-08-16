//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
// PLEASE DO NOT EDIT WITHOUT THE EXPLICIT CONSENT OF THE AUTHOR! \\
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\

package hlt.language.util;

import java.util.Collection;
import java.util.Iterator;

/**
 * This class provides the facilities for a FIFO sequence of objects.
 * It implements the same operation as a stack; namely <tt>push</tt>,
 * <tt>pop</tt>, <tt>peek</tt>, and <tt>isEmpty</tt>. It also provides
 * <tt>enqueue</tt> as a synonym of <tt>push</tt>, and <tt>dequeue</tt>
 * as a synonym of <tt>pop</tt>. In addition, it defines the methods
 * <tt>rush</tt> and <tt>chop</tt> to place an element at the front of
 * the queue and remove (and return) the newest element in the queue
 * (respectively).
 *
 * @version     Last modified on Tue Jun 19 17:38:00 2012 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">Hassan A&iuml;t-Kaci</a>
 */

public class Queue
{
  private QueueElement oldest = null;
  private QueueElement newest = null;
  private int size = 0;

  /**
   * Constructs an empty queue.
   */
  public Queue ()
    {
    }

  /**
   * Constructs a queue containing the given object.
   */
  public Queue (Object object)
    {
      push(object);
    }

  /**
   * Constructs a queue containing the given array of objects
   * such that the lowest index is the oldest in the queue.
   */
  public Queue (Object[] objects)
    {
      for (int i=0; i<objects.length; i++)
        push(objects[i]);         
    }

  /**
   * Constructs a queue containing the given collection of objects.
   * No guarantee is provided as for the order of enqueuing.
   */
  public Queue (Collection objects)
    {
      for (Iterator i=objects.iterator(); i.hasNext();)
        push(i.next());   
    }

  /**
   * Returns <tt>true</tt> iff this queue is empty.
   */
  public final boolean isEmpty ()
    {
      return size == 0;
    }

  /**
   * Returns the current number of elements in this queue.
   */
  public final int size ()
    {
      return size;
    }

  /**
   * Enqueues the given object in this queue.
   */
  public final void enqueue (Object object)
    {
      push(object);
    }      

  /**
   * Enqueues the given object into this queue.
   */
  public final void push (Object object)
    {
      QueueElement elt = new QueueElement(object);
      elt.succ = newest;
      if (newest != null) newest.pred = elt;
      newest = elt;
      if (size == 0) oldest = newest;
      size++;
    }      

  /**
   * Places the given object at the head of this queue.
   */
  public final void rush (Object object)
    {
      QueueElement elt = new QueueElement(object);
      elt.pred = oldest;
      if (oldest != null) oldest.succ = elt;
      oldest = elt;
      if (size == 0) newest = oldest;
      size++;
    }      

  /**
   * Removes and returns the oldest object queued in this queue.
   */
  public final Object dequeue ()
    {
      return pop();
    }      

  /**
   * Removes and returns the oldest object queued in this queue.
   */
  public final Object pop ()
    {
      if (isEmpty())
        throw new EmptyQueueException();

      Object elt = oldest.element;
      oldest = oldest.pred;
      if (oldest != null) oldest.succ = null;
      size--;
      return elt;
    }

  /**
   * Removes and returns the <i>newest</i> object queued in this queue.
   */
  public final Object chop ()
    {
      if (isEmpty())
        throw new EmptyQueueException();

      Object elt = newest.element;
      newest = newest.succ;
      if (newest != null) newest.pred = null;
      size--;
      return elt;
    }

  /**
   * Returns an iterator for this queue in "popping" order; <i>i.e.</i>,
   * from the oldest to the newest elelemnt.   
   */
  public final Iterator iterator ()
    {
      return new QueueIterator(this);
    }

  /**
   * Returns the oldest object queued in this queue without removing it.
   */
  public final Object peek ()
    {
      if (isEmpty())
        throw new EmptyQueueException();

      return oldest.element;
    }

  /**
   * Returns this queue after reversing it <i>in situ</i>.
   */
  public final Queue reverse ()
    {
      QueueElement elt = newest;

      while (elt != null)
        {
          QueueElement next = elt.succ;
          elt.succ = elt.pred;
          elt.pred = next;
          elt = next;
        }

      elt = oldest;
      oldest = newest;
      newest = elt;

      return this;
    }

  /**
   * Returns a string representation of this queue. It is of the form:
   * <pre>&gt;&gt; newest ... oldest &gt;&gt;</pre>
   */
  public String toString ()
    {
      StringBuilder buff = new StringBuilder(">> ");
      QueueElement elt = newest;
      
      while (elt != null)
        {
          buff.append(elt.element);
          elt = elt.succ;
          if (elt != null) buff.append(", ");
        }
      buff.append(" >>");

      return buff.toString();
    }    

  /**
   * This is the class of queue elements.
   */
  private static class QueueElement
    {
      Object element;
      QueueElement succ;
      QueueElement pred;

      QueueElement (Object object)
        {
          element = object;
        }
    }

  /**
   * This provides an iterator for <tt>Queue</tt> objects.
   */

  private static class QueueIterator implements Iterator
    {
      private QueueElement current;

      QueueIterator (Queue queue)
        {
          if (!queue.isEmpty())
            current = queue.oldest;
        }

      public final boolean hasNext ()
        {
          return current != null;
        }

      public final Object next ()
        {
          if (current == null)
            return null;
          
          Object object = current.element;
          current = current.pred;
          return object;
        }

      /**
       * This method is not implemented - it just throws an
       * <tt>UnsupportedOperationException</tt>.
       */
      public final void remove ()
        {
          throw new UnsupportedOperationException();
        }
    }

}


