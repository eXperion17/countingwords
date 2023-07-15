package countingWords;

import java.util.Comparator;

public class WordFrequencyCompare implements Comparator<WordFrequency> {

	@Override
	public int compare(WordFrequency freqOne, WordFrequency freqTwo) {
		return Integer.compare(freqOne.getFrequency(), freqTwo.getFrequency());
	}
	

}
