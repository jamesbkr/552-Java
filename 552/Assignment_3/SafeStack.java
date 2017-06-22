
package ajeffrey.teaching.util.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;


public interface SafeStack {

    public void push (Object element);
    public Object pop ();
    public int size ();
    public Iterator iterator ();
  
    public static final SafeStackFactory factory = new SafeStackFactoryImpl ();

}


class SafeStackFactoryImpl implements SafeStackFactory {

    public SafeStack build () { return new SafeStackImpl (); }

}

class SafeStackImpl implements SafeStack {

    protected Object[] contents = new Object[1];
    protected int size = 0;
	protected int version = 0;


    public synchronized void push (Object element) {
	while (size == contents.length) { grow (); }
	contents[size++] = element;
	version++;
    }
    
    public synchronized  Object pop () {
	if (size == 0) { throw new NoSuchElementException (); }
	final Object result = contents[--size];
	contents[size] = null;
	version++;
	return result;
    }

    public synchronized  int size () { return size; }

    public synchronized Iterator iterator () { return new SafeStackIterator (contents, size); }

    protected synchronized void grow () {
	Object[] newContents = new Object[contents.length * 2];
	System.arraycopy (contents, 0, newContents, 0, size);
	contents = newContents;
    }






class SafeStackIterator implements Iterator {

    protected final Object[] contents;
    protected final int size;
    protected int current = 0;

    SafeStackIterator (final Object[] contents, final int size) { 
	this.contents = contents; this.size = size; 
    }

    public boolean hasNext () { synchronized(SafeStackImpl.this){return current < size; }}

    public Object next () { 
		synchronized(SafeStackImpl.this){
			if (current !=version)
				{throw new ConcurrentModificationException();}
			else if (current == size)
				{throw new NoSuchElementException();}
			else
				{return contents[current++];}
		}}

    public void remove () { throw new UnsupportedOperationException (); }

}
}

