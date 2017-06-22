package ajeffrey.teaching.util.list;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;
import ajeffrey.teaching.debug.Debug;

/**
 * An ordered collection of elements.
 * This is a cut down version of the Java collections library,
 * intended for teaching purposes.
 * @author Alan Jeffrey
 * @version 1.0.4
 * @see MutableListFactory
 */
public interface OptimisticMutableList {

    /**
     * Add a new element to the list.
     * @param element the object to add
     */
    public void add (Object element);

    /**
     * Remove an element from the list.
     * @param element the object to remove
     * @exception NoSuchElementException thrown if the element 
     *   is not in the list.
     */
    public void remove (Object element);

    /**
     * Get an iterator over the elements in the list.
     * @return an iterator over the elements in the list
     */
    public Iterator iterator ();

    /**
     * The size of the list.
     * @return the size of the list
     */
    public int size ();

    /**
     * A factory for building mutable lists.
     */
    public static final OptimisticMutableListFactory factory = new OptimisticMutableListFactoryImpl ();

}

class OptimisticMutableListFactoryImpl implements OptimisticMutableListFactory {

    public OptimisticMutableList build () {
	return new OptimisticMutableListImpl ();
    }

}

class OptimisticMutableListImpl implements OptimisticMutableList {
    protected AtomicReference<ImmutableList> atomicList = new AtomicReference<ImmutableList>(ImmutableList.empty);
   

    public void add (final Object element) {
		 ImmutableList oldContents;
         ImmutableList newContents;  
        
        boolean done = false;
       do{
        oldContents = atomicList.get();
	    Debug.out.println 
		("MutableListImpl.add: Starting...");
	    newContents = oldContents.cons (element);
	    Debug.out.println 
		("MutableListImpl.add: Calling " + 
		 oldContents + ".cons (" + element + ")");
	   
	    Debug.out.println 
		("MutableListImpl.add: setting contents = " +
		 newContents);
	    //contents = newContents;
	    Debug.out.println 
		("MutableListImpl.add: ...done.");
		done = atomicList.compareAndSet(oldContents,newContents);
	}while(!done);
	
    }

    public void remove (final Object element) {
		 ImmutableList oldContents;
         ImmutableList newContents; 
     
        boolean done = false;
       do{
		oldContents = atomicList.get();
	    Debug.out.println 
		("MutableListImpl.remove: Starting...");
		newContents = oldContents.remove(element);
	    Debug.out.println 
		("MutableListImpl.remove: Calling " + oldContents + 
		 ".remove (" + element + ")");
	    Debug.out.println 
		("MutableListImpl.remove: setting contents = " + 
		 newContents);
	  //  contents = newContents;
	    Debug.out.println 
		("MutableListImpl.remove: ...done.");
		done = atomicList.compareAndSet(oldContents,newContents);
	}while(!done);
    }

    public Iterator iterator () { 
	return atomicList.get().iterator ();
    }

    public int size () {
	return atomicList.get().size ();
    }

    public String toString () {
	return "{ contents = " + atomicList.get() + " }";
    }

}
