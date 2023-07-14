package countingWords;

public class WordFrequency implements IWordFrequency {
	private String word;
	private int frequency;
	
	public WordFrequency(String word, int frequency) {
		this.word = word;
		this.frequency = frequency;
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
