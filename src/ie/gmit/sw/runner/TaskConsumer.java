package ie.gmit.sw.runner;

import java.rmi.Naming;
import java.util.*;
import java.util.concurrent.*;

import ie.gmit.sw.cipher.VigenereBreaker;

// consumer
public class TaskConsumer implements Runnable {
	private BlockingQueue<Task> reqQueue;
	private Map<Long, Result> resMap = new ConcurrentHashMap<Long, Result>();

	public TaskConsumer(BlockingQueue<Task> reqQueue, Map<Long, Result> resMap) {
		this.reqQueue = reqQueue;
		this.resMap = resMap;
	}

	@Override
	public void run() {
		System.out.println("Consumer thread starting...");
		try{
			// take request from queue (same as pop, deletes from head but uses the task)
			Task req = reqQueue.take();
			
			// create rmi stub
			VigenereBreaker vb = (VigenereBreaker) Naming.lookup(CrackerHandler.remoteHost);
			
			// decrypt (blocking)
			String[] data = vb.decrypt(req.getCipherText(), req.getMaxKeyLen());
			
			// create result object with all the data
			Result result = new Result(data[0], req.getTaskNum(), data[1], data[1].length());
			result.setCipherTextGiven(req.getCipherText());
			result.setMaxKeyLenGiven(req.getMaxKeyLen());
			System.out.println("Created result object: " + result.toString());
			
			// add to resMap (client polls this map)
			resMap.put(req.getTaskNum(), result);
			System.out.println("Added result object to map.\n");
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
