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


    public UnsortedSet(){
        myCon = new ArrayList<>();
    }
    @Override
    public boolean add(E item) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public ISet<E> difference(ISet<E> otherSet) {
        // TODO Auto-generated method stub
        return new UnsortedSet<>();
    }

    @Override
    public Iterator<E> iterator() {
        return myCon.iterator();
    }

    @Override
    public ISet<E> union(ISet<E> otherSet) {
        return new UnsortedSet<>();
    }

    @Override
    public void clear() {
        myCon.clear();
    }

    @Override
    public ISet<E> intersection(ISet<E> otherSet) {
        return new UnsortedSet<>();
    }

    @Override
    public int size() {
        return myCon.size();
    }

}