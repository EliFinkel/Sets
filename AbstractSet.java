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
    public  boolean addAll(ISet<E> otherSet){
        if(otherSet == null){
            throw new IllegalArgumentException("Illegal Argument: " + 
                "otherSet cannot be null");
        }
        int prevSize = size();
        Iterator<E> it = otherSet.iterator();
        while(it.hasNext()){
            add(it.next());
        }
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
        while (setIt.hasNext()) {
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
        while (otherIt.hasNext()) {
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
        if(this == other){
            return true;
        }

        if (!(other instanceof ISet<?>)) {
            return false;
        }
        ISet<?> otherSet = (ISet<?>) other;

        if(this.size() != otherSet.size()){
            return false;
        }
        Iterator<?> otherIt = otherSet.iterator();

        while (otherIt.hasNext()) {
            Object nextElement = otherIt.next();
            Iterator<E> setIt = iterator();
            boolean found = false;
            while (setIt.hasNext() && !found) {
                if (setIt.next().equals(nextElement)) {
                    found = true;
                }
            }
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
        while (setIt.hasNext()) {
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

    @Override
    public ISet<E> intersection(ISet<E> otherSet){
        ISet<E> intSet = otherSet.union(otherSet);
        Iterator<E> otherIt = otherSet.iterator();
        while(otherIt.hasNext()){
            E element = otherIt.next();
            if(this.contains(element)){
                intSet.add(element);
            }
        }
        return intSet;
        
    }
}