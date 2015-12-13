package ie.gmit.sw.runner;

public class Task {
	private String inputText;
	private int maxKeyLen;
	private long taskNum;
	private String keyWord;
	private TaskType taskType;

	public TaskType getTaskType() {
		return taskType;
	}

	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}
	
	// encrypt/decrypt
	public Task(String inputText, String keyWord, long taskNum, TaskType taskType) {
		this.inputText = inputText;
		this.keyWord = keyWord;
		System.out.println("contructor: keyWord: " + keyWord);
		this.taskNum = taskNum;
		this.taskType = taskType;
	}
	
	// crack
	public Task(String inputText, int maxKeyLen, long taskNum, TaskType taskType) {
		this.inputText = inputText;
		this.maxKeyLen = maxKeyLen;
		this.taskNum = taskNum;
		this.taskType = taskType;
	}
	
	public String getInputText() {
		return inputText;
	}

	public void setInputText(String inputText) {
		this.inputText = inputText;
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

	
	
	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	@Override
	public String toString() {
		return "Task [inputText=" + inputText + ", maxKeyLen=" + maxKeyLen + ", taskNum=" + taskNum + ", keyWord="
				+ keyWord + ", taskType=" + taskType + "]";
	}




	
}
