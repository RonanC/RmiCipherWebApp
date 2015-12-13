package ie.gmit.sw.runner;

import java.util.*;
import java.util.concurrent.*;

public class VigenereRequestManager {
	private BlockingQueue reqQueue = new ArrayBlockingQueue<Task>(10);
	private Map<Long, String> resMap = new ConcurrentHashMap<Long, String>();

	// producer
	// creates thread to add to queue
	public void add(final Task r){
		try{
			//queue.put(r)//blocks if queue full
			new Thread(

					new Runnable(){
				public void run(){
					try{
						// add to queue
						reqQueue.put(r);
					}catch(Exception e){
						System.out.println(e);
					}
				}
			}
					
					
					).start();
		}
		catch (Exception e){
			
		}
	}

	// 
	public String getResult(long jobNumber) {
		if (resMap.containsKey(jobNumber)) {
			return resMap.get(jobNumber);
		} else {
			return null; // no result
		}
	}
}
