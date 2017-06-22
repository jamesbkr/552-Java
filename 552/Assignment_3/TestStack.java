package ajeffrey.teaching.test;

import ajeffrey.teaching.util.stack.SafeStack;
import java.util.Iterator;

public class TestStack {

    public static void main (String[] args) {

	final SafeStack stack = SafeStack.factory.build ();
	stack.push ("fred"); stack.push ("wilma"); stack.push ("barney"); stack.push ("betty");
	for (Iterator i = stack.iterator (); i.hasNext ();) {
	    System.out.println (i.next ());
	}
	
    }

}
