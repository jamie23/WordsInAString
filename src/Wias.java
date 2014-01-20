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
		
		int firstLetter = 0;
		int secondLetter = 1;
		String wordToLookup = "";
		
		while(firstLetter<word.length()){
			wordToLookup = word.substring(firstLetter, secondLetter);
			
			if(dict.containsKey(wordToLookup) && wordToLookup.length()>2){
				System.out.println(wordToLookup);
				firstLetter = secondLetter;
				secondLetter++;
			}else if(secondLetter == word.length() && word.length()>2){
				
				/*Keep this commented if you don't want letters which lead nowhere output.
				* System.out.println(word.substring(firstLetter,firstLetter+1));
				*/
				
				firstLetter++;
				secondLetter = firstLetter+1;
			}else{
				secondLetter++;
			}
		}
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
