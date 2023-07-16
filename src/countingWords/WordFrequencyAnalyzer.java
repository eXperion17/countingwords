package countingWords;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WordFrequencyAnalyzer implements IWordFrequencyAnalyzer {
	
	// Storing the most recent input and results to prevent redundancy
	private String recentAnalyzedInput;
	private List<IWordFrequency> recentWordFrequency;

	public WordFrequencyAnalyzer() {
		// Write any tests here
		
	}
	
	
	
	@Override
	public int calculateHighestFrequency(String text) {
		if (text == null || text.isBlank()) {
			System.out.println("ERROR: Couldn't calculate highest frequency with no input text. Returning -1.");
			return -1;
		}
		
		
		// Check if the text is identical, if it is we just use what we've already processed
		if (text != recentAnalyzedInput) {
			processInput(text);			
		}
		
		
		
		int highestFrequency = 0;
		for (int i = 0; i < recentWordFrequency.size(); i++)  {
			if (recentWordFrequency.get(i).getFrequency() > highestFrequency) {
				// Simply check if the currently recorded frequency is lower than the word we're currently checking
				highestFrequency = recentWordFrequency.get(i).getFrequency();
			}
		}
		
		return highestFrequency;
	}

	@Override
	public int calculateFrequencyForWord(String text, String word) {
		// Returning errors for edge cases
		if (text == null || text.isBlank() || word == null || word.equalsIgnoreCase("")) {
			System.out.println("ERROR: Couldn't calculate highest frequency with no input text or word. Returning -1.");
			return -1;
		}
		
		// Check if the text is identical, if it is we just use what we've already processed
		if (text != recentAnalyzedInput) {
			processInput(text);			
		}
		
		
		// Find the WordFrequency of the word, before returning the actual frequency of it
		word = word.toLowerCase();
		IWordFrequency wordFreq = recentWordFrequency.get(findWordIn(recentWordFrequency, word));
		
		return wordFreq.getFrequency();
	}

	@Override
	public List<IWordFrequency> calculateMostFrequentNWords(String text, int n) {
		// Returning errors for edge cases
		if (text == null || text.isBlank() || n < 0) {
			System.out.println("ERROR: Couldn't calculate highest frequency with no input text or word. Returning null.");
			return null;
		}
		
		// Check if the text is identical, if it is we just use what we've already processed
		if (text != recentAnalyzedInput) {
			processInput(text);			
		}
		
		// Sort the collection using a custom Comparator
		Collections.sort(recentWordFrequency, new WordFrequencyCompare());

		List<IWordFrequency> mostFrequent = new ArrayList<IWordFrequency>();
		for (int i = 0; i < n; i++) {
			// If the input words ended up higher, we just end it here.
			if (i == recentWordFrequency.size()) {
				break;
			}
				
			IWordFrequency wordToAdd = recentWordFrequency.get(recentWordFrequency.size() - i-1);
			
			mostFrequent.add(wordToAdd);
		}

		return mostFrequent;
	}
	

	///////////////////////////////////////////////
	
	
	private boolean processInput(String text) {
		// Check to see if the input is legitimate
				if (text.length() == 0) {
					//TODO Print error
					return false;
				}
				
				// Convert all to lower case,
				text = text.toLowerCase();
				
				// Declare variable to use within the loop and to document the words
				int charSinceLastWord = 0;
				List<IWordFrequency> wordsFound = new ArrayList<IWordFrequency>();
				
				// Loop through every character of the input
				for (int i = 0; i < text.length(); i++)  {
					char currLetter = text.charAt(i);
					
					// Is the char part of a word, or a separator?
					if (isCharALetter(currLetter)) {
						// Take note of it before we continue the loop
						charSinceLastWord++;
						
						// So long as the index isn't the last character, that is all this current loop has to do
						if (i != text.length() - 1) {
							continue;
						} else {
							// In case the input ends with a letter, we add 1 to the index so it grabs the last word correctly
							i++;
						}
					// If its not a letter and there hasn't been a single char/letter yet, we also end the loop right here
					} else if (charSinceLastWord == 0)
						continue;
					
					// Every loop that gets past here, is a word that will be added					

					// Using substring to extract the word, by keeping track of when the last whitespace was 
					String word = text.substring(i-charSinceLastWord, i);
					
					// Find the index of the WordFrequency that has the current word
					int indexOfFreq = findWordIn(wordsFound, word);
					
					// If this is the first entry OR if the word couldn't be found...
					if (wordsFound.size() == 0 || indexOfFreq == -1) {
						WordFrequency wordFreq = new WordFrequency(word, 1);
						wordsFound.add(wordFreq);
						
					} else {
						// We tell the WordFrequency to add one onto its frequency
						wordsFound.get(indexOfFreq).addFrequency();
					}
					
					System.out.println(word);
						
					charSinceLastWord = 0;
				}
				
				// Declare the most recently processed input and output
				recentAnalyzedInput = text;
				recentWordFrequency = wordsFound;

				System.out.println("\n Input processed \n");
				return true;
	}
	
	private int findWordIn(List<IWordFrequency> wordFreq, String word) {
		for (int i = 0; i < wordFreq.size(); i++) {			
			if (word.compareTo(wordFreq.get(i).getWord()) == 0) {
				return i;
			}
		}
		return -1;
	}
	
	
	private boolean isCharALetter(char input) {
		// Simply checking whether the input fits within the range, as its a numerical value
		return (input >= 'a' && input <= 'z');
	}
	

}
