package countingWords;

import java.util.Comparator;

public class WordFrequencyCompare implements Comparator<IWordFrequency> {

	@Override
	public int compare(IWordFrequency freqOne, IWordFrequency freqTwo) {
		// If both values are the same, then we sort it by ascendent alphabetical order
		if (Integer.compare(freqOne.getFrequency(), freqTwo.getFrequency()) == 0) {
			return freqTwo.getWord().compareTo(freqOne.getWord());
		} else {
			// If frequency isn't the same we simply compare that instead.
			return Integer.compare(freqOne.getFrequency(), freqTwo.getFrequency());
		}
		
		
	}
	

}
