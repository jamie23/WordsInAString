import java.util.HashMap;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Wias {

	public static void main(String[] args) throws IOException {
		HashMap dict = new HashMap();
		dict = getDict();
		String word = getWord().toLowerCase();

		int numLetters = 1;
		int count = 0;
		int leastDiscarded = 10;
		int bestRun = 0;
		
		//Run this for loop for the largest amount of min char's to check per run.
		for(int i=0;i<6;i++){
			
			int firstLetter = 0;
			int secondLetter = 1;
			String wordToLookup = "";
			
			System.out.println("Run " + i);
			while(firstLetter<word.length()){
				
				wordToLookup = word.substring(firstLetter, secondLetter);
				
				if(dict.containsKey(wordToLookup) && wordToLookup.length()>numLetters){
					System.out.println(wordToLookup);
					firstLetter = secondLetter;
					secondLetter++;
				}else if(secondLetter == word.length() && word.length()>numLetters){
					
					/*Keep this commented if you don't want letters which lead nowhere output.
					* System.out.println(word.substring(firstLetter,firstLetter+1));
					*/
					count++;
					firstLetter++;
					secondLetter = firstLetter+1;
				}else{
					secondLetter++;
				}

			}
			//Keep track of which run discard the least letters
			if(count<leastDiscarded){
				leastDiscarded = numLetters;
				bestRun = i;
			}
			numLetters++;
		}
		System.out.println("Run: " + bestRun + " uses the most letters");
	}

	/*
	 * Returns map of keys/values containing the words in the dictionary
	 */
	static HashMap getDict() throws IOException{
		HashMap words = new HashMap();
		FileInputStream filePath;
		BufferedReader bufread;

		try {
			filePath = new FileInputStream("//Users//jamie//programming//words");
			bufread = new BufferedReader(new InputStreamReader(filePath));
			String line;
			
			while((line = bufread.readLine())!= null)
			{
				words.put(line, line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println("Reached");
		return words;
	}
	
	
	/*
	 * Return the users input sentence
	 */
	static String getWord(){
		System.out.println("Enter your character string");
		Scanner in = new Scanner(System.in);
		String userInput = in.nextLine();
		
		return userInput;
	}
}
