package countingWords;

import java.util.Comparator;

public class WordFrequencyCompare implements Comparator<WordFrequency> {

	@Override
	public int compare(WordFrequency freqOne, WordFrequency freqTwo) {
		// If both values are the same, then we sort it by ascendent alphabetical order
		if (Integer.compare(freqOne.getFrequency(), freqTwo.getFrequency()) == 0) {
			return freqTwo.getWord().compareTo(freqOne.getWord());
		} else {
			return Integer.compare(freqOne.getFrequency(), freqTwo.getFrequency());
		}
		
		
	}
	

}
