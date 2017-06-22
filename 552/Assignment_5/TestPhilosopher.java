package ajeffrey.teaching.test;

import ajeffrey.teaching.debug.Debug;
import ajeffrey.teaching.debug.StepDebugStream;

import ajeffrey.teaching.dining.Philosopher;
import ajeffrey.teaching.dining.PhilosopherFactory;
import ajeffrey.teaching.dining.TokenPhilosopher;

/**
 * A test of the dining philosophers, with four philosophers.
 * @author Alan Jeffrey
 * @version 1.0.1
 * @see Philosopher
 */
public class TestPhilosopher {

    public static void main (String[] args) throws Exception {
	// Switch on step debugging
	
	
	Debug.out.addFactory (StepDebugStream.factory);
	//Debug.out.addFile ("TestPhilosopherDeadlock.txt");
	// Send debugging to stderr
	Debug.out.addPrintStream (System.err);
	// Create the forks
	final Comparable fork1 ="fork 1";
	final Comparable fork2 ="fork 2";
	final Comparable fork3 = "fork 3";
	final Comparable fork4 = "Fork 4";
	// Which philosopher factory to use: you may want to edit this!
	final PhilosopherFactory factory = TokenPhilosopher.factory;
	// Create the philosophers
	final Philosopher fred = factory.build (fork1, fork2, "Fred");
	final Philosopher wilma = factory.build (fork2, fork3, "Wilma");
	final Philosopher barney = factory.build (fork3, fork4, "Barney");
	final Philosopher betty = factory.build (fork4, fork1, "Betty");
	// Start the philosophers
	fred.start ();
	wilma.start ();
	barney.start ();
	betty.start ();
	
    }

}
