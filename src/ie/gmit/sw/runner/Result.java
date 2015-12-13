package ie.gmit.sw.runner;

public class Result {
	private String plainText;
	private long taskNum;
	private String keyWord;
	private int keyLen;
	private String cipherTextGiven;
	private int maxKeyLenGiven;

	public String getCipherTextGiven() {
		return cipherTextGiven;
	}

	public void setCipherTextGiven(String cipherTextGiven) {
		this.cipherTextGiven = cipherTextGiven;
	}

	public int getMaxKeyLenGiven() {
		return maxKeyLenGiven;
	}

	public void setMaxKeyLenGiven(int maxKeyLenGiven) {
		this.maxKeyLenGiven = maxKeyLenGiven;
	}

	public Result(String plainText, long taskNum, String key, int keyLen) {
		this.plainText = plainText;
		this.taskNum = taskNum;
		this.keyWord = key;
		this.keyLen = keyLen;
	}

	public String getPlainText() {
		return plainText;
	}

	public void setPlainText(String plainText) {
		this.plainText = plainText;
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
		return "Result [plainText=" + plainText + ", taskNum=" + taskNum + ", keyWord=" + keyWord + ", keyLen=" + keyLen
				+ ", cipherTextGiven=" + cipherTextGiven + ", maxKeyLenGiven=" + maxKeyLenGiven + "]";
	}
	
	
}
