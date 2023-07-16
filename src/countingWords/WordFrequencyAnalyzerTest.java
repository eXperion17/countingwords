package countingWords;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class WordFrequencyAnalyzerTest {
	private WordFrequencyAnalyzer analyzer;
	private String testParagraph = "This story is about a little test that could. It had a very long paragraph to test, but the little test was not sure if it could test it properly. "
			+ "But the other tests cheered it on. The little test was scared, but once it started testing, it realized that it really was not that bad. The little test after the test case, became a proper test.";
	
	
	@Test
	public void testCalculateHighestFrequency() {
		// We need the analyzer itself to test, and it'll create a new one if it hasn't been made yet.
		if (analyzer == null)
			CreateAnalyzer();
		
		// If no input is handled well
		assertEquals(-1, analyzer.calculateHighestFrequency(null));
		assertEquals(-1, analyzer.calculateHighestFrequency(""));
		assertEquals(-1, analyzer.calculateHighestFrequency("  "));
		
		// Checking regular sentences and longer paragraphs
		assertEquals(2, analyzer.calculateHighestFrequency("This is just a sentence, with this having two on frequency."));
		assertEquals(2, analyzer.calculateHighestFrequency("This is just a sentence, with a lot more    extra   spaces    while having two on frequency.   "));
		assertEquals(8, analyzer.calculateHighestFrequency(testParagraph));
	}

	@Test
	public void testCalculateFrequencyForWord() {
		if (analyzer == null)
			CreateAnalyzer();
		
		// If no input is handled well
		assertEquals(-1, analyzer.calculateFrequencyForWord(null, ""));
		assertEquals(-1, analyzer.calculateFrequencyForWord("", null));
		assertEquals(-1, analyzer.calculateFrequencyForWord("  ", null));
		
		// Checking regular sentences and longer paragraphs
		assertEquals(2, analyzer.calculateFrequencyForWord("This is just a sentence, with this having two on frequency.", "this"));
		assertEquals(1, analyzer.calculateFrequencyForWord("This is just a sentence, with this having two on frequency.", "a"));
		assertEquals(1, analyzer.calculateFrequencyForWord("This is just a sentence, with a lot more    extra   spaces    while having one on frequency.   ", "this"));
		assertEquals(8, analyzer.calculateFrequencyForWord(testParagraph, "test"));
		assertEquals(1, analyzer.calculateFrequencyForWord(testParagraph, "testing"));
		assertEquals(4, analyzer.calculateFrequencyForWord(testParagraph, "little"));
		
		// higher case input with lower and vice versa
		assertEquals(4, analyzer.calculateFrequencyForWord(testParagraph.toUpperCase(), "little"));
		assertEquals(4, analyzer.calculateFrequencyForWord(testParagraph.toLowerCase(), "LITTLE"));
	}

	@Test
	public void testCalculateMostFrequentNWords() {
		if (analyzer == null)
			CreateAnalyzer();

		// Check too high and too low parameters
		List<IWordFrequency> mostFrequent = analyzer.calculateMostFrequentNWords(testParagraph, 0);
		assertEquals(0, mostFrequent.size());
		mostFrequent = analyzer.calculateMostFrequentNWords("This only has five words.", 8);
		assertEquals(5, mostFrequent.size());
		
		// Alphabetical order tests
		assertEquals("five", mostFrequent.get(0).getWord());
		assertEquals("has", mostFrequent.get(1).getWord());
		assertEquals("only", mostFrequent.get(2).getWord());
		assertEquals("this", mostFrequent.get(3).getWord());
		assertEquals("words", mostFrequent.get(4).getWord());
		
		
		// Testing highest frequency
		mostFrequent = analyzer.calculateMostFrequentNWords(testParagraph, 8);
		assertEquals("test", mostFrequent.get(0).getWord());
		assertEquals(8, mostFrequent.get(0).getFrequency());
		assertEquals("it", mostFrequent.get(1).getWord());
		assertEquals(7, mostFrequent.get(1).getFrequency());
		
	}
	
	
	////
	
	private WordFrequencyAnalyzer CreateAnalyzer() {
		return analyzer = new WordFrequencyAnalyzer();
	}

}
 