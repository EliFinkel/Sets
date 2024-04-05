/*  Student information for assignment:
 *
 *  On our honor, Gabriel Aguilar and Eli Finkel, 
 *  this programming assignment is our own work
 *  and we have not provided this code to any other student.
 *
 *  Number of slip days used: 0
 *
 *  Student 1 (Student whose Canvas account is being used)
 *  UTEID: gaa2292
 *  email address: gaa2292@utexas.edu
 *  TA name: Gracelynn Ray
 *  
 *  Student 2 
 *  UTEID: egf525
 *  email address: eligfinkel@utexas.edu
 */

import java.util.Iterator;
import java.util.ArrayList;

 /**
  * A simple implementation of an ISet. 
  * Elements are not in any particular order.
  * Students are to implement methods that 
  * were not implemented in AbstractSet and override
  * methods that can be done more efficiently. 
  * An ArrayList must be used as the internal storage container.
  *
  */
public class UnsortedSet<E> extends AbstractSet<E> {

    private ArrayList<E> myCon;

    /**
     * create an empty UnsortedSet
     */
    // O(1)
    public UnsortedSet() {
        myCon = new ArrayList<>();
    }

    /**
     * Create a copy of other that is unsorted.<br>
     * 
     * @param other != null
     */
    // O(N)
    public UnsortedSet(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("Illegal argument: " +
                                                "otherSet cannot be null.");
        }

        // Init. myCon
        myCon = new ArrayList<>();
        Iterator<E> it = otherSet.iterator();
        // Loop through other
        while (it.hasNext()) {
            // Add each element of other to this set
            this.add(it.next());
        }
    }

    /**
     * Add an item to this set.
     * <br>
     * item != null
     * 
     * @param item the item to be added to this set. item may not equal null.
     * @return true if this set changed as a result of this operation,
     *         false otherwise.
     */
    // O(N)
    @Override
    public boolean add(E item) {
        if (item == null) {
            throw new IllegalArgumentException("Illegal Argument: " +
                    "item cannot be null");
        }
        
        if(!contains(item)) {
            // Add item if not a duplicate to this set and return true
            myCon.add(item);
            return true;
        } 

        return false;
    }

    /**
     * Make this set empty.
     * <br>
     * pre: none
     * <br>
     * post: size() = 0
     */
    // O(1)
    @Override
    public void clear() {

        myCon.clear();
    }

    /**
     * Create a new set that is the union of this set and otherSet.
     * <br>
     * pre: otherSet != null
     * <br>
     * post: returns a set that is the union of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * 
     * @param otherSet != null
     * @return a set that is the union of this set and otherSet
     */
    @Override
    // O(N^2)
    public ISet<E> union(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("Illegal argument: " +
                                                "otherSet cannot be null.");
        }

        // Create a new unsorted set that is a copy of this set
        ISet<E> result = new UnsortedSet<>(this);

        Iterator<E> it = otherSet.iterator();
        // Iterate through otherSet
        while (it.hasNext()) {
            // Add each element of other to the new set if not already present
            E element = it.next();
            if (!this.contains(element)) {
                result.add(element);
            }
        }

        return result;
    }

    /**
     * Create a new set that is the difference of this set and otherSet.
     * Return an ISet of elements that are in this Set but not in otherSet.
     * Also called the relative complement.
     * <br>
     * Example: If ISet A contains [X, Y, Z] and ISet B contains [W, Z]
     * then A.difference(B) would return an ISet with elements [X, Y] while
     * B.difference(A) would return an ISet with elements [W].
     * <br>
     * pre: otherSet != null
     * <br>
     * post: returns a set that is the difference of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br>
     * pre: otherSet != null
     * 
     * @param otherSet != null
     * @return a set that is the difference of this set and otherSet
     */
    // O(N^2)
    @Override
    public ISet<E> difference(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("Illegal argument: " +
                                                "otherSet cannot be null.");
        }

        ISet<E> result = new UnsortedSet<>();

        Iterator<E> it = iterator();
        // Iterate through this set
        while(it.hasNext()) {
            E element = it.next();
            // If this element is in this set but not in otherSet add it to new set
            if(!otherSet.contains(element)) {
                result.add(element);
            }
        }

        return result;
    }

     /**
     * Return the number of elements of this set.
     * pre: none
     * 
     * @return the number of items in this set
     */
    // O(1)
    @Override
    public int size() {
        return myCon.size();
    }

    /**
     * Return an Iterator object for the elements of this set.
     * pre: none
     * 
     * @return an Iterator object for the elements of this set
     */
    // O(1)
    @Override
    public Iterator<E> iterator() {
        return myCon.iterator();
    }

}