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
 * In this implementation of the ISet interface the elements in the Set are
 * maintained in ascending order.
 *
 * The data type for E must be a type that implements Comparable.
 *
 * Implement methods that were not implemented in AbstractSet
 * and override methods that can be done more efficiently. An ArrayList must
 * be used as the internal storage container. For methods involving two set,
 * if that method can be done more efficiently if the other set is also a
 * SortedSet, then do so.
 */
public class SortedSet<E extends Comparable<? super E>> extends AbstractSet<E> {

    private ArrayList<E> myCon;

    /**
     * create an empty SortedSet
     */
    // O(1)
    public SortedSet() {
        myCon = new ArrayList<>();
    }

    /**
     * Create a copy of other that is sorted.<br>
     * 
     * @param other != null
     */
    // O(NLogN)
    public SortedSet(ISet<E> other) {
        if (other == null) {
            throw new IllegalArgumentException("Illegal Argument: " +
                    "other cannot be null");
        }

        // Init. myCon
        myCon = new ArrayList<>();
        Iterator<E> otherIt = other.iterator();
        // Loop through other
        while (otherIt.hasNext()) {
            // Add each element of other to this set
            E element = otherIt.next();
            this.add(element);
        }
    }

    /**
     * Return the smallest element in this SortedSet.
     * <br>
     * pre: size() != 0
     * 
     * @return the smallest element in this SortedSet.
     */
    // O(1)
    public E min() {
        if (size() == 0) {
            throw new IllegalArgumentException("Illegal Argument: " +
                    "size is 0");
        }

        // Return the first element in the sorted set
        return myCon.get(0);
    }

    /**
     * Return the largest element in this SortedSet.
     * <br>
     * pre: size() != 0
     * 
     * @return the largest element in this SortedSet.
     */
    // O(1)
    public E max() {
        if (size() == 0) {
            throw new IllegalArgumentException("Illegal Argument: " +
                    "size is 0");
        }

        // Return the last element in the sorted set
        return myCon.get(size() - 1);
    }

    /**
     * Computes the union of this set with another set.
     *
     * @param otherSet The other set to compute the union with.
     * @return The union of this set with the other set.
     */
    // O(Log N)
    public int binarySearch(ArrayList<E> data, E target) {
        if (data == null || target == null) {
            throw new IllegalArgumentException("Illegal Argument: " +
                                                "Data nor Target can be null.");
        }

        int left = 0;
        int right = data.size() - 1;
        while (left <= right) {
            // Find the middle index and compare our target to the middle value
            int middle = left + (right - left) / 2;
            int compareResult = target.compareTo(data.get(middle));
            if (compareResult == 0) {
                // Target found
                return middle;
            } else if (compareResult < 0) {
                // Move left for the next middle calculation
                right = middle - 1;
            } else {
                // Move right for the next middle calculation
                left = middle + 1;
            }
        }

        // Return the correct insertion point for targer
        return left;
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

        // Find the point to insert item
        int insertionPoint = binarySearch(myCon, item);

        // Check that item does not exist in the set
        boolean isDuplicate = (insertionPoint < myCon.size() &&
                                                item.equals(myCon.get(insertionPoint)));
        if (!isDuplicate) {
            // Add item if not a duplicate to this set and return true
            myCon.add(insertionPoint, item);
            return true;
        }

        return false;
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
            throw new IllegalArgumentException("Illegal Argument: " +
                    "otherSet cannot be null");
        }

        ISet<E> diffSet = new SortedSet<>();

        Iterator<E> it = iterator();
        // Iterate through this set
        while (it.hasNext()) {
            E element = it.next();
            // If this element is in this set but not in otherSet add it to diffSet
            if (!otherSet.contains(element)) {
                diffSet.add(element);
            }
        }

        return diffSet;
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
     * Make this set empty.
     * <br>
     * pre: none
     * <br>
     * post: size() = 0
     */
    // O(1)
    @Override
    public void clear() {
        // Clear the internal ArrayList
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
            throw new IllegalArgumentException("Illegal Argument: " +
                    "otherSet cannot be null");
        }

        // Create a new sorted set that is a copy of this set
        ISet<E> unionSet = new SortedSet<>(this);

        Iterator<E> otherIt = otherSet.iterator();
        // Iterate through otherSet
        while (otherIt.hasNext()) {
            // Add each element of other to the unionSet if not already present
            E element = otherIt.next();
            if (!this.contains(element)) {
                unionSet.add(element);
            }
        }

        return unionSet;
    }

}