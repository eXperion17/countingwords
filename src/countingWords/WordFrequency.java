package countingWords;

public class WordFrequency implements IWordFrequency, Comparable<WordFrequency> {
	private String word;
	private int frequency;
	
	public WordFrequency(String word, int frequency) {
		this.word = word;
		this.frequency = frequency;
	}
	
	public void addFrequency() {
		frequency++;
	}

	@Override
	public String getWord() {
		return word;
	}

	@Override
	public int getFrequency() {
		return frequency;
	}
	

}
