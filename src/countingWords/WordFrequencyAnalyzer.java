package countingWords;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WordFrequencyAnalyzer implements IWordFrequencyAnalyzer {
	
	// Storing the most recent input and results to prevent redundancy
	private String recentAnalyzedInput;
	private List<WordFrequency> recentWordFrequency;

	public WordFrequencyAnalyzer() {
		//calculateHighestFrequency("This should have about two entries because this is being used twice.");
		//System.out.println(calculateHighestFrequency("This this this how how hello boom"));
		//System.out.println(calculateMostFrequentNWords("This this this how how hello boom this", 4));
	}
	
	
	
	@Override
	public int calculateHighestFrequency(String text) {
		if (text == null || text.isBlank()) {
			System.out.println("ERROR: Couldn't calculate highest frequency with no input text. Returning -1.");
			return -1;
		}
		
		
		//Check if the text is identical, if it is we just use what we've already processed
		if (text != recentAnalyzedInput) {
			processInput(text);			
		}
		
		
		
		int highestFrequency = 0;
		for (int i = 0; i < recentWordFrequency.size(); i++)  {
			if (recentWordFrequency.get(i).getFrequency() > highestFrequency) {
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
		
		//Check if the text is identical, if it is we just use what we've already processed
		if (text != recentAnalyzedInput) {
			processInput(text);			
		}
		
		
		// Find the WordFrequency of the word, before returning the actual frequency of it
		word = word.toLowerCase();
		var wordFreq = recentWordFrequency.get(findWordIn(recentWordFrequency, word));
		
		return wordFreq.getFrequency();
	}

	@Override
	public List<IWordFrequency> calculateMostFrequentNWords(String text, int n) {
		// Returning errors for edge cases
		if (text == null || text.isBlank() || n < 0) {
			System.out.println("ERROR: Couldn't calculate highest frequency with no input text or word. Returning null.");
			return null;
		}
		
		//Check if the text is identical, if it is we just use what we've already processed
		if (text != recentAnalyzedInput) {
			processInput(text);			
		}
		

		Collections.sort(recentWordFrequency, new WordFrequencyCompare());

		List<IWordFrequency> mostFrequent = new ArrayList<IWordFrequency>();
		for (int i = 0; i < n; i++) {
			// If the input words ended up higher, we just end it here.
			if (i == recentWordFrequency.size()) {
				break;
			}
				
			WordFrequency wordToAdd = recentWordFrequency.get(recentWordFrequency.size() - i-1);
			
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
				
				//Declare internal/temporary* variable to use within the loop and to document the words
				int charSinceLastWord = 0;
				List<WordFrequency> wordsFound = new ArrayList<WordFrequency>();
				
				//Loop through every character of the input
				for (int i = 0; i < text.length(); i++)  {
					// Declare char variable, easy reference 
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
						//We tell the WordFrequency to add one onto its frequency
						wordsFound.get(indexOfFreq).addFrequency();
					}
					
					//wordsFound.add(word);
					System.out.println(word);
						
					charSinceLastWord = 0;
				}
				
				// Declare the most recently processed input and output
				recentAnalyzedInput = text;
				recentWordFrequency = wordsFound;

				System.out.println("\n Input processed \n");
				return true;
	}
	
	private int findWordIn(List<WordFrequency> wordFreq, String word) {
		for (int i = 0; i < wordFreq.size(); i++) {
			//if (wordFreq.get(i).getWord() == word) {
			
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
