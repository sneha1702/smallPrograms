import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
/**
 * Dictionary and Translation Service
 * 
 * Given the English-German dictionary file, you should write the following
 * programs:
 * 
 * A simple program that provides translation of an English word into a German
 * word using <br/>
 * - two ArrayLists <br/>
 * - one HashMap <br/>
 * 
 * A simple program that lists all the entries of the dictionary file
 * alphabetically sorted with the English word followed by the German word, and
 * vice-versa.
 * 
 * A simple program that translates a given text (e.g. a Wikipedia page) from
 * English into German. (Why is it much harder to do it the other way round?)
 * 
 * This would be the standard Java program.
 * 
 * @author Sneha Parihar
 */
public class DictionaryStandardJava {
	// List or Map should go here (instance variable):
	private TreeMap <String, String> dictDb = new TreeMap <String, String>(); 

	/**
	 * Constructor. This is where execution starts.
	 */
	public DictionaryStandardJava() {
		// load dictionary
		loadDictionaryFromFile("dictionary_en_de.txt");
		System.out.println("Enter text to translate: ");
		
		// read user input
		Scanner sc = new Scanner(System.in);
		
		// translate the sentence
		while(sc.hasNext())
		{
			String englishText = sc.nextLine();
			String germanText = translateText(englishText);
			System.out.println(germanText);
		}
	}

	/** This function translates the text from english into german
	 * @param englishWord
	 * @return Translated german text
	 */
	private String translateText(String enText) {
		String deText = "";
		String word = "";
		StringTokenizer tokenizer = new StringTokenizer(enText);
		while(tokenizer.hasMoreTokens())
		{
			word = tokenizer.nextToken().toLowerCase();
			if(dictDb.get(word) != null){
				deText += dictDb.get(word);
				deText += " ";	
			}
			else{
				deText += word;
				deText += " ";	
			}
		}
		return deText;
	}

	/**
	 * This method should load the dictionary from file and store it either in
	 * two Lists or a Map
	 * 
	 * @param fileName
	 */
	private void loadDictionaryFromFile(String fileName) {
		try {
			//BufferedReader br = new BufferedReader(new FileReader(fileName));
			FileInputStream fstream = new FileInputStream(fileName);
			// Get the object of DataInputStream
			//DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream , Charset.forName("UTF-8")));
			while (true) {
				String words = br.readLine();
				if (words == null)
					break;
				StringTokenizer st = new StringTokenizer(words, "=",false);
				String en = st.nextToken();
				String de = st.nextToken();
				String key = en.toLowerCase();
				dictDb.put(key, de);
			}
			br.close();			
			sortDictionary();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** This function sorts the dictionary Alphabetically
	 */

	private void sortDictionary() {
		NavigableMap<String, String> nMap = dictDb.descendingMap();
		Set<String> keySet = nMap.keySet();
		for(String key: keySet){
			System.out.println(key+" : "+dictDb.get(key));
		}
		
	}

	/**
	 * You do not need to change anything here.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		DictionaryStandardJava dsj = new DictionaryStandardJava();
	}

}
