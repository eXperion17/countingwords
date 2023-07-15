package countingWords;

import java.util.ArrayList;
import java.util.List;

public class WordFrequencyAnalyzer implements IWordFrequencyAnalyzer {
	
	// Storing the most recent input and results to prevent redundancy
	private String recentAnalyzedInput;
	private List<IWordFrequency> recentWordFrequency;

	public WordFrequencyAnalyzer() {
		calculateHighestFrequency("This is just a sentence.");
	}
	
	
	@Override
	public int calculateHighestFrequency(String text) {
		// Check to see if the input is legitimate
		if (text.length() == 0) {
			//TODO Print error
			return -1;
		}
		
		// Convert all to lower case,
		text = text.toLowerCase();
		
		//Declare internal/temporary* variable to use within the loop and to document the words
		int charSinceLastWord = 0;
		List<String> wordsFound = new ArrayList<String>();
		
		//Loop through every character of the input
		for (int i = 0; i < text.length(); i++)  {
			// Declare char variable, easy reference 
			char currLetter = text.charAt(i);
			
			// Is the char part of a word, or a separator?
			if (isCharALetter(currLetter)) {
				// Take note of it before we continue the loop
				charSinceLastWord++;
				
				// So long as the index isn't the last character, that is all this loop has to do
				if (i != text.length() - 1) {
					continue;
				} else {
					// In case the input ends with a letter, we add 1 to the index so it grabs the last word correctly
					i++;
				}
			} else if (charSinceLastWord == 0)
				continue;
			
			// Using substring to extract the word, by keeping track of when 
			String word = text.substring(i-charSinceLastWord, i);
			wordsFound.add(word);
			System.out.println(word);
				
			charSinceLastWord = 0;
			
		}
		
		
		
		// TODO: See about using regex and \s to quickly find and separate words
		// TODO: Using multiple spaces between words freaks it out, still need to fix that too. My fix for the end of the sentence is the likely reason for that.
		// TODO: Once this is fully functional, move onto a separate method that the three other methods use to help return the values.
		// TODO: Not to forget, to use the class's variables to prevent any redundant calculations if the new input matches the previous output
		// TODO: Refactor the interfaces (and how they're structured vs classes that rely on them) to industry-levels
		// TODO: Look into test cases, perhaps even a 3rd party framework as the assignment mentioned

		
		
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int calculateFrequencyForWord(String text, String word) {
		//First check if the text is identical
		
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<IWordFrequency> calculateMostFrequentNWords(String text, int n) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	///////////////////////////////////////////////
	
	private boolean isCharALetter(char input) {
		// Simply checking whether the input fits within the range, as its a numerical value
		return (input >= 'a' && input <= 'z');
	}
	

}
