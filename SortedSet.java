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
    public SortedSet(ISet<E> other) {
        if (other == null) {
            throw new IllegalArgumentException("Illegal Argument: " +
                    "other cannot be null");
        }
        myCon = new ArrayList<>();
        Iterator<E> otherIt = other.iterator();
        while(otherIt.hasNext()){
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
    public E max() {
        if (size() == 0) {
            throw new IllegalArgumentException("Illegal Argument: " +
                    "size is 0");
        }
        return myCon.get(size() - 1);
    }

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

   

    @Override
    public ISet<E> difference(ISet<E> otherSet) {
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

    @Override
    public Iterator<E> iterator() {
        return myCon.iterator();
    }

  
    @Override
    public int size() {
        return myCon.size();
    }

    @Override
    public void clear(){
        myCon.clear();
    }

    @Override
    public ISet<E> union(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("Illegal Argument: " +
                    "otherSet cannot be null");
        }
        ISet<E> unionSet = new SortedSet<>(this);
        Iterator<E> otherIt = otherSet.iterator();
        while(otherIt.hasNext()){
            E element = otherIt.next();
            if(!this.contains(element)){
                unionSet.add(element);
            }
        }
        return unionSet;
    }

}