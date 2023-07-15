package countingWords;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WordFrequencyAnalyzer implements IWordFrequencyAnalyzer {
	
	// Storing the most recent input and results to prevent redundancy
	private String recentAnalyzedInput;
	private List<WordFrequency> recentWordFrequency;
	

	// TODO: See about using regex and \s to quickly find and separate words
	// TODO: Using multiple spaces between words freaks it out, still need to fix that too. My fix for the end of the sentence is the likely reason for that.
	// TODO: Once this is fully functional, move onto a separate method that the three other methods use to help return the values.
	// TODO: Not to forget, to use the class's variables to prevent any redundant calculations if the new input matches the previous output
	// TODO: Refactor the interfaces (and how they're structured vs classes that rely on them) to industry-levels
	// TODO: Look into test cases, perhaps even a 3rd party framework as the assignment mentioned
	

	public WordFrequencyAnalyzer() {
		calculateHighestFrequency("This is just a sentence.");
	}
	
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
				
				return true;
	}
	
	private int findWordIn(List<WordFrequency> wordFreq, String word) {
		for (int i = 0; i < wordFreq.size(); i++) {
			
			if (wordFreq.get(i).getWord() == word) {
				return i;
			}
			
		}
		
		return -1;
	}
	
	
	@Override
	public int calculateHighestFrequency(String text) {
		//First check if the text is identical
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
		//First check if the text is identical
		if (text != recentAnalyzedInput) {
			processInput(text);			
		}
		
		// Find the WordFrequency of the word, before returning the actual frequency of it
		var wordFreq = recentWordFrequency.get(findWordIn(recentWordFrequency, word));
		return wordFreq.getFrequency();
	}

	@Override
	public List<IWordFrequency> calculateMostFrequentNWords(String text, int n) {
		//First check if the text is identical
		if (text != recentAnalyzedInput) {
			processInput(text);			
		}
		

		Collections.sort(recentWordFrequency, new WordFrequencyCompare());

		List<IWordFrequency> mostFrequent = new ArrayList<IWordFrequency>();
		for (int i = 0; i < n; i++) {
			WordFrequency wordToAdd = recentWordFrequency.get(recentWordFrequency.size() - i);
			
			mostFrequent.add(wordToAdd);
		}

		return mostFrequent;
	}
	
	
	///////////////////////////////////////////////
	
	private boolean isCharALetter(char input) {
		// Simply checking whether the input fits within the range, as its a numerical value
		return (input >= 'a' && input <= 'z');
	}
	

}
