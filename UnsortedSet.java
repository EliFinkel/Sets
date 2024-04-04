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

    public UnsortedSet() {
        myCon = new ArrayList<>();
    }

    public UnsortedSet(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("Other set must not be null.");
        }
        myCon = new ArrayList<>();
        Iterator<E> it = otherSet.iterator();
        while (it.hasNext()) {
            this.add(it.next());
        }
    }

    public boolean add(E item) {
        if(!contains(item)) {
            myCon.add(item);
            return true;
        } else {
            return false;
        }
    }

    public void clear() {
        myCon.clear();
    }

    public boolean difference(E item) {
        return true;
    }

    public ISet<E> union(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("other set may not be null.");
        }
        ISet<E> result = new UnsortedSet<>(this);
        Iterator<E> it = otherSet.iterator();
        while (it.hasNext()) {
            E element = it.next();
            if (this.contains(element)) {
                result.add(element);
            }
        }
        return result;
    }

    public ISet<E> difference(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("other set may not be null.");
        }
        ISet<E> result = new UnsortedSet<>();
        Iterator<E> it = this.iterator();
        while(it.hasNext()) {
            E element = it.next();
            if(!otherSet.contains(element)) {
                result.add(element);
            }
        }
        return result;
    }

    public int size() {
        return myCon.size();
    }

    @Override
    public Iterator<E> iterator() {
        return myCon.iterator();
    }
}
