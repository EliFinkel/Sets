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

/**
 * Students are to complete this class.
 * Students should implement as many methods
 * as they can using the Iterator from the iterator
 * method and the other methods.
 *
 */
public abstract class AbstractSet<E> implements ISet<E> {

    /*
     * DELETE THIS COMMENT FROM YOUR SUBMISSION.
     * 
     * RECALL:
     * 
     * NO INSTANCE VARIABLES ALLOWED.
     * 
     * NO DIRECT REFERENCE TO UnsortedSet OR SortedSet ALLOWED.
     * (In other words the data types UnsortedSet and SortedSet
     * will not appear any where in this class.)
     * 
     * NO DIRECT REFERENCES to ArrayList or other Java Collections.
     * 
     * NO METHODS ADDED other than those in ISet and Object.
     */

    /**
     * A union operation. Add all items of otherSet that
     * are not already present in this set to this set.
     * 
     * @param otherSet != null
     * @return true if this set changed as a result of this operation,
     *         false otherwise.
     */
    public boolean addAll(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("Illegal Argument: " +
                    "otherSet cannot be null");
        }
        int prevSize = size();
        Iterator<E> it = otherSet.iterator();
        // Iterate through other set
        while (it.hasNext()) {
            // Add all from otherSet
            add(it.next());
        }

        // Return true if the size has been changed
        return prevSize != size();
    }

    /**
     * Determine if item is in this set.
     * <br>
     * pre: item != null
     * 
     * @param item element whose presence is being tested.
     *             Item may not equal null.
     * @return true if this set contains the specified item, false otherwise.
     */
    public boolean contains(E item) {
        if (item == null) {
            throw new IllegalArgumentException("Illegal Argument: " +
                    "item cannot be null");
        }

        Iterator<E> setIt = iterator();
        // Iterate through this set
        while (setIt.hasNext()) {
            // If this set has an element equal to item return true
            if (setIt.next().equals(item)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Determine if all of the elements of otherSet are in this set.
     * <br>
     * pre: otherSet != null
     * 
     * @param otherSet != null
     * @return true if this set contains all of the elements in otherSet,
     *         false otherwise.
     */
    public boolean containsAll(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("Illegal Argument: " +
                    "otherSet cannot be null");
        }

        Iterator<E> otherIt = otherSet.iterator();
        // Iterate through other set
        while (otherIt.hasNext()) {
            // If this set doesent contain an element of totherSet return false
            if (!this.contains(otherIt.next())) {
                return false;
            }
        }

        return true;
    }

    /**
     * Determine if this set is equal to other.
     * Two sets are equal if they have exactly the same elements.
     * The order of the elements does not matter.
     * <br>
     * pre: none
     * 
     * @param other the object to compare to this set
     * @return true if other is a Set and has the same elements as this set
     */
    public boolean equals(Object other) {
        // Check if other implements ISet and cast if so
        if (other == null || !(other instanceof ISet<?>)) {
            return false;
        }

        ISet<?> otherSet = (ISet<?>) other;
        // Check size is equal
        if (this.size() != otherSet.size()) {
            return false;
        }

        Iterator<?> otherIt = otherSet.iterator();
        // Iterate through otherSet
        while (otherIt.hasNext()) {
            Object nextElement = otherIt.next();
            Iterator<E> setIt = iterator();
            boolean found = false;
            // Check each element in otherSet and thisSet
            while (setIt.hasNext() && !found) {
                if (setIt.next().equals(nextElement)) {
                    found = true;
                }
            }
            // If found = false return false otherwise return true
            if (!found) {
                return false;
            }
        }

        return true;
    }

    /**
     * Remove the specified item from this set if it is present.
     * pre: item != null
     * 
     * @param item the item to remove from the set. item may not equal null.
     * @return true if this set changed as a result of this operation,
     *         false otherwise
     */
    public boolean remove(E item) {
        if (item == null) {
            throw new IllegalArgumentException("Illegal Argument: " +
                    "item cannot be null");
        }

        int prevSize = size();
        Iterator<E> setIt = iterator();
        // Iterate through this set
        while (setIt.hasNext()) {
            // If the element equals item remove it
            if (setIt.next().equals(item)) {
                setIt.remove();
            }
        }

        return prevSize != size();
    }

    /**
     * Return a String version of this set.
     * Format is (e1, e2, ... en)
     * 
     * @return A String version of this set.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        String seperator = ", ";
        result.append("(");

        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            result.append(it.next());
            result.append(seperator);
        }
        // get rid of extra separator
        if (this.size() > 0) {
            result.setLength(result.length() - seperator.length());
        }

        result.append(")");
        return result.toString();
    }

    /**
     * Create a new set that is the intersection of this set and otherSet.
     * <br>
     * pre: otherSet != null
     * <br>
     * post: returns a set that is the intersection of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br>
     * 
     * @param otherSet != null
     * @return a set that is the union of this set and otherSet
     */
    @Override
    // O(N^2)
    public ISet<E> intersection(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("Illegal Argument: " +
                    "otherSet cannot be null!");
        }

        // Start with the union of this and otherSet
        ISet<E> intSet = this.union(otherSet);

        Iterator<E> unionIt = intSet.iterator();
        // Loop through unionSet
        while (unionIt.hasNext()) {
            E element = unionIt.next();
            // Filter out elements that are not in both sets
            if (!this.contains(element) || !otherSet.contains(element)) {
                unionIt.remove();
            }
        }

        return intSet;
    }
}