/*  Student information for assignment:
 *
 *  On <MY|OUR> honor, <NAME1> and <NAME2),
 *  this programming assignment is <MY|OUR> own work
 *  and <I|WE> have not provided this code to any other student.
 *
 *  Number of slip days used:
 *
 *  Student 1 (Student whose Canvas account is being used)
 *  UTEID:
 *  email address:
 *  TA name:
 *
 *  Student 2
 *  UTEID:
 *  email address:
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
        myCon = new ArrayList<>();
        Iterator<E> otherIt = other.iterator();
        while (otherIt.hasNext()) {
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
        int left = 0;
        int right = data.size() - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;
            int compareResult = target.compareTo(data.get(middle));

            if (compareResult == 0) {
                return middle; // Target found
            } else if (compareResult < 0) {
                right = middle - 1; // Move left for the next middle calculation
            } else {
                left = middle + 1; // Move right for the next middle calculation
            }
        }

        return left; // Suitable insertion point if target is not found
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

        int insertionPoint = binarySearch(myCon, item);

        boolean isDuplicate = (insertionPoint < myCon.size() && item.equals(myCon.get(insertionPoint)));

        if (!isDuplicate) {
            myCon.add(insertionPoint, item);
            return true;
        }
        return true;
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
        while (it.hasNext()) {
            E element = it.next();
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

    @Override
    public ISet<E> intersection(ISet<E> otherSet) {

        if (otherSet == null) {
            throw new IllegalArgumentException("Illegal Argument: " +
                    "otherSet cannot be null!");
        }
        ISet<E> intSet = new SortedSet<>();

        Iterator<E> otherIt = otherSet.iterator();
        while (otherIt.hasNext()) {
            E element = otherIt.next();
            if (this.contains(element)) {
                intSet.add(element);
            }
        }
        return intSet;

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
        myCon.clear();
    }

    /**
     * Create a new set that is the union of this set and otherSet.
     * <br>
     * pre: otherSet != null
     * <br>
     * post: returns a set that is the union of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br>
     * pre: otherSet != null
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
        ISet<E> unionSet = new SortedSet<>(this);
        Iterator<E> otherIt = otherSet.iterator();
        while (otherIt.hasNext()) {
            E element = otherIt.next();
            if (!this.contains(element)) {
                unionSet.add(element);
            }
        }
        return unionSet;
    }

}