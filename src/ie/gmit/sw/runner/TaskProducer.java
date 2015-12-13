package ie.gmit.sw.runner;

import java.rmi.Naming;
import java.util.*;
import java.util.concurrent.*;

import ie.gmit.sw.cipher.VigenereBreaker;

// task producer (there will be many temporary instances of this)
public class TaskProducer implements Runnable {
	private BlockingQueue<Task> reqQueue;
	private Task task;

	public TaskProducer(BlockingQueue<Task> reqQueue, Task task) {
		this.reqQueue = reqQueue;
		this.task = task;
	}

	@Override
	public void run() {
		try{
			// simulate lag (10 seconds)
//			Thread.sleep(10000);
			
			// add request to queue
			reqQueue.put(task);
			
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
