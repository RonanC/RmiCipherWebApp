package ie.gmit.sw.runner;

import java.rmi.Naming;
import java.util.*;
import java.util.concurrent.*;

import ie.gmit.sw.cipher.VigenereBreaker;

// consumer task (this thread will be created once and will run infinitely)
// blocking queue will block it when no items are available, rmi request is also blocking.
public class TaskConsumer implements Runnable {
	private BlockingQueue<Task> reqQueue;
	private Map<Long, Result> resMap = new ConcurrentHashMap<Long, Result>();

	public TaskConsumer(BlockingQueue<Task> reqQueue, Map<Long, Result> resMap) {
		this.reqQueue = reqQueue;
		this.resMap = resMap;
	}

	@Override
	public void run() {
		while(true){
			System.out.println("Consumer thread starting...");
			try{
				// take request from queue (same as pop, deletes from head but uses the task)
				Task req = reqQueue.take();
				
				// create rmi stub
				VigenereBreaker vb = (VigenereBreaker) Naming.lookup(CrackerHandler.remoteHost);
				
				String[] dataArr;
				String dataStr;
				Result result = null;
				
				// blocking
				if (req.getTaskType() == TaskType.ENCRYPT) {
					dataStr = vb.encrypt(req.getInputText(), req.getKeyWord());
					result = createResult(req, dataStr);
				}
				else if (req.getTaskType() == TaskType.DECRYPT) {
					dataStr = vb.decrypt(req.getInputText(), req.getKeyWord());
					result = createResult(req, dataStr);
				}
				else if (req.getTaskType() == TaskType.CRACK) {
					dataArr = vb.crack(req.getInputText(), req.getMaxKeyLen());
					result = createResult(req, dataArr);
				}
				else{
					System.out.println("Error");
				}			
				
				// add to resMap (client polls this map)
				if (result != null) {
					resMap.put(req.getTaskNum(), result);
					System.out.println("Added result object to map.\n");
				}

			}catch(Exception e){
				System.out.println(e);
			}
		}
	}

	// for crack
	private Result createResult(Task req, String[] data) {
		// create result object with all the data
		Result result = new Result(data[0], req.getTaskNum(), data[1], data[1].length(), req.getTaskType());
		result.setInputText(req.getInputText());
		result.setMaxKeyLenGiven(req.getMaxKeyLen());
		System.out.println("Created result object: " + result.toString());
		return result;
	}
	
	// for encrypt/decrypt
	private Result createResult(Task req, String data) {
		// create result object with all the data
		System.out.println("en/de-cryption data: " + data);
		System.out.println(req.toString());
		Result result = new Result(data, req.getTaskNum(), req.getTaskType(), req.getKeyWord(), req.getInputText());
		result.setMaxKeyLenGiven(req.getMaxKeyLen());
		System.out.println("Created result object: " + result.toString());
		return result;
	}
}
