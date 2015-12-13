package ie.gmit.sw.runner;

public class Task {
	private String cipherText;
	private int maxKeyLen;
	private long taskNum;
	
//	public Request() {
//		
//	}
	
	public Task(String cipherText, int maxKeyLen, long taskNum) {
		this.cipherText = cipherText;
		this.maxKeyLen = maxKeyLen;
		this.taskNum = taskNum;
	}
	
	public String getCipherText() {
		return cipherText;
	}

	public void setCipherText(String cypherText) {
		this.cipherText = cypherText;
	}

	public int getMaxKeyLen() {
		return maxKeyLen;
	}

	public void setMaxKeyLen(int maxKeyLen) {
		this.maxKeyLen = maxKeyLen;
	}

	public long getTaskNum() {
		return taskNum;
	}

	public void setTaskNum(long taskNum) {
		this.taskNum = taskNum;
	}

	@Override
	public String toString() {
		return "Request [cypherText=" + cipherText + ", maxKeyLen=" + maxKeyLen + ", taskNum=" + taskNum + "]";
	}
	
}
