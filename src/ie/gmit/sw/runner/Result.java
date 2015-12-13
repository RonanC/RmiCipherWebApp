package ie.gmit.sw.runner;

public class Result {
	private String resultText;
	private long taskNum;
	private String keyWord;
	private int keyLen;
	private String inputText;
	private int maxKeyLenGiven;
	private TaskType taskType;

	public TaskType getTaskType() {
		return taskType;
	}

	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}

	public String getInputText() {
		return inputText;
	}

	public void setInputText(String inputText) {
		this.inputText = inputText;
	}

	public int getMaxKeyLenGiven() {
		return maxKeyLenGiven;
	}

	public void setMaxKeyLenGiven(int maxKeyLenGiven) {
		this.maxKeyLenGiven = maxKeyLenGiven;
	}

	// used for cracking only, hence the keyLen
	public Result(String resultText, long taskNum, String key, int keyLen, TaskType taskType) {
		this.resultText = resultText;
		this.taskNum = taskNum;
		this.keyWord = key;
		this.keyLen = keyLen;
		this.taskType = taskType;
	}

	// used for either encryption or decryption
	public Result(String resultText, long taskNum, TaskType taskType, String keyWord, String inputText) {
		this.taskNum = taskNum;
		this.taskType = taskType;
		this.keyWord = keyWord;
		this.inputText = inputText;
		this.resultText = resultText;
	}

	public String getResultText() {
		return resultText;
	}

	public void setResultText(String resultText) {
		this.resultText = resultText;
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

	public void setKeyWord(String key) {
		this.keyWord = key;
	}

	public int getKeyLen() {
		return keyLen;
	}

	public void setKeyLen(int keyLen) {
		this.keyLen = keyLen;
	}

	@Override
	public String toString() {
		return "Result [resultText=" + resultText + ", taskNum=" + taskNum + ", keyWord=" + keyWord + ", keyLen="
				+ keyLen + ", inputText=" + inputText + ", maxKeyLenGiven=" + maxKeyLenGiven + ", taskType=" + taskType
				+ "]";
	}

}
