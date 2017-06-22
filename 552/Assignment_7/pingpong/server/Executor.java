package ajeffrey.teaching.pingpong.server;

import ajeffrey.teaching.debug.Debug;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
/**
 * An Executor class for executing tasks.
 * @author Alan Jeffrey
 * @version 1.0.1
 */

public interface Executor {

    /**
     * Try to execute a given task.
     * The task will be run, if system resources permit.
     * If the system is too busy, then the task will be cancelled.
     * @param task the task to execute
     */
    public void execute (Task task);

    /**
     * An executor.
     */
    public Executor singleton = new ExecutorImpl ();

}

class ExecutorImpl implements Executor {
		private final ThreadPoolExecutor ex; 
    // This is a simplistic executor which just builds a new
    // thread for each task.  This is not realistic!

	public ExecutorImpl(){
		ex = new ThreadPoolExecutor(10,
									50,
									(long)5000,
									TimeUnit.NANOSECONDS, 
									
									new ArrayBlockingQueue<Runnable>(100)
									);
	}

    public void execute (Task task) {
	Debug.out.println ("Executor.execute: Starting");
	System.out.println(ex.getActiveCount() + "??????");
	ex.execute(task);
	Debug.out.println ("Executor.execute: Returning");
    }

}
