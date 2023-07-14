package countingWords;


import java.util.List;

public interface IWordFrequencyAnalyzer {
	int calculateHighestFrequency(String text);
	int calculateFrequencyForWord(String text, String word);
	List<IWordFrequency> calculateMostFrequentNWords(String text, int n);

}
